package agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import modelos.Cliente;

public class Agente1 extends Agent {

    @Override
    protected void setup() { // Dar vida al Agente
        addBehaviour(new Comportamiento()); // Agregar el comportamiento al Agente.
    }

    @Override
    protected void takeDown() { // Matar al Agente
        super.takeDown();
    }

    //El comportamiento es una sub-clase "Comportamiento"
    class Comportamiento extends CyclicBehaviour {
        @Override
        public void action() { // Accion que realizara de forma acíclica.
            System.out.println(getName());
            Comunicacion.msj(ACLMessage.INFORM, getAgent(), "Ag2", null, new Cliente("Miguel", "Mendosa",
                    "miguel.mendosa@epn.edu.ec", "2300163678", 959662882, 21), "CD:Ag1->Ag2");
            blockingReceive();
        }
    }

}
