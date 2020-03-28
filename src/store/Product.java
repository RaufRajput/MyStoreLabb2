package store;

import java.util.Objects;

public class Product {
    private int id;
    private String type;
    private String name;
    private int quantity;
    private double price;
    private Category category;

    public Product() {
    }

    public Product(int id, String type, String name, int quantity, double price, Category category) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                quantity == product.quantity &&
                Double.compare(product.price, price) == 0 &&
                Objects.equals(type, product.type) &&
                Objects.equals(name, product.name) &&
                Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, name, quantity, price, category);
    }

    @Override
    public String toString() {
        return  "\nid=" + id +
                "\ntype='" + type + '\'' +
                "\nname='" + name + '\'' +
                "\nquantity='" + quantity + '\'' +
                "\nprice='" + price + '\'' +
                category;
    }

    public String showProduct(){
        return "id=" + id +
                "\ntype='" + type + '\'' +
                "\nname='" + name + '\'' +
                "\nquantity='" + quantity + '\'' +
                "\nprice='" + price + '\'' +
                "\ncategory name='" + category.getCategoryName() + '\'' +
                "\ncategory color='" + category.getCategoryColor() + '\'' +
                "\ncategory description='" + category.getCategoryDescription() + '\'';

    }

    public String showProductForFile(){
        return "id=" + id + "%n" +
                "type='" + type + '\'' + "%n" +
                "name='" + name + '\'' + "%n" +
                "quantity='" + quantity + '\'' + "%n" +
                "price='" + price + '\'' + "%n" +
                "category name='" + category.getCategoryName() + '\'' + "%n" +
                "category color='" + category.getCategoryColor() + '\'' + "%n" +
                "category description='" + category.getCategoryDescription() + '\'' + "%n";

    }

    public String showSpecificInfo(){
        return  "type='" + type + '\'' + "%n" +
                "name='" + name + '\'' + "%n" +
                "price='" + price + '\'' + "%n" +
                "category name='" + category.getCategoryName() + '\'' + "%n" +
                "category color='" + category.getCategoryColor() + '\'' + "%n" +
                "category description='" + category.getCategoryDescription() + '\'' + "%n";

    }
}
