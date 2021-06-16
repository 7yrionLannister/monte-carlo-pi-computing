package org.ow2.frascati.examples.helloworld.annotated;

import java.util.Random;

public class Hilo extends Thread {
    public long num = 0;
    public Random r;
    public long dots;
    public void run() {
        try {
            for (int i = 0; i < dots; i++) {
              double x = r.nextDouble();
              double y = r.nextDouble();
    
              if ((x * x) + (y * y) <= 1) {
                num++;
              }
            }
          } catch (Exception e) {
              System.out.println("F");
          }
    }
}