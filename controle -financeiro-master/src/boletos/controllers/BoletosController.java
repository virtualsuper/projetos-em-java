/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boletos.controllers;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import static org.jrimum.bopepo.exemplo.Exemplos.mostreBoletoNaTela;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import principal.models.TbBoletosGerados;
import principal.models.TbClienteContratos;

/**
 *
 * @author ANDRE PORTO
 */
public class BoletosController {

    public static void gerar(TbBoletosGerados parametroBoleto, File file) {

        String cnpjcpf = parametroBoleto.getContratoId().getClienteId().getNrCpfCnpj14().trim();
        String cpnp;
        if (cnpjcpf.length() == 11) {
            cpnp = cnpjcpf.substring(0, 3) + "." + cnpjcpf.substring(3, 6) + "." + cnpjcpf.substring(6, 9) + "-" + cnpjcpf.substring(9, 11);
        } else {
            cpnp = cnpjcpf.substring(0, 2) + "." + cnpjcpf.substring(2, 5) + "." + cnpjcpf.substring(5, 8) + "/" + cnpjcpf.substring(8, 12) + "-" + cnpjcpf.substring(12, 14);
        }
        //aterar o nome para nome da empresa
        Cedente cedente = new Cedente(parametroBoleto.getContratoId().getClienteId().getDsCliente(), cpnp);

        //Informando o endereço do sacado.
        Sacado sacado = new Sacado(parametroBoleto.getContratoId().getClienteId().getDsCliente(), "113.764.169-05");
        Endereco enderecoSac = new Endereco();
        //enderecoSac.setUF(UnidadeFederativa.valueOfSigla(parametroBoleto.getContratoId().getClienteId().getDsUf()));
        enderecoSac.setLocalidade(parametroBoleto.getContratoId().getClienteId().getDsMunicipio() + " - " + parametroBoleto.getContratoId().getClienteId().getDsUf());
        String cep = parametroBoleto.getContratoId().getClienteId().getDsCep();
        enderecoSac.setCep(cep.isEmpty() ? "" : (cep.substring(0, 5) + "-" + cep.substring(5, 8)));
        enderecoSac.setBairro(parametroBoleto.getContratoId().getClienteId().getDsBairro());
        enderecoSac.setLogradouro(parametroBoleto.getContratoId().getClienteId().getDsLogradouro());
        sacado.addEndereco(enderecoSac);

        // Informando dados sobre a conta bancária do título.
        ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_ITAU.create());
        contaBancaria.setNumeroDaConta(new NumeroDaConta(21046, "9"));
        contaBancaria.setCarteira(new Carteira(30));
        contaBancaria.setAgencia(new Agencia(0041));

        Calendar cal = Calendar.getInstance();
        cal.setTime(parametroBoleto.getDtVencimento());
        Titulo titulo = new Titulo(contaBancaria, sacado, cedente);
        titulo.setNumeroDoDocumento("123456");
        titulo.setNossoNumero("99345678");
        titulo.setDigitoDoNossoNumero("1");
        titulo.setDigitoDoNossoNumero("5");
        titulo.setValor(new BigDecimal(StringUtils.leftPad(parametroBoleto.getVlBoleto().toString().replaceAll("[.]", ""), 10, "0")));
        titulo.setDataDoDocumento(Calendar.getInstance().getTime());
        System.out.println(cal.getTime());
        titulo.setDataDoVencimento(cal.getTime());
        titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
        titulo.setAceite(Titulo.Aceite.A);
        titulo.setDesconto(new BigDecimal(0.05));
        titulo.setDeducao(BigDecimal.ZERO);
        titulo.setMora(BigDecimal.ZERO);
        titulo.setAcrecimo(BigDecimal.ZERO);
        titulo.setValorCobrado(BigDecimal.ZERO);

        Boleto boleto = new Boleto(titulo);
        boleto.setLocalPagamento("Pagável preferencialmente na Rede X ou em qualquer Banco até o Vencimento.");
        boleto.setInstrucaoAoSacado("Senhor sacado, sabemos sim que o valor cobrado não é o esperado, aproveite o DESCONTÃO!");
        boleto.setInstrucao1("PARA PAGAMENTO 1 até Hoje não cobrar nada!");
        boleto.setInstrucao2("PARA PAGAMENTO 2 até Amanhã Não cobre!");
        boleto.setInstrucao3("PARA PAGAMENTO 3 até Depois de amanhã, OK, não cobre.");

