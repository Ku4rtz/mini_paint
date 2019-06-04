/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice4;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author JEROME
 */
public class RecentFile implements Serializable, Comparable<RecentFile> {
    //Properties
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.FRANCE);
    Date date;
    private String path;
    private String name;

    //Constructor
    public RecentFile(Date date, String path, String name) {
        this.date = date;
        this.path = path;
        this.name = name;
    }

    //Getters and setters
    public Date getDate() {
        return this.date;
    }
    
    public String getFormattedDate(){
        return dateFormat.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public int compareTo(RecentFile recentFile) {
        return getDate().compareTo(recentFile.getDate());
    }
}
