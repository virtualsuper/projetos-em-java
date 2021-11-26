package Email;

import boletos.controllers.BoletosController;
import static boletos.controllers.BoletosController.getUrl;
import java.io.File;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import principal.models.TbBoletosGerados;

public class JavaMailApp
{
  public static void main(String[] args) {
   
		  // Create the attachment
		    TbBoletosGerados parametroBoleto = new TbBoletosGerados();
                  // Caminho do arquivo a ser enviado
                  String diretorio = ("");
                  
		  File f = new File("C:\\E-Judi\\Documentos\\Financeiro\\Boletos\\092021\\VINICIOS SANTANA\\1_124.pdf" + diretorio); 
               
		  		  
		  EmailAttachment attachment = new EmailAttachment();
		  attachment.setPath(f.getPath()); // Obtem o caminho do arquivo
		  attachment.setDisposition(EmailAttachment.ATTACHMENT);
		  attachment.setDescription("File");
		  attachment.setName(f.getName()); // Obtem o nome do arquivo

		  try {
			// Create the email message
			MultiPartEmail email = new MultiPartEmail();
			email.setDebug(true);
			email.setHostName("smtp.gmail.com");
			email.setAuthentication("santana@virtualsuper.com.br","S@nt4#21");
			email.setSSL(true);
			email.addTo("santana@virtualsuper.com.br"); //pode ser qualquer um email
		        email.setFrom("santana@virtualsuper.com.br"); //aqui necessita ser o email que voce fara a autenticacao
			email.setSubject("The file");
			email.setMsg("The file");

			// add the attachment
			email.attach(attachment);

			// send the email
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

}