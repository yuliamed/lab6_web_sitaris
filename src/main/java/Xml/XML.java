package Xml;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import Entity.*;
import ProdBuilder.*;

public class XML {
String path;

    public XML(String path) {
        this.path = path;
    }

    public  void readProducts(ArrayList<Product> baseArray) {
        //ArrayList<Product> baseArray = new ArrayList<>();
        try {
            // Получение фабрики, чтобы после получить билдер документов.
            DocumentBuilderFactory createFactory = DocumentBuilderFactory.newInstance();
            // Получили из фабрики билдер, который парсит XML, создает структуру Document в виде иерархического дерева.
            DocumentBuilder documentBuilder = createFactory.newDocumentBuilder();
            // Запарсили XML, создав структуру Document. Теперь у нас есть доступ ко всем элементам, каким нам нужно.
            Document document = documentBuilder.parse(new File(path));
            // метод normal --> стабилизирует документ XML (убирает пробелы)
            document.getDocumentElement().normalize();
            // NodeList --> объект подобный массиву (размер) // Весь XML документ
            NodeList baseList = document.getDocumentElement().getElementsByTagName("Product");
            buildProductWithXml(baseList, baseArray);
            //baseArray.add(productBuilder.build());
        } catch (
                ParserConfigurationException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (
                SAXException e) {
            e.printStackTrace();
        }
        //return baseArray;
    }

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

    private Location getLocation(Element element) {
        NamedNodeMap attributes = element.getAttributes();
        if (attributes != null) {
            char cell = attributes.getNamedItem("cell").getNodeValue().charAt(0);
            int floor = Integer.parseInt(attributes.getNamedItem("floor").getNodeValue());
            int row = Integer.parseInt(attributes.getNamedItem("row").getNodeValue());
            System.out.println(cell + " " + floor + " " + row);
            return new Location(row, floor, cell);
        }
        return null;
    }

    private Dimension getDimension(Element element) {
        NamedNodeMap attributes = element.getAttributes();
        if (attributes != null) {
            double height = Double.parseDouble(attributes.getNamedItem("height").getNodeValue());
            double length = Double.parseDouble(attributes.getNamedItem("length").getNodeValue());
            double width = Double.parseDouble(attributes.getNamedItem("width").getNodeValue());
            double weight = Double.parseDouble(attributes.getNamedItem("weight").getNodeValue());
            //System.out.println(cell + " " + floor + " " + row);
            return new Dimension(height, length, width, weight);
        }
        return null;
    }

    private void buildProductWithXml(NodeList baseList, ArrayList<Product> returnArr) {
        for (int i = 0; i < baseList.getLength(); i++) {
            Element elemProduct = (Element) baseList.item(i);
            int amount = Integer.parseInt(getTagValue("amount", elemProduct));
            String name = getTagValue("name", elemProduct);
            ProductBuilder productBuilder = new ProductBuilderImpl(name, amount);
            System.out.println(i);
            if (elemProduct.getElementsByTagName("location").item(0) != null) {
                System.out.println("location");
                Element elemLocation = (Element) elemProduct.getElementsByTagName("location").item(0);
                Location l = getLocation(elemLocation);
                productBuilder.setLocation(l);
            }
            if (elemProduct.getElementsByTagName("dimension").item(0) != null) {
                System.out.println("dimension");
                Element elemLocation = (Element) elemProduct.getElementsByTagName("dimension").item(0);
                Dimension d = getDimension(elemLocation);
                productBuilder.setDimension(d);
            }
            returnArr.add(productBuilder.build());
        }
    }

    public void writeToXML(ArrayList<Product> arr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;try {
            builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("Base");
            document.appendChild(root);
            for (Product p : arr) {
                root.appendChild(getProductToXML(p, document));
            }
            //создаем объект TransformerFactory для печати в консоль
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // для красивого вывода в консоль
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);

            //печатаем в консоль или файл
            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File(path));

            //записываем данные
            transformer.transform(source, console);
            transformer.transform(source, file);
            System.out.println("Создание XML файла закончено");
        } catch (Exception e){
            System.out.println(e);
        }
    }

    private Node getProductToXML(Product p, Document document) {
        Element product = document.createElement("Product");

        product.appendChild(getProductName(p, document));
        product.appendChild(getProductAmount(p, document));
        if(p.getDimension()!=null){
            product.appendChild(getProductDimension(p, document));
        }
        if(p.getLocation()!=null){
            product.appendChild(getProductLocation(p, document));
        }
        return product;
    }

    private Node getProductLocation(Product p, Document document) {
        Element nodeDimension = document.createElement("location");
        nodeDimension.setAttribute("floor", String.valueOf(p.getLocation().getFloor()));
        nodeDimension.setAttribute("row", String.valueOf(p.getLocation().getRow()));
        nodeDimension.setAttribute("cell", String.valueOf(p.getLocation().getCell()));
        return nodeDimension;
    }

    private Node getProductDimension(Product p, Document document) {
        Element nodeDimension = document.createElement("dimension");
        nodeDimension.setAttribute("height", String.valueOf(p.getDimension().getHeight()));
        nodeDimension.setAttribute("length", String.valueOf(p.getDimension().getLength()));
        nodeDimension.setAttribute("width", String.valueOf(p.getDimension().getWidth()));
        nodeDimension.setAttribute("weight", String.valueOf(p.getDimension().getWeight()));
        return nodeDimension;
    }

    private Node getProductAmount(Product p, Document document) {
        Element nodeName = document.createElement("amount");
        nodeName.appendChild(document.createTextNode(String.valueOf(p.getAmount())));
        return nodeName;
    }

    private Node getProductName(Product p, Document document) {
        Element nodeName = document.createElement("name");
        nodeName.appendChild(document.createTextNode(p.getName()));
        return nodeName;
    }
}
