import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class confirmation extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        out.println("<html>\n" +
        "    <head>\n" +
                "        <title>Foil Me, Daddy</title>\n" +
                "        <!-- link to main stylesheet -->\n" +
                "        <link rel=\"stylesheet\" type=\"text/css\" href=\"main.css\">\n" +
                "        <?php require 'purchase.php';?>\n" +
                "\n" +
                "    </head>\n" +
                "    <span id=\"insertHere\"></span>\n" +
                "    <a href=\"productPage\"  class=\"topHeader\"><h1>Foil Me, <em>Daddy</em></h1></a>\n" +
                "    <nav class=\"menu\">\n" +
                "        <ul>\n" +
                "            <li><a href=\"productPage\">Home</a></li>\n" +
                "            <li><a href=\"about.html\">About Us</a></li>\n" +
                "            <li><a href=\"confirmation.html\">Order Confirmations</a></li>\n" +
                "        </ul>\n" +
                "    </nav>\n" +
                "    <p>Thank you for ordering from Foil Me, Daddy! Your Order ID is : " + session.getAttribute("oid") + "</p><br>" +
                "    <p>Please come again!</p>");
    }
}