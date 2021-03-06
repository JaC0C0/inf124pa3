import java.net.URL;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.mysql.jdbc.Driver;
import java.sql.*;

public class item extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String url = "jdbc:mysql://matt-smith-v4.ics.uci.edu/inf124db057?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "inf124db057";
        String password = "wRd8MJP2XGWa";
        String sql = "SELECT * FROM `Products` WHERE pid = " + request.getParameter("pid");
        HttpSession session = request.getSession();
        session.setAttribute("pid", Integer.parseInt(request.getParameter("pid")));
        PrintWriter out = response.getWriter();
//        RequestDispatcher rd = request.getRequestDispatcher("history");
//        try{
//            rd.include(request,response);
//        } catch (Exception e) {
//            out.println(e);
//        }
        response.setContentType("text/html");
        String css = request.getContextPath() + "/webfiles/main.css";
        try {
            out.println("<html>\n" +
                    "    <head>\n" +
                    "        <title>Foil Me, Daddy</title>\n" +
                    "        <!-- link to main stylesheet -->\n" +
                    "        <link rel=\"stylesheet\" type=\"text/css\" href=\"main.css\">\n" +
                    "    </head>\n" +
                    "\n" +
                    "    <a href=\"PA4/store.jsp\" class=\"topHeader\"><h1>Foil Me, <em>Daddy</em></h1></a>\n" +
                    "    <nav class=\"menu\">\n" +
                    "        <ul>\n" +
                    "            <li><a href=\"PA4/store.jsp\">Home</a></li>\n" +
                    "            <li><a href=\"about.html\">About Us</a></li>\n" +
                    "            <li><a href=\"checkout\">Checkout</a></li>\n" +
                    "            <li><a href=\"confirmation.html\">Order Confirmations</a></li>\n" +
                    "        </ul>\n" +
                    "    </nav>\n" +
                    "    <a href=\"PA4/store.jsp\">\n" +
                    "    <h2>Store</h2>\n" +
                    "    </a>\n");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String material = rs.getString("material");
                String description = rs.getString("description");
                String img = response.encodeURL(rs.getString("img"));
//                String newImg = img.replace("", "/");
                int inv = rs.getInt("inv");
                int pid = rs.getInt("pid");

                out.println("    <h4>" + name + "</h4>\n" +
                        "    <img src='" + img + "' alt='" + name + "' class='fill'>\n" +
                        "    <p>" + description + "</p><p>Price: $" + price + "</p><p>Material: " + material + "</p>\n");
                out.println("<form action='cart' method='post'>\n" +
                        "<input type='hidden' name='pid' value='" + pid + "'/>\n" +
                        "<div>\n" +
                        "    <label>Quantity (1-999):</label>\n" +
                        "    <input type=\"text\" name=\"quantity\" required pattern=\"[0-9]{1,3}\">\n" +
                        "</div><br><br>\n" +
                        "<input id='addToCart' class='bttn' type='submit' value='Add to cart' name='submit'>\n" +
                        "</form>");
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            out.println(e);
        }

    }

    public void destroy() {

    }
}