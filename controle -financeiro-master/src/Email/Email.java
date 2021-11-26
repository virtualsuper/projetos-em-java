/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;


import boletos.controllers.BoletosController;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;

/**
 *
 * @author SANTANA
 */
public class Email {
    
    public static void main(String [] args){ 
     
    String hostname = "smtp.gmail.com";
    final String username = "santana@virtualsuper.com.br";
    final String password = "S@nt4#21";
    
        HtmlEmail email = new HtmlEmail();
        email.setHostName(hostname);
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator(username, password));
      
        email.setDebug(true);
        
        Properties props = new Properties();
        props.setProperty(hostname, "smtp");
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.port", "" + 587);
        props.setProperty("mail.smtp.starttls.enable", "true");
     
    
    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

    try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("santana@virtualsuper.com.br"));    //EMAIL DO BENEFICIÁRIO
        message.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse("viniciossantanag@gmail.com"));                   //EMAIL DO SACADO
        message.setSubject("Sua Fatura E-judi!");
        message.setText("PFA");

        MimeBodyPart messageBodyPart = new MimeBodyPart();

        Multipart multipart = new MimeMultipart();

        messageBodyPart = new MimeBodyPart();
        String file =  "C:\\E-Judi\\Documentos\\Financeiro\\Boletos\\092021\\ANDRE PORTO\\2_125.pdf";
        String fileName = "2_125.pdf";
        DataSource source = new FileDataSource(file);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        System.out.println("Sending");

        Transport.send(message);

        System.out.println("Done");

    } catch (MessagingException e) {
        e.printStackTrace();
    }
    
    
    }
          public static String getUrl(String parametro) {
        String url = null;
        try {
            Properties arquivoConfiguracaoUrl = new Properties();
            arquivoConfiguracaoUrl.load(new FileInputStream(new File("C:/E-Judi/Configuracoes/Configuracao.txt")));
            url = arquivoConfiguracaoUrl.getProperty(parametro);
        } catch (IOException e) {
        }

        return url;
    }
}
