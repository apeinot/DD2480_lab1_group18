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
    computePUM computes the array PUM according to the rules stated in the problem description
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
	
	
    public void decide(){

    }

    public static void main (String[] args){
        System.out.println("Hello World");
    }

}
