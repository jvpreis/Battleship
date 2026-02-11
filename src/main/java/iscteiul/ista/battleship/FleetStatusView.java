package iscteiul.ista.battleship;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

/**
 * ASCII renderer for a fleet's status. Uses IShip.getPositions() and IShip.stillFloating().
 * - 'S' = ship position (only if showShips == true)
 * - 'X' = ship position for a sunk ship
 * - '.' = empty
 */
public final class FleetStatusView {

    private FleetStatusView() { /* utility */ }

    public static String render(Collection<IShip> ships, int rows, int cols, boolean showShips) {
        char[][] grid = new char[rows][cols];
        for (int r = 0; r < rows; r++) for (int c = 0; c < cols; c++) grid[r][c] = '.';

        if (ships != null) {
            for (IShip ship : ships) {
                boolean sunk = !ship.stillFloating();
                List<IPosition> positions = ship.getPositions();
                if (positions == null) continue;
                for (IPosition p : positions) {
                    int ri = p.getRow() - 1;
                    int ci = p.getColumn() - 1;
                    if (ri >= 0 && ri < rows && ci >= 0 && ci < cols) {
                        if (sunk) grid[ri][ci] = 'X';
                        else if (showShips) grid[ri][ci] = 'S';
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("   ");
        for (int c = 1; c <= cols; c++) sb.append(String.format("%2d", c));
        sb.append(System.lineSeparator());

        for (int r = 0; r < rows; r++) {
            sb.append(String.format("%2d ", r + 1));
            for (int c = 0; c < cols; c++) {
                sb.append(' ');
                sb.append(grid[r][c]);
            }
            sb.append(System.lineSeparator());
        }

        sb.append(System.lineSeparator());
        sb.append("Legend: S = ship, X = sunk ship, . = empty");
        sb.append(System.lineSeparator());
        return sb.toString();
    }

    public static void print(Collection<IShip> ships, int rows, int cols, boolean showShips) {
        System.out.print(render(ships, rows, cols, showShips));
    }

    /**
     * Reflection-friendly helper: tries to call a getShips() method on the provided fleet object.
     * If successful and return is a Collection<IShip>, it prints the rendered board.
     * If not, prints a helpful message to the console.
     */
    @SuppressWarnings("unchecked")
    public static void printFromFleet(Object fleet, int rows, int cols, boolean showShips) {
        if (fleet == null) {
            System.out.println("No fleet available to display.");
            return;
        }
        try {
            Method m = fleet.getClass().getMethod("getShips");
            Object result = m.invoke(fleet);
            if (result instanceof Collection) {
                print((Collection<IShip>) result, rows, cols, showShips);
            } else {
                System.out.println("Fleet.getShips() exists but did not return a Collection<IShip>.");
            }
        } catch (NoSuchMethodException e) {
            System.out.println("Fleet object has no getShips() method. Cannot render status.");
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Could not invoke getShips() on fleet: " + e.getMessage());
        }
    }
}

