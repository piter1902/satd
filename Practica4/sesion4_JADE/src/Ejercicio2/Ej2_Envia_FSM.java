package Ejercicio2;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

// Documentation: https://jade.tilab.com/doc/api/jade/core/behaviours/FSMBehaviour.html
public class Ej2_Envia_FSM extends FSMBehaviour {

    // TODO: Change this to take it dynamically
    private final static String RECV_NAME = "Receiver";
    private static final String TEXT = "Text to send";
    private static final int EV_VE_RECIBIR_TEXTO = 0;
    private static final int EV_VE_ENVIAR_TEXTO = 1;
    private static final int EV_VE_FIN = 2;
    private final String enviar_numero = "Enviar numero";
    private final String enviar_texto = "Enviar texto";
    private final String recibir_texto = "Recibir texto";
    private final String fin = "Fin";
    private int contador;
    private AID receiver;


    public Ej2_Envia_FSM(Agent a, int nVeces) {
        super(a);
        contador = nVeces;
        declareStates();
        declareTransitions();
    }

    private void declareTransitions() {
        // Irá al estado de fin desde recibir (porque empieza enviando)
        registerTransition(enviar_numero, enviar_texto, EV_VE_ENVIAR_TEXTO);
        registerTransition(enviar_texto, recibir_texto, EV_VE_RECIBIR_TEXTO);
        registerTransition(recibir_texto, enviar_texto, EV_VE_ENVIAR_TEXTO);
        registerTransition(recibir_texto, fin, EV_VE_FIN);
    }

    private void declareStates() {
        // 1st state is send nVeces to receiver
        registerFirstState(new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.printf("Agente: %s . Estado inicial (Enviar numero)\n", myAgent.getLocalName());
                ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);
                receiver = new AID(RECV_NAME, AID.ISLOCALNAME);

                aclMessage.addReceiver(receiver);
                aclMessage.setContent(Integer.toString(contador));

                myAgent.send(aclMessage);
            }

            @Override
            public int onEnd() {
                return 0;
            }
        }, enviar_numero);

        registerState(new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.printf("Agente: %s . Estado: Enviar texto\n", myAgent.getLocalName());
                ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);
                aclMessage.addReceiver(receiver);
                aclMessage.setContent(TEXT);

                myAgent.send(aclMessage);
                contador--;
                System.out.printf("Agente %s . Enviado. Quedan %d envios", myAgent.getLocalName(), contador);
            }

            @Override
            public int onEnd() {
                return EV_VE_RECIBIR_TEXTO;
            }
        }, enviar_texto);

        registerState(new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.printf("Agente: %s . Estado: Recibir texto\n", myAgent.getLocalName());
                ACLMessage aclMessage = myAgent.blockingReceive();
                System.out.printf("Agente %s . Recibido: %s\n", myAgent.getLocalName(), aclMessage.getContent());
            }

            @Override
            public int onEnd() {
                // Tiene que hacer una transición más para recibir el texto.
                return contador == 0 ? EV_VE_FIN : EV_VE_ENVIAR_TEXTO;
            }
        }, recibir_texto);

        registerLastState(new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.printf("El agente emisor %s ha terminado", myAgent.getLocalName());
            }

            @Override
            public int onEnd() {
                return -1;
            }
        }, fin);
    }


}
