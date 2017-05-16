package backup;

import beans.CategoryBean;
import beans.ProductBean;
import models.CategoryEntity;
import models.ProductEntity;
import models.UnregistredProductEntity;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@Stateless
public class FromXml {
    @Resource
    SessionContext ctx;
    @Inject
    ProductBean productBean;
    @Inject
    CategoryBean categoryBean;

    private Map<Integer, Integer> importProducts(List<UnregistredProductEntity> products,
                                                 Map<Integer, Integer> categoryMapping,
                                                 MergePolicy policy) {
        Map<Integer, Integer> result = new HashMap<>();
        try {
            for (UnregistredProductEntity product : products) {
                int category = -1;
                if (categoryMapping.containsKey(product.getCategoryId())) {
                    category = categoryMapping.get(product.getCategoryId());
                }

                if (policy == MergePolicy.IGNORE_COPIES) {
                    if (productBean.getByName(product.getName()).size() > 0) {
                        continue;
                    }
                } else if (policy == MergePolicy.RENAME_COPIES) {
                    while (!productBean.getByName(product.getName()).isEmpty()) {
                        product.setName(product.getName() + " (импортировано)");
                    }
                }

                ProductEntity pe = productBean.addProduct(product.getName(),
                                                          product.getDescription(),
                                                          product.getCount(),
                                                          product.getPrice(),
                                                          category);
                result.put(product.getId(), pe.getId());
            }
        } catch (RuntimeException e) {
            ctx.setRollbackOnly();
            throw e;
        }
        return result;
    }

    private Map<Integer, Integer> importCategories(List<CategoryEntity> categories) {
        Map<Integer, Integer> result = new HashMap<>();
        try {
            for (CategoryEntity category : categories) {
                int newId = -1;
                List<CategoryEntity> local = categoryBean.getByName(category.getName());
                if (local.size() > 0) {
                    newId = local.get(0).getId();
                } else {
                    newId = categoryBean.addCategory(category.getName(), -1).getId();
                }
                result.put(category.getId(), newId);
            }
        } catch (RuntimeException e) {
            ctx.setRollbackOnly();
            throw e;
        }
        return result;
    }

    public Map<Integer, Integer> importBackup(String path, MergePolicy policy) throws JAXBException {
        File file = new File(path);
        JAXBContext jaxbContext = JAXBContext.newInstance(BackupUnit.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        BackupUnit content = (BackupUnit) jaxbUnmarshaller.unmarshal(file);
        return importProducts(content.getProducts(), importCategories(content.getCategories()), policy);
    }
}

