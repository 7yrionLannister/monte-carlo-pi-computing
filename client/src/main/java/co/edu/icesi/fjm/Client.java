package co.edu.icesi.jfm;

import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Reference;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;


public class Client implements Runnable {
    // SCA reference
    private ComputePI s;

    @Reference
    public final void setComputePI(ComputePI cpi) {
        this.s = cpi;
    }

    public Client() {
        System.out.println("CLIENT created");
    }

    @Init
    public void init() {
        System.out.println("CLIENT initialized");
    }

    @Override
    public final void run() {
        System.out.println("Run method is dummy");
        /*
        System.out.println("Call the service...");
        String file = "matrices.txt";
        FileInputStream fis = null;
        try {
        fis = new FileInputStream(new File(file));
        } catch (Exception e) {
            System.out.println(":C");
        }
        Scanner sc = new Scanner(fis);
        String[] line = sc.nextLine().split(" ");
        int rows = Integer.parseInt(line[0]);
        int cols = Integer.parseInt(line[1]);
        double[][] A = new double[rows][cols];
        for(int i = 0; i < rows; i++) {
            line = sc.nextLine().split(" ");
            for(int j = 0; j < line.length; j++) {
                A[i][j] = Double.parseDouble(line[j]);
            }
        }
        sc.nextLine(); // empty line between matrices
        line = sc.nextLine().split(" ");
        rows = Integer.parseInt(line[0]);
        cols = Integer.parseInt(line[1]);
        double[][] B = new double[rows][cols];
        for(int i = 0; i < rows; i++) {
            line = sc.nextLine().split(" ");
            for(int j = 0; j < line.length; j++) {
                B[i][j] = Double.parseDouble(line[j]);
            }
        }

        double[][] C = s.multMat(A, B);
        for(double[] c : C) {
            System.out.println(Arrays.toString(c));
        }
        sc.close();
        */
    }
}
