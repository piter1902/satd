/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio3;

import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author goyo
 */
public class Ej3_ReceptorPasajeVuelo_Comportamiento extends SimpleBehaviour {

    public void action() {

        ACLMessage resp = myAgent.blockingReceive();
        if (resp != null) {
            if (resp.getPerformative() == ACLMessage.CONFIRM) {
                System.out.println("recibido");
                try {
                    Object obj = resp.getContentObject();
                    if (obj instanceof Ej3_PasajeVuelo) {
                        Ej3_PasajeVuelo pb = (Ej3_PasajeVuelo) obj;

                        System.out.println("Origen: " + pb.getOrigen());
                        System.out.println("Destino: " + pb.getDestino());
                        System.out.println("Tarifa: " + pb.getTipoTarifa());
                        System.out.println("Aerolinea: " + pb.getAerolinea());
                    }
                } catch (Exception ce) {
                    System.out.println("error " + ce.toString());
                }
            }
        }
    }
    
    public boolean done() {
        return true;
    }
}
