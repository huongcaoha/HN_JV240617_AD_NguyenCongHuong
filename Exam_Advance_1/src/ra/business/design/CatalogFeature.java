package ra.business.design;

import ra.business.entity.Catalog;
import ra.business.implement.IGenericService;
import ra.common.IMethod;

import java.util.List;

public class CatalogFeature implements IGenericService<Catalog,Integer> {
    @Override
    public List<Catalog> getAll() {
        return IMethod.listCatalog();
    }

    @Override
    public void save(Catalog catalog) {
        List<Catalog> catalogs = IMethod.listCatalog();
        catalogs.add(catalog);
        IMethod.saveData(IMethod.fileCatalog,catalogs);
    }

    @Override
    public Catalog findById(Integer id) {
        List<Catalog> catalogs = IMethod.listCatalog();
        Catalog catalog = new Catalog();
        int index = catalogs.stream().map(Catalog::getCatalogId).toList().indexOf(id);
        if(index == -1){
            return null ;
        }else {
            return catalogs.get(index);
        }
    }

    @Override
    public void delete(Integer id) {
        List<Catalog> catalogs = IMethod.listCatalog();
        int index = catalogs.stream().map(Catalog::getCatalogId).toList().indexOf(id);
        if(index == -1){
            System.err.println("Not found catalog with id = " + id);
        }else {
            catalogs.remove(index);
            IMethod.saveData(IMethod.fileCatalog,catalogs);
            System.out.println("Delete catalog successfully !");
        }
    }

}
