package ra.business.design;

import ra.business.entity.Product;
import ra.business.implement.IGenericService;
import ra.common.IMethod;

import java.util.List;

public class ProductFeature implements IGenericService<Product,String> {
    @Override
    public List<Product> getAll() {
        return IMethod.listProduct();
    }

    @Override
    public void save(Product product) {
        List<Product> products = IMethod.listProduct();
        products.add(product);
        IMethod.saveData(IMethod.fileProduct,products);
    }

    @Override
    public Product findById(String productId) {
        List<Product> products = IMethod.listProduct();
        int index = products.stream().map(Product::getProductId).toList().indexOf(productId);
        if(index == -1){
            return null ;
        }else {
            return products.get(index);
        }
    }

    @Override
    public void delete(String productId) {
        List<Product> products = IMethod.listProduct();
        int index = products.stream().map(Product::getProductId).toList().indexOf(productId);
        if(index == -1){
            System.err.println("Not found product with id : " + productId); ;
        }else {
            products.remove(index);
            IMethod.saveData(IMethod.fileProduct,products);
            System.out.println("Delete product successfully !");
        }
    }
}
