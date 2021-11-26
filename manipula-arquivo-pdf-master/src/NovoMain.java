
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SANTANA
 */
public class NovoMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         try {
      // cria o reader para o pdf já criado
      PdfReader reader = new PdfReader("C:\\Users\\SANTANA\\Documents\\PDFS\\Anexo II Agrupado-001.pdf");
      // n recebe o numero total de páginas
      int n = reader.getNumberOfPages();
       System.out.println(n);
      // tamanho da primeira página
      Rectangle psize = reader.getPageSize(1);
      float width = psize.getHeight();
      float height = psize.getWidth();

      // crie o segundo pdf
      Document document = new Document(PageSize.A4.rotate());
      PdfWriter writer = PdfWriter.getInstance(document,
      new FileOutputStream("C:\\Users\\SANTANA\\Documents\\PDFS\\Anexo II Agrupado-00001.pdf"));
      document.open();
      // adiciona conteúdo ao segundo pdf
      PdfContentByte cb = writer.getDirectContent();
      int i = 0;
      int p = 0;
      while (i < n) {
         document.newPage();
         p++;
         i++;
         PdfImportedPage page1 = writer.getImportedPage(reader, i);
         cb.addTemplate(page1, 0, 0);
         BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
         cb.beginText();
         cb.setFontAndSize(bf, 7);
         cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "página " + p + " de " + n , height  -20, 15, 0);
         cb.endText();
      }
      document.close();
    }
    catch (Exception de) {
      de.printStackTrace();
    }
}
    }
    

