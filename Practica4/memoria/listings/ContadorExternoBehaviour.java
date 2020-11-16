package Ejercicio1;

import jade.core.behaviours.CyclicBehaviour;

class ContadorExternoBehaviour extends CyclicBehaviour {
    private final Ej1_Agente agente;

    public ContadorExternoBehaviour(Ej1_Agente agente) {
        this.agente = agente;
    }

    @Override
    public void action() {
        System.out.format("Contador: %d", agente.getContador());
        agente.setContador(agente.getContador() - 1);
        if (agente.getContador() == 0) {
            // El agente se suicida
            agente.doDelete();
        }
    }
}
