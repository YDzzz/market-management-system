package Mode;

import Service.Purchase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import static Service.Purchase.gui.*;

public class PurcherGUI {
    public JPanel getPanel1() {
        return panel1;
    }
    private JPanel panel1;
    private JButton button1;
    private JTable table1;
    DefaultTableModel tModel;
    private JButton button;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JPanel panel2;
    private JScrollPane scrollPane1;

    String[] str = null;
    Object[][] playerInfo = null;

    public PurcherGUI(int a){
        ;
    }

    public JButton getButton1() {
        return button1;
    }

    public JTable getTable1() {
        return table1;
    }

    public DefaultTableModel gettModel() {
        return tModel;
    }

    public JButton getButton() {
        return button;
    }

    public JButton getButton2() {
        return button2;
    }

    public JButton getButton3() {
        return button3;
    }

    public JButton getButton4() {
        return button4;
    }

    public JButton getButton5() {
        return button5;
    }

    public JPanel getPanel2() {
        return panel2;
    }

    public JScrollPane getScrollPane1() {
        return scrollPane1;
    }

    public String[] getStr() {
        return str;
    }

    public Object[][] getPlayerInfo() {
        return playerInfo;
    }

    public PurcherGUI() {
        button5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MyFrame frame = new MyFrame("LoadingGUI");
                frame.setContentPane(new LoadingGUI().panel1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                frame.closeFrame();
            }
        });

        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(Purchase.whetherFlush(inventoryGUI)){
                    str = Purchase.getAlertInventoryColumn();
                    try {
                        playerInfo = Purchase.sqlSelectStockout();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    tModel.setDataVector(playerInfo,str);
                    table1.updateUI();
                    System.out.println("SUCCESS");
                }
            }
        });

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(Purchase.whetherFlush(purchaseGUI)){
                    str = Purchase.getPurchaseColumn();
                    try {
                        playerInfo = Purchase.sqlSelectPurchase();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    tModel.setDataVector(playerInfo,str);
                    table1.updateUI();
                    System.out.println("SUCCESS");
                }
            }
        });

        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(Purchase.whetherFlush(returnGUI)){
                    str = Purchase.getPurchaseColumn();
                    try {
                        playerInfo = Purchase.sqlSelectReturn();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    tModel.setDataVector(playerInfo,str);
                    table1.updateUI();
                    System.out.println("SUCCESS");
                }
            }
        });

        button4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(Purchase.whetherFlush(applyGUI)){
                    str = Purchase.getPurchaseColumn();
                    try {
                        playerInfo = Purchase.sqlSelectApply();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    tModel.setDataVector(playerInfo,str);
                    table1.updateUI();
                    System.out.println("SUCCESS");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PurchaserGUI");
        frame.setContentPane(new PurcherGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() throws SQLException, ClassNotFoundException {
        // TODO: place custom component creation code here

        str = Purchase.getPurchaseColumn();
        playerInfo = Purchase.sqlSelectPurchase();

        Purchase.setCurrentGUI(purchaseGUI);
        tModel = new DefaultTableModel(playerInfo,str);
        table1 = new JTable(tModel);
        button5 = new JButton();
        button5.setFocusPainted(false);
    }
}
