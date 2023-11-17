package pack;

import gilstein.util.Print;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Texture {

    BufferedImage image;

//    Print p = new Print();

    Color[][] colors = new Color[16][16];

    Pixel[][] pixels = new Pixel[16][16];


    double[][][] ps;

    public Texture(String path,double[] x, double[] y) {
        try {
            File imageFile = new File(path);
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < colors.length; i++) {
            for(int j = 0; j < colors[i].length; j++) {
                colors[i][j] = new Color((image.getRGB(i, j) >> 16) & 0xFF,image.getRGB(i, j) >> 8 & 0xFF,image.getRGB(i, j) & 0xFF);
            }
        }

        CoordLine l = new CoordLine(x[0],y[0],x[1],y[1]);
        CoordLine l2 = new CoordLine(x[3],y[3],x[2],y[2]);

        CoordLine[] larr = new CoordLine[17];

        double[][] temp = l.splitTo16();


        double[][] temp2 = l2.splitTo16();


        double[][][] points = new double[17][17][2];

        ps = points;

        for(int i = 0; i < larr.length; i++) {
            larr[i] = new CoordLine(temp[i][0],temp[i][1],temp2[i][0],temp2[i][1]);
        }
        for(int i = 0; i < 17; i++) {
            points[i] = larr[i].splitTo16();
        }

        for(int i = 0; i < pixels.length; i++) {
            for(int j = 0; j < pixels[i].length; j++) {
                pixels[i][j] = new Pixel(new double[][]{{points[i][j][0],points[i][j][1]},{points[i+1][j][0],points[i+1][j][1]},{points[i+1][j+1][0],points[i+1][j+1][1]},{points[i][j+1][0],points[i][j+1][1]}});
            }
        }

//        for(int i = 0; i < points.length; i++) {
//            for(int j = 0; j < points[i].length; j++) {
//                System.out.print("(" + points[i][j][0] + "," + points[i][j][1] + "),");
//            }
//            System.out.println();
//        }

    }

    public void setCoordinates(double[] x, double[] y) {

        CoordLine l = new CoordLine(x[0],y[0],x[1],y[1]);
        CoordLine l2 = new CoordLine(x[3],y[3],x[2],y[2]);

        CoordLine l3 = new CoordLine(x[1],y[1],x[2],y[2]);
        CoordLine l4 = new CoordLine(x[0],y[0],x[3],y[3]);

        CoordLine[] larr = new CoordLine[17];

        double[][] temp = l.splitTo16();

        double[][] temp2 = l2.splitTo16();

        double[][] temp3 = l3.splitTo16();

        double[][] temp4 = l4.splitTo16();

        CoordLine[] clarr = new CoordLine[17];
        CoordLine[] clarr2 = new CoordLine[17];

        double[][][] points = new double[clarr.length][clarr2.length][2];



        for(int i = 0; i < clarr.length; i++) {
            clarr[i] = new CoordLine(temp[i][0],temp[i][1],temp2[i][0],temp2[i][1]);
        }

        for(int i = 0; i < clarr.length; i++) {
            clarr2[i] = new CoordLine(temp3[i][0],temp3[i][1],temp4[i][0],temp4[i][1]);
        }

        for(int i = 0; i < points.length; i++) {
            for(int j = 0; j < points[i].length; j++) {
                points[i][j] = clarr[i].intersection(clarr2[j]);
//                if(j != 0 && points[i][j][1] == points[i][j - 1][1]) {
//                    System.out.println("problem");
//                    System.out.println("(" + points[i][j][0] + "," + points[i][j][1] + ") m1 = " + clarr[i].getM() + " m2 = " + clarr2[j].getM());
//                }
            }
        }

//        for(int i = 0; i < points.length; i++) {
//            for(int j = 0; j < points[i].length; j++) {
//                System.out.print("(" + points[i][j][0] + "," + points[i][j][1] + "), ");
//            }
//            System.out.println();
//        }
//        System.out.println("done");

        for(int i = 0; i < pixels.length; i++) {
            for(int j = 0; j < pixels[i].length; j++) {
                //System.out.print(0);
                pixels[i][j] = new Pixel(new double[][]{{points[i][j][0],points[i][j][1]},{points[i+1][j][0],points[i+1][j][1]},{points[i+1][j+1][0],points[i+1][j+1][1]},{points[i][j+1][0],points[i][j+1][1]}});
            }
            //System.out.println();
        }
        //System.out.println("----");
        ps = points.clone();


//        for(int i = 0; i < temp.length; i++) {
//            System.out.println(p.nextDouble(temp[i]));
//        }
//
//        for(int i = 0; i < temp2.length; i++) {
//            System.out.println(p.nextDouble(temp2[i]));
//        }

//        double[][][] points = new double[17][17][2];
//
//
//
//        for(int i = 0; i < larr.length; i++) {
//            larr[i] = new CoordLine(temp[i][0],temp[i][1],temp2[i][0],temp2[i][1]);
//
//        }
//        for(int i = 0; i < 17; i++) {
//            points[i] = larr[i].splitTo16();
//        }
//
//        for(int i = 0; i < pixels.length; i++) {
//            for(int j = 0; j < pixels[i].length; j++) {
//                pixels[i][j] = new Pixel(new double[][]{{points[i][j][0],points[i][j][1]},{points[i+1][j][0],points[i+1][j][1]},{points[i+1][j+1][0],points[i+1][j+1][1]},{points[i][j+1][0],points[i][j+1][1]}});
//            }
//        }

//        ps = points.clone();

    }
    public void draw(Graphics g) {

        //int count = 0;

        for(int i = 0; i < pixels.length; i++) {
            for(int j = 0; j < pixels[i].length; j++) {
                //System.out.println(count++);
                g.setColor(colors[i][j]);
                g.fillPolygon(new int[]{(int)pixels[i][j].xy[0][0],(int)pixels[i][j].xy[1][0],(int)pixels[i][j].xy[2][0],(int)pixels[i][j].xy[3][0]},new int[]{(int)pixels[i][j].xy[0][1],(int)pixels[i][j].xy[1][1],(int)pixels[i][j].xy[2][1],(int)pixels[i][j].xy[3][1]},4);
            }
        }
    }

}
