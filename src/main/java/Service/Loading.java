package Service;

import Mode.MyFrame;
import Mode.PurcherGUI;
import Mode.SaleList;
import WoShiShenLiLingHuaDeGou.ViewManager;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class Loading {
    static String url = null;
    static String password = null;
    static String userName = null;
    static ViewManager vm = null;
    static String userType = null;

    static {
        url = DBInform.getUrl();
        password = DBInform.getPassword();
        userName = DBInform.getUserName();
        try {
            vm = new ViewManager(url,userName,password,"U_USER");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean check (String name, String password) throws SQLException {
        vm.setSelectColumnNameSql(null);;
        vm.setSelectWhereSql("NAME = '" + name + "'");
        ArrayList<String[]> arrayList= vm.select();
        if (!arrayList.isEmpty()){
            if (arrayList.get(0)[1].equals(password)){
                userType = arrayList.get(0)[2];
                return true;
            }
        }
        return false;
    }
    private static void loadPurcher(){
        MyFrame frame = new MyFrame("PurcherGUI");
        frame.setContentPane(new PurcherGUI().getPanel1());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.closeFrame();
    }
    private static void loadSale(){
        MyFrame frame = new MyFrame("SaleList");
        frame.setContentPane(new SaleList().getPanel1());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.closeFrame();
    }
    public static void loading(String name, String password){
        Loading loading = new Loading();
        try {
            if (check(name, password)) {
                switch (userType){
                    case ("salesman")->loadSale();
                    case ("storekeeper") ->loadPurcher();
                }
            }else {
                System.out.println("ERROR");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
