package Mode;

import javax.swing.*;
import java.awt.event.*;

public class SaleListAdd extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private String id = null;
    private String num = null;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private Boolean answer = null;

    public SaleListAdd(int a){
        ;
    }

    @Override
    public JPanel getContentPane() {
        return contentPane;
    }

    public JButton getButtonOK() {
        return buttonOK;
    }

    public JButton getButtonCancel() {
        return buttonCancel;
    }

    public JTextArea getTextArea1() {
        return textArea1;
    }

    public JTextArea getTextArea2() {
        return textArea2;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public String getId() {
        return id;
    }

    public String getNum() {
        return num;
    }

    public SaleListAdd() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        id = textArea2.getText();
        num = textArea1.getText();
        answer = true;
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        answer = false;
        dispose();
    }

    public static void main(String[] args) {
        SaleListAdd dialog = new SaleListAdd();
        dialog.pack();
        dialog.setVisible(true);
    }

}
