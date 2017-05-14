package backup;

import beans.CategoryBean;
import beans.ProductBean;
import models.ProductEntity;
import models.UnregistredProductEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;

@Named
@Stateless
public class ToXml {
    @Inject
    CategoryBean categoryBean;
    @Inject
    ProductBean productBean;

    public void export(String path) throws JAXBException {
        File file = new File(path);
        JAXBContext jaxbContext = JAXBContext.newInstance(BackupUnit.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        BackupUnit backup = new BackupUnit();
        backup.setCategories(categoryBean.getAll());
        ArrayList<UnregistredProductEntity> products = new ArrayList<>();
        for (ProductEntity entity : productBean.getAll())
            products.add(new UnregistredProductEntity(entity));
        backup.setProducts(products);

        jaxbMarshaller.marshal(backup, file);
    }
}
