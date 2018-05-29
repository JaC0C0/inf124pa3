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
        response.setContentType("text/html");
        String css = request.getContextPath() + "/webfiles/main.css";
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>\n" +
                    "    <head>\n" +
                    "        <title>Foil Me, Daddy</title>\n" +
                    "        <!-- link to main stylesheet -->\n" +
                    "        <link rel=\"stylesheet\" type=\"text/css\" href=\"main.css\">\n" +
                    "    </head>\n" +
                    "\n" +
                    "    <a href=\"Store.php\" class=\"topHeader\"><h1>Foil Me, <em>Daddy</em></h1></a>\n" +
                    "    <nav class=\"menu\">\n" +
                    "        <ul>\n" +
                    "            <li><a href=\"Store.php\">Home</a></li>\n" +
                    "            <li><a href=\"about.html\">About Us</a></li>\n" +
                    "            <li><a href=\"confirmation.html\">Order Confirmations</a></li>\n" +
                    "        </ul>\n" +
                    "    </nav>\n" +
                    "    <a href=\"Store.php\">\n" +
                    "    <h2>Store</h2>\n" +
                    "    </a>\n");
            Class.forName ("com.mysql.jdbc.Driver");
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
                        "    <p>" + name + "</p><p>Price: $" + price + "</p><p>Material: " + material + "</p>\n");
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