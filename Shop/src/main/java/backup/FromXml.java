package backup;

import beans.CategoryBean;
import beans.ClientBean;
import beans.OrderBean;
import beans.ProductBean;
import models.OrderEntity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Named
@Stateless
public class FromXml {
    @Resource
    SessionContext ctx;
    static DocumentBuilder builder;

    public static void ParamLangXML() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try { builder = factory.newDocumentBuilder(); }
        catch (ParserConfigurationException e) { e.printStackTrace(); }
    }

    @Inject
    ProductBean productBean;
    public Map<Integer, Integer> importProducts(String path, Map<Integer, Integer> categoryMapping) throws TransformerException, IOException, SAXException {
        Map<Integer, Integer> result = new HashMap<>();
        ParamLangXML();
        Document doc = builder.parse(path);
        try{
            Node products = doc.getElementsByTagName("products").item(0);
            for (int i=0; i<products.getChildNodes().getLength(); i++) {
                if (products.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element product = (Element) products.getChildNodes().item(i);
                    
                    int id = Integer.parseInt(product.getElementsByTagName("id").item(0).getTextContent());
                    String name = product.getElementsByTagName("name").item(0).getTextContent();
                    float price = Float.parseFloat(product.getElementsByTagName("price").item(0).getTextContent());
                    int count = Integer.parseInt(product.getElementsByTagName("count").item(0).getTextContent());
                    int categoryId = Integer.parseInt(product.getElementsByTagName("category_id").item(0).getTextContent());
                    String description = product.getElementsByTagName("description").item(0).getTextContent();
                    
                    int newId = productBean.addProduct(name, description, count, price, categoryMapping.get(categoryId)).getId();
                    result.put(id, newId);
                }
            }
        }
        catch (RuntimeException e) {
            ctx.setRollbackOnly();
            throw e; // or display error message
        }
        return result;
    }

    @Inject
    CategoryBean categoryBean;
    public Map<Integer, Integer> importCategories(String path) throws TransformerException, IOException, SAXException {
        Map<Integer, Integer> result = new HashMap<>();
        ParamLangXML();
        Document doc = builder.parse(path);
        try{
            Node categories = doc.getElementsByTagName("categories").item(0);
            for (int i=0; i<categories.getChildNodes().getLength(); i++) {
                if (categories.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element category = (Element) categories.getChildNodes().item(i);
                    
                    int id = Integer.parseInt(category.getElementsByTagName("id").item(0).getTextContent());
                    String name = category.getElementsByTagName("name").item(0).getTextContent();
                    
                    int newId = categoryBean.addCategory(name, -1).getId();
                    result.put(id, newId);
                }
            }
        }
        catch (RuntimeException e) {
            ctx.setRollbackOnly();
            throw e; // or display error message
        }
        return result;
    }
    
    @Inject
    ClientBean clientBean;
    public Map<Integer, Integer> importClients(String path) throws TransformerException, IOException, SAXException {
        Map<Integer, Integer> result = new HashMap<>();
        ParamLangXML();
        Document doc = builder.parse(path);
        try{
            Node clients = doc.getElementsByTagName("clients").item(0);
            for (int i=0; i<clients.getChildNodes().getLength(); i++) {
                if (clients.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element client = (Element) clients.getChildNodes().item(i);
                    
                    int id = Integer.parseInt(client.getElementsByTagName("id").item(0).getTextContent());
                    String name = client.getElementsByTagName("name").item(0).getTextContent();
                    String address = client.getElementsByTagName("default_address").item(0).getTextContent();
                    Boolean isAdmin = Boolean.valueOf(client.getElementsByTagName("isAdmin").item(0).getTextContent());

                    int newId = clientBean.addClient(name, address, isAdmin).getId();
                    result.put(id, newId);
                }
            }
        }
        catch (RuntimeException e) {
            ctx.setRollbackOnly();
            throw e; // or display error message
        }
        return result;
    }

    @Inject
    OrderBean orderBean;
    public Map<Integer, Integer> importOrders(String path, Map<Integer, Integer> clientMapping) throws TransformerException, IOException, SAXException {
        Map<Integer, Integer> result = new HashMap<>();
        ParamLangXML();
        Document doc = builder.parse(path);
        try{
            Node orders = doc.getElementsByTagName("orders").item(0);
            for (int i=0; i<orders.getChildNodes().getLength(); i++) {
                if (orders.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element order = (Element) orders.getChildNodes().item(i);
                    
                    int id = Integer.parseInt(order.getElementsByTagName("id").item(0).getTextContent());
                    int clientId = Integer.parseInt(order.getElementsByTagName("clientId").item(0).getTextContent());
                    String address = order.getElementsByTagName("address").item(0).getTextContent();
                    Date date = Date.valueOf(order.getElementsByTagName("date").item(0).getTextContent());
                    String status = order.getElementsByTagName("status").item(0).getTextContent();
                    
                    OrderEntity entity = new OrderEntity();
                    entity.setId(id);
                    entity.setClientByClientId(clientBean.get(clientMapping.get(clientId)));
                    entity.setAddress(address);
                    entity.setDate(date);
                    entity.setStatus(status);
                    int newId = orderBean.persist(entity).getId();
                    result.put(id, newId);
                }
            }
        }
        catch (RuntimeException e) {
            ctx.setRollbackOnly();
            throw e; // or display error message
        }
        return result;
    }
}
