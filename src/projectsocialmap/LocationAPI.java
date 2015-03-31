/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectsocialmap;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.net.URL;
import java.io.InputStream;
import java.sql.*;

public class LocationAPI extends MyLibrary {

    String date;

    LocationAPI(String username, String pass, String phone) {
        try {
            setUsername(username);
            setPassword(pass);
            setMsisdn(phone);
            String xmlURL = "http://phptiger.info/gp.php?uid=" + username + "&&pass=" + pass + "&&phone=" + phone;
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            //Document doc = docBuilder.parse (new File("book.xml"));
            URL url = new URL(xmlURL);
            InputStream stream = url.openStream();
            Document doc = docBuilder.parse(stream);

            // normalize text representation
            doc.getDocumentElement().normalize();
            /*System.out.println ("Root element of the doc is " + 
            doc.getDocumentElement().getNodeName());*/


            NodeList listOfPersons = doc.getElementsByTagName("LBSResponse");
            int totalPersons = listOfPersons.getLength();
            /*System.out.println("Total no of response : " + totalPersons);*/

            for (int s = 0; s < listOfPersons.getLength(); s++) {


                Node firstPersonNode = listOfPersons.item(s);
                if (firstPersonNode.getNodeType() == Node.ELEMENT_NODE) {


                    Element firstPersonElement = (Element) firstPersonNode;

                    //-------
                    NodeList firstNameList = firstPersonElement.getElementsByTagName("Latitude");
                    Element firstNameElement = (Element) firstNameList.item(0);

                    NodeList textFNList = firstNameElement.getChildNodes();
                    /*System.out.println("Latitude : " + 
                    ((Node)textFNList.item(0)).getNodeValue().trim());*/
                    setLatitude(((Node) textFNList.item(0)).getNodeValue().trim());

                    //-------
                    NodeList lastNameList = firstPersonElement.getElementsByTagName("Longitude");
                    Element lastNameElement = (Element) lastNameList.item(0);

                    NodeList textLNList = lastNameElement.getChildNodes();
                    /*System.out.println("Longitude : " + 
                    ((Node)textLNList.item(0)).getNodeValue().trim());*/
                    setLongitude(((Node) textLNList.item(0)).getNodeValue().trim());

                    //----
                    NodeList ageList = firstPersonElement.getElementsByTagName("Timestamp");
                    Element ageElement = (Element) ageList.item(0);

                    NodeList textAgeList = ageElement.getChildNodes();
                    /*System.out.println("Timestamp : " + 
                    ((Node)textAgeList.item(0)).getNodeValue().trim());*/
                    date = ((Node) textAgeList.item(0)).getNodeValue().trim();

                    //------
                    NodeList status = firstPersonElement.getElementsByTagName("Status");
                    Element statusElement = (Element) status.item(0);

                    NodeList textStatus = statusElement.getChildNodes();
                    /*System.out.println("Status : " + 
                    ((Node)textStatus.item(0)).getNodeValue().trim());*/
                    setStatusMsg(((Node) textStatus.item(0)).getNodeValue().trim());

                    //------

                }//end of if clause


            }//end of for loop with s var


        } catch (SAXParseException err) {
            System.out.println("** Parsing error" + ", line "
                    + err.getLineNumber() + ", uri " + err.getSystemId());
            System.out.println(" " + err.getMessage());

        } catch (SAXException e) {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();

        }catch (NullPointerException e) {
            setDate("0");
            setLatitude("0");
            setLongitude("0");
            setStatusMsg("Please insert correct login Information");
        }catch (Throwable t) {
            setDate("0");
            setLatitude("0");
            setLongitude("0");
            setStatusMsg("Please insert correct login Information");
        }
        //System.exit (0);

    }//end of getLocationInfo()

    LocationAPI() {

    }
    
    public LocationAPI flocation (String msisdn){
        String username = null;
        String pass = null;
        try {
            Class.forName("java.sql.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
            Statement st = conn.createStatement();
            String query = "Select * from user_info WHERE phone = '" + msisdn + "';";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                username = rs.getString("username");
                pass = rs.getString("password");
            }
            
            
            rs.close();
            st.close();
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(LocationAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new LocationAPI(username, pass, msisdn);
    }

    public boolean checkLogin() {
       if (getStatusMsg().equals("OK")) {
            return true;
        }
        return false;
    }

    @Override
    public String getDate() {
        return date;
    }
    
    
}