/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaterialP4;
import jade.core.Agent;

/**
 *
 * @author goyo
 */
public class Ej3_EmisorPasajeVuelo extends Agent{
    protected void setup(){
    addBehaviour( new Ej3_EmisorPasajeVuelo_Comportamiento());
    } 
    
    protected void takeDown(){
        System.out.println("El agente " + getLocalName() + " muere");
    }
}
