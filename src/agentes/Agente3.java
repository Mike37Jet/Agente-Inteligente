package agentes;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;
import modelos.Cliente;

public class Agente3 extends Agent {
    Cliente cliente;

    @Override
    protected void setup() { // Dar vida al Agente
        addBehaviour(new Comportamiento()); // Agregar el comportamiento al Agente.
    }

    @Override
    protected void takeDown() { // Matar al Agente
        AgentContainer contenedorPrincipal = (jade.wrapper.AgentContainer)getArguments()[0];
        try {
            contenedorPrincipal.createNewAgent("AgenteH", AgenteH.class.getName(), new Object[]{cliente, contenedorPrincipal, 1}).start();
        } catch (StaleProxyException e) {
            throw new RuntimeException(e);
        }
    }

    //El comportamiento es una sub-clase "Comportamiento"
    class Comportamiento extends Behaviour {
        @Override
        public void action() { // Accion que realizara de forma acíclica.

            try {
                ACLMessage aclmsj = blockingReceive();
                Cliente cliente = (Cliente) aclmsj.getContentObject();
                cliente.setEdad(cliente.getEdad() * 2);
                doDelete();
            } catch (UnreadableException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public boolean done() {
            return false;
        }
    }
}