package iscteiul.ista.battleship;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * Simple Swing window that shows FleetStatusView.render(...) in a scrollable text area.
 */
public final class FleetStatusWindow {

    private FleetStatusWindow() { /* utility */ }

    public static void show(final Collection<IShip> ships, final int rows, final int cols, final boolean showShips) {
        final String text = FleetStatusView.render(ships, rows, cols, showShips);
        SwingUtilities.invokeLater(() -> createAndShow(text, "Fleet Status"));
    }

    /**
     * Reflection-friendly entry: tries to call getShips() on fleet object and displays the result.
     */
    @SuppressWarnings("unchecked")
    public static void showFromFleet(final Object fleet, final int rows, final int cols, final boolean showShips) {
        if (fleet == null) {
            SwingUtilities.invokeLater(() -> createAndShow("No fleet available to display.", "Fleet Status"));
            return;
        }
        try {
            Method m = fleet.getClass().getMethod("getShips");
            Object result = m.invoke(fleet);
            if (result instanceof Collection) {
                show((Collection<IShip>) result, rows, cols, showShips);
            } else {
                final String msg = "Fleet.getShips() exists but did not return a Collection<IShip>.";
                SwingUtilities.invokeLater(() -> createAndShow(msg, "Fleet Status"));
            }
        } catch (NoSuchMethodException e) {
            final String msg = "Fleet object has no getShips() method. Cannot render status.";
            SwingUtilities.invokeLater(() -> createAndShow(msg, "Fleet Status"));
        } catch (IllegalAccessException | InvocationTargetException e) {
            final String msg = "Could not invoke getShips() on fleet: " + e.getMessage();
            SwingUtilities.invokeLater(() -> createAndShow(msg, "Fleet Status"));
        }
    }

    private static void createAndShow(String text, String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JTextArea ta = new JTextArea(text);
        ta.setEditable(false);
        ta.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        ta.setCaretPosition(0);
        JScrollPane sp = new JScrollPane(ta);
        sp.setPreferredSize(new Dimension(600, 400));
        frame.getContentPane().add(sp, BorderLayout.CENTER);

        // Optional: a small control panel to toggle showShips if desired in future
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton close = new JButton("Close");
        close.addActionListener(e -> frame.dispose());
        bottom.add(close);
        frame.getContentPane().add(bottom, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null); // center on screen
        frame.setVisible(true);
    }
}

