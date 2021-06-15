package org.ow2.frascati.examples.helloworld.annotated;

import org.osoa.sca.annotations.Service;


@Service
public interface Server {
    long generateDots(int seed, long numPuntos) throws Exception ;
}