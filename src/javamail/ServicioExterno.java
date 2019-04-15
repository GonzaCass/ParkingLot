package javamail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class ServicioExterno {

    // Replace sender@example.com with your "From" address.
    // This address must be verified.
    static final String FROM = "gonzacastroolaizola@gmail.com";
    static final String FROMNAME = "Gonzalo";
	
    // Replace recipient@example.com with a "To" address. If your account 
    // is still in the sandbox, this address must be verified.
    static final String TO = "gonzacass@hotmail.com";
    
    // Replace smtp_username with your Amazon SES SMTP user name.
    static final String SMTP_USERNAME = "gonzacastroolaizola@gmail.com";
    
    // Replace smtp_password with your Amazon SES SMTP password.
    static final String SMTP_PASSWORD = "Gonzathekiller48";
    
    // The name of the Configuration Set to use for this message.
    // If you comment out or remove this variable, you will also need to
    // comment out or remove the header below.
    static final String CONFIGSET = "ConfigSet";
    
    // Amazon SES SMTP host name. This example uses the EE.UU. Oeste (Oregón) region.
    // See https://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html#region-endpoints
    // for more information.
    static final String HOST = "smtp.gmail.com";
    
    // The port you will connect to on the Amazon SES SMTP endpoint. 
    static final int PORT = 587;
    
    static final String SUBJECT = "test";
    
    static final String BODY = String.join(
    	    System.getProperty("line.separator"),
    	    "<h1>Servicio de Estacionamiento</h1>",
    	    "<p>Paga tu estadia por favor" 
    	    //"<a href='https://github.com/javaee/javamail'>Javamail Package</a>",
    	    //" for <a href='https://www.java.com'>Java</a>."
    	    //Para adjuntar un link
    	);

    public static void enviarMail (String asunto, String mail) {

        // Create a Properties object to contain connection configuration information.
    	Properties props = System.getProperties();
    	props.put("mail.transport.protocol", "smtp");
    	props.put("mail.smtp.port", PORT); 
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.auth", "true");

        // Create a Session object to represent a mail session with the specified properties. 
    	Session session = Session.getDefaultInstance(props);

 
        try
        { 
        	// Create a message with the specified information. 
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM,FROMNAME));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mail));
            msg.setSubject(asunto);
            msg.setContent(BODY,"text/html");
            
            // Add a configuration set header. Comment or delete the 
            // next line if you are not using a configuration set
            msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
                
            // Create a transport.
            Transport transport = session.getTransport();
                        
            // Send the message.
            System.out.println("Enviando");
            
            // Connect to Amazon SES using the SMTP username and password you specified above.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
        	
            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
            JOptionPane.showMessageDialog(null,"Email enviado!");
            transport.close();
        }
        catch (Exception ex) {
            System.out.println("El email no fue enviado.");
            System.out.println("Error message: " + ex.getMessage());
        }
     
    }
}
