/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Michael
 */
public class SavePokemon extends HttpServlet {

    static int unnamedCount = 1;
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
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(true);
            String name = (String)request.getParameter("name");
            if (name.equals("")) {
                name = "Unnamed(" + unnamedCount + ")";
                unnamedCount++;
            }
            ArrayList<Integer> stats = new ArrayList<>();
            populateStatsArray(stats, request);
            session.setAttribute(name, stats);
            out.println("<span class='saved-pokemon' id='"+name+"'>" + name + " - " + stats + "</span> [delete] [rename] [^] [v]<br>");
        }
    }
    
    private void populateStatsArray(ArrayList<Integer> stats, HttpServletRequest req) {
        stats.add(Integer.parseInt(req.getParameter("hp")));
        stats.add(Integer.parseInt(req.getParameter("att")));
        stats.add(Integer.parseInt(req.getParameter("def")));
        stats.add(Integer.parseInt(req.getParameter("sat")));
        stats.add(Integer.parseInt(req.getParameter("sdf")));
        stats.add(Integer.parseInt(req.getParameter("spe")));
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
