/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicios.AvisoIncendios;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;


public class Agente10X_AvisadorIncendioManejador extends AchieveREInitiator {

    public Agente10X_AvisadorIncendioManejador(Agent a, ACLMessage msg) {
        super(a, msg);
    }

    // handleAgree -> La estación de bomberos ha respondido
    protected void handleAgree(ACLMessage agree) {
        System.out.println("La estacion de bomberos " + agree.getSender().getName()
                + " informa que ha salido a apagar el fuego.");
    }

    // handleRefuse -> La estacion de bomberos esta demasiado lejos
    @Override
    protected void handleFailure(ACLMessage failure) {
        System.out.printf("La estación de bomberos %s no existe o tiene algún fallo. Razón: %s\n", failure.getSender().getName(), failure.getContent());
        super.handleFailure(failure);
    }

    // handleNotUnderstood -> La estacion de bomberos no entiende el mensaje
    @Override
    protected void handleInform(ACLMessage inform) {
        System.out.printf("La estación de bomberos %s informa: %s \n", inform.getSender().getName(), inform.getContent());
        super.handleInform(inform);
    }

    // handleInform -> La estacion de bomberos indica que ha apagado el fuego
    @Override
    protected void handleNotUnderstood(ACLMessage notUnderstood) {
        System.out.printf("La estación de bomberos %s envió un mensaje incomprensible. Mensaje: %s\n",
                notUnderstood.getSender().getName(), notUnderstood.getContent());
        super.handleNotUnderstood(notUnderstood);
    }

    // handleFailure -> La estacion de bomberos no existe o tiene algun fallo
    @Override
    protected void handleRefuse(ACLMessage refuse) {
        System.out.printf("La estación de bomberos %s está demasiado lejos. Razón: %s\n",
                refuse.getSender().getName(), refuse.getContent());
        super.handleRefuse(refuse);
    }
}
