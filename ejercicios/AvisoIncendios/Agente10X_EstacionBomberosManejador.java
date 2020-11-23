/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicios.AvisoIncendios;

import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;

import java.util.StringTokenizer;

public class Agente10X_EstacionBomberosManejador extends AchieveREResponder {

    double distancia;

    public Agente10X_EstacionBomberosManejador(Agente10X_EstacionBomberos a, MessageTemplate mt) {
        super(a, mt);
        // Asignar la distancia del Agente que se pasa como parámetro a la parte privada
        this.distancia = a.getDistancia();
    }

    protected ACLMessage handleRequest(ACLMessage request) throws NotUnderstoodException, RefuseException {
        System.out.println("Estacion " + myAgent.getLocalName() + ": Hemos recibido una llamada de " + request.getSender().getName() + " diciendo que ha visto fuego.");
        StringTokenizer st = new StringTokenizer(request.getContent());
        // Recorremos el contenido del mensaje con el StringTokenizer
        String contenido = st.nextToken(); // fuego
        if (contenido.equalsIgnoreCase("fuego")) {
            // Coger el siguiente token y obtener el valor de la distancia
            // Se deben coger dos tokens ya que la cadena es de tipo "Fuego a <distancia> km"
            st.nextToken(); // a (skip value)
            contenido = st.nextToken(); // distancia
            if (Double.parseDouble(contenido) <= this.distancia) {
                // Se atiende la petición (AGREE)
                ACLMessage msg = new ACLMessage(ACLMessage.AGREE);
                msg.addReceiver(request.getSender());
                return msg;
            } else {
                // Si la distancia del incencio es mayor de la que puede atender
                // la estacion de bomberos -> RefuseException
                throw new RefuseException("La estación de bomberos está demasiado lejos.");
            }
        } else {
            // Si la primera palabra no es fuego... -> NotUnderstoodException
            throw new NotUnderstoodException("Estacion de bomberos ¿?-> no entiendo el mensaje.");
        }
    }

    protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response) throws FailureException {
        // Cálculo de probabilidad de apagar el incendio.
        if (Math.random() > 0.2) {
            // Informar de que se ha apagado el fuego
            ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
            aclMessage.addReceiver(request.getSender());
            aclMessage.setContent("Se ha apagado el fuego!");
            return aclMessage;
        } else {
            // lanzar una excepcion FailureException e indicar
            // que se han quedado sin agua
            throw new FailureException("La estación se ha quedado sin agua.");
        }
    }
}
