package ra.common;

import ra.business.entity.CartItem;
import ra.business.entity.Catalog;
import ra.business.entity.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IMethod {
        public static String fileCatalog = "src/ra/database/listCatalog.txt";
        public static String fileProduct = "src/ra/database/listProduct.txt" ;
        public static String fileCart = "src/ra/database/listCart.txt" ;
        public static Scanner scanner = new Scanner(System.in);

        public static<T> List<T> getList(String fileName){
            List<T> list = new ArrayList<>();
            try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
                list = (List<T>) in.readObject();
            }catch (Exception e){
                System.err.println("Read file error !");
            }
            return list ;
        }

        public static <T> boolean saveData(String fileName , List<T> list){
            boolean isSuccess ;
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))){
                out.writeObject(list);
                return true ;
            }catch (Exception e){
                System.err.println("Write file error !");
                return false ;
            }
        }

        public static int getNumber(String label){

            int number ;
            while (true){
                try {
                    System.out.println(label);
                    number = Integer.parseInt(scanner.nextLine().trim());
                    if(number > 0){
                        break;
                    }else {
                        System.err.println("Enter number > 0 !");
                    }
                }catch (NumberFormatException e){
                    System.err.println("Number invalid !");
                }
            }
            return number ;
        }

        public static List<Catalog> listCatalog(){
            return getList(fileCatalog);
        }

        public static List<Product> listProduct(){
            return getList(fileProduct);
        }
        public static List<CartItem> listCart(){
            return getList(fileCart);
        }

    public static void main(String[] args) {
        List<Catalog> catalogs = new ArrayList<>();
        saveData(fileCatalog,catalogs);
    }
}
