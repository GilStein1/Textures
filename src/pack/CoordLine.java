package pack;

import java.math.BigDecimal;

public class CoordLine {

    private double m;
    private double bt;

    boolean mnull = false;

    private double x1,x2,y1,y2;

    public CoordLine(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;

        if(x2 == x1 || Math.abs(x2 - x1) < 0.000001) {
            //m = Math.pow(100,100000);
            //System.out.println("yes");
            //x2 += 0.0001;
            //m = null;
            mnull = true;
            //System.out.println("indeed");
        }

        this.m = (y2 - y1)/(x2 - x1);


        if(y2 == y1) {
            m = 0;
        }

        this.bt = (y1 - m*x1);

        //System.out.println("m = " + m);

        //System.out.println("b = " + bt);

    }

    public double[][] splitTo16() {

        double xx1,yy1;



        //xx1 = Math.max(x1,x2);
        //yy1 = m*xx1 + bt;

        xx1 = x1;
        yy1 = y1;


        //double[][] arr = new double[17][2];
        double[][] arr = new double[17][2];
        double a,b,c,d;
        double x;

        boolean temp;

        //int count = 16;

        arr[16][0] = (x2);
        arr[16][1] = (y2);

        arr[0][0] = xx1;
        arr[0][1] = yy1;

        if(!mnull) {

            for(int i = 1; i < arr.length - 1; i++) {
                d = Math.sqrt((this.x1-x2)*(this.x1-x2) + (this.y1-y2)*(this.y1-y2)) * (((double)i)/((double)arr.length-1));

                //System.out.println("D = " + d);

                a = 1 + m*m;
                b = 2*m*(bt-yy1) - 2*xx1;
                c = Math.pow(bt-yy1,2) + xx1*xx1 - d*d;

                temp = checkIfInRange((-b +Math.sqrt(Math.abs(b*b - 4*a*c)))/(2*a),m*(-b +Math.sqrt(Math.abs(b*b - 4*a*c)))/(2*a) + bt);

                if(temp) {
                    x = (-b +Math.sqrt(Math.abs(b*b - 4*a*c)))/(2*a);
                }
                else {
                    x = (-b -Math.sqrt(Math.abs(b*b - 4*a*c)))/(2*a);
                }



                //System.out.println("x = " + x);

                //System.out.println("thing = " + (b*b - 4*a*c));

                arr[i][0] = x;
                arr[i][1] = m*x + bt;

                if(arr[i][1] == 0) {
                    System.out.println("(" + arr[i][0] + "," + arr[i][1] + ") m = " + m + " b = " + bt);
                }

//                if(arr[i][1] < 0) {
//                    //System.out.println("(" + arr[i][0] + "," + arr[i][1] + ") m = " + m + " b = " + bt);
//                    //arr[i][1] = Math.abs(arr[i][1]);
//                }
//                if(arr[i][1] > 900) {
//                    System.out.println("(" + arr[i][0] + "," + arr[i][1] + ") m = " + m + " b = " + bt);
//                }

                //count--;
            }

        }
        else {
            //System.out.println("It is problem");

            for(int i = 1; i < arr.length - 1; i++) {
                d = Math.abs(y2 - y1) * (((double)i)/((double)arr.length-1));

                arr[i][0] = x1;
                arr[i][1] = Math.min(y1,y2) + d;
                //System.out.println(arr[i][1]);

            }
        }


        return arr;
    }
    public boolean checkIfInRange(double x, double y) {

        if((x >= min(x1,x2) && x <= max(x1,x2)) && (y >= min(y1,y2) && y <= max(y1,y2))) {
            return true;
        }
        return false;
    }
    public double min(double a, double b) {
        return (a < b)? a : b;
    }
    public double max(double a, double b) {
        return (a > b)? a : b;
    }
    public double[] intersection(CoordLine line) {
        double[] point = new double[2];

        if(!mnull && !line.mnull) {
            point[0] = (line.getBt() - this.getBt())/(this.getM() - line.getM());
            point[1] = m*point[0] + bt;
            return point;
        }
        else if (mnull && !line.mnull){
            point[0] = x1;
            point[1] = line.m*x1 + line.bt;
            return point;
        }
        else  {
            point[0] = line.x1;
            point[1] = line.x1*m + bt;
            return point;
        }


    }

    public double getM() {
        return m;
    }

    public double getBt() {
        return bt;
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public double getY1() {
        return y1;
    }

    public double getY2() {
        return y2;
    }
}
