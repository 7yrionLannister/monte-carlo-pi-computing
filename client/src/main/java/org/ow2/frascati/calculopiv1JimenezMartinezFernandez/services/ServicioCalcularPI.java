package org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services;

import org.osoa.sca.annotations.Service;


@Service
public interface ServicioCalcularPI {
    double recibirPuntos(long puntos, int semilla);
}
