package Ejercicio2;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

// Documentation: https://jade.tilab.com/doc/api/jade/core/behaviours/FSMBehaviour.html
public class Ej2_Envia_FSM extends FSMBehaviour {

    private final static String RECV_NAME = "Receiver";

    private final int contador;

    public Ej2_Envia_FSM(Agent a, int nVeces) {
        super(a);
        contador = nVeces;
        // 1st state is send nVeces to receiver
        registerFirstState(new OneShotBehaviour() {
            @Override
            public void action() {
                ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);
                AID recv = new AID(RECV_NAME, AID.ISLOCALNAME);

                aclMessage.addReceiver(recv);
                aclMessage.setContent(Integer.toString(nVeces));

                myAgent.send(aclMessage);
            }
        }, "Enviar numero");

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
