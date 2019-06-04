/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice4;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author JEROME
 */
public class Draw extends JPanel {

    private int posXInit;
    private int posYInit;
    private int posXMouse;
    private int posYMouse;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean onClick = false;
    private int typeDrawing = 2;
    private boolean isFilled = false;
    private ArrayList<RecentFile> recentFiles = new ArrayList<RecentFile>();
    private Color colorDrawing;
    private ArrayList<Shape> shapeList = new ArrayList<Shape>();
    private final int OVAL = 0;
    private final int RECT = 1;
    private final int LINE = 2;
    
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        
        //Loading all shape objects of list

        for (Shape shape : shapeList) {
            if (shape instanceof Ellipse) {
                Ellipse ellipse = (Ellipse) shape;
                g.setColor(ellipse.getColor());
                if (ellipse.isIsFilled()) {
                    g.fillOval(ellipse.getX(), ellipse.getY(), ellipse.getWidth(), ellipse.getHeight());
                } else {
                    g.drawOval(ellipse.getX(), ellipse.getY(), ellipse.getWidth(), ellipse.getHeight());
                }
            } else if (shape instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) shape;
                g.setColor(rectangle.getColor());
                if (rectangle.isIsFilled()) {
                    g.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
                } else {
                    g.drawRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
                }
            } else if (shape instanceof Ligne) {
                Ligne ligne = (Ligne) shape;
                g.setColor(ligne.getColor());
                g.drawLine(ligne.getX(), ligne.getY(), ligne.getxEnd(), ligne.getyEnd());
            }
        }

        g.setColor(colorDrawing); //Define color

        switch (typeDrawing) { //Drawing each shape function of shape chosen
            // Ovale
            case OVAL:
                if (posXMouse < posXInit) { // if mouse is at left from starting position
                    x = posXMouse;
                    width = posXInit - posXMouse;
                } else { // sinon
                    x = posXInit;
                    width = posXMouse - posXInit;
                }

                if (posYMouse < posYInit) { // if mouse is above the starting position
                    y = posYMouse;
                    height = posYInit - posYMouse;
                } else { // sinon
                    y = posYInit;
                    height = posYMouse - posYInit;
                }

                if (isFilled) { // If the shape is filled
                    g.fillOval(x, y, width, height);
                } else {
                    g.drawOval(x, y, width, height);
                }
                break;

            // Rectangle
            case RECT:
                if (posXMouse < posXInit) {
                    x = posXMouse;
                    width = posXInit - posXMouse;
                } else {
                    x = posXInit;
                    width = posXMouse - posXInit;
                }

                if (posYMouse < posYInit) {
                    y = posYMouse;
                    height = posYInit - posYMouse;
                } else {
                    y = posYInit;
                    height = posYMouse - posYInit;
                }

                if (isFilled) {
                    g.fillRect(x, y, width, height);
                } else {
                    g.drawRect(x, y, width, height);
                }
                break;

            // Ligne
            case LINE:
                // Starting positions
                x = posXInit;
                y = posYInit;
                // Ending positions
                width = posXMouse;
                height = posYMouse;

                g.drawLine(x, y, width, height);
                break;
            
            case 3:
                
        }
    }
    
    public void clearLast() { //Function deleting the last drawed shape
        posXInit = 0;
        posYInit = 0;
        posXMouse = 0;
        posYMouse = 0;
        x = 0;
        y = 0;
        width = 0;
        height = 0;
    }

    public void clearAll() { //Function deleting all the shapes
        this.shapeList.clear();
        posXInit = 0;
        posYInit = 0;
        posXMouse = 0;
        posYMouse = 0;
        x = 0;
        y = 0;
        width = 0;
        height = 0;
        repaint();
    }

    //Setters
    public void setTypeDrawing(int typeDrawing) {
        this.typeDrawing = typeDrawing;
    }

    public void setColorDrawing(Color colorDrawing) {
        this.colorDrawing = colorDrawing;
    }

    public void setIsFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }
    
    public void setShapeList(ArrayList<Shape> shapeList)
    {
        this.shapeList = shapeList;
    }
    
    public void setRecentFiles(ArrayList<RecentFile> recentFiles)
    {
        this.recentFiles = recentFiles;
    }
    
    //Getter
    public ArrayList<RecentFile> getRecentFiles()
    {
        return this.recentFiles;
    }
    
    //Finding file in list of recent files
    public RecentFile findRecentFile(String path) {
    for(RecentFile recentFile : recentFiles) {
        if(recentFile.getPath().toString().equals(path)) {
            return recentFile;
        }
    }
    return null;
}
    
    //Function saving work into a gile
    public void save(String path, String name, JList jlist) throws FileNotFoundException, IOException
    {
        //Save work into file
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(shapeList);
        oos.close();
        fos.close();
       
        //Adding or updating list of recent files depending if the file is already existing
        RecentFile checkedFile = findRecentFile(path);
        
        if(checkedFile != null)
        {
            checkedFile.setDate(new Date());
        }
        else
        {
            recentFiles.add(new RecentFile(new Date(), path, name));
        }
        
        //Sorting list for displaying in view in the correct order (the most recent at the top)
        Collections.sort(recentFiles, Collections.reverseOrder());
        
        Vector vect = new Vector();
        
        for (RecentFile files : recentFiles) {
            vect.add(files.getFormattedDate()+ " - " + files.getName() + " (" + files.getPath() + ")");
        }
        
        jlist.setListData(vect);
        
        //Put the recent files list into a file, then it can be displayed when start application
        FileOutputStream fos2 = new FileOutputStream(System.getProperty("user.home") + "//recentFiles.mj");
        ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
        oos2.writeObject(recentFiles);       
        oos2.close();
        fos2.close();
    }
    
    //Loading work with processing exception
    public void load(String path, String name, JFrame jframe) throws ClassNotFoundException, FileNotFoundException, IOException
    {
        FileInputStream fis;
        try {
            fis = new FileInputStream(path);
            try {
                ObjectInputStream ois = new ObjectInputStream(fis);
                this.setShapeList((ArrayList<Shape>)ois.readObject());
                fis.close();
                ois.close();
                jframe.setTitle(name);
            } catch (IOException ex) {
                Logger.getLogger(Draw.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Le fichier est introuvable, la liste sera rafraichie au prochain lancement de l'application.","Titre : Erreur fatale",JOptionPane.ERROR_MESSAGE);
            RecentFile toDelete = this.findRecentFile(path);
            recentFiles.remove(toDelete);
            FileOutputStream fos2 = new FileOutputStream(System.getProperty("user.home") + "//recentFiles.mj");
            ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
            oos2.writeObject(recentFiles);       
            oos2.close();
            fos2.close();
        }
        
        repaint();
    }

    public void undo() {
        if (!shapeList.isEmpty()) {
            Shape objectToRemove = shapeList.get(shapeList.size() - 1); //delete the last item of the shape list
            shapeList.remove(objectToRemove);
            posXInit = 0;
            posYInit = 0;
            posXMouse = 0;
            posYMouse = 0;
            x = 0;
            y = 0;
            width = 0;
            height = 0;
            repaint();
        }
    }

    public Draw() {

        this.typeDrawing = typeDrawing;
        this.colorDrawing = colorDrawing;

        //setBackground(Color.RED);
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (!onClick) { //When we are not clicking
                    posXInit = e.getX();
                    posYInit = e.getY()+30; //adapted to the cursor (pencil)
                    onClick = true; //We are clicking
                }

                posXMouse = e.getX();
                posYMouse = e.getY()+30;

                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                onClick = false; //We are not clicking anymore
                //adding the new shape to the shapelist
                if (typeDrawing == OVAL) {
                    shapeList.add(new Ellipse(isFilled, width, height, x, y, colorDrawing));
                } else if (typeDrawing == RECT) {
                    shapeList.add(new Rectangle(isFilled, width, height, x, y, colorDrawing));
                } else if (typeDrawing == LINE) {
                    shapeList.add(new Ligne(posXMouse, posYMouse, x, y, colorDrawing));
                }
                clearLast(); //Delete the last shape drawed (prevent from having 2 shapes at the same location)
                repaint();
            }
        });
    }
}
