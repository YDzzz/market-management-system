package Mode;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class Demo {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JList table = new JList();
        DefaultListModel model = new DefaultListModel();
        String[] columnIdentifiers = {"one", "two", "three"};//表头
        String[][] data = new String[5][3];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                if(j == 2) {
                    continue;
                }
                data[i][j] = "asd";
            }
        }
        table.setModel(model);
        table.add(new SaleStruct("1","1","1","1"));


        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);
        frame.setBounds(100, 100, 300, 200);
        frame.setVisible(true);
    }



}

class qListCellRendererButton implements ListCellRenderer {
    private SaleStruct saleStruct = null;

    public qListCellRendererButton(String s1, String s2, String s3, String s4){
        super();
        saleStruct = new SaleStruct(s1, s2, s3, s4);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        return saleStruct;
    }
}

class qListCellEditorButton extends DefaultCellEditor{

    private SaleStruct btn;
    public qListCellEditorButton(String s1, String s2, String s3, String s4) {
        super(new JTextField());
        //设置点击一次就激活，否则默认好像是点击2次激活。
        this.setClickCountToStart(1);
        btn = new SaleStruct(s1, s2, s3, s4);
    }
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return btn;
    }


}