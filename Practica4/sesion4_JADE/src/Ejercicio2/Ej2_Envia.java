/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio2;

import jade.core.Agent;

/**
 * @author goyo
 */
public class Ej2_Envia extends Agent {
    protected void setup() {
        System.out.println("Hola, soy el agente " + getLocalName());
        int contador = Integer.parseInt(getArguments()[0].toString());

        addBehaviour(new Ej2_Envia_FSM(this, contador));

        //addBehaviour(new Ej2_Envia_Behaviour());

    }

    protected void takeDown() {
        System.out.print("El agente " + getLocalName() + " muere");
    }
}
