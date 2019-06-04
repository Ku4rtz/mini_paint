/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice4;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author JEROME
 */
public class Main {
    
    public static void main(String[] args) {       
        View screen;
        try {
            screen = new View();
            screen.setVisible(true);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Un ou plusieurs fichiers manquent pour exécuter le programme. Veuillez réinstaller l'application.","Titre : Erreur fatale",JOptionPane.ERROR_MESSAGE);
        }
    }
}
