package backup;

import models.ProductEntity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.List;

/**
 * Created by Дмитрий on 08.05.2017.
 */
public class ToXml {
    static DocumentBuilder builder;

    public static void ParamLangXML() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try { builder = factory.newDocumentBuilder(); }
        catch (ParserConfigurationException e) { e.printStackTrace(); }
    }

    public static void exportProducts(List<ProductEntity> productsList, String path) throws TransformerException {
        ParamLangXML();
        Document doc = builder.newDocument();
        Element products = doc.createElement("products");

        for (ProductEntity entity : productsList) {
            Element product = doc.createElement("product");

            Element id = doc.createElement("id");
            id.appendChild(doc.createTextNode(String.valueOf(entity.getId())));
            product.appendChild(id);

            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode(entity.getName()));
            product.appendChild(name);

            Element price = doc.createElement("price");
            price.appendChild(doc.createTextNode(String.valueOf(entity.getPrice())));
            product.appendChild(price);

            Element count = doc.createElement("count");
            count.appendChild(doc.createTextNode(String.valueOf(entity.getCount())));
            product.appendChild(count);

            Element categoryId = doc.createElement("category_id");
            categoryId.appendChild(doc.createTextNode(String.valueOf(entity.getCategory().getId())));
            product.appendChild(categoryId);

            Element description = doc.createElement("description");
            if (entity.getDescription() == null) {
                description.appendChild(doc.createTextNode(""));
            } else {
                description.appendChild(doc.createTextNode(entity.getDescription()));
            }
            product.appendChild(description);

            products.appendChild(product);
        }
        doc.appendChild(products);
        convert(doc,path);
    }

    public static void convert(Document doc,String path) throws TransformerException {
        DOMSource domSource = new DOMSource(doc);
        StreamResult outStream = new StreamResult(path);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(domSource, outStream);
    }
}
