/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ferramentas.Fields;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author allan
 */
public class CpfCnpjUtils {

    private CpfCnpjUtils() {
    }

    /**
     * converte um numero de cnpj em texto sem formatação
     * <br>Este método não faz verificação da validação do cpf.
     *
     * @param cnpj
     * @return CNPJ String (99999999999999)
     */
    public static String cnpjToString(final long cnpj) {
        return CpfCnpjUtils.cnpjToString(String.valueOf(cnpj));
    }

    public static String cnpjToString(final String cnpj) {
        String value = cnpj.trim();

        if (value.length() < 14) {
            return leftPad(value, 14, "0");
        } else {
            return value;
        }
    }

    /**
     * converte um numero de CPF em texto sem formatação
     * <br>Este método não faz verificação da validação do cpf.
     *
     * @param String cpf - cpf
     * @return CPF String (99999999999)
     */
    public static String cpfToString(final long cpf) {
        return CpfCnpjUtils.cpfToString(String.valueOf(cpf));
    }

    public static String cpfToString(final String cpf) {
        String value = cpf.trim();

        if (value.length() < 11) {
            return leftPad(value, 11, "0");
        } else {
            return value;
        }
    }

    /**
     * Este método converte um numero de cpf ou cnpj em texto sem formatação;
     * <br>Este método faz uma verificação na validação do parm1 para
     * identificar o tipo de formatação.
     *
     * @param String cpfCnpj - cnpj ou cpf
     * @return String CPF(99999999999) ou CNPJ(99999999999999)
     */
    public static String cpfCnpjToString(final long cpfCnpj) {
        return CpfCnpjUtils.cpfCnpjToString(String.valueOf(cpfCnpj));
    }

    public static String cpfCnpjToString(final String cpfCnpj) {
        String value = cpfCnpj.trim();

        if (CpfCnpjUtils.isValidCpf(value)) {
            return CpfCnpjUtils.cpfToString(value);
        } else {
            return CpfCnpjUtils.cnpjToString(value);
        }
    }

    /**
     * Retorna uma string formatada em CNPJ
     * <br>Este método não faz verificação da validação do CNPJ
     *
     * @param String cnpj - o cnpj
     * @return String CNPJ formatada (99.999.999/9999-99)
     */
    public static String formatCnpj(final String cnpj) {
        String value = cnpj.trim();

        if (value.length() > 14) {
            return value;
        }

        value = leftPad(value, 14, "0");

        Matcher m = Pattern.compile("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})$").matcher(value);
        return m.replaceAll("$1.$2.$3/$4-$5");
    }

    public static String formatCnpj(final long cnpj) {
        return CpfCnpjUtils.formatCnpj(String.valueOf(cnpj));
    }

