import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class checkout extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = "jdbc:mysql://matt-smith-v4.ics.uci.edu/inf124db057?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "inf124db057";
        String password = "wRd8MJP2XGWa";
        HttpSession session = request.getSession();
        ArrayList<ArrayList<Integer>> cart = (ArrayList<ArrayList<Integer>>)session.getAttribute("cart");
        if (session.getAttribute("pid") == null) {
            return;
        }
        PrintWriter out = response.getWriter();
        out.println("<html>\n" +
                "    <head>\n" +
                "        <title>Foil Me, Daddy</title>\n" +
                "        <!-- link to main stylesheet -->\n" +
                "        <link rel=\"stylesheet\" type=\"text/css\" href=\"main.css\">\n" +
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
                "    <h2>Cart</h2>\n" +
                "    </a>");
        if (cart != null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, user, password);
                Statement st = conn.createStatement();

                out.println("<table>");
                int totalPrice = 0;
                for (int i = 0; i < cart.size(); i++) {
                    String sql = "SELECT * FROM `Products` WHERE pid = " + cart.get(i).get(0);
                    ResultSet rs = st.executeQuery(sql);
                    while (rs.next()) {
                        String name = rs.getString("name");
                        int price = rs.getInt("price");
                        totalPrice += price * cart.get(i).get(1);
                        String material = rs.getString("material");
                        String description = rs.getString("description");
                        String img = response.encodeURL(rs.getString("img"));
                        int inv = rs.getInt("inv");
                        int newpid = rs.getInt("pid");
                        out.println("   <tr class = 'itemBox'>" +
                                "       <td class = 'picCol'>" +
                                "           <a href='item?pid=" + newpid + "'>" +
                                "           <img src='" + img + "' alt=" + name + " class='fill grow'>" +
                                "           </a>" +
                                "       </td>" +
                                "       <td class = 'descCol'><p>" + name + "</p><p>Price: $" + price + "</p><p>Material: " + material + "</p><p>Quantity: " + cart.get(i).get(1) + "</p></td>" +
                                "       </tr>");
                    }
                    rs.close();
                }
                out.println("</table>");
                conn.close();
                out.println("<h2>Total Price: $" + totalPrice + "</h2>");
            } catch (Exception e) {
                out.println(e);
            }
        }
        out.println("<p>Please enter all fields outlined in red</p>" +
                "<form action=\"purchase\" method=\"POST\">'" +
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