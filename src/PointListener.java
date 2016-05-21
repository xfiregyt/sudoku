package sudoku;

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
public interface PointListener {
    public void makeSure(Point point, int num);

    public void makeChange();

    public void addPoint(Point point);
}
