package pack;

import gilstein.graphics.Board;
import gilstein.graphics.Ellipse;
import gilstein.graphics.Rectangle;
import gilstein.util.*;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static boolean yes = false;
    public static void main(String[] args) {

        Board b = new Board(900,600,"Test");

        Time t = new Time();



        JFrame frame = new JFrame("Textures test");
        //this.f = frame;
        //frame.add(this.canvas);
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        Point mousePos = MouseInfo.getPointerInfo().getLocation();

        Texture T = new Texture("src//jungle_planks.png",new double[]{100,501,390,30},new double[]{100,110,400,440});

        //Texture T = new Texture("src//jungle_planks.png",new double[]{100,500,500,100},new double[]{100,100,400,400});

        MyPanel m = new MyPanel(T);

        frame.add(m);

        Ellipse[][] e = new Ellipse[17][17];

        for(int i = 0; i < e.length; i++) {
            for(int j = 0; j < e[i].length; j++) {
                e[i][j] = new Ellipse(b,0,0,5,5,Color.BLUE);
            }
        }

        while (true) {

            if(b.mouseClicked()) {
                //System.out.println("yes");
            }

            //yes = b.mouseClicked();

            mousePos = MouseInfo.getPointerInfo().getLocation();

            T.setCoordinates(new double[]{100,501,390, mousePos.x},new double[]{100,110,400, mousePos.y});

            //System.out.println(" ");

            m.update();

            for(int i = 0; i < T.pixels.length; i++) {
                for(int j = 0; j < T.pixels[i].length; j++) {
                    e[i][j].moveTo((int)T.ps[i][j][0],(int)T.ps[i][j][1]);
                }
            }




            t.setTime(0.02);
        }

//        Time t = new Time();
//
//        //double[][] points = {{100,100},{501,110},{390,400},{30,440}};
//
//        double[][] points = {{(Math.random()*600),(Math.random()*600)},{(Math.random()*600),(Math.random()*600)},{(Math.random()*600),(Math.random()*600)},{(Math.random()*600),(Math.random()*600)}};
//
//        CoordLine[] larr = new CoordLine[4];
//
//        double[][][] arr = new double[4][17][2];
//
//        for(int i = 0; i < larr.length; i++) {
//            larr[i] = new CoordLine(points[i%4][0],points[i%4][1],points[(i + 1)%4][0],points[(i + 1)%4][1]);
//            arr[i] = larr[i].splitTo16();
//        }
//
//        gilstein.graphics.Line[] larrr = new gilstein.graphics.Line[34];
//
//        for(int i = 0; i < 17; i++) {
//            larrr[i] = new gilstein.graphics.Line(b,(int)arr[0][i][0],(int)arr[0][i][1],(int)arr[2][16-i][0],(int)arr[2][16-i][1],Color.BLUE);
//            larrr[i + 17] = new gilstein.graphics.Line(b,(int)arr[1][i][0],(int)arr[1][i][1],(int)arr[3][16-i][0],(int)arr[3][16-i][1],Color.BLUE);
//        }
//
//        while (true) {
//
//            //System.out.println(b.getFrame().getHeight());
//
//            if(true) {
//
//                for(gilstein.graphics.Line l : larrr) {
//                    l.erase();
//                }
//
//                //System.out.println("clicked");
//
//                //{{100,100},{501,110},{390,400},{30,440}}
//
//                points = new double[][]{{100,100},{501,110},{390,400},{b.getPointerXonBoard(),b.getPointerYonBoard()}};
//
//                for(int i = 0; i < larr.length; i++) {
//                    larr[i] = new CoordLine(points[i%4][0],points[i%4][1],points[(i + 1)%4][0],points[(i + 1)%4][1]);
//                    arr[i] = larr[i].splitTo16();
//                }
//
//                larrr = new gilstein.graphics.Line[34];
//
//                for(int i = 0; i < 17; i++) {
//                    larrr[i] = new gilstein.graphics.Line(b,(int)arr[0][i][0],(int)arr[0][i][1],(int)arr[2][16-i][0],(int)arr[2][16-i][1],Color.BLUE);
//                    larrr[i + 17] = new gilstein.graphics.Line(b,(int)arr[1][i][0],(int)arr[1][i][1],(int)arr[3][16-i][0],(int)arr[3][16-i][1],Color.BLUE);
//                }
//
//                //System.out.println("");
//                t.setTime(0.02);
//
//            }
//            //System.out.println("");
//        }

//        double x1 = 500;
//        double y1 = 500;
//        double x2 = 100;
//        double y2 = 100;
//
//
//
//        CoordLine l = new CoordLine(x2,y2,x1,y1);
//
//        double[][] arr = l.splitTo16();
//
//        Ellipse[] e = new Ellipse[17];
//
//        for(int i = 0; i < e.length; i++) {
//            e[i] = new Ellipse(b,(int)arr[i][0],(int)arr[i][1],5,5,Color.BLUE);
//            //System.out.println(arr[i][0] + " , " + arr[i][1]);
//        }
//
//        Rectangle r = new Rectangle(b,0,0,4,4,Color.RED);
//
//        while (true) {
//            l = new CoordLine(400,400,b.getPointerXonBoard(),b.getPointerYonBoard());
//            for(int i = 0; i < e.length; i++) {
//
//                arr = l.splitTo16();
//
//                //e[i] = new Ellipse(b,(int)arr[i][0],(int)arr[i][1],5,5,Color.BLUE);
//                e[i].moveTo((int)arr[i][0],(int)arr[i][1]);
//                r.moveTo(b.getPointerXonBoard(),b.getPointerYonBoard());
//                //System.out.println(arr[i][0] + " , " + arr[i][1]);
//                //t.setTime(0.02);
//            }
//        }



    }
}