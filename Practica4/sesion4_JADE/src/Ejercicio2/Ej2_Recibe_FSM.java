package Ejercicio2;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

// Documentation: https://jade.tilab.com/doc/api/jade/core/behaviours/FSMBehaviour.html
public class Ej2_Recibe_FSM extends FSMBehaviour {

    private final String recibir_numero = "Recibir numero";
    private final String recibir_texto = "Recibir texto";
    private final String enviar_texto = "Enviar texto";
    private final String fin = "Fin";

    private final int EV_VE_RECIBIR_TEXTO = 0;
    private final int EV_VE_ENVIAR_TEXTO = 1;
    private final int EV_VE_FIN = 2;


    private AID receiver;
    private int contador;
    private String text;

    public Ej2_Recibe_FSM(Agent a) {
        super(a);
        declareStates();
        declareTransitions();
    }

    private void declareTransitions() {
        // Irá al estado de fin desde enviar (porque empieza recibiendo)
        registerTransition(recibir_numero, recibir_texto, EV_VE_RECIBIR_TEXTO);
        registerTransition(recibir_texto, enviar_texto, EV_VE_ENVIAR_TEXTO);
        registerTransition(enviar_texto, recibir_texto, EV_VE_RECIBIR_TEXTO);
        registerTransition(enviar_texto, fin, EV_VE_FIN);
    }

    private void declareStates() {
        // 1st state is send nVeces to receiver
        registerFirstState(new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.printf("Agente: %s . Estado inicial (Recibir numero)\n", myAgent.getLocalName());
                ACLMessage aclMessage = myAgent.blockingReceive();

                contador = Integer.parseInt(aclMessage.getContent());
                receiver = aclMessage.getSender();
            }

            @Override
            public int onEnd() {
                return EV_VE_RECIBIR_TEXTO;
            }
        }, recibir_numero);

        registerState(new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.printf("Agente: %s . Estado: Recibir texto\n", myAgent.getLocalName());
                ACLMessage aclMessage = myAgent.blockingReceive();
                if (text == null) {
                    text = aclMessage.getContent();
                }
                System.out.printf("Agente %s . Recibido: %s\n", myAgent.getLocalName(), aclMessage.getContent());
            }

            @Override
            public int onEnd() {
                return EV_VE_ENVIAR_TEXTO;
            }
        }, recibir_texto);

        registerState(new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.printf("Agente: %s . Estado: Enviar texto\n", myAgent.getLocalName());
                ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);
                aclMessage.addReceiver(receiver);
                aclMessage.setContent(text);

                myAgent.send(aclMessage);
                contador--;
                System.out.printf("Agente %s . Enviado. Quedan %d envios", myAgent.getLocalName(), contador);
            }

            @Override
            public int onEnd() {
                return contador == 0 ? EV_VE_FIN : EV_VE_RECIBIR_TEXTO;
            }
        }, enviar_texto);

        registerLastState(new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.printf("El agente receptor %s ha terminado", myAgent.getLocalName());
            }

            @Override
            public int onEnd() {
                return -1;
            }
        }, fin);
    }


}
