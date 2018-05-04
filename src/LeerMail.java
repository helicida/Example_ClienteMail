/**
 * Created by helicida on 24/02/16.
 */

import javax.mail.*;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by 46465442z on 24/02/16.
 */
public class LeerMail {

    private static Message[] messages;      // Array de message
    private static Properties properties;   // Properties

    public static void main(String[] args) throws IOException, MessagingException {

        // Ajustamos primero las properties
        properties = System.getProperties();
        properties.put("mail.pop.host", "pop.gmail.com");
        properties.put("mail.pop.port", "995");
        properties.put("mail.pop.starttls.enable", "true");

        // Credenciales de usuario
        String direccionCorreo = "poblenouDAM2sbarjola@gmail.com";   // Dirección de correo
        String contrasenyaCorreo = "pruebajavamail";                 // Contraseña

        // Mostramos los emails
        obtenerMensajes(direccionCorreo, contrasenyaCorreo);
    }

    public static void obtenerMensajes(String direccionCorreo, String contrasenyaCorreo) throws MessagingException, IOException {

        // abrimos sesion de email y le pasamos su datos
        Session emailSession = Session.getDefaultInstance(properties);
        Store store = emailSession.getStore("pop3s");
        store.connect("pop.gmail.com", direccionCorreo, contrasenyaCorreo);

        // Extraemos los correos de la carpeta Inbox
        Folder emailFolder = store.getFolder("INBOX");
        emailFolder.open(Folder.READ_ONLY);
        messages = emailFolder.getMessages();

        // Mostramos la bienvenida al usuario y el menú

        System.out.println("---------------------------------------------------------------------");
        System.out.println("-----------------------------LISTA MAILS-----------------------------");
        System.out.println("---------------------------------------------------------------------");

        System.out.println("Bienvenido: " + direccionCorreo);
        System.out.println("Tienes " + messages.length + " mensajes en tu bandeja de entrada");

        // Imprimimos los correos de forma ordenada
        imprimirMensajes();

        // Cerramos conexion
        emailFolder.close(false);
        store.close();

    }

    public static void imprimirMensajes() throws MessagingException, IOException {
        for (int iterador = 0; iterador < messages.length; iterador++) {
            Message message = messages[iterador];   // Imprimimos todos los correos con un for

            System.out.println("\n---------------------------------------------------------------------");
            System.out.println((iterador + 1) + " - " + message.getSubject());
            System.out.println("De:                     " + message.getFrom()[0]);
            System.out.println("Cuerpo del coreo:       " + message.getContent().toString());
            System.out.println("---------------------------------------------------------------------");
        }
    }
}
