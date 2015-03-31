/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectsocialmap;

import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author M
 */
public class DbLibrary {

    Connection con;
    Statement st;
    ResultSet rs;
    int resultNum = 0;
    int size;
    int eventNum;
    //String frndArray[];
   // Vector<String> myFriends = new Vector<String>();

    DbLibrary(String dbPath, String user, String pass) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String sourceURL = "jdbc:mysql://localhost:3306/project";
            con = (Connection) DriverManager.getConnection(sourceURL, user, pass);
            st = con.createStatement();
            //System.out.println("Connected");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /*public void flist(String obj) {
        String u;
        try {
            u = obj;
            int i = 0;
            String dbgetFrnd2 = "SELECT * FROM friends WHERE statusCurrent = 'ok' AND sentFrom = '" + u + "';";
            ResultSet rs1 = st.executeQuery(dbgetFrnd2);
            while (rs1.next()) {
                myFriends.add(rs1.getString("Reqto"));
            }
            String dbgetFrnd1 = "SELECT * FROM friends WHERE statusCurrent = 'ok' AND Reqto = '" + u + "';";
            ResultSet rs2 = st.executeQuery(dbgetFrnd1);
            while (rs2.next()) {
                myFriends.add(rs2.getString("sentFrom"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }*/

    public String dbgetUserId(MyLibrary obj) throws SQLException {
        String ret = "SELECT id From user_info WHERE username='" + obj.getUsername() + "'";
        rs = st.executeQuery(ret);
        while (rs.next()) {
            ret = rs.getString("id");
        }
        return ret;
    }

    public String dbgetUserId(String msisdn) throws SQLException {
        String ret = "SELECT * From user_info WHERE phone='" + msisdn + "'";
        rs = st.executeQuery(ret);
        while (rs.next()) {
            ret = rs.getString("id");
        }
        return ret;
    }

    public boolean loginInfoCheck(String user, String pass, String phone) {
        String ret = "SELECT * From user_info WHERE username='" + user + "'";
        try {
            rs = st.executeQuery(ret);
            while (rs.next()) {
                // Process the row.  
                resultNum++;
            }


        } catch (SQLException ex) {
        }

        if (resultNum > 0) {
            return false;
        }

        return true;
    }

    public void loginInfoInsert(String user, String pass, String phone) {
        try {
            String loginInfoInsert = "INSERT INTO user_info (username, password, phone) VALUES ('"
                    + user + "', '" + pass + "', '" + phone + "')";
            //System.out.println(loginInfoInsert);

            if (loginInfoCheck(user, pass, phone)) {
                st.executeUpdate(loginInfoInsert);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dbLocationUpdate(MyLibrary obj) {
        try {
            String dbLocationUpdate = "INSERT INTO location_log (user_id, latitude, longitude, date, time) VALUES ('"
                    + dbgetUserId(obj) + "', '" + obj.getLatitude() + "', '" + obj.getLongitude() + "', '" + obj.getDate() + "', '" + obj.getTime() + "')";
            st.executeUpdate(dbLocationUpdate);
        } catch (SQLException ex) {
            Logger.getLogger(DbLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dbgetInfo(MyLibrary obj) {
        try {
            int i = 0;
            String uid = dbgetUserId(obj);
            String dbgetLocation = "SELECT * From location_log WHERE user_id = " + uid;
            rs = st.executeQuery(dbgetLocation);
            while (rs.next()) {
                if (Integer.parseInt(rs.getString("id")) > i) {
                    i = Integer.parseInt(rs.getString("id"));
                }
            }

            String dbgetLocation2 = "SELECT * From user_info, location_log WHERE location_log.id = " + i + " AND user_info.id = " + uid;
            rs = st.executeQuery(dbgetLocation2);
            while (rs.next()) {

                obj.setLatitude(rs.getString("latitude"));
                obj.setLongitude(rs.getString("longitude"));
                obj.setDate(rs.getString("date") + " " + rs.getString("time") + " (GMT + 06)");
                obj.setName(rs.getString("name"));

                try {
                    if (rs.getString("name").isEmpty()) {
                        obj.setName(rs.getString("username"));
                    }
                } catch (NullPointerException ex) {
                    obj.setName(rs.getString("username"));
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(DbLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String dbgetFrndNum(MyLibrary obj) {

        String uid;

        try {
            size = 0;
            uid = dbgetUserId(obj);
            // System.out.println(uid);
            String dbgetFrnd = "SELECT * FROM friends WHERE statusCurrent = 'pending' AND Reqto = '" + uid + "';";
            rs = st.executeQuery(dbgetFrnd);
            while (rs.next()) {
                size++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "You have " + size + " add to map requests";
    }

    public String dbgetEventsNum(MyLibrary obj) {
        /*int numEvents = 0;
        String uid;
        try {
        uid = obj.getUid();
        flist(uid);
        String esr = "SELECT * FROM event_log where user_id='" + uid + "';";
        rs = st.executeQuery(esr);
        while (rs.next()) {
        numEvents++;
        }
        for(int k=0;k<myFriends.size();k++){
        String esr2 = "select * from event_log where user_id='" + myFriends.elementAt(k) + "';";
        rs = st.executeQuery(esr2);
        while (rs.next()) {
        numEvents++;
        }}
        
        } catch (SQLException e) {
        System.out.println(e);
        }
        return "You have " + numEvents + " events on your map";*/
        String uid;
        

        try {
            size = 0;
            uid = dbgetUserId(obj);
            // System.out.println(uid);
            String dbgetFrnd = "SELECT * FROM friends WHERE statusCurrent = 'ok' AND Reqto = '" + uid + "';";
            rs = st.executeQuery(dbgetFrnd);
            while (rs.next()) {
                size++;
            }

            String dbgetFrnd2 = "SELECT * FROM friends WHERE statusCurrent = 'ok' AND sentFrom = '" + uid + "';";
            rs = st.executeQuery(dbgetFrnd2);
            while (rs.next()) {
                size++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[] fArray = new String[size];
        //Enter friend id into fArray
        try {
            int i = 0;
            eventNum = 0;
            uid = dbgetUserId(obj);
            // System.out.println(uid);
            String dbgetFrnd = "SELECT * FROM friends WHERE statusCurrent = 'ok' AND Reqto = '" + uid + "';";
            rs = st.executeQuery(dbgetFrnd);
            while (rs.next()) {
                fArray[i++] = rs.getString("sentFrom");
            }

            String dbgetFrnd2 = "SELECT * FROM friends WHERE statusCurrent = 'ok' AND sentFrom = '" + uid + "';";
            rs = st.executeQuery(dbgetFrnd2);
            while (rs.next()) {
                fArray[i++] = rs.getString("Reqto");
            }

            for (int j = 0; j < fArray.length; j++) {
                String dbgetEvnt = "SELECT * FROM event_log WHERE user_id = '" + fArray[j] + "';";
                rs = st.executeQuery(dbgetEvnt);
                while (rs.next()) {
                    eventNum++;
                }
            }

            String dbgetEvnt = "SELECT * FROM event_log WHERE user_id = '" + uid + "';";
            rs = st.executeQuery(dbgetEvnt);
            while (rs.next()) {
                eventNum++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }



        return "You have " + eventNum + " events on your map";
    }

    public void dbClose() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
