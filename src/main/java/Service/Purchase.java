package Service;

import WoShiShenLiLingHuaDeGou.ViewManager;

import java.sql.SQLException;
import java.util.ArrayList;

public class Purchase {
    private static String[] purchaseColumn = new String[]{"单号", "供应商", "批号", "货物名", "数量", "总金额", "状态"};
    private static String[] alertInventoryColumn = new String[]{"货物id", "物品名", "库存警告线", "库存", "状态"};
    private static Object[][] nullData1 = new String[][]{{"NULL","NULL","NULL","NULL","NULL", "NULL", "NULL"}};
    private static Object[][] nullData2 = new String[][]{{"NULL","NULL", "NULL", "NULL"}};
    public enum gui {purchaseGUI, inventoryGUI, returnGUI, applyGUI};
    private static gui currentGUI = null;

    public static void setCurrentGUI(gui currentGUI) {
        Purchase.currentGUI = currentGUI;
    }

    public static String[] getPurchaseColumn() {
        return purchaseColumn;
    }

    public static String[] getAlertInventoryColumn() {
        return alertInventoryColumn;
    }

    public static Object[][] getNullData1() {
        return nullData1;
    }

    public static Object[][] getNullData2() {
        return nullData2;
    }

    /**
     * meismyong
     * @return Boolean
     * */
    public static Boolean whetherFlush(gui a){
        if (a == currentGUI) return false;
        else {currentGUI = a; return true;}
    }
    public static String[][] sqlSelectPurchase() throws SQLException, ClassNotFoundException {
        String userName = DBInform.getUserName();
        String password = DBInform.getPassword();
        String url = DBInform.getUrl();
        ViewManager viewManager = new ViewManager(url, userName, password,"PURCHASE");
        viewManager.setSelectColumnNameSql(null);
        viewManager.setSelectWhereSql(null);
        ArrayList<String[]> list = viewManager.select();
        String[][] s= new String[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            s[i] = list.get(i);
        }
        return s;
    }
    public static Object[][] sqlSelectApply() throws SQLException, ClassNotFoundException {
        String userName = DBInform.getUserName();
        String password = DBInform.getPassword();
        String url = DBInform.getUrl();
        ViewManager viewManager = new ViewManager(url, userName, password,"PURCHASE");
        viewManager.setSelectColumnNameSql(null);
        viewManager.setSelectWhereSql("STATE = '申请中'");
        ArrayList<String[]> list = viewManager.select();
        Object[][] s= new String[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            s[i] = list.get(i);
        }
        return s;
    }
    public static String[][] sqlSelectReturn() throws SQLException, ClassNotFoundException {
        String userName = DBInform.getUserName();
        String password = DBInform.getPassword();
        String url = DBInform.getUrl();
        ViewManager viewManager = new ViewManager(url, userName, password,"PURCHASE");
        viewManager.setSelectColumnNameSql(null);
        viewManager.setSelectWhereSql("STATE = '退货中'");
        ArrayList<String[]> list = viewManager.select();
        String[][] s= new String[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            s[i] = list.get(i);
        }
        return s;
    }
    public static String[][] sqlSelectStockout() throws SQLException, ClassNotFoundException {
        String userName = DBInform.getUserName();
        String password = DBInform.getPassword();
        String url = DBInform.getUrl();
        ViewManager viewManager = new ViewManager(url, userName, password,"INVENTORY");
        viewManager.setSelectColumnNameSql("ID", "SPECIES", "AVAILABLE", "BASELINE", "I_MODE");
        viewManager.setSelectWhereSql("I_MODE in ('缺货', '无货')");
        ArrayList<String[]> list = viewManager.select();

        String[][] s= new String[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            s[i] = list.get(i);
        }
        return s;
    }
}
