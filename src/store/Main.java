package store;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        try {
            File file = new File("store.txt");
            List<Product> purchasedProducts = new ArrayList<>();
            Scanner sc = new Scanner(file);
                while (sc.hasNextLine())
                {
                    String[] productString = sc.nextLine().split(",");
                    Product product = new Product(Integer.parseInt(productString[0]),
                            productString[1], productString[2], Integer.parseInt(productString[3]),
                            Double.valueOf(productString[4]),
                            new Category(productString[5], productString[6], productString[7]));
                    ProductList.getProducts().put(product.getId(), product);
                }
                sc = new Scanner(System.in);
                while(true){
                    System.out.println("Welcome to Portal:\nWhat would you like to do, Please select your preference?\n\n");
                    System.out.println("1. Want to buy Product?\n2. Want to add product?\n3. Want to change Product?\n4. Show Products?\n5.Exit ?");
                    int selection = sc.nextInt();
                    int productId;
                    switch (selection){
                        case 1:
                            showData();
                            System.out.println("Please Enter Product Id:");
                            sc = new Scanner(System.in);
                            productId = sc.nextInt();
                            Product product = showSingleProduct(productId);
                            if(product.getQuantity() < 1){
                                System.out.println("Stock Not available, Please select different product.");
                            }
                            else {
                                System.out.println("Are You Sure, You want to buy product '"+ product.getName() + '\'' + "?\n 1. Yes\n2. No");
                                int result = sc.nextInt();
                                if(result == 1){
                                    product.setQuantity(product.getQuantity() - 1);
                                    ProductList.getProducts().replace(productId, product);
                                    purchasedProducts.add(product);
                                    showMessage("Congratulations! you have successfully purchased product '"+ product.getName() + '\''+ "and your amount will be "+ product.getPrice());
                                }
                            }

                            break;
                        case 2:
                            System.out.println("Please Enter Product Details in comma seperated form:\nProduct details should be id, type, name, quantity, price, category name, category color, category description.");
                            sc = new Scanner(System.in);
                            String newProduct = sc.nextLine();
                            addProduct(newProduct);
                            showMessage("Product added Successfully");
                            break;
                        case 3:
                            System.out.println("Please Enter Product Id:");
                            sc = new Scanner(System.in);
                            productId = sc.nextInt();
                            showSingleProduct(productId);
                            System.out.println("Please Enter Product Details to change in comma seperated form:\nProduct details should be type, name, quantity, price, category name, category color, category description.");
                            sc = new Scanner(System.in);
                            String updateProduct = sc.nextLine();
                            boolean result = updateProduct(updateProduct, productId);
                            if(result){
                                showMessage("Product updated Successfully");
                            }
                            break;
                        case 4:
                            showData();
                            break;
                        case 5:
                            generateOutput(purchasedProducts);
                            showMessage("Thank you for visiting us. Your Receipt has been generated successfully.");
                            System.exit(0);
                            break;
                    }

                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found. Make sure to enter proper path.\n Thank you.");
            }

    }

    public static void showData(){
        HashMap<Integer, Product> sortedData = ProductList.getProducts().entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        sortedData.forEach((a, product) -> System.out.println(product));
    }

    public static Product showSingleProduct(int productId){
        Product product = new Product();
        if(ProductList.getProducts().containsKey(productId)){
            product = ProductList.getProducts().get(productId);
            System.out.println(product.showProduct());
        }
        return product;
    }

    public static void addProduct(String productDetail){
        String[] productString = productDetail.split(",");
        Product product = new Product(Integer.parseInt(productString[0]),
                productString[1], productString[2], Integer.parseInt(productString[3]),
                Double.valueOf(productString[4]),
                new Category(productString[5], productString[6], productString[7]));
        ProductList.getProducts().put(product.getId(), product);
    }

    public static boolean updateProduct(String productDetail, int productId){
        if(ProductList.getProducts().containsKey(productId)){
            String[] productString = productDetail.split(",");
            Product product = new Product(productId,
                    productString[0], productString[1], Integer.parseInt(productString[2]),
                    Double.valueOf(productString[3]),
                    new Category(productString[4], productString[5], productString[6]));
            ProductList.getProducts().replace(productId, product);
            return true;
        }
        else {
            System.out.println("Product Id was not valid.");
            return false;
        }

    }

    public static void showMessage(String message){
        System.out.println("**************************************************************************************");
        System.out.println(message);
        System.out.println("**************************************************************************************");
    }

    public static void generateOutput(List<Product> purchasedProducts){
        try (FileWriter fstream = new FileWriter("output.txt");
             BufferedWriter info = new BufferedWriter(fstream)) {

            info.write(String.format("Thank you for purchasing from our store.%n"));

            info.write(String.format("You have bought "+ purchasedProducts.size() + " products from our Store and your total amount is "+ purchasedProducts.stream().mapToDouble(Product::getPrice).sum()));
            info.newLine();
            info.write(String.format("Product details are:%n"));
            purchasedProducts.stream().forEach(product -> {
                try {
                    info.write(String.format(product.showProductForFile()));
                    info.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            info.write(String.format("Our all Products are:%n"));

            ProductList.getProducts().forEach((a, product) -> {

                try {
                    info.write(String.format(product.showProductForFile()));
                    info.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

            info.newLine();
            info.write(String.format("**************************************************************************************"));
            info.newLine();
            info.write(String.format("Thank you for visiting us"));
            info.newLine();
            info.write(String.format("**************************************************************************************"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
