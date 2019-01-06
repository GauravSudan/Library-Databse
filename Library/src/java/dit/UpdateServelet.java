/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dit;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gaurav Sudan
 */
@WebServlet(name = "UpdateServelet", urlPatterns = {"/UpdateServelet"})
public class UpdateServelet extends HttpServlet {

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
        try {
            String a=request.getParameter("name");
            String b=request.getParameter("author");
            String c=request.getParameter("code");
            String d=request.getParameter("description");
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/csefdb2","root","");
            PreparedStatement pst=con.prepareStatement("update book set BOOK_NAME = ?,AUTHOR = ? , DESCRIPTION=?  where  CODE=?");
            pst.setString(1,a);
            pst.setString(2,b);
            pst.setString(3,d);
            pst.setString(4,c);
            
            int status=pst.executeUpdate();
            if(status>0)
            {
                response.sendRedirect("Welcome.html");
            }
            else
            {
                response.sendRedirect("Fail.html");
            }
            //PreparedStatement pst=con.prepareStatement("update student set name = ? where password =?");
            con.close();
            // count=count+1;
            /* TODO output your page here*/
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet First</title>");  
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>You are user no :" +count+ "</h1>");
            out.println("</body>");
            out.println("</html>");
             
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
