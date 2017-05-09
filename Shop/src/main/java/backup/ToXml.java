package backup;

import models.*;
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
            description.appendChild(doc.createTextNode(String.valueOf(entity.getDescription())));

            product.appendChild(description);

            products.appendChild(product);
        }
        doc.appendChild(products);
        convert(doc,path);
    }

    public static void exportClients(List<ClientEntity> clientsList, String path) throws TransformerException{
        ParamLangXML();
        Document doc = builder.newDocument();
        Element clients = doc.createElement("clients");

        for (ClientEntity entity : clientsList) {
            Element client = doc.createElement("client");

            Element id = doc.createElement("id");
            id.appendChild(doc.createTextNode(String.valueOf(entity.getId())));
            client.appendChild(id);

            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode(entity.getName()));
            client.appendChild(name);

            Element address = doc.createElement("default_address");
            address.appendChild(doc.createTextNode(String.valueOf(entity.getDefaultAddress())));
            client.appendChild(address);

            Element isAdmin = doc.createElement("is_admin");
            isAdmin.appendChild(doc.createTextNode(String.valueOf(entity.getAdmin())));
            client.appendChild(isAdmin);

            clients.appendChild(client);
        }
        doc.appendChild(clients);
        convert(doc,path);
    }

    public static void exportCategories(List<CategoryEntity> categoryList, String path) throws TransformerException {
        ParamLangXML();
        Document doc = builder.newDocument();
        Element categories = doc.createElement("categories");

        for (CategoryEntity entity : categoryList) {
            Element category = doc.createElement("category");

            Element id = doc.createElement("id");
            id.appendChild(doc.createTextNode(String.valueOf(entity.getId())));
            category.appendChild(id);

            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode(entity.getName()));
            category.appendChild(name);

            Element parentId = doc.createElement("parent_id");
            parentId.appendChild(doc.createTextNode(String.valueOf(entity.getCategoryByParentId())));
            category.appendChild(parentId);

            categories.appendChild(category);
        }
        doc.appendChild(categories);
        convert(doc,path);
    }

    public static void exportOrders(List<OrderEntity> ordersList, String path) throws TransformerException {
        ParamLangXML();
        Document doc = builder.newDocument();
        Element orders = doc.createElement("orders");

        for (OrderEntity entity : ordersList) {
            Element order = doc.createElement("order");

            Element id = doc.createElement("id");
            id.appendChild(doc.createTextNode(String.valueOf(entity.getId())));
            order.appendChild(id);

            Element clientId = doc.createElement("clientId");
            clientId.appendChild(doc.createTextNode(String.valueOf(entity.getClientByClientId().getId())));
            order.appendChild(clientId);

            Element address = doc.createElement("address");
            address.appendChild(doc.createTextNode(entity.getAddress()));
            order.appendChild(address);

            Element date = doc.createElement("date");
            date.appendChild(doc.createTextNode(String.valueOf(entity.getDate())));
            order.appendChild(date);

            Element status = doc.createElement("status");
            status.appendChild(doc.createTextNode(String.valueOf(entity.getStatus())));
            order.appendChild(status);

            orders.appendChild(order);
        }
        doc.appendChild(orders);
        convert(doc,path);
    }

    public static void exportCarts(List<CartEntity> cartsList, String path) throws TransformerException{
        ParamLangXML();
        Document doc = builder.newDocument();
        Element carts = doc.createElement("carts");

        for (CartEntity entity : cartsList) {
            Element cart = doc.createElement("cart");

            Element id = doc.createElement("id");
            id.appendChild(doc.createTextNode(String.valueOf(entity.getId())));
            cart.appendChild(id);

            Element clientId = doc.createElement("clientId");
            clientId.appendChild(doc.createTextNode(String.valueOf(entity.getClientId())));
            cart.appendChild(clientId);

            Element productId = doc.createElement("product_id");
            productId.appendChild(doc.createTextNode(String.valueOf(entity.getProductId())));
            cart.appendChild(productId);

            Element count = doc.createElement("count");
            count.appendChild(doc.createTextNode(String.valueOf(entity.getCount())));
            cart.appendChild(productId);

            carts.appendChild(cart);
        }
        doc.appendChild(carts);
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
