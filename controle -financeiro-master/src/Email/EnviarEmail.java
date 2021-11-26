/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 *
 * @author SANTANA
 */
public class EnviarEmail {
    
     public void enviarEmail(String emailCliente, List lemails, String titulo, String texto, String dsemail, String senha) throws MessagingException, EmailException {

        String[] emails = new String[lemails.size()];
        int x = 0;
        for (Object l : lemails) {
            emails[x] = (String) l;
            x++;
        }
        HtmlEmail mensagem = conectaEmail(dsemail, senha);
        System.out.println("email " + emailCliente);
        mensagem.addTo(emailCliente);
        mensagem.addCc(emails);
        mensagem.setSubject(titulo);
        mensagem.setHtmlMsg("<html><left> <p>" + texto + " </p><br/><br/> </html>");
        mensagem.buildMimeMessage();
        Message m = mensagem.getMimeMessage();
        Transport transport = mensagem.getMailSession().getTransport("smtp");
        transport.connect(mensagem.getHostName(), 587, null, null);
        transport.sendMessage(m, m.getAllRecipients());
    }
     
    private HtmlEmail conectaEmail(String dsemail, String senha) throws EmailException {
        String hostname = "smtp.gmail.com";
        String username = dsemail;
        String password = senha;
        //String password = "*45Vs2015@PrInspector";
        //inspector.virtualsuper@gmail.com
        HtmlEmail email = new HtmlEmail();
        email.setHostName(hostname);
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator(username, password));
        email.setFrom(dsemail, "Perguntas do Processo");
        email.setDebug(true);
        Properties props = new Properties();
        props.setProperty(hostname, "smtp");
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.port", "" + 587);
        props.setProperty("mail.smtp.starttls.enable", "true");
        Session mailSession = Session.getInstance(props, new DefaultAuthenticator(username, password));
        email.setMailSession(mailSession);
        return email;
    }
    
}
