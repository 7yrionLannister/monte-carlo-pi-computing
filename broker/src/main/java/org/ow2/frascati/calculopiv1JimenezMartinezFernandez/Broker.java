package org.ow2.frascati.calculopiv1JimenezMartinezFernandez;

import java.rmi.Naming;
import java.util.LinkedList;

import org.osoa.sca.annotations.Property;
import org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.ServicioComBroker;


public class Broker implements ServicioComBroker, Runnable {
	@Property
	private int blockSize;

	private static LinkedList<String> serverUris = new LinkedList<String>();
	private static LinkedList<String> clientUris = new LinkedList<String>();

	private long puntosRetorno;

	LinkedList<Server> servers = new LinkedList<Server>();
	
	public Broker() {
		
	}

	public synchronized long pedirPuntos(String uriCliente, int seed, long numPuntos) {
		/*
		try {
            Client sever =(Server)Naming.lookup(uriCliente);
            System.out.println("client :" + uriCliente);
            servers.add(sever);
            serverUris.add(uriCliente);
        } catch (Exception e) {
            System.out.println("error al hacer binding: " + uriCliente);
            e.printStackTrace();
        }*/
		return 0;
	}

	public synchronized void attachServicioGenerarPuntos(String serverUri) {
		try {
            Server sever =(Server)Naming.lookup(serverUri);
            System.out.println("server :" + serverUri);
            servers.add(sever);
            serverUris.add(serverUri);
        } catch (Exception e) {
            System.out.println("error al hacer binding: " + serverUri);
            e.printStackTrace();
        }
	}

	public synchronized void enviarPuntosACliente(long puntosRetorno) {
		this.puntosRetorno += puntosRetorno;
	}

	public synchronized void detachServicioGenerarPuntos(String serverUri) {
		int index = serverUris.indexOf(serverUri);
		String uriRemoved=serverUris.remove(index);
		servers.remove(index);
		assert(serverUri.equals(uriRemoved));			
	}

	public void run() {
		System.out.println("DASDSADSADSADAWD");
	}
}
