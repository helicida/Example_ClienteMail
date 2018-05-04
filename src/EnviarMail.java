/**
 * Created by helicida on 24/02/16.
 */

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by 46465442z on 24/02/16.
 */
public class EnviarMail {

    // Propiedades del cliente de correo
    private static Session session;         // Sesion de correo
    private static Properties properties;   // Propiedades de la sesion
    private static Transport transport;     // Envio del correo
    private static MimeMessage mensaje;     // Mensaje que enviaremos

    // Credenciales de usuario
    private static String direccionCorreo = "poblenouDAM2sbarjola@gmail.com";   // Dirección de correo
    private static String contrasenyaCorreo = "pruebajavamail";                 // Contraseña

    // Correo al que enviaremos el mensaje
    private static String destintatarioCorreo = "dremon.iespoblenou@gmail.com";

    public static void main(String[] args) throws MessagingException {

        // Ajustamos primero las properties
        properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        //Configuramos la sesión
        session = Session.getDefaultInstance(properties, null);

        enviarMensaje("Hola Dionis","Prueba cliente correo, buen fin de semana. Sergi Barola");
    }

    public static void enviarMensaje(String subject, String content) throws MessagingException {

        // Configuramos los valores de nuestro mensaje
        mensaje = new MimeMessage(session);
        mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destintatarioCorreo));
        mensaje.setSubject(subject);
        mensaje.setContent(content, "text/html");

        // Configuramos como sera el envio del correo
        transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", direccionCorreo, contrasenyaCorreo);
        transport.sendMessage(mensaje, mensaje.getAllRecipients());
        transport.close();

        // Mostramos que el mensaje se ha enviado correctamente
        System.out.println("--------------------------");
        System.out.println("Mensaje enviado");
        System.out.println("---------------------------");
    }
}

