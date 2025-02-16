import java.util.*;

class Product {
    private static int lastProductId = 100;  
    private int productId;
    private String name;
    private int quantity;
    private double price;

    public Product(String name, int quantity, double price) {
        this.productId = ++lastProductId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void displayProduct() {
        System.out.println("🆔 Product ID: " + productId);
        System.out.println("📦 Name: " + name);
        System.out.println("📊 Quantity: " + quantity);
        System.out.println("💲 Price: ₹" + price);
        System.out.println("-------------------------");
    }
}

class Inventory {
    private static List<Product> productList = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== 🏪 Inventory Management System =====");
            System.out.println("1️⃣ Add Product");
            System.out.println("2️⃣ Remove Product");
            System.out.println("3️⃣ Update Product");
            System.out.println("4️⃣ View All Products");
            System.out.println("5️⃣ Search Product");
            System.out.println("6️⃣ Exit");
            System.out.print("🔹 Enter choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine();  

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    removeProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    viewAllProducts();
                    break;
                case 5:
                    searchProduct();
                    break;
                case 6:
                    System.out.println("✅ Exiting Inventory System. Thank you!");
                    return;
                default:
                    System.out.println("❌ Invalid choice! Enter between 1-6.");
            }
        }
    }

    public static void addProduct() {
        System.out.print("📦 Enter Product Name: ");
        String name = sc.nextLine();
        System.out.print("📊 Enter Quantity: ");
        int quantity = sc.nextInt();
        System.out.print("💲 Enter Price: ");
        double price = sc.nextDouble();
        Product product = new Product(name, quantity, price);
        productList.add(product);
        System.out.println("✅ Product Added Successfully! Product ID: " + product.getProductId());
    }

    public static void removeProduct() {
        Product product = findProduct();
        if (product != null) {
            productList.remove(product);
            System.out.println("✅ Product Removed Successfully!");
        }
    }

    public static void updateProduct() {
        Product product = findProduct();
        if (product != null) {
            System.out.print("📊 Enter New Quantity: ");
            int quantity = sc.nextInt();
            System.out.print("💲 Enter New Price: ");
            double price = sc.nextDouble();
            product.setQuantity(quantity);
            product.setPrice(price);
            System.out.println("✅ Product Updated Successfully!");
        }
    }

    public static void viewAllProducts() {
        if (productList.isEmpty()) {
            System.out.println("❌ No Products Available!");
            return;
        }
        System.out.println("\n===== 📋 Product List =====");
        for (Product p : productList) {
            p.displayProduct();
        }
    }

    public static void searchProduct() {
        Product product = findProduct();
        if (product != null) {
            product.displayProduct();
        }
    }

    private static Product findProduct() {
        System.out.print("🔢 Enter Product ID: ");
        int id = sc.nextInt();
        for (Product p : productList) {
            if (p.getProductId() == id) {
                return p;
            }
        }
        System.out.println("❌ Product Not Found!");
        return null;
    }
}
