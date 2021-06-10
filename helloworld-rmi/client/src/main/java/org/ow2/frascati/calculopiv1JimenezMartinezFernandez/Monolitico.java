/**
 * OW2 FraSCAti Examples: HelloWorld RMI
 * Copyright (C) 2008-2010 INRIA, University of Lille 1
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * Contact: frascati@ow2.org
 *
 * Author: Damien Fournier
 * 
 * Contributor(s): Nicolas Dolet
 *                 Philippe Merle
 *
 */
package org.ow2.frascati.calculopiv1JimenezMartinezFernandez;

import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Reference;

public class Monolitico
  implements Runnable
{

	  public long puntitosEstan(long numeros, int semilla){

	    Random r = new Random(semilla);

	    long nPuntosDentroDelCirculo = 0;
	    
	    for (int i = 0; i < numeros; i++) {
			
	    	double x = r.nextDouble();
	    	double y = r.nextDouble();
	    	
	    	if ((x*x)+(y*y) <= 1 ) {
				
	    		nPuntosDentroDelCirculo++;
			}
	    	
			
		}

	    return nPuntosDentroDelCirculo;
	    
	  }
	  
	  public double calcularPi(long puntosACalcular, int semilla) {
		  
		  long p = puntitosEstan(puntosACalcular, semilla);
		  
		  double pi = (4*((double)p)/(puntosACalcular));
				  
		 return pi;
	  }

  //--------------------------------------------------------------------------
  // Implementation of the Runnable interface
  // --------------------------------------------------------------------------

  public final void run()
  {
	Scanner n =new Scanner(System.in);
	System.out.println("Por favor digite los puntos");
	long entrada = n.nextLong();
	System.out.println("Por favor digite la semilla que desea");
	int entradaSemilla = n.nextInt();
	System.out.println("Espere por favor");	
    System.out.println(calcularPi(entrada, entradaSemilla));
  }
}
