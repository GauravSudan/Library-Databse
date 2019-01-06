/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gaurav Sudan
 */
@WebServlet(name = "print", urlPatterns = {"/print"})
public class print extends HttpServlet {
            
FileWriter fw=null;		//open file
BufferedWriter bw=null;	//write data onto file from buffer

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
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
            
            
try

{
fw=new FileWriter("print.txt",false);	//false means if file exist,it will delete it and create new file
bw=new BufferedWriter(fw);
}
catch(Exception e1)
{
System.out.println("File path not found");

}

try
{
bw.write("              !!BOOK CORNER!!			");
bw.newLine();
bw.write("_______________________________________________________");
bw.newLine();

bw.write("Name of Book :"+book);
bw.newLine();
bw.write("Author of Book :"+author);
bw.newLine();

bw.write("Code of Book :"+code);
bw.newLine();
bw.write("Description of Book:"+description);
bw.newLine();

}
catch (Exception n)
{}
            
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet print</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet print at </h1>");
            out.println("</body>");
            out.println("</html>");*/
            }
        }
            catch (Exception ee)
            {}
         finally { 
            try
{
bw.close();
fw.close();

}
catch(Exception e2)
{}
            out.close();
        }
        try
{
	ProcessBuilder pb=new ProcessBuilder("Notepad.exe","C:\\Users\\Gaurav Sudan\\AppData\\Local\\VirtualStore\\Program Files (x86)\\Apache Software Foundation\\Apache Tomcat 7.0.14\\bin\\print.txt");		//to open notepad automatically
	pb.start();
}
catch(Exception e2)
{}
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(print.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(print.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(print.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(print.class.getName()).log(Level.SEVERE, null, ex);
        }
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
