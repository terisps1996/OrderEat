package project.ordereat.Model;

public class Order {
    private int ID;
    private String productId;
    private String productName;
    private String Quantity;
    private String Price;
    private String Discount;
    private String Time;

    public Order() {
    }

    public Order(int ID, String productId, String productName, String quantity, String price, String discount) {
        this.ID = ID;
        this.productId = productId;
        this.productName = productName;
        Quantity = quantity;
        Price = price;
        Discount = discount;
    }

    public Order(String productId, String productName, String quantity, String price, String discount) {
        this.productId = productId;
        this.productName = productName;
        Quantity = quantity;
        Price = price;
        Discount = discount;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
