/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicios.AvisoIncendios;

import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Agente10X_EstacionBomberos extends Agent {
    public double distancia;

    public double getDistancia() {
        return distancia;
    }

    protected void setup() {
        distancia = (Math.random() * 10);
        System.out.println("Estacion " + getLocalName() + ": Pendiente de alarmas de incendio hasta " + distancia + " kms");
        // Crear un MessageTemplate de tipo FIPA_REQUEST;
        MessageTemplate protocolo =
                new MessageTemplate(aclMessage ->
                        aclMessage.getProtocol().equals(FIPANames.InteractionProtocol.FIPA_REQUEST));
        // Asignar una Performativa de tipo REQUEST al objeto MessageTemplate
        MessageTemplate performativa =
                MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        // Componer Plantilla con las anteriores (and)
        MessageTemplate plantilla = MessageTemplate.and(protocolo, performativa);

        addBehaviour(new Agente10X_EstacionBomberosManejador(this, plantilla));
    }

    protected void takeDown() {
        System.out.println("El agente " + getLocalName() + " muere");
    }
}
