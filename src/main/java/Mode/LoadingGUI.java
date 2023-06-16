package Mode;

import Service.Loading;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;


public class LoadingGUI {
    public JPanel panel1;
    private JButton Button3;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JCheckBox CheckBox;
    private JCheckBox CheckBox2;
    private JButton Button1;
    private JButton Button2;

    public JButton getButton1() {
        return Button1;
    }

    public LoadingGUI(int a){
        ;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public JButton getButton3() {
        return Button3;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public JCheckBox getCheckBox() {
        return CheckBox;
    }

    public JCheckBox getCheckBox2() {
        return CheckBox2;
    }

    public JButton getButton2() {
        return Button2;
    }

    public LoadingGUI() {

        //登录
       Button1.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               String name = textField1.getText();
               String password = String.valueOf(passwordField1.getPassword());
               Loading.loading(name,password);
           }
       });



        //找回密码
        Button2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
            }
        });
       //注册账号
        Button3.addMouseListener(null);
    }


    private void createUIComponents() {
        Button2 = new JButton();
        Button2.setFocusPainted(false);
        Button2.setBorder(null);

        Button1 = new JButton();
        Button1.setFocusPainted(false);


        Button3 = new JButton();
        Button3.setFocusPainted(false);
        Button3.setBorder(null);

        textField1 = new JTextField();
        textField1.setBorder(null);

        passwordField1 = new JPasswordField();
        passwordField1.setBorder(null);

    }
}




