import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class checkout extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("pid") == null) {
            return;
        }
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
                "    <a href=\"productPage\">\n" +
                "    <h2>Store</h2>\n" +
                "    </a>" +
                "<p>Please enter all fields outlined in red</p>" +
                "<form action=\"purchase.php\" method=\"POST\">'" +
                "        <div>\n" +
                "            <label>First Name:</label>\n" +
                "            <input type=\"text\" name=\"firstname\"  required>\n" +
                "        </div>\n" +
                "        <div>\n" +
                "            <label>Last Name:</label>\n" +
                "            <input type=\"text\" name=\"lastname\" required>\n" +
                "        </div><br>\n" +
                "        <div>\n" +
                "            <label>Phone Number (use dashes or spaces):</label>\n" +
                "            <input type=\"text\" name=\"phone\" id=\"phone\" required maxlength=\"12\">\n" +
                "        </div><br>\n" +
                "        <div>\n" +
                "            <label>Street Address:</label>\n" +
                "            <input type=\"text\" name=\"street\" required class=\"linput\">\n" +
                "        </div><br>\n" +
                "        <div>\n" +
                "            <label>Zipcode:</label>\n" +
                "            <input type=\"text\" name=\"zip\" id=\"zip\" required>\n" +
                "        </div>\n" +
                "        <div>\n" +
                "            <label>City:</label>\n" +
                "            <input type=\"text\" name=\"city\" id=\"city\" required class=\"sinput\">\n" +
                "        </div>\n" +
                "        <div>\n" +
                "            <label>State:</label>\n" +
                "            <input type=\"text\" name=\"state\" id=\"state\" class=\"sinput\" required>\n" +
                "        </div>\n" +
                "        <br>\n" +
                "        <div>\n" +
                "            <label>Shipping Method:</label>\n" +
                "            <input type=\"radio\" name=\"shipping\" value=\"Overnight\" checked=\"checked\" required>Overnight Shipping - $14.99<br>\n" +
                "            <input type=\"radio\" name=\"shipping\" value=\"Expedited\">Expedited Shipping (2-3 business days) - $9.99<br>\n" +
                "            <input type=\"radio\" name=\"shipping\" value=\"Standard\" >Standard Shipping (5-7 business days) - $4.99<br>\n" +
                "        </div><br>\n" +
                "        <div>\n" +
                "            <label>Credit Card Number:</label>\n" +
                "            <input type=\"text\" name=\"creditcard\" required pattern=\"[0-9]{16}\">\n" +
                "        </div>\n" +
                "        <div>\n" +
                "            <label>CVV:</label>\n" +
                "            <input type=\"text\" name=\"cvv\" required pattern=\"[0-9]{3}\">\n" +
                "        </div><br><br>\n" +
                "        <input id='purchase' class=\"bttn\" type=\"submit\" value=\"Submit\" name=\"submit\">\n" +
                "        <input class=\"bttn\"type=\"reset\" value=\"Reset\">\n" +
                "        <span id=\"insertHere\"></span>\n" +
                "    </form>\n" +
                "</html>");
    }
}