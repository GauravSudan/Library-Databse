/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gaurav Sudan
 */
@WebServlet(name = "displays", urlPatterns = {"/displays"})
public class displays extends HttpServlet {
    String a1="null";
       

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
            
                        try
                        {
            String a1=request.getParameter("name");
            String b1=request.getParameter("author");
            String c1=request.getParameter("code");
            String d1=request.getParameter("description");
            

            Class.forName("com.mysql.jdbc.Driver");
            Connection con =(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/csefdb2","root","");
            Statement st = (Statement) con.createStatement();
            String query = "SELECT * FROM book";
            ResultSet resultSet = st.executeQuery(query);
          
            while (resultSet.next()) {
                String book = resultSet.getString("BOOK_NAME");
                String author = resultSet.getString("AUTHOR");
                String code = resultSet.getString("CODE");
                String description = resultSet.getString("DESCRIPTION");
            
                
            out.println("<html>");
            out.println("<center>");
            out.println("<head>");
            out.println("<title>Servlet First</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<form action=\"print\">");
            out.println("<table border="+2+">");
            
            out.println("<tr>");
            out.println("<th>BOOK NAME</th>");
            out.println("<th>AUTHOR NAME</th>");
            out.println("<th>CODE</th>");
            out.println("<th>DESCRIPTION</th>");
            out.println("</tr>");
            
            out.println("<tr>");
            out.println("<td>" +book+"</td>");
            out.println("<td>"+author+"</td>");
            out.println("<td>"+code+"</td>");
            out.println("<td>"+description +"</td>");
            out.println("</tr>");
            
            
            
            out.println("</table>");
            out.println("<br><br><br><br><br>");
            out.println("<input type=\"submit\" value=\"PRINT\"/>");
            out.println("</form>");
            out.println("</body>");
            out.println("</center>");
            out.println("</html>");

            } System.out.println(a1);
           
            con.close();
            // count=count+1;
            /* TODO output your page here*/
             
        }
        catch(Exception e)
        { 
            out.println(e.getMessage());    //obg=ject of PrintWriter class
        }
        finally {            
            
            out.close();
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
