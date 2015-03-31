/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectsocialmap;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author M
 */
public class TempData {
    public int eventCount;
    public String eventArray[];
    
    public void eventCount(){
        try {
            eventCount = 0;
            Class.forName("java.sql.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
            Statement st = conn.createStatement();
            String query = "SELECT * FROM event_log;";
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
                eventCount += 1;
            }
        } catch (Exception ex) {
            Logger.getLogger(TempData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
