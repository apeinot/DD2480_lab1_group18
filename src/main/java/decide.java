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
	Calculates whether there exists a set of N_PTS consecutive points such that
	the distance from the line between the first and last point of the set to
	the furthest point is greater than DIST.
	@return true if at least one point is further away than DIST from the line
	*/
	public boolean lic6(){
		if (NUMPOINTS < 3){
			return false;
		}
		boolean res;
		int n = PARAMETERS.N_PTS;
		for (int i = 0; i <= NUMPOINTS-n; i++){
			double[] x = new double[n];
			double[] y = new double[n];
			for (int j = 0; j < n; j++){
				x[j] = X[i+j];
				y[j] = Y[i+j];
			}
			res = lic6Calculator(PARAMETERS.DIST, x, y);
			if (res){
				return true;
			}
		}
		return false;

	}

	/**
	Helper function for lic6(). Calculates the distance from the line between
	the first and last point to all other points
	@param dist the minimum distance between furthest point and line
	@param x array of x coordinates
	@param y array of y coordinates
	@return true if the furthest point from the line is more than dist units away
	*/
	public boolean lic6Calculator(double dist, double[] x, double[] y){
		double d = 0;
		double TOL = 0.000001;
		double fx = x[0];
		double fy = y[0];
		double lx = x[x.length-1];
		double ly = y[y.length-1];
		double lineSqrd = Math.pow(lx-fx,2)+Math.pow(ly-fy,2);
		for (int i = 1; i < x.length-1; i++){
			if (lineSqrd < TOL){
				d = Math.pow(fx-x[i],2)+Math.pow(fy-y[i],2);
			} else {
				double t = ((x[i]-fx)*(lx-fx)+(y[i]-fy)*(ly-fy))/lineSqrd;
				t = Math.max(0, Math.min(1,t));
				double px = fx + t * (lx-fx);
				double py = fy + t * (ly-fy);
				d = Math.pow(px-x[i],2)+Math.pow(py-y[i],2);
			}
			if (d > Math.pow(dist,2)){
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
