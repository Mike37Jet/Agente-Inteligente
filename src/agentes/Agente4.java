package agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import modelos.Cliente;

public class Agente4  extends Agent {
    boolean enviado = true;


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
                if(enviado){
                    cliente.setEdad(cliente.getEdad() + 1);
                    Comunicacion.msj(ACLMessage.REQUEST, getAgent(), "Ag2", null, cliente, "CD:Ag4->Ag2");
                    enviado = false;
                }else {
                    Comunicacion.msj(ACLMessage.INFORM, getAgent(), "Ag3", null, cliente, "CD:Ag4->Ag3");
                }


            } catch (UnreadableException e) {
                throw new RuntimeException(e);
            }
        }
    }
}