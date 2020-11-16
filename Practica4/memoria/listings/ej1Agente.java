package Ejercicio1;


import jade.core.Agent;

public class Ej1_Agente extends Agent {

    // Contador del agente
    private int contador;

    @Override
    protected void setup() {
        // El agente acepta un parametro
        contador = Integer.parseInt(getArguments()[0].toString());
        System.out.format("Hola, mi nombre es: %s . UUID: %s\n", getLocalName(), getAID().getName());
        addBehaviour(new ContadorExternoBehaviour(this));
        super.setup();
    }

    @Override
    protected void takeDown() {
        System.out.printf("El agente %s muere\n", getLocalName());
        super.takeDown();
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
}
