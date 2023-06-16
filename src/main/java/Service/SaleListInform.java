package Service;

public class SaleListInform {
    public String id;
    public String Specie;
    public float price;
    public float discount;
    public int num;
    public int available;

    public SaleListInform(String id, String specie, float price, float discount,int num, int available) {
        this.id = id;
        Specie = specie;
        this.price = price;
        this.discount = discount;
        this.num = num;
        this.available = available;
    }
    public SaleListInform(String id, String specie, float price, float discount,int num) {
        this.id = id;
        Specie = specie;
        this.price = price;
        this.discount = discount;
        this.num = num;
    }
}
