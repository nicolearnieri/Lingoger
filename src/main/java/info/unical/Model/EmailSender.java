package info.unical.Model;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.api.mailer.config.TransportStrategy;

public class EmailSender {
    private static final String username = "lingoger@libero.it";
    private static final String password = "ProgettoLingoger31!";

    public static void sendEmail(String recipient, String subject, String body) {
        // Creazione dell'oggetto Email utilizzando Simple Java Mail
        Email email = EmailBuilder.startingBlank()
                .from(username) // Indirizzo e-mail del mittente
                .to(recipient) // Indirizzo e-mail del destinatario
                .withSubject(subject) // Oggetto dell'e-mail
                .withPlainText(body) // Corpo dell'e-mail
                .buildEmail(); // Creazione dell'oggetto Email

        // Configurazione del Mailer per inviare l'e-mail utilizzando il server SMTP di libero
        Mailer mailer = MailerBuilder.withSMTPServer("smtp.libero.it", 587, username, password)
                .withTransportStrategy(TransportStrategy.SMTP_TLS) // Usa TLS per la crittografia
                .buildMailer();

        // Invio dell'e-mail
        mailer.sendMail(email);

        System.out.println("Email inviata con successo a " + recipient);
    }
}