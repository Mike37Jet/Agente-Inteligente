package agentes;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.io.Serializable;

public class Comunicacion {
    public static void msj(int tipoMSJ, Agent emisor, String receptor, String contenidoStr, Serializable contenidoObj, String conversacionID) {
        //ENVIAR MENSAJE
        ACLMessage acl = new ACLMessage(tipoMSJ);
        AID receptorID = new AID();
        receptorID.setLocalName(receptor);
        acl.addReceiver(receptorID); //Receptor
        acl.setSender(emisor.getAID()); //Emisor
        acl.setLanguage(FIPANames.ContentLanguage.FIPA_SL); //Fijar Lenguaje

        // La condicion permite enviar 2 tipos de mensaje 1 a la vez
        if (contenidoObj == null) {
            acl.setContent(contenidoStr);
        } else {
            try {
                acl.setContentObject(contenidoObj);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // Fijar id de la conversacion
        acl.setConversationId(conversacionID);
        // Enviar mensaje
        emisor.send(acl);

    }
}
