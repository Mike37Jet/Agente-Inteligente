package agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import modelos.Cliente;

public class Agente5 extends Agent {

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

            try {
                ACLMessage aclmsj = blockingReceive();
                Cliente cliente = (Cliente) aclmsj.getContentObject();
                cliente.setEdad(cliente.getEdad() + 1);
                Comunicacion.msj(ACLMessage.REQUEST, getAgent(), "Ag2", null, cliente, "CD:Ag5->Ag2");
            } catch (UnreadableException e) {
                throw new RuntimeException(e);
            }

        }
    }
}