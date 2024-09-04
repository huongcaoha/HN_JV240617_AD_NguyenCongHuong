package ra.business.design;

import ra.business.entity.Product;
import ra.common.IMethod;

import java.util.List;

public class ProductService {
    public static void main(String[] args) {
        ProductFeature productFeature = new ProductFeature();
        while (true){
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                                        |                                        |                                        |");
            System.out.println("|            1. Add product              |         2. Display list product        |  3. Sort products by price descending  |");
            System.out.println("|                                        |                                        |                                        |");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
            System.out.println("|                              |                             |                                |                            |");
            System.out.println("|   4. Delete product by id    |      5. Search by name      |     6. Edit product by id      |           7. Back          |");
            System.out.println("|                              |                             |                                |                            |");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            int choice = IMethod.getNumber("Enter your choice : ");
            switch (choice){
                case 1 : {
                    int number = IMethod.getNumber("Enter number product want add : ");
                    for(int i = 1 ; i <= number ; i++){
                        System.out.println("Enter product number : " +i);
                        Product product = new Product();
                        product.inputData(IMethod.scanner);
                        productFeature.save(product);
                    }
                    System.out.println("Add successfully " + number + " product !");
                    break;
                }
                case 2 : {
                    List<Product> products = IMethod.listProduct();
                    if(products.isEmpty()){
                        System.err.println("List product empty !");
                    }else {
                        System.out.println("****************************** LIST PRODUCTS ********************************");
                        System.out.println("┏━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━┓");
                        System.out.printf("| %-8s | %-26s | %-12s | %-27s | %-8s | %-27s | %-12s |\n","ID","Product Name","Price","Descriptions","Stock","Catalog Name","Status");
                        System.out.println("┗━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━┛");
                        for(Product pro : products){
                            pro.displayData();
                        }
                    }
                    break;
                }
                case 3 : {
                    List<Product> products = IMethod.listProduct();
                    List<Product> newProducts = products.stream().sorted((productA ,productB) -> productB.getProductPrice().compareTo(productA.getProductPrice())).toList();
                    System.out.println("List product after sort descending : ");
                    if(newProducts.isEmpty()){
                        System.err.println("List product empty !");
                    }else {
                        System.out.println("****************************** LIST PRODUCTS ********************************");
                        for(Product pro : newProducts){
                            pro.displayData();
                        }
                    }
                    break;
                }
                case 4 : {
                    String idProduct ;
                    while (true){
                        System.out.println("Enter id product to delete :");
                        idProduct = IMethod.scanner.nextLine().trim();
                        if(idProduct.isEmpty()){
                            System.err.println("Cannot be left blank !");
                        }else {
                            break;
                        }
                    }
                    productFeature.delete(idProduct);
                    break;
                }
                case 5 : {
                    List<Product> products = IMethod.listProduct();
                    String productName ;
                    while (true){
                        System.out.println("Enter product name to search :");
                        productName = IMethod.scanner.nextLine().trim();
                        if(productName.isEmpty()){
                            System.err.println("Cannot be left blank !");
                        }else {
                            break;
                        }
                    }
                    String finalProductName = productName;
                    List<Product> rs = products.stream().filter(product -> product.getProductName().contains(finalProductName)).toList();
                    if(rs.isEmpty()){
                        System.err.println("Not found product with name : " + productName);
                    }else {
                        System.out.println("****************************** RESULT SEARCH ********************************");
                        for(Product pro : rs){
                            pro.displayData();
                        }

                    }

                    break;
                }
                case 6 : {
                    List<Product> products = IMethod.listProduct();
                    String idProduct ;
                    while (true){
                        System.out.println("Enter id product to update :");
                        idProduct = IMethod.scanner.nextLine().trim();
                        if(idProduct.isEmpty()){
                            System.err.println("Cannot be left blank !");
                        }else {
                            break;
                        }
                    }
                    int index = products.stream().map(Product::getProductId).toList().indexOf(idProduct);
                    if(index == -1){
                        System.err.println("Not found product !");
                    }else {
                        products.get(index).updateData(IMethod.scanner);
                        IMethod.saveData(IMethod.fileProduct,products);
                        System.out.println("Update product successfully !");
                    }
                    break;
                }
                case 7 : {
                    return;
                }
                default: {
                    System.err.println("Enter choice from 1 to 7 !");
                }

            }
        }
    }
}
