package agentes;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.wrapper.AgentContainer;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;
import modelos.Cliente;

public class AgenteH extends Agent {
    Cliente cliente;
    AgentContainer contenedorPrincipal;
    int alias;
    int contador = 1;

    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    @Override
    protected void takeDown() {
        if (contador > 10) {

        } else {
            try {
                contenedorPrincipal.createNewAgent("AgenteHijo" + alias, AgenteH.class.getName(), new Object[]{cliente, contenedorPrincipal, alias, contador}).start();
            } catch (StaleProxyException e) {
                throw new RuntimeException(e);
            }
        }
    }

    class Comportamiento extends Behaviour {
        @Override
        public void action() {

            cliente = (Cliente) getArguments()[0];
            cliente.setEdad(cliente.getEdad() + 1);
            contenedorPrincipal = (AgentContainer) getArguments()[1];
            alias = ((int) getArguments()[2]) + 1;
            contador = ((int) getArguments()[3]) + 1;
            System.out.println("Soy hijo: " + contador);
            System.out.println(getName());
            System.out.println(cliente.getEdad());
            doDelete();
        }

        @Override
        public boolean done() {
            return false;
        }
    }

}
