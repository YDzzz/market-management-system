package Mode;

import javax.swing.*;
import java.awt.*;

public class SaleStruct extends Component {
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;

    private String s1;
    private String s2;
    private String s3;
    private String s4;

    public SaleStruct(String s1, String s2, String s3, String s4){
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
    }
    public SaleStruct(){};

    public static void main(String[] args) {
        JFrame frame = new JFrame("SaleStruct");
        frame.setContentPane(new SaleStruct("1", "2", "3", "3").panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        label1 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label2 = new JLabel();
        label1.setText(s1);
        label2.setText(s2);
        label3.setText(s3);
        label4.setText(s4);
    }
}
