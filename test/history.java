import java.net.URL;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.mysql.jdbc.Driver;
import java.sql.*;
import java.util.Arrays;

public class history extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = "jdbc:mysql://matt-smith-v4.ics.uci.edu/inf124db057?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "inf124db057";
        String password = "wRd8MJP2XGWa";
        HttpSession session = request.getSession();
        int pid = (int)session.getAttribute("pid");
        if (null == session.getAttribute("recentViewed")) {
            int[] array = new int[]{0,0,0,0,0};
            session.setAttribute("recentViewed", array);
        }
        int[] recentItems = (int[])session.getAttribute("recentViewed");
        boolean found = false;
        for (int i = 0; i < recentItems.length; i++) {
            if (recentItems[0] == pid) {
                break;
            } else if (recentItems[i] == pid) {
                for (int j = i - 1; j >= 0; j--) {
                    recentItems[j + 1] = recentItems[j];
                }
                found = true;
                recentItems[0] = pid;
                break;
            } else if (recentItems[i] == 0) {
                for (int j = i - 1; j >= 0; j--) {
                    recentItems[j + 1] = recentItems[j];
                }
                found = true;
                recentItems[0] = pid;
                break;
            }
        }
        if (!found) {
            for (int j = 3; j >= 0; j--) {
                recentItems[j + 1] = recentItems[j];
            }
            recentItems[0] = pid;
        }
        session.setAttribute("recentViewed", recentItems);
        PrintWriter out = response.getWriter();
        try {
            Class.forName ("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            if (recentItems[0] == 0) {
                out.println("<p>No recent items</p>");
            } else {
                out.println("<p>Recent Items</p><br>");
                for (int i = 0; i < 5; i++) {
                    String sql = "SELECT * FROM `Products` WHERE pid = " + Integer.toString(pid);
                    ResultSet rs = st.executeQuery(sql);
                    while (rs.next()) {
                        String name = rs.getString("name");
                        int price = rs.getInt("price");
                        String material = rs.getString("material");
                        String description = rs.getString("description");
                        String img = response.encodeURL(rs.getString("img"));
                        int inv = rs.getInt("inv");
//                        int pid = rs.getInt("pid");
                        out.println("   <tr class = 'itemBox'>" +
                                "       <td class = 'picCol'>" +
                                "           <a href='item?pid=" + pid + "'>" +
                                "           <img src='" + img + "' alt=" + name + " class='fill grow'>" +
                                "           </a>" +
                                "       </td>" +
                                "       <td class = 'descCol'><p>" + name + "</p><p>Price: $" + price + "</p><p>Material: " + material + "</p></td>" +
                                "       </tr>");
                    }
                    rs.close();
                }
            }
            conn.close();
        } catch (Exception e) {
            out.println(e);
        }

    }
}