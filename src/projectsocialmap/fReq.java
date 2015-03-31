/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectsocialmap;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author Administrator
 */
public class fReq {

    public String getReqId(String p) {
        String reqId = "err";
        String sReq = "select * from user_info where phone='" + p + "';";
        try {
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
            Statement st = (Statement) conn.createStatement();
            ResultSet rs = st.executeQuery(sReq);
            if (rs.next()) {
                reqId = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("error in fReq");
            return null;
        }
        return reqId;
    }

    public String getReqPh(String i) {
        String reqPh = null;
        String sReq = "select * from user_info where id='" + i + "';";
        try {
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
            Statement st1 = (Statement) conn.createStatement();
            ResultSet rs1 = st1.executeQuery(sReq);
            if (rs1.next()) {
                reqPh = rs1.getString(5);
            }
        } catch (Exception e) {
            System.out.println("error in fReq");
            return null;
        }
        return reqPh;
    }


}
