package co.edu.icesi.jfm;

import org.osoa.sca.annotations.Property;


public class Server implements {
    // TODO todo esto es del ejemplo, adaptarlo


    @Property
    private String header = "->";

    private int count = 1;

    /**
     * Default constructor.
     */
    public Server()
    {
        System.out.println("SERVER created.");
    }
    
    /**
     * multiplicarMat implementation
     */
    @Override
    public double[][] multMat(double[][] A, double[][] B) throws Exception
    {
        if(A[0].length != B.length) {
            throw(new Exception("You cannot multiply these matrices"));
        }
        double[][] C = new double[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
}
