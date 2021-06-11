package org.ow2.frascati.calculopiv1JimenezMartinezFernandez;

import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Scope;
import org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.ServicioGenerarPuntos;
import org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.ServicioCalcularPI;
import org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.ServicioComBroker;


@Scope("COMPOSITE")
public class Broker implements ServicioComBroker {
	@Reference
	private ServicioCalcularPI servicioCalcularPI;

	public Broker() {
		
	}

	public void aumentarNodosDeProcesamiento(int nodos) {
		// TODO Auto-generated method stub
		
	}

	public long pedirPuntos(int seed, long numPuntos, int numNodosIniciales) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void attachServicioGenerarPuntos(ServicioGenerarPuntos ServicioGenerarPuntos) {
		// TODO Auto-generated method stub
		
	}

	public void enviarPuntosACliente(long puntosRetorno) {
		// TODO Auto-generated method stub
		
	}

	public void detachServicioGenerarPuntos(ServicioGenerarPuntos ServicioGenerarPuntos) {
		// TODO Auto-generated method stub
		
	}
}
