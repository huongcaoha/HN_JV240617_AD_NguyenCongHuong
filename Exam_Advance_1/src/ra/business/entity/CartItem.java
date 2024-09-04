package ra.business.entity;

import ra.common.IMethod;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartItem implements Serializable {
    private Integer cartItemId;
    private Product product ;
    private Double price ;
    private Integer quantity ;

    public CartItem() {
    }

    public CartItem( Product product, Double price, Integer quantity) {
        this.cartItemId = getIdCartItem();
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public int getIdCartItem(){
        List<CartItem> cartItems = IMethod.listCart();
        if(cartItems.isEmpty()){
            return 1 ;
        }else {
            return cartItems.getLast().getCartItemId() + 1 ;
        }
    }
    public Integer getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Integer cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void displayData(){
        NumberFormat formatter = NumberFormat.getInstance(Locale.GERMANY);
        System.out.println("┏━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━┳━━━━━━━━━━┓");
        System.out.printf("| %-8d | %-26s | %-12s | %-8d |\n",cartItemId,product.getProductName(),formatter.format(price)+"VNĐ",quantity);
        System.out.println("┗━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━┻━━━━━━━━━━┛");
    }

}
