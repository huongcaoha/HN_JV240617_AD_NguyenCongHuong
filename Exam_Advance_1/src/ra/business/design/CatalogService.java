package ra.business.design;

import ra.business.entity.Catalog;
import ra.business.entity.Product;
import ra.common.IMethod;

import java.util.List;
import java.util.Objects;

public class CatalogService {
    public static void main(String[] args) {
        while (true){
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                                        |                                        |                                        |");
            System.out.println("|            1. Add catalog              |         2. Display list catalog        |          3. Edit catalog by id         |");
            System.out.println("|                                        |                                        |                                        |");
            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
            System.out.println("|                                                            |                                                             |");
            System.out.println("|                  4. Delete catalog by id                   |                         5. Back                             |");
            System.out.println("|                                                            |                                                             |");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            int choice = IMethod.getNumber("Enter your choice : ");
            switch (choice){
                case 1 : {
                    List<Catalog> catalogs = IMethod.listCatalog();
                    int number = IMethod.getNumber("Enter number catalog want add : ");
                    for(int i = 1 ; i <= number ; i++){
                        System.out.println("Enter catalog number " + i);
                        Catalog catalog = new Catalog();
                        catalog.inputData(IMethod.scanner);
                        catalogs.add(catalog);
                        IMethod.saveData(IMethod.fileCatalog,catalogs);
                    }
                    System.out.println("Add successfully " + number + " catalog !");
                    for(Catalog cate : catalogs){
                        cate.displayData();
                    }
                    break;
                }
                case 2 : {
                    List<Catalog> catalogs = IMethod.listCatalog();
                    if(catalogs.isEmpty()){
                        System.err.println("List catalog empty !");
                    }else {
                        System.out.println("****************************** LIST CATALOG **********************************");
                        System.out.println("┏━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                        System.out.printf("| %-8s | %-28s | %-28s |\n","ID","Catalog Name","Descriptions");
                        System.out.println("┗━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
                        for(Catalog cate : catalogs){
                            cate.displayData();
                        }
                    }
                    break;
                }
                case 3 : {
                    List<Catalog> catalogs = IMethod.listCatalog();
                    int idCatalog = IMethod.getNumber("Enter id catalog to edit : ");
                    int index = catalogs.stream().map(Catalog::getCatalogId).toList().indexOf(idCatalog);
                    if(index == -1){
                        System.err.println("Not found id catalog !");
                    }else {
                        catalogs.get(index).updateData(IMethod.scanner);
                        IMethod.saveData(IMethod.fileCatalog,catalogs);
                        System.out.println("update successfully !");
                    }
                    break;
                }
                case 4 : {
                    List<Catalog> catalogs = IMethod.listCatalog();
                    int idCatalog = IMethod.getNumber("Enter id catalog to delete : ");
                    int index = catalogs.stream().map(Catalog::getCatalogId).toList().indexOf(idCatalog);
                    if(index == -1){
                        System.err.println("Not found id catalog !");
                    }else {
                        List<Product> products = IMethod.listProduct();
                        boolean checkExist = false;
                        for(Product pro : products){
                            if(Objects.equals(pro.getCatalog().getCatalogId(), catalogs.get(index).getCatalogId())){
                                checkExist = true ;
                                break;
                            }
                        }
                        if(checkExist){
                            System.err.println("Cannot delete because catalog existed product !");
                        }else {
                            catalogs.remove(index);
                            IMethod.saveData(IMethod.fileCatalog,catalogs);
                            System.out.println("Delete successfully !");
                        }
                    }
                    break;
                }
                case 5 : {
                    return;
                }
                default: {
                    System.err.println("Enter choice from 1 to 5 !");
                }
            }
        }
    }
}
