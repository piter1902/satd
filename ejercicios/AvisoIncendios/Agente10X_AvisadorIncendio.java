/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicios.AvisoIncendios;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

public class Agente10X_AvisadorIncendio extends Agent {

    protected void setup() {
        doWait(5000);
        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            System.out.println("Solicitando ayuda a varias estaciones de bomberos...");
            ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
            // Añadir todas las estaciones que se pasan como parametro
            // como receptoras del mensaje: ¿Como se separan los parametros a la entrada?

            // Las estaciones se pasan como parametros separados por ,
            for (Object o : args) {
                if (o instanceof String) {
                    // Tratamiento de las estaciones destino
                    // Se añaden los receptores a la lista de destinatarios (como nodos locales)
                    msg.addReceiver(new AID((String) o, AID.ISLOCALNAME));
                }
            }

            // Indicar que el protocolo de interaccion del mensaje de envio es de tipo
            // FIPA REQUEST
            // Source: https://github.com/mihaimaruseac/JADE-ARIA/blob/master/src/examples/protocols/FIPARequestInitiatorAgent.java
            msg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);

            // Cálculo de la distancia del fuego.
            double fuego = (Math.random() * 5);
            String message = "fuego a " + fuego + " kms";
            System.out.println(message);
            // Set content of the message
            msg.setContent(message);

            addBehaviour(new Agente10X_AvisadorIncendioManejador(this, msg));
        } else {
            // Tratamiento de que no se han pasado argumentos al agente.
            System.out.println("Especifique el nombre de al menos una estación de bomberos en el área");
        }

    }

    protected void takeDown() {
        System.out.println("El agente " + getLocalName() + " muere");
    }
}
