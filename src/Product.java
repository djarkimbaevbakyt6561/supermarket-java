public class Product {
    String productName;
    int price;
    int count;
    int id;
    public Product() {
    }

    public Product(String productName, int price, int count, int id) {
        this.productName = productName;
        this.price = price;
        this.count = count;
        this.id = id;
    }

    @Override
    public String toString() {
        return "ID: " + id + ". " + productName + ", цена: " + price + "с, " + "кол-во: " + count ;
    }
}
