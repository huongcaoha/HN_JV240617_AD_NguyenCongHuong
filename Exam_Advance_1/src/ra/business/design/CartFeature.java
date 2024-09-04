package ra.business.design;

import ra.business.entity.CartItem;
import ra.business.entity.Product;
import ra.common.IMethod;

import java.util.ArrayList;
import java.util.List;

public class CartFeature {
    public static void displayCart(List<CartItem> cartItems){
        if(cartItems.isEmpty()){
            System.err.println("List product in cart empty !");
        }else {
            System.out.println("***************************** CARTS ****************************");
            System.out.println("┏━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━┳━━━━━━━━━━┓");
            System.out.printf("| %-8s | %-26s | %-12s | %-8s |\n","ID","Product Name","Price","Quantity");
            System.out.println("┗━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━┻━━━━━━━━━━┛");
            for(CartItem item : cartItems){
                item.displayData();
            }
        }
    }

    public static void displayListProducts(List<Product> products){
        if(products.isEmpty()){
            System.err.println("List product empty !");
        }else {
            System.out.println("************************************* LIST PRODUCTS ****************************************");
            System.out.println("┏━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━┓");
            System.out.printf("| %-8s | %-26s | %-12s | %-27s | %-8s | %-27s | %-12s |\n","ID","Product Name","Price","Descriptions","Stock","Catalog Name","Status");
            System.out.println("┗━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━┛");
            for(Product pro : products){
                if(pro.getStatus()){
                    pro.displayData();
                }
            }
        }
    }

    public static void addToCart(){
        List<Product> products = IMethod.listProduct();
        if(products.isEmpty()){
            System.err.println("List product empty !");
        }else {
            displayListProducts(IMethod.listProduct());
            List<CartItem> cartItems = IMethod.listCart();
            String idProduct ;
            int stock = 0 ;
            int indexCart = -1 ;
            int indexProduct = -1 ;
            while (true){
                System.out.println("Enter id product :");
                idProduct = IMethod.scanner.nextLine().trim();
                indexProduct = products.stream().map(Product::getProductId).toList().indexOf(idProduct);
                if(indexProduct == -1){
                    System.err.println("Not found id product !");
                }else {
                    stock = products.get(indexProduct).getStock();
                    indexCart = cartItems.stream()
                            .map(cartItem -> cartItem.getProduct().getProductName())
                            .toList().
                            indexOf(products.get(indexProduct).getProductName());
                    break;
                }
            }
            int quantity ;
            while (true){
                quantity = IMethod.getNumber("Enter quantity : ");
                if(quantity > stock){
                    System.err.println("Quantity > stock !");
                }else {
                    break;
                }
            }
            if(indexCart == -1){
                CartItem cartItem = new CartItem(products.get(indexProduct),products.get(indexProduct).getProductPrice(),quantity);
                cartItems.add(cartItem);
            }else {
                cartItems.get(indexCart).setQuantity(cartItems.get(indexCart).getQuantity()+ quantity);
            }
            IMethod.saveData(IMethod.fileCart,cartItems);
            products.get(indexProduct).setStock(products.get(indexProduct).getStock()-quantity);
            IMethod.saveData(IMethod.fileProduct,products);
            System.out.println("Add to cart successfully !");

        }

    }

    public static void updateQuantity(){
        List<CartItem> cartItems = IMethod.listCart();
        List<Product> products = IMethod.listProduct();
        displayCart(cartItems);
        if(cartItems.isEmpty()){
            System.err.println("Carts empty , cannot update !");
        }else {
            int cartId ;
            int indexCart = -1 ;
            while (true){
                cartId = IMethod.getNumber("Enter id cart item want update : ");
                indexCart = cartItems.stream().map(CartItem::getCartItemId).toList().indexOf(cartId);
                if(indexCart == -1){
                    System.err.println("Not found cart id , try again !");
                }else {
                    break;
                }
            }
            int indexProduct = products.stream()
                    .map(Product::getProductName)
                    .toList()
                    .indexOf(cartItems.get(indexCart).getProduct().getProductName());
            int quantity ;
            while (true){
                quantity = IMethod.getNumber("Enter quantity want update : ");
                if(quantity > products.get(indexProduct).getStock()){
                    System.err.println("Quantity > stock !");
                }else {
                    break;
                }
            }

            int difference = quantity - cartItems.get(indexCart).getQuantity();
                products.get(indexProduct).setStock(products.get(indexProduct).getStock()- difference);
            cartItems.get(indexCart).setQuantity(quantity);
            IMethod.saveData(IMethod.fileProduct,products);
            IMethod.saveData(IMethod.fileCart,cartItems);
            System.out.println("Update quantity successfully !");
        }
    }

    public static void deleteProductCart(){
        List<CartItem> cartItems = IMethod.listCart();
        displayCart(cartItems);
        if(cartItems.isEmpty()){
            System.err.println("Carts empty , cannot delete !");
        }else {
            int cartId;
            int indexCart = -1;
            while (true) {
                cartId = IMethod.getNumber("Enter id cart item want delete : ");
                indexCart = cartItems.stream().map(CartItem::getCartItemId).toList().indexOf(cartId);
                if (indexCart == -1) {
                    System.err.println("Not found cart id , try again !");
                } else {
                    break;
                }
            }
            cartItems.remove(indexCart);
            IMethod.saveData(IMethod.fileCart,cartItems);
            System.out.println("Delete product successfully !");
        }
    }

    public static void deleteAllProductCart(){
        List<CartItem> cartItems = IMethod.listCart();
       if(cartItems.isEmpty()){
           System.err.println("Cannot delete because carts empty !");
       }else {
           List<Product> products = IMethod.listProduct();
           for(CartItem cartItem : cartItems){
               int indexProduct = products.stream()
                       .map(Product::getProductName)
                       .toList()
                       .indexOf(cartItem.getProduct().getProductName());
               products.get(indexProduct).setStock(products.get(indexProduct).getStock()+cartItem.getQuantity());
           }
           IMethod.saveData(IMethod.fileProduct,products);
           cartItems.clear();
           IMethod.saveData(IMethod.fileCart,cartItems);
           System.out.println("Delete all product cart successfully !");
       }
    }


}
