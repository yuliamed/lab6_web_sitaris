import Entity.Product;
import Xml.ReaderCommand;
import Xml.WriterCommand;
import Xml.XML;
import Xml.XMLWorker;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/index.jsp"})
public class NameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        pw.println("<html>");
        pw.println("<head><title>Product table</title></title>");
        pw.println("<script src=\"https://www.google.com/jsapi\"></script>");
        pw.println(" <script>");
        pw.println("google.load(\"visualization\", \"1\", {packages:[\"corechart\"]});");
        pw.println("google.setOnLoadCallback(drawChart);");
        pw.println("function drawChart() {\n" +
                "    var data = google.visualization.arrayToDataTable([\n" +
                "     ['Год', 'Россия', 'США'],\n" +
                "     ['1860', 1.3, 70],\n" +
                "     ['1885', 2000, 3120],\n" +
                "     ['1901', 12170, 9920]\n" +
                "    ]);");
        pw.println("var options = {\n" +
                "     title: 'Добыча нефти',\n" +
                "     hAxis: {title: 'Год'},\n" +
                "     vAxis: {title: 'Тыс. тонн'}\n" +
                "    };");
        pw.println("var chart = new google.visualization.ColumnChart(document.getElementById('oil'));\n" +
                "");
        pw.println("chart.draw(data, options);\n" +
                "   }");
        pw.println("  </script>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("<h2>Product table:</h2>");
        pw.println("<table border=\"1\">");
        pw.println("<tbody>");
        pw.println("<tr>");
        pw.println("<th scope=\"col\">Name</td>");
        pw.println("<th scope=\"col\">Quantity</td>");
        pw.println("<th scope=\"col\">Dimension</td>");
        pw.println("<th scope=\"col\">Location</td>");
        pw.println("</tr>");
        String path = "C:\\University\\sitaris\\lab6_web\\src\\main\\java\\Res\\Base.xml";
        XML objXML = new XML(path);
        XMLWorker xmlWorker = new XMLWorker(new WriterCommand(objXML), new ReaderCommand(objXML));
        ArrayList<Product> products = new ArrayList<>();
        xmlWorker.readXml(products);
        for (Product p : products) {
            writeProduct(pw,p);
        }
        pw.println("</table>");

        pw.println("<div id=oil style=\"width: 500px; height: 400px;\"></div>");
        pw.println("</body></html>");
    }

    void writeProduct(PrintWriter pw, Product p){
        pw.println("<tr>");
        pw.println("<td>" + p.getName() + "</td>");
        pw.println("<td>" + p.getAmount() + "</td>");
        if (p.getDimension() != null) {
            pw.println("<td>");
            pw.println("<table border=\"0\">");
            pw.println("<tbody>");
            pw.println("<tr>");
            pw.println("<td>"+p.getDimension().getHeight()+"</td>");
            pw.println("<td>"+p.getDimension().getLength()+"</td>");
            pw.println("<td>"+p.getDimension().getWidth()+"</td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td colspan=\"3\">weight: "+p.getDimension().getWeight()+"</td>");
            pw.println("</tr>");
            pw.println("</tbody>");
            pw.println("</table>");
            pw.println("</td>");
        }
        else pw.println("<td>NO INFO</td>");
        if (p.getLocation() != null)
            pw.println("<td>" + p.getLocation().toString() + "</td>");
        else pw.println("<td>NO</td>");
        pw.println("</tr>");
    }
}
