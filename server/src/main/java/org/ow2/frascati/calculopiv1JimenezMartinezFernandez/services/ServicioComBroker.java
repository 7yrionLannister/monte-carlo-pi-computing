package org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services;

import org.ow2.frascati.calculopiv1JimenezMartinezFernandez.Server;
import org.osoa.sca.annotations.Service;


@Service
public interface ServicioComBroker {
    void attachServer(Server server);
    void enviarPuntosCliente(long puntosRetorno);
    void detachServer(Server server);
    
}
