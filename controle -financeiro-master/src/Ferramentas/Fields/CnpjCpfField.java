package Ferramentas.Fields;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;


public class CnpjCpfField extends JFormattedTextField {
    public static  final String CPF = ("###.###.###-##"), CNPJ = "##.###.###/####-##", CEP = ("##.###-###");
    private  String inTipo = "";
    private  MaskFormatter mask;

    public CnpjCpfField() {
      //  setTypeComponent(CNPJ);
    }

    public void setTypeComponent(String tipo) {
        try {
            inTipo = tipo;
            mask = new MaskFormatter(inTipo);
            mask.setPlaceholderCharacter('_');
            mask.install(this);
        setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {

                switch (inTipo) {
                    case CNPJ: {
                        if (!getText().equals("__.___.___/____-__") && !isCNPJ()) {
                        //    Message.show(getRootPane(), "Preencha o CNPJ corretamente!", Message.WARNING_MESSAGE);
                            return false;
                        }
                        break;
                    }
                    case CPF: {
                        if (!getText().equals("___.___.___-__") && !isCPF()) {
                    //        Message.show(getRootPane(), "Preencha o CPF corretamente!", Message.WARNING_MESSAGE);
                            return false;
                        }
                        break;
                    }
                    case CEP: {
                        if (!getText().equals("__.___-___") && !isCEP()) {
                        //    Message.show(getRootPane(), "Preencha o CEP corretamente!", Message.WARNING_MESSAGE);
                            return false;
                        }
                        break;
                    }
                }
                return true;
            }
        });

        } catch (ParseException ex) {
            Logger.getLogger(CnpjCpfField.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getValue() {
        return getText().replace("/", "").replace("_", "").replace(".", "").replace("-", "");
    }

    public boolean isCPF() {
        return CpfCnpjUtils.isValidCpf(getValue());
    }

    public boolean isCNPJ() {
        return CpfCnpjUtils.isValidCnpj(getValue());
    }
    
    public boolean isCEP() {
        return CpfCnpjUtils.isValidaCep(getValue());
    }

}
