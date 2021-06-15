
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
import java.util.concurrent.Semaphore;


@Service(Runnable.class)
public class ServerImpl implements Runnable, Server {
  private static long nPuntosDentroDelCirculo = 0;
  private static final long SIZE = 100000000;
  private static final int THREADS = 4;
  private static final Semaphore semaphore = new Semaphore(1);

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

  static long count = 0;
	public long generateDots(int seed, long numPuntos) throws Exception {
    System.out.println("generateDots");
    long dotsPerThread = Math.min(SIZE, (int) Math.ceil(numPuntos * 1.0 / THREADS));
    nPuntosDentroDelCirculo = 0;
    final int seedFinal = seed;
    final Random r = new Random(seed);
    int numThreads = (int)Math.ceil(numPuntos * 1.0 / SIZE);
    count = 0;
    for(int j = 0; j < numThreads; j += THREADS) {
      ExecutorService executor = Executors.newFixedThreadPool(THREADS);
      if(j == numThreads - 1) {
        dotsPerThread = Math.min(dotsPerThread, SIZE);
      }
      final long dots = dotsPerThread;
      for(int i = 0; i < THREADS; i++) {
          Thread t = new Thread() {
              public void run() {
                  try {
                    for (int i = 0; i < dots; i++) {
                      semaphore.acquire();
                      count++;
                      double x = r.nextDouble();
                      double y = r.nextDouble();
            
                      if ((x * x) + (y * y) <= 1) {
                        nPuntosDentroDelCirculo++;
                      }
                      semaphore.release();
                    }
                  } catch (Exception e) {
                      System.out.println("F");
                  }
              }
          };
          executor.execute(t);
      }
      executor.shutdown();
      while (!executor.isTerminated());
    }
    System.out.println("* Cuenta = " + count + ", esperado = " + numPuntos);
    
    //executor.shutdown();
    //while (!executor.isTerminated());
    return nPuntosDentroDelCirculo;
	}
}
