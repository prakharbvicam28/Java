import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class A2 extends JFrame {
    enum Shape { LINE, CIRCLE, RECT }
    
    static class Item {
        Shape type;
        Point p1, p2;
        Color col;
        Item(Shape t, Point a, Point b, Color c) {
            type = t; p1 = a; p2 = b; col = c;
        }
    }

    private Shape type = Shape.LINE;
    private Color color = Color.BLACK;
    private final ArrayList<Item> list = new ArrayList<>();
    private Point start, end;

    public A2() {
        setTitle("Paint App");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JButton bLine = new JButton("Line");
        JButton bCirc = new JButton("Circle");
        JButton bRect = new JButton("Rect");
        JButton bCol = new JButton("Color");
        JButton bClr = new JButton("Clear");

        bLine.addActionListener(e -> type = Shape.LINE);
        bCirc.addActionListener(e -> type = Shape.CIRCLE);
        bRect.addActionListener(e -> type = Shape.RECT);
        bCol.addActionListener(e -> {
            Color c = JColorChooser.showDialog(this, "Color", color);
            if (c != null) color = c;
        });
        bClr.addActionListener(e -> { list.clear(); repaint(); });

        panel.add(bLine); panel.add(bCirc); panel.add(bRect);
        panel.add(bCol); panel.add(bClr);

        JPanel canvas = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Item i : list) draw(g, i.type, i.p1, i.p2, i.col);
                if (start != null && end != null) draw(g, type, start, end, color);
            }
        };
        canvas.setBackground(Color.WHITE);

        MouseAdapter ma = new MouseAdapter() {
            public void mousePressed(MouseEvent e) { start = end = e.getPoint(); }
            public void mouseDragged(MouseEvent e) { end = e.getPoint(); repaint(); }
            public void mouseReleased(MouseEvent e) {
                list.add(new Item(type, start, e.getPoint(), color));
                start = end = null;
                repaint();
            }
        };
        canvas.addMouseListener(ma);
        canvas.addMouseMotionListener(ma);

        add(panel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
    }

    private void draw(Graphics g, Shape t, Point p1, Point p2, Color c) {
        g.setColor(c);
        int x = Math.min(p1.x, p2.x), y = Math.min(p1.y, p2.y);
        int w = Math.abs(p1.x - p2.x), h = Math.abs(p1.y - p2.y);
        if (t == Shape.LINE) g.drawLine(p1.x, p1.y, p2.x, p2.y);
        else if (t == Shape.RECT) g.drawRect(x, y, w, h);
        else if (t == Shape.CIRCLE) g.drawOval(x, y, w, h);
    }

    public static void main(String[] args) {
        new A2().setVisible(true);
    }
}