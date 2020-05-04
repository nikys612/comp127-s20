package debugActivity;

import comp127graphics.CanvasWindow;
import comp127graphics.GraphicsGroup;
import comp127graphics.Rectangle;

import java.awt.*;

public class ColorWall extends GraphicsGroup {
    // the space between rectangles
    private static final int GAP = 10;
    // the size of the lines on the rectangles
    private static final int BORDER = 3;
    // the size of each rectangle
    private static final int SIZE = 30;
    // the number of rectangles
    private static final int STEPS = 10;
    // the total size of the art, includes the rectangles, the gap between
    // rectangles, and a border (size gap) between the window and the rectangles
    private static final double MAX = STEPS*(GAP+SIZE+2*BORDER)+GAP;

    public void draw() {
        int x = GAP;
        int y = 0;

        for (int i = 0; i < STEPS; i++) {
            y = GAP;

            for (int j = 0; j < STEPS; j++) {
                Rectangle rect = new Rectangle(x, y, SIZE, SIZE);
                rect.setStroked(true);
                rect.setStrokeWidth(BORDER);
                rect.setStrokeColor(Color.BLACK);
                rect.setFilled(true);
                rect.setFillColor(getColor(x, y));
                add(rect);
                y += (GAP + SIZE + 2 * BORDER);

            }
            x += (GAP + SIZE + 2 * BORDER);


        }
    }

    // no bugs in this method - it creates colors based on where the rectangle is.
    private Color getColor(int x, int y) {
        float r = (float) (x/MAX);
        float g = (float) (y/MAX);
        float b = (float) ((x+y)/(2*MAX));
        return new Color(r, g, b);
    }

    public static void main(String[] args) {
        CanvasWindow window = new CanvasWindow("Color Wall", (int)MAX, (int)MAX);
        ColorWall group = new ColorWall();
        group.draw();
        window.add(group);
    }

}
