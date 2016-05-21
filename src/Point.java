package sudoku;

import java.util.Observer;
import java.util.Observable;
import javax.swing.JButton;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

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
public class Point extends JButton implements Observer {
    // Numbers form 1 to 9
    private Vector numbers = new Vector<Integer>();

    // Listener(Space) to get point change
    private Vector Listeners = new Vector<Space>();

    public Point() {
        // Init numbers
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }

        this.setText(this.toString());

        // Add action listener
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sValue = JOptionPane.showInputDialog(null, getText(),
                        "values", JOptionPane.OK_CANCEL_OPTION);
                if (sValue != null && !sValue.isEmpty()) {
                    try {
                        Integer num = Integer.parseInt(sValue);
                        makeSure(num);
                    } catch (NumberFormatException nfex) {
                        JOptionPane.showMessageDialog(null, "valiable input");
                    }
                }

            }
        });
    }

    // Add space to listen point change
    public void addListener(Space space) {
        this.Listeners.add(space);
        space.addObserver(this);
        space.addPoint(this);
    }

    // Make sure every listener can get change event
    private void makeSure(int num) {
        // Make sure number
        this.numbers.clear();
        this.numbers.add(num);

        // Notify listeners
        for (int i = 0; i < this.Listeners.size(); i++) {
            ((Space) (this.Listeners.get(i))).makeSure(this, num);
        }
        this.setText(toString());
    }

    private void makeChange() {
        // Notify listeners
        for (int i = 0; i < this.Listeners.size(); i++) {
            ((Space) (this.Listeners.get(i))).makeChange();
        }
        this.setText(toString());
    }


    // Observer space change
    public void update(Observable o, Object arg) {
        // Not handle it, if number had make sure
        if (this.numbers.size() == 1) {
            return;
        }

        // Number your get
        int num = ((Integer) arg).intValue();

        // num > 0 :Some point have make sure this number
        // num < 0 :Only one point in the space have this number.(May by you)

        if (num > 0) {
            // if numbers contains this makesure number, remove it
            if (this.numbers.contains(num)) {
                this.numbers.removeElement(num);

                // Notify change
                this.makeChange();

                // if just one number left, make sure this number
                if (this.numbers.size() == 1) {
                    makeSure((Integer)this.numbers.get(0));
                }
            }
        } else {
            // get valiable number
            num *= -1;

            if (this.numbers.contains(num)) {
                this.makeSure(num);
            }
        }

        this.setText(this.toString());
    }

    // Repaints this component.
//    public void repaint() {
//        this.setName(this.toString());
//    }

    // To display numbers
    public String toString() {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < numbers.size(); i++) {
            str.append(numbers.get(i));
        }
        return str.toString();
    }

    // Get number field
    public Vector getNumbers() {
        return this.numbers;
    }
}
