package org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services;

import org.osoa.sca.annotations.Service;


@Service
public interface ServicioComBroker {
    void aumentarNodosDeProcesamiento(int nodos);
    long pedirPuntos(int seed, long numPuntos, int numNodosIniciales);
    void attachServicioGenerarPuntos(ServicioGenerarPuntos ServicioGenerarPuntos);
    void enviarPuntosACliente(long puntosRetorno);
    void detachServicioGenerarPuntos(ServicioGenerarPuntos ServicioGenerarPuntos);
}