        //   Para gerar um boleto em PDF  
        File diretorio = new File(getUrl("financeiro") + System.getProperty("file.separator")
                + "Boletos" + System.getProperty("file.separator") + parametroBoleto.getCompetencia().replaceAll("/", "") + System.getProperty("file.separator") + parametroBoleto.getContratoId().getClienteId().getDsCliente()
                + System.getProperty("file.separator"));
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
        BoletoViewer boletoViewer = new BoletoViewer(boleto);
        File arquivoPdf = boletoViewer.getPdfAsFile(diretorio + System.getProperty("file.separator") + parametroBoleto.getContratoId().getId()
                + "_" + parametroBoleto.getId() + ".pdf");

       

        JFrame frame = new JFrame("JOptionPane exemplo");

    // Cria o JOptionPane por showMessageDialog
    int resposta = JOptionPane.showConfirmDialog(frame,"Deseja visualizar todos boletos gerados na tela?", "Atenção!", JOptionPane.YES_NO_OPTION);
    //verfica se a resposta é verdadeira
    if (resposta == JOptionPane.YES_OPTION) {
         mostreBoletoNaTela(arquivoPdf);
      }  
    

    }
    
    public static void EnviarEmail (TbBoletosGerados parametroBoleto) {
    
        String dir = (parametroBoleto.getDsUrl());
        File f = new File(dir);

        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(f.getPath()); // Obtem o caminho do arquivo
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("File");
        attachment.setName(f.getName()); // Obtem o nome do arquivo
        try {
            // Criando a mensagem de envio
            MultiPartEmail email = new MultiPartEmail();
            email.setDebug(true);
            email.setHostName("smtp.gmail.com");
            email.setAuthentication("santana@virtualsuper.com.br", "S@nt4#21");
            email.setSSL(true);
            System.out.println(parametroBoleto.getDsEamil());
            email.addTo(parametroBoleto.getDsEamil()); //pode ser qualquer um email
            email.setFrom("santana@virtualsuper.com.br"); //aqui necessita ser o email que voce fara a autenticacao
            email.setSubject("Teste Boletos E-judi!");
            email.setMsg("Olá, aqui está seu boleto referente ao nosso contrato! - DESCONSIDERAR, EMAIL APENAS PARA FINS DE TESTES");

            // add the attachment
            email.attach(attachment);

            // Enviando a mensagem
            email.send();
        } catch (EmailException e) {
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

    public static int CalcularFatorVencimento(Date dtFinal) {
        DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Date dtIni = null;
        // Date dtFim = null;
        long m1 = 0;
        long m2 = 0;
        //dtFim = dtFinal;
        try {
            dtIni = (Date) fmt.parse("03/07/2000");
            if (dtIni.compareTo(fmt.parse("22/02/2025")) >= 0) {
                dtIni = fmt.parse("22/02/2025");
            }
        } catch (Exception e) {
        }
        Calendar data1 = new GregorianCalendar();
        data1.setTime(dtIni);
        Calendar data2 = new GregorianCalendar();
        data2.setTime(dtFinal);
        m1 = data1.getTimeInMillis();
        m2 = data2.getTimeInMillis();
        BigDecimal t = new BigDecimal(m1);
        BigDecimal t2 = new BigDecimal(m2);
        BigDecimal t3 = new BigDecimal((24 * 60 * 60 * 1000));

        //Long resultado = ((m2 - m1) / (24 * 60 * 60 * 1000));
        return Integer.valueOf(String.valueOf(t2.subtract(t).divide(t3, RoundingMode.HALF_UP).add(new BigDecimal(1000))));

    }
}

//        
//        // <editor-fold defaultstate="collapsed" desc="Genera dac">      
//        StringBuilder dacCodBarras = new StringBuilder();
//        dacCodBarras.append("341");                                            //Código do Banco na Câmara de Compensação = '341'
//        dacCodBarras.append("9");                                             //Código da Moeda = '9'
//        //        a.insert(offset, null)append( ?);                           //DAC código de Barras (Anexo 2)
//        dacCodBarras.append(CalcularFatorVencimento(cal.getTime()));          //Fator de Vencimento (Anexo 6)
//        dacCodBarras.append(StringUtils.leftPad(parametroBoleto.getVlBoleto()
//        .toString().replaceAll("[.]", ""), 10, "0"));                         //Valor
//        dacCodBarras.append("109");                                           //Carteira
//        dacCodBarras.append(parametroBoleto.getNrNossoNumero());              //Nosso Número
//        dacCodBarras.append(parametroBoleto.getCdDac());                      //DAC [Agência /Conta/Carteira/Nosso Número] (Anexo 4)
//        dacCodBarras.append("0236");                                          //N.º da Agência BENEFICIÁRIO
//        dacCodBarras.append("83420");                                         //N.º da Conta Corrente
//        dacCodBarras.append("6");                                             //DAC [Agência/Conta Corrente] (Anexo 3)
//        dacCodBarras.append("000");                                           //Zeros
//        String dacCodigoBarras = DacCB(dacCodBarras.toString());
//        dacCodBarras.insert(4, dacCodigoBarras);                              //DAC código de Barras (Anexo 2)
//// </editor-fold>
//        b.setDsCodigoBarras(dacCodBarras.toString()); 
//
//        StringBuilder campo1 = new StringBuilder();
//        campo1.append("341");                                                 // Código do Banco na Câmara de Compensação (Itaú=341)
//        campo1.append("9");                                                   // Código da moeda = "9" (*)
//        campo1.append("109");                                                 // Código da carteira de cobrança
//        campo1.append(parametroBoleto.getNrNossoNumero().substring(0, 2));                     // Dois primeiros dígitos do Nosso Número
//        campo1.append(GerarDac(campo1.toString()));                           // DAC que amarra o campo 1 (Anexo3)
//
//        StringBuilder campo2 = new StringBuilder();
//        campo2.append(parametroBoleto.getNrNossoNumero().substring(2, 8));     / Restante do Nosso Número
//        campo2.append(GerarDac("023683420109" + parametroBoleto.getNrNossoNumero()));   // DAC do campo [Agência/Conta/Carteira/ Nosso Número)
//        campo2.append("023");                                                 // Três primeiros números que identificam a Agência
//        campo2.append(GerarDac(campo2.toString()));                           //DAC que amarra o campo 2 (Anexo 3)
//
//        StringBuilder campo3 = new StringBuilder();
//        campo3.append("6");                                                   // Restante do número que identifica a agência
//        campo3.append("834206");                                              // Restante do número que identifica a agência + DAC
//        campo3.append("000");                                                 // Zeros ( Não utilizado )
//        campo3.append(GerarDac(campo3.toString()));                           // DAC que amarra o campo 3 (Anexo3)
//
//        StringBuilder campo4 = new StringBuilder();
//        campo4.append(dacCodigoBarras);  // DAC do Código de Barras (Anexo 2
//
//        StringBuilder campo5 = new StringBuilder();
//        campo5.append(CalcularFatorVencimento(cal.getTime()));  // Fator de vencimento
//        campo5.append(StringUtils.leftPad(parametroBoleto.getVlBoleto().toString().replaceAll("[.]", ""), 10, "0"));  // Valor do Título (*)
//
//        b.setDsLinhaDigitavel(campo1.toString() + campo2.toString() + campo3.toString() + campo4.toString() + campo5.toString());
//        b.setDsCidadeBeneficiario("UMUARAMA-PR");
//
//        b.setDsInstrucao1("SUJEITO A PROTESTO SE NÃO FOR PAGO NO VENCIMENTO.");
//        b.setDsInstrucao2("INSTRUÇÕES DE RESPONSABILIDADE DO BENEFICIÁRIO. QUALQUER DÚVIDA SOBRE ESTE BOLETO,");
//        b.setDsInstrucao3("CONTATE O BENEFICIÁRIO.");
//        b.setNrDocumento(parametroBoleto.getNrNossoNumero());
//        b.setDsEspecieDocumento("DS");
//        b.setDsAceite("N");
//        b.setDtProcessamento(Calendar.getInstance().getTime());
//        b.setNrNossoNumero("109/" + parametroBoleto.getNrNossoNumero() + "-" + parametroBoleto.getCdDac());
//        b.setVlDocumento(parametroBoleto.getVlBoleto());
//        b.setDsPagador(parametroBoleto.getContratoId().getClienteId().getDsCliente());
//        String cnpjcpf = parametroBoleto.getContratoId().getClienteId().getNrCpfCnpj14().trim();
//        String cpnp;
//        if (cnpjcpf.length() == 11) {
//            cpnp = cnpjcpf.substring(0, 3) + "." + cnpjcpf.substring(3, 6) + "." + cnpjcpf.substring(6, 9) + "-" + cnpjcpf.substring(9, 11);
//        } else {
//            cpnp = cnpjcpf.substring(0, 2) + "." + cnpjcpf.substring(2, 5) + "." + cnpjcpf.substring(5, 8) + "/" + cnpjcpf.substring(8, 12) + "-" + cnpjcpf.substring(12, 14);
//        }
//        b.setNrCpfCnpjPagador(cpnp);
//        b.setDsCarteira("109");
//        b.setDsEspecieDinheiro("R$");
//        b.setDsLogradouroPagador(parametroBoleto.getContratoId().getClienteId().getDsLogradouro());
//        b.setDsBairroPagador(parametroBoleto.getContratoId().getClienteId().getDsBairro());
//        b.setDsCidadePagador(parametroBoleto.getContratoId().getClienteId().getDsMunicipio() + "-" + parametroBoleto.getContratoId().getClienteId().getDsUf());
//        String cep = parametroBoleto.getContratoId().getClienteId().getDsCep();
//        b.setDsCepPagador(cep.isEmpty() ? "" : (cep.substring(0, 5) + "-" + cep.substring(5, 8)));
//        lBoleto.add(b);
//
//        //   Para gerar um boleto em PDF  
//        GerarRelatorio r = new GerarRelatorio();
//        try {
//            String caminhoPasta = file + "/" + parametroBoleto.getCompetencia().replaceAll("/", "") + "/" + parametroBoleto.getContratoId().getDiaVencimento();
//            File diretorio = new File(caminhoPasta);
//            if (!diretorio.exists()) {
//                diretorio.mkdirs();
//            }
//            HashMap map = new HashMap();
//            InputStream imagem = GerarBoletos.class.getClass().getResourceAsStream("/imagens/itau.png");
//            InputStream imagem2 = GerarBoletos.class.getClass().getResourceAsStream("/imagens/itau.png");
//            map.put("imagem", imagem);
//            map.put("imagem2", imagem2);
//            r.pdf(caminhoPasta + "/" + parametroBoleto.getContratoId().getClienteId().getDsCliente()+"_"+parametroBoleto.getContratoId().getTipoBoletoId().getDsTipoBoleto().substring(0, 3)+ ".pdf", "/boletos/boleto.jasper", lBoleto, map);
//        } catch (Exception ex) {
//            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    public static String GerarDac(String chave) {
//        BigDecimal val = BigDecimal.ZERO;
//        String soma = "";
//
//        for (int x = 1; x <= chave.length(); x++) {
//            String ch = String.valueOf(chave.charAt(chave.length() - x));
//            if (x % 2 == 1) {
//                soma = soma + new BigDecimal(ch).multiply(new BigDecimal(2));
//            } else {
//                soma = soma + new BigDecimal(ch).multiply(new BigDecimal(1));
//            }
//
//        }
//
//        for (int x = 0; x < soma.length(); x++) {
//            String ch = String.valueOf(soma.charAt(x));
//            val = val.add(new BigDecimal(ch));
//
//        }
//        String vl = String.valueOf((val.doubleValue() % 10)).split("[.]")[0];
//
//        if (vl.equals("0")) {
//            return "0";
//        } else {
//            return String.valueOf(BigDecimal.TEN.subtract(new BigDecimal(vl)));
//        }
//
//    }
//    public static int CalcularFatorVencimento(Date dtFinal) {
//        DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
//        Date dtIni = null;
//        // Date dtFim = null;
//        long m1 = 0;
//        long m2 = 0;
//        //dtFim = dtFinal;
//        try {
//            dtIni = (Date) fmt.parse("03/07/2000");
//            if (dtIni.compareTo(fmt.parse("22/02/2025")) >= 0) {
//                dtIni = fmt.parse("22/02/2025");
//            }
//        } catch (Exception e) {
//        }
//        Calendar data1 = new GregorianCalendar();
//        data1.setTime(dtIni);
//        Calendar data2 = new GregorianCalendar();
//        data2.setTime(dtFinal);
//        m1 = data1.getTimeInMillis();
//        m2 = data2.getTimeInMillis();
//        BigDecimal t = new BigDecimal(m1);
//        BigDecimal t2 = new BigDecimal(m2);
//        BigDecimal t3 = new BigDecimal((24 * 60 * 60 * 1000));
//
//        //Long resultado = ((m2 - m1) / (24 * 60 * 60 * 1000));
//        return Integer.valueOf(String.valueOf(t2.subtract(t).divide(t3, RoundingMode.HALF_UP).add(new BigDecimal(1000))));
//
//    }
//
//    public static String DacCB(String chave) {
//        BigDecimal val = BigDecimal.ZERO;
//        String soma;
//        Integer digito = 2;
//        for (int x = 1; x <= chave.length(); x++) {
//            String ch = String.valueOf(chave.charAt(chave.length() - x));
//            val = val.add(new BigDecimal(ch).multiply(new BigDecimal(digito)));
//            if (digito == 9) {
//                digito = 2;
//            } else {
//                digito++;
//            }
//        }
//
//        String vl = String.valueOf((val.doubleValue() % 11)).split("[.]")[0];
//
//        soma = String.valueOf(new BigDecimal(11).subtract(new BigDecimal(vl)));
//
//        if (soma.equals("0") || soma.equals("1") || soma.equals("10") || soma.equals("11")) {
//            soma = "1";
//        }
//
//        return soma;
//    }
//}
