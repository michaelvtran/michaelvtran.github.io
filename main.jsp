<%-- 
    Document   : main
    Created on : Jun 10, 2016, 12:24:14 AM
    Author     : Michael
--%>

<!-- TODO: Implement typing a Pokemon name then getting its stats from pokeapi (probably) -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Smogon hue</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script src="updateStatbars.js"></script>
        <link rel="stylesheet" type="text/css" href="stylesheet.css"> 
        <link rel='shortcut icon' href='favicon.ico' type='image/x-icon'/ >
    </head>
    <body>
        <textarea rows="4" cols="50" style="margin-left:3%;margin-top:3%;"></textarea>
        
        <table class="stat-input-table">
            <tr>
                <td>HP: </td><td><input type="number" class="input-stat" id="hp" value="50" min="1"/></td>
            </tr>
            <tr>
                <td>Attack: </td><td><input type="number" class="input-stat" id="att" value="50" min="1"/></td>
            </tr>
            <tr>
                <td>Defense: </td><td><input type="number" class="input-stat" id="def" value="50" min="1"></td>
            </tr>
            <tr>
                <td>Special Attack: </td><td><input type="number" class="input-stat" id="sat" value="50" min="1"/></td>
            </tr>
            <tr>
                <td>Special Defense: </td><td><input type="number" class="input-stat" id="sdf" value="50" min="1"/></td>
            </tr>
            <tr>
                <td>Speed: </td><td><input type="number" class="input-stat" id="spe" value="50" min="1"/></td>
            </tr>
        </table>
        <br>
        <table class="stat-bar-table">
            <tr>
                <td class="stat-label">HP: </td>
                <td class="stat-number" id="hp-number">50</td>
                <td class="stat-bar" id="hp-bar"><div style="width:25%;height:12px;border:1px solid #ddd;background-color:#ff0000"></div></td>
            </tr>
            <tr>
                <td class="stat-label">Attack: </td>
                <td class="stat-number" id="att-number">50</td>
                <td class="stat-bar" id="att-bar"><div style="width:25%;height:12px;border:1px solid #ddd;background-color:#ff0000"></div></td>
            </tr>
            <tr>
                <td class="stat-label">Defense: </td>
                <td class="stat-number" id="def-number">50</td>
                <td class="stat-bar" id="def-bar"><div style="width:25%;height:12px;border:1px solid #ddd;background-color:#ff0000"></div></td>
            </tr>
            <tr>
                <td class="stat-label">Special Attack: </td>
                <td class="stat-number" id="sat-number">50</td>
                <td class="stat-bar" id="sat-bar"><div style="width:25%;height:12px;border:1px solid #ddd;background-color:#ff0000"></div></td>
            </tr>
            <tr>
                <td class="stat-label">Special Defense: </td>
                <td class="stat-number" id="sdf-number">50</td>
                <td class="stat-bar" id="sdf-bar"><div style="width:25%;height:12px;border:1px solid #ddd;background-color:#ff0000"></div></td>
            </tr>
            <tr>
                <td class="stat-label">Speed: </td>
                <td class="stat-number" id="spe-number">50</td>
                <td class="stat-bar" id="spe-bar"><div style="width:25%;height:12px;border:1px solid #ddd;background-color:#ff0000"></div></td>
            </tr>
        </table>
    </body>
</html>
