/*
 * Generated by: org.ow2.frascati.tinfi.opt.oo.ServiceReferenceClassGenerator
 * on: Thu Jun 10 15:46:38 COT 2021
 */

package org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services;


public class ServicioCalcularPIFcSR
extends org.ow2.frascati.tinfi.oasis.ServiceReferenceImpl<org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.ServicioCalcularPI>
implements org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.ServicioCalcularPI {

  public ServicioCalcularPIFcSR(Class<org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.ServicioCalcularPI> businessInterface,org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.ServicioCalcularPI service)  {
    super(businessInterface,service);
  }

  public org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.ServicioCalcularPI getService()  {
    return this;
  }

  public double recibirPuntos(final long arg0,final int arg1)  {
    double ret = service.recibirPuntos(arg0,arg1);
    return ret;
  }

}
