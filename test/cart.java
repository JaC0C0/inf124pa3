import java.net.URL;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.mysql.jdbc.Driver;
import java.sql.*;
import java.util.ArrayList;
import java.net.URLEncoder;

public class cart extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        ArrayList<ArrayList<Integer>> cart = new ArrayList<ArrayList<Integer>>();

        if (session.getAttribute("cart") != null) {
          cart = (ArrayList<ArrayList<Integer>>)session.getAttribute("cart");
        }
        ArrayList<Integer> item = new ArrayList<Integer>();
        item.add(Integer.parseInt(request.getParameter("pid")));
        item.add(Integer.parseInt(request.getParameter("quantity")));
        cart.add(item);
        session.setAttribute("cart", cart);

        PrintWriter out = response.getWriter();
//        RequestDispatcher rd = request.getRequestDispatcher("history");
//        try{
//            rd.include(request,response);
//        } catch (Exception e) {
//            out.println(e);
//        }
        response.setContentType("text/html");

        try {
            RequestDispatcher rd = request.getRequestDispatcher("item?pid=" + URLEncoder.encode(request.getParameter("pid"), "UTF-8"));
            rd.include(request, response);
            out.println("    <h4>Item Added to Cart</h4>\n");
        } catch (Exception e) {
            out.println(e);
        }

    }

    public void destroy() {

    }
}
