/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michael
 */
public class HexColorGenerator extends HttpServlet {

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
            int statNumber = Integer.parseInt(request.getParameter("statNumber"));
            String barHTMLString = "<div style='width:"+(statNumber/2)+"%;height:12px;border:1px solid #ddd;background-color:#"+getHexFromStat(statNumber)+"'>";
            out.write(barHTMLString);
        }
    }
    
    
    public static String getHexFromStat(int stat) {
        long result, difference;
        if (stat < 50) 
            return "ff0000";
        // just a whole bunch of hard-coded approximations and actual values
        switch (stat) {
            case 144:   return "20ff11"; //actual
            case 145:   return "1aff15"; //actual
            case 146:   return "1aff17";
            case 147:   return "10ff1d"; //actual
            case 148:   return "02ff22";
            case 149:   return "02ff27";
            case 150:   return "02ff2a"; //actual
            case 151:   return "02ff2f";
            case 152:   return "02ff33";
            case 153:   return "02ff37";
            case 154:   return "02ff3b"; //actual
            case 155:   return "02ff3f"; //actual
            case 156:   return "02ff40";
            case 157:   return "02ff42";
            case 158:   return "02ff45";
            case 159:   return "02ff50"; //actual
            case 160:   return "02ff55"; //actual
            case 161:   return "02ff59";
            case 162:   return "02ff5d";
            case 163:   return "02ff62";
            case 164:   return "02ff66"; //actual
            case 165:   return "02ff6a"; //actual
            case 166:   return "02ff6f";
            case 167:   return "02ff74";
            case 168:   return "02ff77";
            case 169:   return "02ff7b";
            case 170:   return "02ff7f"; //actual
            case 171:   return "02ff83";
            case 172:   return "02ff87";
            case 173:   return "02ff8b";
            case 174:   return "02ff90";
            case 175:   return "02ff94"; //actual
            case 176:   return "02ff98";
            case 177:   return "02ff9c";
            case 178:   return "02ffa0";
            case 179:   return "02ffa5";
            case 180:   return "02ffaa"; //actual
            case 181:   return "02ffaf";
            case 182:   return "02ffb4";
            case 183:   return "02ffb8";
            case 184:   return "02ffbb"; //actual
            case 185:   return "02ffbf"; //actual
            case 186:   return "02ffc3";
            case 187:   return "02ffc7";
            case 188:   return "02ffca";
            case 189:   return "02ffd0";
            case 190:   return "02ffd4"; //actual
            case 191:   return "02ffd8";
            case 192:   return "02ffdb";
            case 193:   return "02ffe0";
            case 194:   return "02ffe5"; //actual
            case 195:   return "02ffea";
            case 196:   return "02fff0";
            case 197:   return "02fff4";
            case 198:   return "02fff8";
            case 199:   return "02fffb";
        }
        if (stat < 101) {
            difference = stat - 50;
            if (difference % 2 == 0) {
                result = Long.parseLong("ff0000", 16) + 2560 * (difference/2);
                if (stat >= 78) {
                    if (stat >= 90)
                        return Long.toHexString(result + 1024);
                    return Long.toHexString(result + 512);
                }
                return Long.toHexString(result);
            } else {
                result = Long.parseLong("ff0000", 16) + 1536 * (difference/2);
                result += 1024 * (difference - (difference/2));
                if (stat >= 65) {
                    if (stat >= 81)
                        return Long.toHexString(result + 1024);
                    return Long.toHexString(result + 512);
                } else {
                    return Long.toHexString(result);
                }
            }       
        } else if (stat < 200) { // goes up to 143 in practice
            difference = stat - 101;
            if (stat % 2 == 0) {
                result = Long.parseLong("faff00", 16) - Long.parseLong("060000", 16) * (difference/2);
                result -= Long.parseLong("040000", 16) * (difference - (difference/2));
                if (stat >= 110) {
                    if (stat >= 130)
                        return Long.toHexString(result - Long.parseLong("040000", 16));
                    return Long.toHexString(result - Long.parseLong("020000", 16));
                } else {
                    return Long.toHexString(result);
                }
            } else {
                result = Long.parseLong("faff00", 16) - Long.parseLong("0a0000", 16) * (difference/2);
                if (stat >= 121)
                    return Long.toHexString(result - Long.parseLong("020000", 16));
                return Long.toHexString(result);
            }
        } else {
            return "02ffff";
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
