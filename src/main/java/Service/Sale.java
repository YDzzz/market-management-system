package Service;

import WoShiShenLiLingHuaDeGou.ViewManager;

import java.sql.SQLException;

import java.util.ArrayList;

public class Sale {
    private static String url = null;
    private static String password = null;
    private static String userName = null;
    private static MyQueue<SaleListInform> callBackList = null;
    public static ArrayList<SaleListInform> saleList = null;
    private static float price = 0;
    private static float discount = 0;
    private static int available;

    /////////////////////////////////////////////////////////////////////////////////////



    public static float getPrice() {
        return price;
    }
    public static float getDiscount() {
        return discount;
    }

    private static ViewManager searchManager = null;

    static {
        url = DBInform.getUrl();
        password = DBInform.getPassword();
        userName = DBInform.getUserName();
        callBackList = new MyQueue<SaleListInform>(5);
        saleList = new ArrayList<SaleListInform>();
        try {
            searchManager = new ViewManager(url, userName,password,"INVENTORY");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static MyQueue<SaleListInform> getCallBackList() {
        return callBackList;
    }

    public static float setPrice(int num, float count) {
        Sale.price = num * count;
        return Sale.price;
    }
    public static float setDiscount(int num, float discount){
        Sale.discount = num * discount;
        return Sale.discount;
    }
    public static void init() {
        Sale.saleList.clear();
        while (!Sale.callBackList.isEmpty())
        Sale.callBackList.get();
    }

    public static String search(String id) throws SQLException {
        searchManager.setSelectColumnNameSql("ID", "SPECIES", "DISCOUNT", "PRICE", "AVAILABLE");
        searchManager.setSelectWhereSql("ID = '" + id + "'");
        ArrayList<String[]> select = searchManager.select();
        if (select.isEmpty()){
            Sale.price = 0;
            Sale.discount = 0;
            return "NULL";
        }
        Sale.price = Float.valueOf(select.get(0)[3]);
        Sale.discount = Float.valueOf(select.get(0)[2]);
        Sale.available = Integer.valueOf(select.get(0)[4]);
        return select.get(0)[1];
    }

    public static Boolean add(String id, int num) throws SQLException {
        String str = Sale.search(id);
        if(str.equals("NULL"))return false;
        for (int i = 0; i < Sale.saleList.size(); i++) {
            if (Sale.saleList.get(i).id.equals(id)){
                if (Sale.saleList.get(i).available < num){System.out.println("available is not enough"); return false;}
                Sale.setPrice(num, Sale.price);
                Sale.setDiscount(num, Sale.discount);
                SaleListInform s = new SaleListInform(id, str,Sale.price,Sale.discount,num, Sale.available);
                callBackList.add(s);
                Sale.saleList.get(i).num += num;
                return true;
            }
        }
        Sale.setPrice(num, Sale.price);
        Sale.setDiscount(num, Sale.discount);
        SaleListInform s = new SaleListInform(id, str,Sale.price,Sale.discount,num, Sale.available - num);
        callBackList.add(s);
        if (Sale.available >= num){Sale.available -= num;}
        else {System.out.println("available is not enough"); return false;}
        Sale.saleList.add(s);
        return true;
    }

    public static Boolean callBack(){
        if (Sale.getCallBackList().isEmpty()){
            System.out.println("queue is empty");return false;}
        SaleListInform s = Sale.getCallBackList().get();
        for (int i = 0; i < Sale.saleList.size(); i++) {
            if (Sale.saleList.get(i).id.equals(s.id)){
                Sale.saleList.get(i).num -= s.num;
                Sale.saleList.get(i).available += s.num;
                if(Sale.saleList.get(i).num == 0){
                    Sale.saleList.remove(i);
                }
            }
        }
        Sale.price = s.price;
        Sale.discount = s.discount;
        return true;
    }

    public static void upDate() throws SQLException, ClassNotFoundException {
        for (SaleListInform saleListInform : saleList) {
            searchManager.setupdateSetSql("AVAILABLE = " + saleListInform.available);
            searchManager.setUpdateWhereSql("id = " + saleListInform.id);
            searchManager.change();
            System.out.println(searchManager.getUpdateSql());
        }


    }


}
