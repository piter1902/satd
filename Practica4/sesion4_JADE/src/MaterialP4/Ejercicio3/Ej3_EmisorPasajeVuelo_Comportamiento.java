/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaterialP4.Ejercicio3;

import jade.core.AID;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author goyo
 */
public class Ej3_EmisorPasajeVuelo_Comportamiento extends SimpleBehaviour {

    public void action() {
        Scanner tecla = new Scanner(System.in);

        System.out.println("Escriba el nombre del Agente Receptor: ");
        String agRecibe = tecla.next();
      
        Ej3_PasajeVuelo pa = new Ej3_PasajeVuelo();
        System.out.println("Escriba el origen: ");
        pa.setOrigen(tecla.next());
        System.out.println("Escriba el destino ");
        pa.setDestino(tecla.next());
        System.out.println("Escriba la tarifa");
        pa.setTipoTarifa(tecla.next());
        System.out.println("Escriba la aerolinea ");
        pa.setAerolinea(tecla.next());

        ACLMessage msg = new ACLMessage(ACLMessage.CONFIRM);

        AID aid = new AID(agRecibe, AID.ISLOCALNAME);
        msg.addReceiver(aid);
        try {
            msg.setContentObject(pa); 
        } catch (IOException ce) {
            System.out.println("error " + ce.toString());
        }

        this.myAgent.send(msg);
    }

    public boolean done() {
        return true;
    }

}
