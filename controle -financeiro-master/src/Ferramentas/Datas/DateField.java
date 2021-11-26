package Ferramentas.Datas;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import util.varios.Message;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JWindow;
import javax.swing.text.MaskFormatter;
import org.jdesktop.swingx.JXMonthView;
public class DateField extends JFormattedTextField {

    {
        format.setLenient(false);
    }
    private static final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    private MaskFormatter mask;
    // Container containerSize = null;
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension scrnsize = toolkit.getScreenSize();

    public DateField() {
        try {
            mask = new MaskFormatter("##/##/####");
            mask.setPlaceholderCharacter('_');
            mask.install(DateField.this);

            // containerSize = toolkit.getScreenSize(); 
        } catch (ParseException ex) {
            Logger.getLogger(DateField.class.getName()).log(Level.SEVERE, null, ex);
        }

        addFocusListener(new FocusListener() {
            private final JWindow window = new JWindow() {
                {
                    final JXMonthView m = new JXMonthView();
                    m.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            setDate(m.getSelectionDate());
                            DateField.this.transferFocus();
                        }
                    });
                    m.setTraversable(true);

                    add(new JScrollPane(m));
                    setFocusable(false);
                    pack();
                }
            };

            @Override
            public void focusGained(FocusEvent e) {
                select(0, 0);
                Point p = getLocationOnScreen();
                int x = p.x - 2, y = p.y + getSize().height;
                if (scrnsize != null) {
                    if (scrnsize.getWidth() - p.x < window.getWidth() + 20) {
                        x = p.x - (window.getHeight() - 33);
                    } else {
                        x = p.x - 2;
                    }
                    if (scrnsize.getHeight() - p.y < window.getHeight() + 65) {
                        y = p.y - window.getHeight();
                    } else {
                        y = p.y + getSize().height;
                    }
                }

                window.setLocation(x, y);
                window.setVisible(true);
            }

            @Override
            public void focusLost(FocusEvent e) {
                window.setVisible(false);
            }
        });

        setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                if (getText().equals("__/__/____") || (getText().replaceAll("_", "").length() == 10 && isDate())) {
                    return true;
                }
                JOptionPane.showMessageDialog(getRootPane(), "Preencha a data corretamente!");
                return false;
            }
        });

    }

    public void setInicialDate(final DateField date) {
        final InputVerifier i = getInputVerifier();
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (!i.verify(DateField.this) || isAfter(date)) {
                    return;
                }
                String erro;
                if (!date.isDate()) {
                    erro = "Preencha a data Inicial!";
                    date.grabFocus();
                } else {
                    erro = "A data deve ser superior ou igual a data inicial.";
                    grabFocus();
                    setText(null);
                }
                JOptionPane.showMessageDialog(getRootPane(), erro);
            }

//        date.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusLost(FocusEvent e) {
//                if(b || !date.isAfter(date)) return;
//                Message.show(getRootPane(), "A data deve ser inferior a data final.", Message.WARNING_MESSAGE);
//                b=true;
//                date.setText(null);
//                date.grabFocus();
//            }
//        });
        });
    }

    public Date getDate() {
        return parse(getText());
    }

    public void validaMaiorQHoje() {
        if (!getText().equals("__/__/____") && getDate().compareTo(new Date()) == 1) {
            JOptionPane.showMessageDialog(null, "A data é maior que a data atual.");
            this.requestFocus();
            setText("__/__/____");
        }
    }

    public void setDate(Date date) {
        setText(format(date));
    }

    public boolean isDate() {
        return getDate() != null;
    }

    private boolean isAfter(DateField date) {
        return date.isDate() && !getDate().before(date.getDate());
    }

    /**
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        return date == null ? "" : format.format(date);
    }

    /**
     * Formate a String and return a Date.
     *
     * @param date
     * @return Date
     */
    public static Date parse(String date) {
        try {
            return format.parse(date);
        } catch (ParseException ex) {
            return null;
        }
    }

}
