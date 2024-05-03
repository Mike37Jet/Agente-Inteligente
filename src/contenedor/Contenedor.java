package contenedor;

import agentes.*;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;

public class Contenedor {
    public void crearContenedor(String host, int port, String name) {
        jade.core.Runtime runtime = jade.core.Runtime.instance(); // crear una instancia, el hilo
        Profile p = new ProfileImpl(host, port, name); //crear perfil
        AgentContainer contenedorPrincipal = runtime.createMainContainer(p); //se le envió el perfil al contenedor
        crearAgente(contenedorPrincipal);
    }

    private void crearAgente(AgentContainer contenedorPrincipal) {
        try { //Añadir los agentes al contenedor.
            contenedorPrincipal.createNewAgent("Ag3", Agente3.class.getName(), new Object[]{contenedorPrincipal}).start();  // Se enviá el "Alias", "Nombre de la clase", "Conocimiento previo" y se Inicia el Hilo.
            contenedorPrincipal.createNewAgent("Ag4", Agente4.class.getName(), null).start();
            contenedorPrincipal.createNewAgent("Ag2", Agente2.class.getName(), null).start();
            contenedorPrincipal.createNewAgent("Ag5", Agente5.class.getName(), null).start();
            contenedorPrincipal.createNewAgent("Ag1", Agente1.class.getName(), null).start();
        } catch (StaleProxyException e) {
            throw new RuntimeException(e);
        }
    }

}
