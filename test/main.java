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
    //            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            Class.forName ("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet m = st.executeQuery(sql);
            while (m.next()) {
                String productName = m.getString("name");
                int price = m.getInt("price");

                out.println("Name: " + productName);
                out.println(("PRice: $" + price));
            }
            m.close();
//            if (m == 0)
//                out.println("inserted successfully : "+sql);
//            else
//                out.println("insertion failed");
            conn.close();
        } catch (Exception e) {
            out.println(e);
        }
    }

    public void destroy() {

    }
}