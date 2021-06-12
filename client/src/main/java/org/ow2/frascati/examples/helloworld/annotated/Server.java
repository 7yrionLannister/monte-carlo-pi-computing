package org.ow2.frascati.examples.helloworld.annotated;
import java.rmi.Remote;
import java.rmi.RemoteException;

import org.osoa.sca.annotations.Service;

@Service
public interface Server <T extends Comparable<? super T>>extends Remote {
    long generateDots(int seed, long numPuntos) throws RemoteException;
}