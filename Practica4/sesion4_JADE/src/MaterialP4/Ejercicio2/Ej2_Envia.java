/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaterialP4.Ejercicio2;
import jade.core.Agent;

/**
 *
 * @author goyo
 */
public class Ej2_Envia extends Agent{
    protected void setup(){
        System.out.println("Hola, soy el agente " + getLocalName());
        
        addBehaviour(new Ej2_Envia_Behaviour());
       
    }
    protected void takeDown(){
        System.out.print("El agente " + getLocalName() + " muere");
    }
}
