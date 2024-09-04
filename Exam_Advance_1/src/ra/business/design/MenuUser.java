package ra.business.design;

import ra.business.entity.CartItem;
import ra.business.entity.Product;
import ra.common.IMethod;

import java.util.List;

public class MenuUser {
    public static void main(String[] args) {
        while (true){
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━ MENU USER ━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                                        |                                        |                                        |");
            System.out.println("|            1. See list product         |              2. Add to cart            |          3. See all product carts      |");
            System.out.println("|                                        |                                        |                                        |");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
            System.out.println("|                              |                             |                                |                            |");
            System.out.println("|  4. Change qty product cart  |   5. Delete product cart    |   6. Delete all product cart   |           7. Back          |");
            System.out.println("|                              |                             |                                |                            |");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            int choice = IMethod.getNumber("Enter your choice : ");
            switch (choice){
                case 1 : {
                    List<Product> products = IMethod.listProduct();
                    CartFeature.displayListProducts(products);
                    break;
                }
                case 2 : {
                    CartFeature.addToCart();
                    break;
                }
                case 3 : {
                    List<CartItem> cartItems = IMethod.listCart();
                    CartFeature.displayCart(cartItems);
                    break;
                }
                case 4 : {
                    CartFeature.updateQuantity();
                    break;
                }
                case 5 : {
                    CartFeature.deleteProductCart();
                    break;
                }
                case 6 : {
                    CartFeature.deleteAllProductCart();
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
