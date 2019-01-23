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

	/**
	Assesses whether there at least exists one set of three consecutive data
	data points that are the vertices of a triangle with area greater than AREA1.
	Applies Heron's formula to calculate the area of the triangles.
	@return - true or false, depending on whether the condition described above
	          is fulfilled or not.
	*/
	public boolean LIC3(){
		if(NUMPOINTS != X.length || NUMPOINTS != Y.length)
			return false;
		for(int i = 0; i < NUMPOINTS - 2; i++){
			int first_point = i;
			int second_point = i + 1;
			int third_point = i + 2;

			// The three points of the triangle
			double X1 = X[first_point], Y1 = Y[first_point];
			double X2 = X[second_point], Y2 = Y[second_point];
			double X3 = X[third_point], Y3 = Y[third_point];

			// The three sides of the triangle
			double a = Math.sqrt((X2 - X1) * (X2 - X1) + (Y2 - Y1) * (Y2 - Y1));
			double b = Math.sqrt((X3 - X2) * (X3 - X2) + (Y3 - Y2) * (Y3 - Y2));
			double c = Math.sqrt((X1 - X3) * (X1 - X3) + (Y1 - Y3) * (Y1 - Y3));

			// The "semiperimeter"
			double s = 0.5 * (a + b + c);

			// Heron's formula
			double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
			if(area > PARAMETERS.AREA1){
				return true;
			}
		}
		return false;
	}

    public void decide(){

    }

    public static void main (String[] args){
        System.out.println("Hello World");
    }

}
