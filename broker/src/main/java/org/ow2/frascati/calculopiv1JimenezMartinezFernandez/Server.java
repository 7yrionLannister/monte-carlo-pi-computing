package org.ow2.frascati.calculopiv1JimenezMartinezFernandez;

import java.util.Random;

import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Scope;
import org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.ServicioGenerarPuntos;
import org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.ServicioComBroker;


@Scope("COMPOSITE")
public class Server <T extends Comparable<? super T>> implements ServicioGenerarPuntos <T> , Runnable {

	@Reference
	private ServicioComBroker servicioComBroker;

	public Server(){
		try {
			System.out.println("Corriendo el servidor");
			servicioComBroker.attachServicioGenerarPuntos("xxxxxxxxx");			
		} catch (Exception e) {
			servicioComBroker.detachServicioGenerarPuntos("xxxxxxxxx");
		}
	}

	@Override
	public void generarPuntos(long numeros, int semilla) {

		try{
				Random r = new Random(semilla);

			long nPuntosDentroDelCirculo = 0;

			for (int i = 0; i < numeros; i++) {

				double x = r.nextDouble();
				double y = r.nextDouble();

				if ((x * x) + (y * y) <= 1) {

					nPuntosDentroDelCirculo++;
				}
			}
			servicioComBroker.enviarPuntosACliente(nPuntosDentroDelCirculo);
		}catch(Exception e){
			servicioComBroker.detachServicioGenerarPuntos("asdsad");
		}
		
	}

	public void run() {
		// TODO Auto-generated method stub
		
	}
}
