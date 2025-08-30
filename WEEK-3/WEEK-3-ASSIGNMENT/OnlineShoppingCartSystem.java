import java.util.Scanner;

public class OnlineShoppingCartSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create some sample products
        Product[] products = new Product[10];
        products[0] = new Product("P001", "Laptop", 55000, "Electronics", 5);
        products[1] = new Product("P002", "Smartphone", 30000, "Electronics", 10);
        products[2] = new Product("P003", "Headphones", 2000, "Electronics", 15);
        products[3] = new Product("P004", "Shirt", 1500, "Clothing", 20);
        products[4] = new Product("P005", "Jeans", 2500, "Clothing", 12);
        products[5] = new Product("P006", "Shoes", 3500, "Clothing", 8);
        products[6] = new Product("P007", "Book", 500, "Stationery", 30);
        products[7] = new Product("P008", "Pen", 50, "Stationery", 50);
        products[8] = new Product("P009", "Notebook", 200, "Stationery", 25);
        products[9] = new Product("P010", "Backpack", 1800, "Accessories", 10);

        // Create a shopping cart
        System.out.print("Enter your name: ");
        String customerName = sc.nextLine();
        ShoppingCart cart = new ShoppingCart("CART001", customerName);

        int choice;
        do {
            System.out.println("\n==== Online Shopping Cart Menu ====");
            System.out.println("1. View All Products");
            System.out.println("2. Search Product by ID");
            System.out.println("3. View Products by Category");
            System.out.println("4. Add Product to Cart");
            System.out.println("5. Remove Product from Cart");
            System.out.println("6. View Cart");
            System.out.println("7. Checkout");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("\n--- Product List ---");
                    for (Product p : products) {
                        p.displayProductInfo();
                    }
                    break;

                case 2:
                    System.out.print("Enter Product ID: ");
                    String pid = sc.nextLine();
                    Product found = Product.findProductById(products, pid);
                    if (found != null) found.displayProductInfo();
                    else System.out.println("Product not found!");
                    break;

                case 3:
                    System.out.print("Enter Category: ");
                    String category = sc.nextLine();
                    Product.getProductsByCategory(products, category);
                    break;

                case 4:
                    System.out.print("Enter Product ID to Add: ");
                    String addId = sc.nextLine();
                    Product prodToAdd = Product.findProductById(products, addId);
                    if (prodToAdd != null) {
                        System.out.print("Enter Quantity: ");
                        int qty = sc.nextInt();
                        sc.nextLine();
                        cart.addProduct(prodToAdd, qty);
                    } else {
                        System.out.println("Invalid Product ID!");
                    }
                    break;

                case 5:
                    System.out.print("Enter Product ID to Remove: ");
                    String removeId = sc.nextLine();
                    cart.removeProduct(removeId);
                    break;

                case 6:
                    cart.displayCart();
                    break;

                case 7:
                    cart.checkout();
                    break;

                case 0:
                    System.out.println("Exiting system. Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 0);

        sc.close();
    }
}

// ================= PRODUCT CLASS =================
class Product {
    private String productId;
    private String productName;
    private double price;
    private String category;
    private int stockQuantity;

    // Static variables
    private static int totalProducts = 0;
    private static String[] categories = {"Electronics", "Clothing", "Stationery", "Accessories"};

    // Constructor
    public Product(String productId, String productName, double price, String category, int stockQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
        totalProducts++;
    }

    // Instance methods
    public void displayProductInfo() {
        System.out.println("[" + productId + "] " + productName + " | Price: " + price +
                           " | Category: " + category + " | Stock: " + stockQuantity);
    }

    public String getProductId() {
        return productId;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void reduceStock(int quantity) {
        stockQuantity -= quantity;
    }

    public void increaseStock(int quantity) {
        stockQuantity += quantity;
    }

    // Static methods
    public static Product findProductById(Product[] products, String productId) {
        for (Product p : products) {
            if (p.productId.equals(productId)) {
                return p;
            }
        }
        return null;
    }

    public static void getProductsByCategory(Product[] products, String category) {
        System.out.println("\n--- Products in " + category + " ---");
        boolean found = false;
        for (Product p : products) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                p.displayProductInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No products found in this category!");
        }
    }
}

// ================= SHOPPING CART CLASS =================
class ShoppingCart {
    private String cartId;
    private String customerName;
    private Product[] products;
    private int[] quantities;
    private double cartTotal;
    private int productCount;

    // Constructor
    public ShoppingCart(String cartId, String customerName) {
        this.cartId = cartId;
        this.customerName = customerName;
        this.products = new Product[20]; // cart capacity
        this.quantities = new int[20];
        this.cartTotal = 0;
        this.productCount = 0;
    }

    // Add product
    public void addProduct(Product product, int quantity) {
        if (product.getStockQuantity() >= quantity) {
            products[productCount] = product;
            quantities[productCount] = quantity;
            productCount++;
            product.reduceStock(quantity);
            calculateTotal();
            System.out.println(quantity + " " + product.getProductId() + " added to cart.");
        } else {
            System.out.println("Not enough stock available!");
        }
    }

    // Remove product
    public void removeProduct(String productId) {
        for (int i = 0; i < productCount; i++) {
            if (products[i].getProductId().equals(productId)) {
                products[i].increaseStock(quantities[i]);
                System.out.println(products[i].getProductId() + " removed from cart.");
                // Shift elements left
                for (int j = i; j < productCount - 1; j++) {
                    products[j] = products[j + 1];
                    quantities[j] = quantities[j + 1];
                }
                productCount--;
                calculateTotal();
                return;
            }
        }
        System.out.println("Product not found in cart!");
    }

    // Calculate total
    public void calculateTotal() {
        cartTotal = 0;
        for (int i = 0; i < productCount; i++) {
            cartTotal += products[i].getPrice() * quantities[i];
        }
    }

    // Display cart
    public void displayCart() {
        System.out.println("\n--- Shopping Cart for " + customerName + " ---");
        if (productCount == 0) {
            System.out.println("Cart is empty.");
            return;
        }
        for (int i = 0; i < productCount; i++) {
            System.out.println(products[i].getProductId() + " - " + products[i].getPrice() +
                               " x " + quantities[i]);
        }
        System.out.println("Cart Total: " + cartTotal);
    }

    // Checkout
    public void checkout() {
        if (productCount == 0) {
            System.out.println("Cart is empty. Add products before checkout!");
            return;
        }
        System.out.println("\n--- Checkout Summary ---");
        displayCart();
        System.out.println("Thank you for shopping, " + customerName + "!");
        products = new Product[20]; // reset cart
        quantities = new int[20];
        productCount = 0;
        cartTotal = 0;
    }
}
