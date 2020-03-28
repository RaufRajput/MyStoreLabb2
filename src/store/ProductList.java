package store;

import java.util.HashMap;

public class ProductList {
    private static HashMap<Integer, Product> products = new HashMap<>();

    public static HashMap<Integer, Product> getProducts() {
        return products;
    }

    public static void setProducts(HashMap<Integer, Product> products) {
        ProductList.products = products;
    }
}
