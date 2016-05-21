package sudoku;

import java.util.Observable;
import java.util.Vector;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class Space extends Observable implements PointListener {
    // Control members
    Vector members = new Vector<Point>();

    // Add member to control
    public void addPoint(Point point) {
        if (!this.members.contains(point)) {
            this.members.addElement(point);
        }
    }

    // One point have make sure a number
    public void makeSure(Point point, int num) {
        // Notify all observers some point make sure a number
        this.setChanged();
        this.notifyObservers(num);
    }

    // One point have changed a number
    public void makeChange() {
        this.handleMakeChange();
    }

    // Check in the space, if some other number have make sure
    private void handleMakeChange() {
        // Number of special number from 1 to 9
        int nNum = 0;
        for (int number = 1; number <= 9; number++) {
            nNum = 0;

            // Totle number of 'number'
            for (int i = 0; i < members.size(); i++) {
                if (((Point) members.get(i)).getNumbers().contains(number)) {
                    nNum++;
                }
            }

            // Only one point have this number
            if (nNum == 1) {
                this.setChanged();
                this.notifyObservers(number * -1);
            }
        }
    }
}