    /**
     * Retorna uma string formatada em CPF
     * <br>Este método não faz verificação da validação do CPF
     *
     * @param cpf - o cpf
     * @return String CPF formatada (999.999.999-99)
     */
    public static String formatCpf(final String cpf) {
        String value = cpf.trim();
        if (value.length() > 11) {
            return value;
        }

        value = leftPad(value, 11, "0");
        Matcher m = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})$").matcher(value);

        return m.replaceAll("$1.$2.$3-$4");
    }

    public static String formatCpf(final long cpf) {
        return CpfCnpjUtils.formatCpf(String.valueOf(cpf));
    }

    /**
     * Este método converte um long no formato de um cpf ou cnpj.
     * <br>O parâmetro deve ser um cpf ou cnpj valido para identificação do
     * mesmo na formatação,
     * <br>este método faz uma verificação da validação de um cpf ou cpnpj para
     * identificar a sua formatação.
     *
     * @param	cpfCnpj - o cnpj ou cpf
     * @return	String CPF(999.999.999-99) ou CNPJ(99.999.999/9999-99)
     */
    public static String formatCpfOrCnpj(final long cpfCnpj) {
        String value = Long.toString(cpfCnpj);
        if (CpfCnpjUtils.isValidCpf(value)) {
            return CpfCnpjUtils.formatCpf(value);
        } else {
            return CpfCnpjUtils.formatCnpj(value);
        }
    }

    /**
     * Valida um numero de CPF
     *
     * @param cpf - o numero do CPF
     * @return boolean.
     */
    public static boolean isValidCpf(final long cpf) {
        return isValidCpf(String.valueOf(cpf));
    }

    public static boolean isValidCpf(final String cpf) {
        String value = cpf.trim();
        if (value.length() < 11) {
            value = leftPad(value, 11, "0");
        } else if (value.length() > 11) {
            return false;
        }

        if (!value.equals("00000000000")) {
            int d1 = 0, d2 = 0;
            int digit1 = 0, digit2 = 0, rest = 0;
            int digit = 0;

            try {
                for (int n_Count = 1; n_Count < value.length() - 1; n_Count++) {
                    digit = Integer.valueOf(value.substring(n_Count - 1, n_Count)).intValue();
                    d1 = d1 + (11 - n_Count) * digit;
                    d2 = d2 + (12 - n_Count) * digit;
                }
                //Primeiro resto divis�o por 11.
                rest = (d1 % 11);
                if (rest < 2) {
                    digit1 = 0;
                } else {
                    digit1 = 11 - rest;
                }

                d2 += 2 * digit1;
                //Segundo resto divis�o por 11.
                rest = (d2 % 11);
                if (rest < 2) {
                    digit2 = 0;
                } else {
                    digit2 = 11 - rest;
                }

                String digitoValidador = value.substring(value.length() - 2, value.length());

                return digitoValidador.equals(String.valueOf(digit1).concat(String.valueOf(digit2)));
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    /**
     * Valida um numero de CNPJ
     *
     * @param cnpj - o numero CNPJ
     * @return boolean
     */
    public static boolean isValidCnpj(long cnpj) {
        return isValidCnpj(String.valueOf(cnpj));
    }

    public static boolean isValidCnpj(final String cnpj) {
        String value = cnpj.trim();
        if (value.length() < 14) {
            value = leftPad(value, 14, "0");
        } else if (value.length() > 14) {
            return false;
        }

        try {
            if (!value.equals("00000000000000")) {
                int sum = 0, digit = 0;
                String calc = value.substring(0, 12);
                char[] chrvalue = value.toCharArray();

                //Parte 1
                for (int i = 0; i < 4; i++) {
                    if (chrvalue[i] - 48 >= 0 && chrvalue[i] - 48 <= 9) {
                        sum += (chrvalue[i] - 48) * (6 - (i + 1));
                    }
                }

                for (int i = 0; i < 8; i++) {
                    if (chrvalue[i + 4] - 48 >= 0 && chrvalue[i + 4] - 48 <= 9) {
                        sum += (chrvalue[i + 4] - 48) * (10 - (i + 1));
                    }
                }

                digit = 11 - (sum % 11);
                calc += (digit == 10 || digit == 11) ? "0" : Integer.toString(digit);

                //Parte 2
                sum = 0;
                for (int i = 0; i < 5; i++) {
                    if (chrvalue[i] - 48 >= 0 && chrvalue[i] - 48 <= 9) {
                        sum += (chrvalue[i] - 48) * (7 - (i + 1));
                    }
                }

                for (int i = 0; i < 8; i++) {
                    if (chrvalue[i + 5] - 48 >= 0 && chrvalue[i + 5] - 48 <= 9) {
                        sum += (chrvalue[i + 5] - 48) * (10 - (i + 1));
                    }
                }

                digit = 11 - (sum % 11);
                calc += (digit == 10 || digit == 11) ? "0" : Integer.toString(digit);
                return value.equals(calc);
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Valida um CPF ou CNPJ.
     *
     * @param cpfCnpj - o numero do CNPJ ou CPF
     * @return boolean.
     */
    public static boolean isValidCpfOrCnpj(final String cpfCnpj) {
        if (cpfCnpj.length() <= 11) {
            if (isValidCpf(cpfCnpj)) {
                return true;
            } else {
                return isValidCnpj(cpfCnpj);
            }
        } else if (cpfCnpj.length() > 11) {
            return isValidCnpj(cpfCnpj);
        } else {
            return false;
        }
    }

    public static boolean isValidCpfOrCnpj(final long cpfCnpj) {
        return CpfCnpjUtils.isValidCpfOrCnpj(String.valueOf(cpfCnpj));
    }

    public static boolean isValidaCep(String cep) {
        if (cep.length() == 8) 
            cep = cep.substring(0, 5) + "-" + cep.substring(5, 8);

        return cep.matches("[0-9]{5}-[0-9]{3}");
    }
    
    public static String leftPad(String value,
            int size,
            String pad) {

        if (value.length() < size && !pad.isEmpty()) {
            return leftPad(pad.concat(value), size, pad);
        }
        return value;
    }
}