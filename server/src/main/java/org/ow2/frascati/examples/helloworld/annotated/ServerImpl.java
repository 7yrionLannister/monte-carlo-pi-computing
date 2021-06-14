
package org.ow2.frascati.examples.helloworld.annotated;

import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Property;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.osoa.sca.annotations.Property;
import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Service;


@Service(Runnable.class)
public class ServerImpl implements Runnable, Server {
  private static long nPuntosDentroDelCirculo = 0;
  private static final long SIZE = 100000000;
  @Property
  private String uri;

  public void setUri(String uri) {
    this.uri = uri;
  }

  private Client c;

  @Reference
  public final void setClient(Client service) {
    this.c = service;
  }

  // --------------------------------------------------------------------------
  // Default constructor
  // --------------------------------------------------------------------------

  public ServerImpl() {
    System.out.println("SERVER created");
  }

  @Init
  public final void init() {
    System.out.println("SERVER initialized");
  }

  // --------------------------------------------------------------------------
  // Implementation of the Runnable interface
  // --------------------------------------------------------------------------

  public final void run() {
    System.out.println("Attach server to client...");
    c.attach(uri);
  }

  public final void clientMethod() {
    System.out.println("Client method was called from the server");
  }

	public long generateDots(int seed, long numPuntos) {
    System.out.println("generateDots");
    long numThreads = numPuntos / SIZE;
    if(numThreads == 0) {
      numThreads = 1;
    }
    final long dotsPerThread = numPuntos / numThreads;
    System.out.println("Numero de hilos = " + numThreads + ", puntos por hilo = " + dotsPerThread);
    nPuntosDentroDelCirculo = 0;
    final int seedFinal = seed;
    final Random r = new Random(seed);
    ExecutorService executor = Executors.newFixedThreadPool((int)numThreads);
    for(int i = 0; i < numThreads; i++) {
        Thread t = new Thread() {
            public void run() {
                try {
                  System.out.println("* Hilo comienza");
                  for (int i = 0; i < dotsPerThread; i++) {
                    double x = r.nextDouble();
                    double y = r.nextDouble();
          
                    if ((x * x) + (y * y) <= 1) {
                      nPuntosDentroDelCirculo++;
                    }
                  }
                  System.out.println("* Hilo termina");
                } catch (Exception e) {
                    System.out.println("F");
                }
            }
        };
        executor.execute(t);
    }
    
    executor.shutdown();
    while (!executor.isTerminated());
    return nPuntosDentroDelCirculo;
	}
}
