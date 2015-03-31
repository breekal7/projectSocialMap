/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectsocialmap;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;

/**
 *
 * @author M
 */
public class MyLibrary {

    private boolean windowStatus = true;
    private String username;
    private String uid;
    private String name;
    private String password;
    private String msisdn;
    private String latitude;
    private String longitude;
    private String date;
    private String time;
    private String statusMsg;
    private String dbPath = "jdbc:mysql://localhost:3306/project";
    private String dbuser = "root";
    private String dbpass = "";


    public void setWindowStatus(boolean bol) {
        this.windowStatus = bol;
    }

    public boolean getWindowStatus() {
        return this.windowStatus;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getUsername() {
        return this.username;
    }
    
    public void setName(String str){
        this.name = str;
    }
    
    public String getName(){
        return this.name;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setMsisdn(String str) {
        this.msisdn = str;
    }

    public String getMsisdn() {
        return this.msisdn;
    }

    public void setLatitude(String str) {
        this.latitude = str;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLongitude(String str) {
        this.longitude = str;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getDate() {
        return this.getDate();
    }

    public void setStatusMsg(String str) {
        this.statusMsg = str;
    }

    public String getStatusMsg() {
        return this.statusMsg;
    }

    public String getDbPath() {
        return this.dbPath;
    }

    public String getDbUser() {
        return this.dbuser;
    }

    public String getDbPass() {
        return this.dbpass;
    }

    public String getTime() {
        Calendar calendar = new GregorianCalendar();
        String am_pm;
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        if (calendar.get(Calendar.AM_PM) == 0) {
            am_pm = "AM";
        } else {
            am_pm = "PM";
        }
        time = hour + ":" + minute + ":" + second + " " + am_pm;
        return time;
    }
    
    public void setUid(String str){
        this.uid = str;
    }
    
    public String getUid(){
        return this.uid;
    }
    
    public int unum() {
        int u = 0;
        try {
            Class.forName("java.sql.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
            Statement st = (Statement) conn.createStatement();
            String query = "SELECT * FROM user_info;";
            ResultSet rs = st.executeQuery(query);
            boolean last = rs.last();
            if (last) {
                u = rs.getRow();
            }
            System.out.println(u);

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return u;
    }
    
    
    
    
    
    
    
    
    
}
