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
            out.println("<html>\n" +
                    "    <head>\n" +
                    "        <title>Foil Me, Daddy</title>\n" +
                    "        <!-- link to main stylesheet -->\n" +
                    "        <link rel=\"stylesheet\" type=\"text/css\" href=\"main.css\">\n" +
                    "    </head>\n" +
                    "\n" +
                    "    <a href=\"productPage\" class=\"topHeader\"><h1>Foil Me, <em>Daddy</em></h1></a>\n" +
                    "    <nav class=\"menu\">\n" +
                    "        <ul>\n" +
                    "            <li><a href=\"productPage\">Home</a></li>\n" +
                    "            <li><a href=\"about.html\">About Us</a></li>\n" +
                    "            <li><a href=\"checkout\">Checkout</a></li>\n" +
                    "            <li><a href=\"confirmation.html\">Order Confirmations</a></li>\n" +
                    "        </ul>\n" +
                    "    </nav>\n" +
                    "    <a href=\"productPage\">\n" +
                    "    <h2>Store</h2>\n" +
                    "    </a>\n");
            out.println("    <h4>Item Added to Cart</h4>\n");
        } catch (Exception e) {
            out.println(e);
        }

    }

    public void destroy() {

    }
}
