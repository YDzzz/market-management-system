package Mode;

import Service.Sale;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class SaleList {
    private JPanel panel1;
    private JToolBar toolBar1;
    private JButton newButton;
    private JButton addButton;
    private JButton callBackButton;
    private JButton button1;
    private JButton button;
    private JLabel label;
    private JLabel label2;
    private JTable table1;
    private Object[][] op = null;
    private String[] title = new String[]{"ID", "SPECIE", "NUMBER", "COUNT"};

    DefaultTableModel defaultListModel =null;

    public SaleList(int a){
        ;
    }

    public JToolBar getToolBar1() {
        return toolBar1;
    }

    public JButton getNewButton() {
        return newButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getCallBackButton() {
        return callBackButton;
    }

    public JButton getButton1() {
        return button1;
    }

    public JButton getButton() {
        return button;
    }

    public JLabel getLabel() {
        return label;
    }

    public JLabel getLabel2() {
        return label2;
    }

    public JTable getTable1() {
        return table1;
    }

    public Object[][] getOp() {
        return op;
    }

    public String[] getTitle() {
        return title;
    }

    public DefaultTableModel getDefaultListModel() {
        return defaultListModel;
    }

    public float getPrice() {
        return price;
    }

    public float getDiscount() {
        return discount;
    }

    float price = 0;
    float discount = 0;

    public SaleList() {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MyFrame frame = new MyFrame("LoadingGUI");
                frame.setContentPane(new LoadingGUI().panel1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.closeFrame();
                frame.setVisible(true);
            }
        });
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SaleListAdd dialog = new SaleListAdd();
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                if (!dialog.getAnswer())return;
                //获得id和num
                String id = dialog.getId();
                int num = Integer.valueOf(dialog.getNum());
                //++
                try {
                    if(!Sale.add(id,num)) return;
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                label.setText(String.valueOf(discount += Sale.getDiscount()));
                label2.setText(String.valueOf(price += Sale.getPrice()-Sale.getDiscount()));
                op = new Object[Sale.saleList.size()][];
                for (int i = 0; i < op.length; i++) {
                    op[i] = new Object[4];
                    op[i][0] = Sale.saleList.get(i).id;
                    op[i][1] = Sale.saleList.get(i).Specie;
                    op[i][2] = Sale.saleList.get(i).num;
                    op[i][3] = Sale.saleList.get(i).price;
                }
                defaultListModel.setDataVector(op,title);
                table1.updateUI();
            }
        });

        callBackButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Sale.callBack() == false)return;
                label.setText(String.valueOf(discount -= Sale.getDiscount()));
                label2.setText(String.valueOf(price -=Sale.getPrice()-Sale.getDiscount()));
                op = new Object[Sale.saleList.size()][];
                for (int i = 0; i < op.length; i++) {
                    op[i] = new Object[4];
                    op[i][0] = Sale.saleList.get(i).id;
                    op[i][1] = Sale.saleList.get(i).Specie;
                    op[i][2] = Sale.saleList.get(i).num;
                    op[i][3] = Sale.saleList.get(i).price;
                }
                defaultListModel.setDataVector(op,title);
                table1.updateUI();

            }
        });
        callBackButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Sale.callBack() == false)return;
                label.setText(String.valueOf(discount -= Sale.getDiscount()));
                label2.setText(String.valueOf(price -=Sale.getPrice()-Sale.getDiscount()));
                op = new Object[Sale.saleList.size()][];
                for (int i = 0; i < op.length; i++) {
                    op[i] = new Object[4];
                    op[i][0] = Sale.saleList.get(i).id;
                    op[i][1] = Sale.saleList.get(i).Specie;
                    op[i][2] = Sale.saleList.get(i).num;
                    op[i][3] = Sale.saleList.get(i).price;
                }
                defaultListModel.setDataVector(op,title);
                table1.updateUI();
            }
        });

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Sale.upDate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        newButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sale.init();
                op = null;
                defaultListModel.setDataVector(op,title);
                table1.updateUI();
                label.setText("0");
                label2.setText("0");
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public static void main(String[] args) {
        MyFrame frame = new MyFrame("SaleList");
        frame.setContentPane(new SaleList().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        defaultListModel = new DefaultTableModel(op,title);
        table1 = new JTable(defaultListModel);
    }
}
