import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class purchase extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = "jdbc:mysql://matt-smith-v4.ics.uci.edu/inf124db057?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "inf124db057";
        String password = "wRd8MJP2XGWa";
        HttpSession session = request.getSession();
        ArrayList<ArrayList<Integer>> cart = (ArrayList<ArrayList<Integer>>)session.getAttribute("cart");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            String maxQuery = "SELECT MAX( oid ) as oid FROM `Orders`;";
            ResultSet maxIdSet = st.executeQuery(maxQuery);
            int maxId = maxIdSet.getInt("oid");
            if (maxId >= 0){
                maxId += 1;
            }else{
                maxId = 0;
            }

            String fName = request.getParameter("firstname");
            String lName = request.getParameter("lastname");
            String phone = request.getParameter("phone");
            String addr = request.getParameter("street");
            String city = request.getParameter("city");
            String zip = request.getParameter("zip");
            String cardNum= request.getParameter("cardNum");
            String cvv = request.getParameter("cvv");
            String ship = request.getParameter("ship");

            for (int i = 0; i < cart.size(); i++) {
                String sql = "INSERT INTO `Orders` (`oid`, `pid`, `quantity`, `firstName`, `lastName`, `phoneNum`, `addr`, `city`, `zip`, `cardNum`, `cvv`, `ship`) VALUES ('" + maxId + "','" + cart.get(i).get(0) + "', '" + cart.get(i).get(1) + "', '" + fName + "', '" + lName + "', '" + phone + "', '" + addr + "', '" + city + "', '" + zip + "', '" + cardNum + "', '" + cvv + "', '" + ship + "')";
                ResultSet rs = st.executeQuery(sql);
            }
            session.setAttribute("oid", maxId);
            conn.close();
            RequestDispatcher rd = request.getRequestDispatcher("confirmation");
            rd.forward(request, response);
        } catch (Exception e) {
            out.println(e);
        }
    }
}