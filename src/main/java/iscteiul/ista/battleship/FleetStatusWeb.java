package iscteiul.ista.battleship;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.awt.Desktop;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.concurrent.Executors;

/**
 * Small embedded HTTP server to show fleet.printStatus in a browser.
 * Usage:
 *   FleetStatusWeb.showFromFleet(fleet, rows, cols, showShips);
 */
public final class FleetStatusWeb {

    private FleetStatusWeb() { /* utility */ }

    /**
     * Starts an HTTP server on an ephemeral port and serves the rendered fleet status at "/".
     * Attempts to call fleet.getShips() reflectively.
     * Opens the default browser if supported and prints the URL to stdout.
     */
    @SuppressWarnings("unchecked")
    public static void showFromFleet(final Object fleet, final int rows, final int cols, final boolean showShips) {
        final Collection<IShip> ships;
        if (fleet == null) {
            System.out.println("No fleet available to display.");
            return;
        }
        try {
            Method m = fleet.getClass().getMethod("getShips");
            Object result = m.invoke(fleet);
            if (result instanceof Collection) {
                ships = (Collection<IShip>) result;
            } else {
                System.out.println("Fleet.getShips() exists but did not return a Collection<IShip>.");
                return;
            }
        } catch (NoSuchMethodException e) {
            System.out.println("Fleet object has no getShips() method. Cannot render status.");
            return;
        } catch (Exception e) {
            System.out.println("Could not invoke getShips() on fleet: " + e.getMessage());
            return;
        }

        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(0), 0);
            server.createContext("/", new StatusHandler(ships, rows, cols, showShips));
            server.setExecutor(Executors.newSingleThreadExecutor());
            server.start();
            int port = server.getAddress().getPort();
            String url = "http://localhost:" + port + "/";

            // Try to open default browser
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop d = Desktop.getDesktop();
                    if (d.isSupported(Desktop.Action.BROWSE)) {
                        d.browse(new URI(url));
                    } else {
                        System.out.println("Browser open not supported; please open the URL manually: " + url);
                    }
                } else {
                    System.out.println("Desktop not supported; please open the URL manually: " + url);
                }
            } catch (Exception ex) {
                System.out.println("Could not open browser: " + ex.getMessage());
                System.out.println("Open the URL manually: " + url);
            }

            // Optional: stop server after some time to avoid leaving it running forever.
            // Here we schedule a stop in 10 minutes; adjust or remove as desired.
            Executors.newSingleThreadScheduledExecutor()
                    .schedule(new Runnable() {
                        @Override
                        public void run() {
                            // HttpServer.stop requires an int delay parameter.
                            server.stop(0);
                        }
                    }, 10 * 60, java.util.concurrent.TimeUnit.SECONDS);

            System.out.println("Fleet status web server started at: " + url);
        } catch (IOException ioe) {
            System.out.println("Failed to start HTTP server for fleet status: " + ioe.getMessage());
        }
    }

    private static final class StatusHandler implements HttpHandler {
        private final Collection<IShip> ships;
        private final int rows, cols;
        private final boolean showShips;

        StatusHandler(Collection<IShip> ships, int rows, int cols, boolean showShips) {
            this.ships = ships;
            this.rows = rows;
            this.cols = cols;
            this.showShips = showShips;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String ascii = FleetStatusView.render(ships, rows, cols, showShips);
            String body = "<!doctype html><html><head><meta charset=\"utf-8\"><title>Fleet Status</title>"
                    + "<style>body{font-family:monospace; padding:12px;} pre{white-space:pre-wrap;}</style>"
                    + "</head><body><h2>Fleet Status</h2><pre>"
                    + escapeHtml(ascii)
                    + "</pre></body></html>";

            byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=utf-8");
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        }
    }

    private static String escapeHtml(String s) {
        if (s == null) return "";
        StringBuilder sb = new StringBuilder(s.length());
        for (char c : s.toCharArray()) {
            switch (c) {
                case '&': sb.append("&amp;"); break;
                case '<': sb.append("&lt;"); break;
                case '>': sb.append("&gt;"); break;
                default: sb.append(c);
            }
        }
        return sb.toString();
    }
}
