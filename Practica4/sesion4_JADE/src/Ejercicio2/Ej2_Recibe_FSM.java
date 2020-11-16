package Ejercicio2;

import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

// Documentation: https://jade.tilab.com/doc/api/jade/core/behaviours/FSMBehaviour.html
public class Ej2_Recibe_FSM extends FSMBehaviour {

    private final static String RECV_NAME = "Sender";
    private int contador;

    public Ej2_Recibe_FSM(Agent a) {
        super(a);
        // 1st state is send nVeces to receiver
        registerFirstState(new OneShotBehaviour() {
            @Override
            public void action() {
                ACLMessage aclMessage = myAgent.blockingReceive();

                contador = Integer.parseInt(aclMessage.getContent());
            }
        }, "Recibir número");

        registerState(new OneShotBehaviour() {
            @Override
            public void action() {

            }
        }, "Enviar texto");

        registerState(new OneShotBehaviour() {
            @Override
            public void action() {

            }
        }, "Recibir texto");

        registerLastState(new OneShotBehaviour() {
            @Override
            public void action() {

            }
        }, "Fin");
    }


}
