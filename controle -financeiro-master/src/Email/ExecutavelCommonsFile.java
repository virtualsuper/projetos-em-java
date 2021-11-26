/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;
import static boletos.controllers.BoletosController.getUrl;
import java.io.File;  
import org.apache.commons.mail.EmailAttachment;  
import org.apache.commons.mail.EmailException;  
import org.apache.commons.mail.MultiPartEmail;  
import principal.models.TbBoletosGerados;

public class ExecutavelCommonsFile {  

  /**
    * @param args
    */  
  public static void main(String[] args) {  

        // Create the attachment  
          TbBoletosGerados parametroBoleto = new TbBoletosGerados();
        // Caminho do arquivo a ser enviado  
        String destino = (parametroBoleto.getContratoId().getDsDescricao());  
        
        File f = new File (destino);
                  
        EmailAttachment attachment = new EmailAttachment();  
        attachment.setPath(f.getPath()); // Obtem o caminho do arquivo  
        attachment.setDisposition(EmailAttachment.ATTACHMENT);  
        attachment.setDescription("Anexo");  
        attachment.setName(f.getName()); // Obtem o nome do arquivo  

        try {  
          // Create the email message  
          MultiPartEmail email = new MultiPartEmail();  
          email.setDebug(true);  
          email.setHostName("smtp.gmail.com");  
          email.setAuthentication("santana@virtualsuper.com.br","S@nt4#21");  
          email.setSSL(true);  
          email.addTo("santana@virtualsuper.com.br","viniciossantanag@gmail.com"); //pode ser qualquer email  
          email.setFrom("santana@virtualsuper.com.br"); //será passado o email que você fará a autenticação
          email.setSubject("Teste anexo");  
          email.setMsg("Teste anexo");  

          // add the attachment  
          email.attach(attachment);  

          // send the email  
          email.send();  
      } catch (EmailException e) {  
          e.printStackTrace();  
      }  
  }  

}