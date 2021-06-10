package org.ow2.frascati.calculopiv1JimenezMartinezFernandez;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

		configurarEventos();
		Scanner n = new Scanner(System.in);
		System.out.println("Por favor digite los puntos");
		long entrada = n.nextLong();
		System.out.println("Por favor digite la semilla que desea");
		int entradaSemilla = n.nextInt();
		System.out.println("Espere por favor");
		System.out.println(calcularPi(entrada, entradaSemilla));
	}

	private void configurarEventos() {
		interfazGrafica.getBtncalcularPI().addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String seed = interfazGrafica.getTextFieldSemilla().getText().trim();
					int numNodos = Integer.parseInt(interfazGrafica.getTextFieldNumNodosProcesamiento().getText().trim());
					int numPuntos = Integer.parseInt(interfazGrafica.getTextFieldNumPuntos().getText().trim());
					if(!seed.isEmpty() && numNodos > 0 && numPuntos > 0) {
						// TODO llamar al metodo que entrega los puntos y cuando los entregue mandar ese arreglo al metodo que calcula pi aqui (debe ser modificado para recibir los puntos)
						// Y mostrar el resultado
						System.out.println("----------------------------");
						System.out.println("seed es " + seed);
						System.out.println("npuntos es " + numPuntos);
						System.out.println("nnodos es " + numNodos);
						System.out.println("----------------------------");
					}
				}
				
			}
		);
	}
}
