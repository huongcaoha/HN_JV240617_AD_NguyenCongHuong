package ra.business.entity;

import ra.common.IMethod;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Catalog implements Serializable {
    private Integer catalogId ;
    private String catalogName ;
    private String descriptions ;

    public Catalog() {
    }

    public Catalog(Integer catalogId, String catalogName, String descriptions) {
        this.catalogId = getId();
        this.catalogName = catalogName;
        this.descriptions = descriptions;
    }
    public int getId(){
        List<Catalog> catalogs = IMethod.listCatalog();
        if(catalogs.isEmpty()){
            return 1 ;
        }else {
            return catalogs.getLast().getCatalogId() + 1 ;
        }
    }

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public void inputData(Scanner scanner){
        this.catalogId = getId();
        List<Catalog> catalogs = IMethod.listCatalog();
        inputCatalogName(scanner, catalogs);
        inputDescriptions(scanner);
    }

    public void updateData(Scanner scanner){
        List<Catalog> catalogs = IMethod.listCatalog();
        updateCatalogName(scanner, catalogs);
        inputDescriptions(scanner);
    }



    private void inputDescriptions(Scanner scanner) {
        while (true){
            System.out.println("Enter descriptions : ");
            this.descriptions = scanner.nextLine().trim();
            if(descriptions.isEmpty()){
                System.err.println("Cannot be left blank !");
            }else {
                break;
            }
        }
    }

    private void inputCatalogName(Scanner scanner, List<Catalog> catalogs) {
        while (true){
            System.out.println("Enter catalogName : ");
            this.catalogName = scanner.nextLine().trim();
            if(catalogName.isEmpty()){
                System.err.println("Cannot be left blank !");
            }else {
                int index = catalogs.stream().map(Catalog::getCatalogName).toList().indexOf(catalogName);
                if(index == -1){
                    break;
                }else {
                    System.err.println("Catalog name existed !");
                }
            }
        }
    }

    private void updateCatalogName(Scanner scanner, List<Catalog> catalogs) {
        while (true){
            System.out.println("Enter catalogName : ");
            this.catalogName = scanner.nextLine().trim();
            if(catalogName.isEmpty()){
                System.err.println("Cannot be left blank !");
            }else {
                String oddName = this.catalogName;
                int index = catalogs.stream().map(Catalog::getCatalogName).toList().indexOf(catalogName);
                if(index == -1){
                    break;
                }else {
                    if(catalogs.get(index).catalogName.equalsIgnoreCase(oddName)){
                        break;
                    }else {
                        System.err.println("Catalog name existed !");
                    }

                }
            }
        }
    }

    public void displayData(){
        System.out.println("┏━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.printf("| %-8d | %-28s | %-28s |\n",catalogId,catalogName,descriptions);
        System.out.println("┗━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        IMethod.saveData(IMethod.fileProduct,products);
    }
}
