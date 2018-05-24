import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

public class main extends HttpServlet{
//    public static void main() {
//        String url = "jdbc:mysql:@matt-smith-v4.ics.uci.edu";
//        String user = "inf124db057";
//        String password = "wRd8MJP2XGWa";
//
//        String sql = "SELECT * FROM `PRODUCTS` WHERE pid = 432231";
//
//        try {
////            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
//            Class.forName ("org.gjt.mm.mysql.Driver");
//            Connection conn = DriverManager.getConnection(url, user, password);
//            Statement st = conn.createStatement();
//            int m = st.executeUpdate(sql);
//            if (m == 1)
//                System.out.println("inserted successfully : "+sql);
//            else
//                System.out.println("insertion failed");
//            conn.close();
//        } catch (Exception e) {
//            System.err.println(e);
//        }
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String url = "jdbc:mysql:@matt-smith-v4.ics.uci.edu";
        String user = "inf124db057";
        String password = "wRd8MJP2XGWa";

        String sql = "SELECT * FROM `PRODUCTS` WHERE pid = 432231";

        try {
//            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            Class.forName ("org.gjt.mm.mysql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            int m = st.executeUpdate(sql);
            if (m == 1)
                System.out.println("inserted successfully : "+sql);
            else
                System.out.println("insertion failed");
            conn.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}