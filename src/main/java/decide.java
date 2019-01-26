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
    public boolean[] PUV = new boolean[15];
    public boolean[][] PUM = new boolean[15][15];
    public boolean[] CMV = new boolean[15];
    public boolean[] FUV = new boolean[15];
    public boolean LAUNCH;

    /**
    computeFUV calculates the vector FUV according to the rules stated in the problem description
    This method assumes that PUM and PUV are present previous to its execution
    */
    public void computeFUV(){
        for(int i = 0; i < 15; i++){
            FUV[i] = true;

            // if this is not the case FUV[i] is true by definition
            if(PUV[i]){
                for(int j = 0; j < 15; j++){

                    // the diagonal entries are not defined
                    if(i == j){
                        continue;
                    }

                    // all entries in the row have to be true
                    if(!PUM[i][j]){
                        FUV[i] = false;
                        break;
                    }
                }
	    }
	}
    }

    /**
    computePUM calculates the array PUM according to the rules stated in the problem description
    This method assumes that LCM and all CMVs are computed previous to its execution
    */
    public void computePUM(){
        for(int i = 0; i < 15; i++){
            for(int j = i + 1; j < 15; j++){
                String val = LCM[i][j];
                // apply the corresponding operation
                if(val.equals("ANDD")){
                    PUM[i][j] = CMV[i] && CMV[j];
                }
                else if(val.equals("ORR")){
                    PUM[i][j] = CMV[i] || CMV[j];
                }
                else{
                    PUM[i][j] = true;
                }
                // it is a symmetric matrix
                PUM[j][i] = PUM[i][j];
            }
        }
    }


    /**
    Computation of the LIC number 0
    Assess whether there exist at least one set of two consecutive data points which the distance
    between them is greater than LENGTH1.
    @return - true if the condition is fulfilled (otherwise False)
    */
    public boolean LIC0()
    {
        double dist = 0;
        for (int i=1; i<NUMPOINTS; i++)
        {
                dist = Math.sqrt((X[i]-X[i-1])*(X[i]-X[i-1]) + (Y[i] - Y[i-1])*(Y[i] - Y[i-1]));
                if (dist > PARAMETERS.LENGTH1)
                {
                        return true;
                }
        }

        return false;
    }


    /**
    Checks if there exists three consecutive points that cannot be contained
    in a circle with radius PARAMETERS.RADIUS1 by finding the smallest circle
    containing the points and comparing the radius
    @return true if three such points are found, otherwise false
    */
    public boolean lic1(){
        double rad = PARAMETERS.RADIUS1;
	double[] x = new double[3];
	double[] y = new double[3];
	for (int i = 0; i < NUMPOINTS-2; i++) {
	    x[0] = X[i];
	    x[1] = X[i+1];
	    x[2] = X[i+2];
	    y[0] = Y[i];
	    y[1] = Y[i+1];
	    y[2] = Y[i+2];
	    boolean res = lic1Calculator(rad, x, y);
	    if (res) {
	        return res;
	    }
	}
	return false;
    }

    /**
    Helper function for calculating result of lic1(), calculates whether a
    circle with radius rad can contain all three points given
    @param rad radius of circle
    @param x array of x coordinates
    @param y array of y coordinates
    @return true if circle cannot contain the three points
    */
    public boolean lic1Calculator(double rad, double[] x, double[] y){
	double TOL = 0.000001;
	double r;
	double d12 = Math.sqrt(Math.pow(x[0]-x[1],2)+Math.pow(y[0]-y[1],2));
	double d13 = Math.sqrt(Math.pow(x[0]-x[2],2)+Math.pow(y[0]-y[2],2));
	double d23 = Math.sqrt(Math.pow(x[1]-x[2],2)+Math.pow(y[1]-y[2],2));
	if (Math.abs(d12) < TOL || Math.abs(d13) < TOL || Math.abs(d23) < TOL){
	    //If two points coincide with each other, the smallest circle will have a diameter
	    //equal to the distance between the other two points.
	    r = Math.max(Math.max(d12,d13),d23)/2;
	} else {
	    //Calculate the angles of the triangle the three points create
	    double v1 = Math.acos(((x[1]-x[0])*(x[2]-x[0])+(y[1]-y[0])*(y[2]-y[0]))/(d12*d13));
	    double v2 = Math.acos(((x[2]-x[1])*(x[0]-x[1])+(y[2]-y[1])*(y[0]-y[1]))/(d12*d23));
	    double v3 = Math.acos(((x[0]-x[2])*(x[1]-x[2])+(y[0]-y[2])*(y[1]-y[2]))/(d13*d23));
	    if (Math.toDegrees(Math.max(Math.max(v1,v2),v3)) < 90){
	        //For an acute triangle the smallest circle will be the circumscribed circle
	        //of the triangle
			double offset = Math.pow(x[1],2)+Math.pow(y[1],2);
			double bc = (Math.pow(x[0],2)+Math.pow(y[0],2)-offset)/2;
			double cd = (offset-Math.pow(x[2],2)-Math.pow(y[2],2))/2;
			double det = (x[0]-x[1])*(y[1]-y[2])-(x[1]-x[2])*(y[0]-y[1]);
			double idet = 1/det;
			double centerx = (bc*(y[1]-y[2])-cd*(y[0]-y[1]))*idet;
			double centery = (cd*(x[0]-x[1])-bc*(x[1]-x[2]))*idet;
			r = Math.sqrt(Math.pow(x[1]-centerx,2)+Math.pow(y[1]-centery,2));
	    } else {
		//The smallest circle that encloses a right or obtuse triangle has its
		//diameter equal to the longest side of the triangle
		r = Math.max(Math.max(d12,d13),d23)/2;
	    }
	}
	return r > rad;
    }

    	/**
	Find whether or not there exists two sets of three points, both with
	A_PTS and B_PTS between the first and second and second and third point
	respectively, where the first set cannot be contained in a circle with
	radius RADIUS1, whereas the second set can be contained in a circle with
	radius RADIUS2.  Uses helper function lic1Calculator
	@return true if both conditions are met
	*/
	public boolean lic13(){
		if (NUMPOINTS < 5 || PARAMETERS.A_PTS < 1 || PARAMETERS.B_PTS < 1){
			return false;
		}
		double rad1 = PARAMETERS.RADIUS1;
		double rad2 = PARAMETERS.RADIUS2;
		double[] x = new double[3];
		double[] y = new double[3];
		int adist = PARAMETERS.A_PTS;
		int bdist = PARAMETERS.B_PTS;
		boolean res1 = false;
		boolean res2 = false;
		for (int i = 0; i < NUMPOINTS-adist-bdist-2; i++){
			x[0] = X[i];
			x[1] = X[i+adist+1];
			x[2] = X[i+adist+bdist+2];
			y[0] = Y[i];
			y[1] = Y[i+adist+1];
			y[2] = Y[i+adist+bdist+2];
			if(!res1){
				res1 = lic1Calculator(rad1, x, y);
			}
			if(!res2){
				res2 = !lic1Calculator(rad2, x, y);
			}
			if (res1 && res2) {
				return true;
			}
		}
		return false;
	}

	/**
	There exists at least one set of three data points, separated by exactly E PTS and F PTS con-
	secutive intervening points, respectively, that are the vertices of a triangle with area greater

	than AREA1. In addition, there exist three data points (which can be the same or different

	from the three data points just mentioned) separated by exactly E PTS and F PTS consec-
	utive intervening points, respectively, that are the vertices of a triangle with area less than

	AREA2. Both parts must be true for the LIC to be true. The condition is not met when
	NUMPOINTS < 5.
	0 ≤ AREA2
	Must be true:
	1 ≤ E_PTS, 1 ≤ F_PTS		|Theese two are not a part of lab instructions.
	E PTS+F PTS ≤ NUMPOINTS−3	|I just assumed they were.
	*/
	public boolean LIC14(){
		int e = PARAMETERS.E_PTS;
		int f = PARAMETERS.F_PTS;
		if(NUMPOINTS < 5 || PARAMETERS.AREA2 < 0 || !(1 <= e && 1 <= f && e+f <= NUMPOINTS-3)){ //Bad input data
			return false;
		}
		int B = e+1;
		int C = B+f+1;
		double area;
		boolean GT = false; //Greater than. This indicates if we found an area that is greater than AREA1
		boolean LT = false; //Lesser than. This indicates if we found an area that is lesser than AREA2
		for (int A = 0; C < NUMPOINTS ; A++, B++, C++) {
			area = Math.abs(X[A]*(Y[B] - Y[C]) + X[B]*(Y[C] - Y[A]) + X[C]*(Y[A] - Y[B]))/2; //coordinate geometry formula for area
			// System.out.println("Area:" + area + " with points:(" + X[A] + "," + Y[A] + ")(" + X[B] + "," + Y[B] + ")(" + X[C] + "," + Y[C] + ")");
			GT = GT?GT:area > PARAMETERS.AREA1;//IF GT/LT is already set, do nothing.
			LT = LT?LT:area < PARAMETERS.AREA2;//Else set GT/LT to GT/LT comparison.
		}
		return GT&&LT;
	}

    public void decide(){

    }

    public static void main (String[] args){
        System.out.println("Hello World");
    }






















}
