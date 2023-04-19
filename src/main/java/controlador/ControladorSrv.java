/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.*;

/**
 *
 * @author sebas
 */
@WebServlet(name = "ControladorSrv", urlPatterns = {"/ControladorSrv"})
public class ControladorSrv extends HttpServlet {
    ListMascotas lista=new ListMascotas();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            
            //mascota
            String tipoMascota = request.getParameter("txtTipoMascota");
            boolean toxoplasmosis = Boolean.parseBoolean(request.getParameter("txtFelinoToxoplasmosis"));
            int nivelEntranamiento = Integer.parseInt(request.getParameter("txtCaninoNvEntrenamiento"));
             
            String nombre = request.getParameter("txtNombre");
            String raza = request.getParameter("txtRaza");
            String color = request.getParameter("txtColor");
            int edad = Integer.parseInt(request.getParameter("txtEdad"));
            
            CrearObjeto.pp(nombre, raza, color, edad, tipoMascota, toxoplasmosis, nivelEntranamiento);
            CrearObjeto.definirMascota();

            lista.agregarMascota(CrearObjeto.getMascota());
            
            CrearObjeto.definirNumeroM(lista.listaSize());
            
            
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href=\"stiloSrv.css\" rel=\"stylesheet\" type=\"text/css\"/> \n" +
"        <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
"        <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
"        <link href=\"https://fonts.googleapis.com/css2?family=Montserrat:wght@500&display=swap\" rel=\"stylesheet\"> ");
            out.println("<title>Servlet ControladorSrv</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<article>");           
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Nombre</th>");
            out.println("<th>Raza</th>");
            out.println("<th>Color</th>");
            out.println("<th>Edad</th>");
            out.println("<th>Otros</th>");
            out.println("<th>Accion</th>");
            out.println("</tr>");
            
            for(int i=0;i<lista.listaSize();i++){
                out.println("<tr>"+lista.consultarMascotas(i)+"</tr>");
            }
            
            if(request.getParameter("txteliminar") != null){
                lista.eliminarMascota(Integer.parseInt("txteliminar"));
            }
            
            out.println("</table>");
            out.println("<a href=\"index.html\">Agregar otra mascota</a>");
            out.println("</article>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
