package org.ow2.frascati.examples.helloworld.annotated;

import org.osoa.sca.annotations.Service;


@Service
public interface Client {
    void attach(String uri);
    double computePi(long totalPoints, long circlePoints);
}