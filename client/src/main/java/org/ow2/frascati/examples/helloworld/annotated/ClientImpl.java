package org.ow2.frascati.examples.helloworld.annotated;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.osoa.sca.annotations.Property;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Random;


public class ClientImpl
  implements Client, Runnable
{
    private static ArrayList<Server> servers = new ArrayList<Server>();
    private static int numServers = 0;
    private static GUI gui;

    private static long dotsGenerated = 0;

    @Property
    private String header = "->";

    private int count = 1;

    /**
     * Default constructor.
     */
    public ClientImpl()
    {
        System.out.println("CLIENT created.");
    }

    public double computePi(long totalPoints, long circlePoints) {
		double pi = (4 * ((double) circlePoints) / (totalPoints));

		return pi;
	}

    public final void attach(String uri) {
        System.out.println(uri);
        try {
            Server s = (Server) Naming.lookup(uri);
            servers.add(s);
            System.out.println("El tamanio de la lista de servers es " + servers.size());
            numServers = servers.size();
            gui.getTextFieldNumNodosProcesamiento().setText(numServers + "");
        } catch (Exception e) {
            System.out.println("No se pudo hacer el lookup");
            System.out.println("**************************************************");
            System.out.println(e.getMessage());
            System.out.println("**************************************************");
        }
    }

    public final void run() {
        System.out.println("CLIENT: Started");
        try {
            if(gui == null) {
                gui = new GUI();
                gui.setLocationRelativeTo(null);
                gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                gui.setVisible(true);
                configureEvents();
            }
        } catch (Exception e) {
            System.out.println("Se produjo una excepcion al configurar la interfaz grafica");
            e.printStackTrace();
        }
    }

    private void configureEvents() {
		gui.getBtncalcularPI().addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					final int seed = Integer.parseInt(gui.getTextFieldSemilla().getText().trim());
					long numPuntos = Long.parseLong(gui.getTextFieldNumPuntos().getText().trim());
					if(seed > 0&& numPuntos > 0) {
						System.out.println("----------------------------");
						System.out.println("seed es " + seed);
						System.out.println("npuntos es " + numPuntos);
						System.out.println("----------------------------");

                        System.out.println("SERVERS A IMPRIMIR: " + servers.size());
                        dotsGenerated = 0;
                        final long eachDots = (long)Math.ceil(numPuntos * 1.0 / servers.size());
                        ArrayList<Thread> threads = new ArrayList<Thread>();
                        ExecutorService executor = Executors.newFixedThreadPool(servers.size());
                        long startMillis = System.currentTimeMillis();
                        final Random r = new Random(seed);
                        for(final Server s : servers) {
                            Thread t = new Thread() {
                                public void run() {
                                    try {
                                        dotsGenerated += s.generateDots(r.nextInt(1000), eachDots);
                                    } catch (RemoteException re) {
                                        System.out.println("Se produjo una excepcion remota");
                                    }
                                }
                            };
                            threads.add(t);
                            executor.execute(t);
                        }
                        
                        executor.shutdown();
		                while (!executor.isTerminated()); // espera el resultado
                        long endMillis = System.currentTimeMillis();
                        long duration = (endMillis - startMillis);
                        System.out.println("Se tardo " + duration + " milisegundos");

                        System.out.println("..... " + dotsGenerated + " ........");
                        double pi = computePi(numPuntos, dotsGenerated);
                        System.out.println("..... pi = " + pi + " ........");
                        gui.getTextFieldPi().setText(pi+"");
					}
				}
			}
		);
	}
}
