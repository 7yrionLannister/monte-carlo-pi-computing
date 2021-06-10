package org.ow2.frascati.calculopiv1JimenezMartinezFernandez;

import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;

import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Reference;

public class Cliente implements Runnable {
	private InterfazGrafica interfazGrafica;

	public long puntitosEstan(long numeros, int semilla) {
		Random r = new Random(semilla);

		long nPuntosDentroDelCirculo = 0;

		for (int i = 0; i < numeros; i++) {

			double x = r.nextDouble();
			double y = r.nextDouble();

			if ((x * x) + (y * y) <= 1) {

				nPuntosDentroDelCirculo++;
			}
		}
		return nPuntosDentroDelCirculo;
	}

	public double calcularPi(long puntosACalcular, int semilla) {

		long p = puntitosEstan(puntosACalcular, semilla);

		double pi = (4 * ((double) p) / (puntosACalcular));

		return pi;
	}

	// --------------------------------------------------------------------------
	// Implementation of the Runnable interface
	// --------------------------------------------------------------------------

	public final void run() {
		// configurar la interfaz grafica
		try {
			interfazGrafica = new InterfazGrafica();
			interfazGrafica.setLocationRelativeTo(null);
			interfazGrafica.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			interfazGrafica.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}


		Scanner n = new Scanner(System.in);
		System.out.println("Por favor digite los puntos");
		long entrada = n.nextLong();
		System.out.println("Por favor digite la semilla que desea");
		int entradaSemilla = n.nextInt();
		System.out.println("Espere por favor");
		System.out.println(calcularPi(entrada, entradaSemilla));
	}
}
