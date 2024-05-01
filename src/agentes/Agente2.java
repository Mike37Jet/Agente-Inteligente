package agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import modelos.Cliente;

public class Agente2 extends Agent {

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
            ACLMessage aclmsj = blockingReceive(); // Se mantiene bloqueado, despues recive un mesaje y lo guarda.
            Cliente cliente = null;
            try {
                // if(aclmsj.getSender().getName().equals("Ag1")){}
                if (aclmsj.getConversationId().equals("CD:Ag1->Ag2")) {
                    cliente = (Cliente) aclmsj.getContentObject();
                    cliente.setEdad(cliente.getEdad() + 1);
                    Comunicacion.msj(ACLMessage.REQUEST, getAgent(), "Ag5", null, cliente, "CD:Ag2->Ag5");
                } else if (aclmsj.getConversationId().equals("CD:Ag5->Ag2")) {
                    Comunicacion.msj(ACLMessage.REQUEST, getAgent(), "Ag4", null, cliente, "CD:Ag2->Ag4");
                } else if (aclmsj.getConversationId().equals("CD:Ag4->Ag2")) {
                    Comunicacion.msj(ACLMessage.REQUEST, getAgent(), "Ag4", null, cliente, "CD:Ag2->Ag4");
                }


            } catch (UnreadableException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
