package org.ow2.frascati.calculopiv1JimenezMartinezFernandez;

import java.util.Random;

import org.osoa.sca.annotations.Property;
import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Service;
import org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.ServicioGenerarPuntos;
import org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.ServicioComBroker;

@Service(Runnable.class)
public class Server implements ServicioGenerarPuntos, Runnable {

	@Property
	private String myServiceUri;

	@Reference(name = "servicioComBroker")
	private ServicioComBroker servicioComBroker;

	public Server(){
		System.out.println(myServiceUri);
	}

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
			servicioComBroker.detachServicioGenerarPuntos(myServiceUri);
		}
		
	}

	public void run() {
		System.out.println("Me lleva la que me trajo");
		System.out.println(myServiceUri);

		servicioComBroker.attachServicioGenerarPuntos(myServiceUri);
	}
}
