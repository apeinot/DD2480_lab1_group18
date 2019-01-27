//Main file of Decide programm

import java.lang.Math;

class Decide {

    public static final double PI = 3.1415926535;

    public class PARAMETERS_T{

        public double LENGTH1 ;
        public double RADIUS1 ;
	public double EPSILON ;
	public double AREA1 ;
	public int Q_PTS ;
	public int QUADS;
	public double DIST;
	public int N_PTS ;
	public int K_PTS ;
	public int A_PTS ;
	public int B_PTS ;
	public int C_PTS ;
	public int D_PTS ;
	public int E_PTS ;
	public int F_PTS ;
	public int G_PTS ;
	public double LENGTH2 ;
	public double RADIUS2 ;
	public double AREA2 ;

	public PARAMETERS_T(){
	    LENGTH1 = 100;
            RADIUS1 = 20;
            EPSILON = PI/3;
            AREA1 = 200;
            Q_PTS = 10;
            QUADS = 2;
            DIST = 50;
            N_PTS = 15;
            K_PTS = 15;
            A_PTS = 3;
            B_PTS = 9;
            C_PTS = 4;
            D_PTS = 10;
            E_PTS = 6;
            F_PTS = 9;
            G_PTS = 14;
            LENGTH2 = 150;
            RADIUS2 = PI/2;
            AREA2 = 300;
	}
    }

    public PARAMETERS_T PARAMETERS = new PARAMETERS_T();
    public double[] X = new double[100];
    public double[] Y = new double[100];
    public int NUMPOINTS;
    public String[][] LCM = new String[15][15]; //possible value "NOTUSED", "ORR", "ANDD"
    public boolean[][] PUM = new boolean[15][15];
    public boolean[] CMV = new boolean[15];
    public boolean[] FUV = new boolean[15];
    public boolean LAUNCH;


    public void decide(){

    }

    /**
    Checks if there exists at least one set of Q PTS consecutive data points that lie 
    in more than QUADS quadrants. A point exactly between two quadrants is always 
    considered a member of the quadrant of lower index.
    */
    public boolean LIC4(){
	int p = PARAMETERS.Q_PTS;
	int q = PARAMETERS.QUADS;
	int[] pointsInQuads = {0,0,0,0}; //How many points in our consecutive intervall are in the different quadrants
	int filledQuadrants = 0; //How many quadrant have points in them.
	if(!(2 <= p && p <= NUMPOINTS && 1 <= q && q <= 3)){ //Parameter(s) not in domain
	    return false;
	}
	int quadrant;
	for (int i = 0 ; i < NUMPOINTS ; i++) {
	    if(i >= p){ //If we have examined more than or exactly p points.
		quadrant = LIC4QuadrantHelper(X[i-p],Y[i-p]); //Get in which quadrant the previous last of the consecutive points were.
		pointsInQuads[quadrant]--;
		if(pointsInQuads[quadrant] == 0){ //If that was the last point in that quadrant.
		    filledQuadrants--;
		}
	    }
	    quadrant = LIC4QuadrantHelper(X[i],Y[i]); //Get in which quadrant the first of the consecutive points is.
	    pointsInQuads[quadrant]++;
	    if(pointsInQuads[quadrant] == 1){ //If that quadrant was previously empty
		filledQuadrants++;
	    }
	    if(filledQuadrants > q){
		return true;
	    }
	}
	return false;
    }

    /**
    This method calculates to which quadrant a point belong to.
    A point exactly between two quadrants is always considered a member of the quadrant of lower index.
    For example (0,1) is quadrant 1, (-1,0) is quadrant 2 and (0,0) is quadrant 1
    @param x the x coordinate
    @param y the y coordinate
    @return A number in the range 0 - 3 denoting quadrant.
    */
    public int LIC4QuadrantHelper(double x, double y){
	if(y >= 0){ //Quadrant 1 or 2
	    if(x >= 0){
		return 0;
	    }
	    return 1;
        }
	else{ //Quadrant 3 or 4
	    if(x > 0){
		return 3;
	    }
	    return 2;
	}
    }

    public static void main (String[] args){
        System.out.println("Hello World");
    }

}
