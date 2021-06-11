package org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services;

import org.osoa.sca.annotations.Service;


@Service
public interface ServicioGenerarPuntos {
    void generarPuntos(long puntos, int semilla);
}
