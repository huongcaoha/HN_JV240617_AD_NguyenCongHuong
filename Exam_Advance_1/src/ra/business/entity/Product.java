package ra.business.entity;

import ra.common.IMethod;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Product implements Serializable {
    private String productId ;
    private String productName ;
    private Double productPrice ;
    private String descriptions ;
    private Integer stock ;
    private Catalog catalog ;
    private Boolean status ;

    public Product() {
    }

    public Product(String productId, String productName, Double productPrice, String descriptions, Integer stock, Catalog catalog) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.descriptions = descriptions;
        this.stock = stock;
        this.catalog = catalog;
        this.status = true;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void inputData(Scanner scanner){
        List<Product> products = IMethod.listProduct();
        List<Catalog> catalogs = IMethod.listCatalog();
        inputProductId(scanner, products);
        inputProductName(scanner);
        inputPrice(scanner);
        inputDescriptions(scanner);
        inputStock(scanner);
        inputCatalog(scanner, catalogs);
        this.status = true ;

    }

    public void updateData(Scanner scanner){
        List<Product> products = IMethod.listProduct();
        List<Catalog> catalogs = IMethod.listCatalog();
        inputProductName(scanner);
        inputPrice(scanner);
        inputDescriptions(scanner);
        inputStock(scanner);
        inputCatalog(scanner, catalogs);
        this.status = true ;

    }

    private void inputCatalog(Scanner scanner, List<Catalog> catalogs) {
        while (true){
            for(Catalog cate : catalogs){
                cate.displayData();
            }
            int cateId ;
            try {
                System.out.println("Enter catalog id :");
                cateId = Integer.parseInt(scanner.nextLine().trim());
                int index = catalogs.stream().map(Catalog::getCatalogId).toList().indexOf(cateId);
                if(index == -1){
                    System.err.println("Not found catalog id !");
                }else {
                    this.catalog = catalogs.get(index);
                    break;
                }
            }catch (NumberFormatException e){
                System.err.println("Catalog id invalid !");
            }
        }
    }

    private void inputStock(Scanner scanner) {
        while (true){
            try {
                System.out.println("Enter stock : ");
                this.stock = Integer.parseInt(scanner.nextLine().trim());
                if(stock >= 10){
                    break;
                }else {
                    System.err.println("Enter stock >= 10 !");
                }
            }catch (NumberFormatException e){
                System.err.println("Number stock invalid !");
            }
        }
    }

    private void inputDescriptions(Scanner scanner) {
        while (true){
            System.out.println("Enter description : ");
            this.descriptions = scanner.nextLine().trim();
            if(descriptions.isEmpty()){
                System.err.println("Cannot be left blank !");
            }else {
                break;
            }
        }
    }

    private void inputPrice(Scanner scanner) {
        while (true){
            try {
                System.out.println("Enter price : ");
                this.productPrice = Double.parseDouble(scanner.nextLine().trim());
                if(productPrice > 0){
                    break;
                }else {
                    System.err.println("Enter price > 0 ! ");
                }
            }catch (NumberFormatException e){
                System.err.println("Price invalid !");
            }
        }
    }

    private void inputProductName(Scanner scanner) {
        while (true){
            System.out.println("Enter product name : ");
            this.productName = scanner.nextLine().trim();
            if(productName.isEmpty()){
                System.err.println("Cannot be left blank !");
            }else {
                break;
            }
        }
    }

    private void inputProductId(Scanner scanner, List<Product> products) {
        while (true){
            System.out.println("Enter product id (Start with letter P and add 4 numbers, no duplicates) : ");
            this.productId = scanner.nextLine().trim();
            if(productId.matches("^P[0-9]{4}$")){
                int index = products.stream().map(Product::getProductId).toList().indexOf(productId);
                if(index == -1) {
                    break;
                }else {
                    System.err.println("Product id existed !");
                }
            }else {
                System.err.println("Start with letter P and add 4 numbers, no duplicates !");
            }
        }
    }

    public void displayData(){
        NumberFormat formatter = NumberFormat.getInstance(Locale.GERMANY);
        System.out.println("┏━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━┓");
        System.out.printf("| %-8s | %-26s | %-12s | %-27s | %-8d | %-27s | %-12s |\n",productId,productName,formatter.format(productPrice)+"VNĐ",descriptions,stock,catalog.getCatalogName(),status ? "Sell" : "Not sell");
        System.out.println("┗━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━┛");

    }

}
