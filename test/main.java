//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.mysql.jdbc.Driver;
import java.sql.*;

public class main extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String url = "jdbc:mysql://matt-smith-v4.ics.uci.edu/inf124db057?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "inf124db057";
        String password = "wRd8MJP2XGWa";
        String sql = "SELECT * FROM `Products` WHERE pid = 432231";
        response.setContentType("text/html");
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
            ResultSet m = st.executeQuery(sql);
            while (m.next()) {
                String productName = m.getString("name");
                int price = m.getInt("price");

                out.println("Name: " + productName);
                out.println(("Price: $" + price));
            }
            m.close();
            conn.close();
        } catch (Exception e) {
            out.println(e);
        }
    }

    public void destroy() {

    }
}