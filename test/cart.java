import java.net.URL;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.mysql.jdbc.Driver;
import java.sql.*;

public class cart extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        session.setAttribute("pid",request.getParameter("pid"));
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
            out.println("<form action='cart' method='post'>\n");
        } catch (Exception e) {
            out.println(e);
        }

    }

    public void destroy() {

    }
}
