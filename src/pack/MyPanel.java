package pack;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {

    Texture t;

    public MyPanel(Texture t) {
        this.t = t;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        t.draw(g);
    }
    public void update() {
        repaint();
    }


}
