/**
 * 
 */
package battleship;

import java.util.Iterator;
import java.util.List;

public interface IFleet
{
    Integer BOARD_SIZE = 10;
    Integer FLEET_SIZE = 10;

    List<IShip> getShips();
    
    boolean addShip(IShip s);

    List<IShip> getShipsLike(String category);

    List<IShip> getFloatingShips();

    IShip shipAt(IPosition pos);
    
    void printStatus();
}
