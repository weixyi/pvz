package model;

public class Point  {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param x the <em>x</em>-coordinate of the point
     * @param y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    public int distance2(Point that) {
        return (this.x-that.x)*(this.x-that.x)+
                (this.y-that.y)*(this.y-that.y);
    }

    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }


    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        Point p00 = new Point(0, 0);
        Point p11 = new Point(1, 1);
        Point p01 = new Point(0, 1);
        Point p10 = new Point(1, 0);
        System.out.println(p00);
        System.out.println(p11);


    }
}
