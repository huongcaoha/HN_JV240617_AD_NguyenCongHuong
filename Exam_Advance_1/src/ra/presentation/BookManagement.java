package ra.presentation;

import ra.business.design.CatalogService;
import ra.business.design.MenuUser;
import ra.business.design.ProductService;
import ra.common.IMethod;

public class BookManagement {
    public static void main(String[] args) {
        while (true){
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                              |                              |                              |                              |");
            System.out.println("|    1. Catalog management     |     2. Product management    |         3. For users         |           4. Exit            |");
            System.out.println("|                              |                              |                              |                              |");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            int choice = IMethod.getNumber("Enter your choice : ");
            switch (choice){
                case 1 : {
                    CatalogService.main(args);
                    break;
                }
                case 2 : {
                    ProductService.main(args);
                    break;
                }
                case 3 : {
                    MenuUser.main(args);
                    break;
                }
                case 4 : {
                    System.out.println("Goodbye !!!");
                    return;
                }
                default: {
                    System.err.println("Enter choice from 1 to 3 !");
                }
            }
        }
    }
}
