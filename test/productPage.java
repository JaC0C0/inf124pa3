import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.mysql.jdbc.Driver;
import java.sql.*;

public class productPage extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String url = "jdbc:mysql://matt-smith-v4.ics.uci.edu/inf124db057?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "inf124db057";
        String password = "wRd8MJP2XGWa";
        String sql = "SELECT * FROM `Products`";
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
                    "    </a>\n" +
                    "    <table>");
            Class.forName ("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String material = rs.getString("material");
                String description = rs.getString("description");
                String img = rs.getString("img");
                int inv = rs.getInt("inv");
                int pid = rs.getInt("pid");


                out.println("   <tr class = 'itemBox'>" +
                        "       <td class = 'picCol'>" +
                        "           <a href='item.php?pid=" + pid + ">" +
                        "           <img src='" + img + "' alt=" + name + " class='fill grow'>" +
                        "           </a>" +
                        "       </td>" +
                        "       <td class = 'descCol'><p>" + name + "</p><p>Price: $" + price + "</p><p>Material: " + material + "</p></td>" +
                        "       </tr>");
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