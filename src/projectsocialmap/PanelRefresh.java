/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectsocialmap;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author M
 */
public class PanelRefresh extends Thread {

    private int sleepTime = 1000;
    JLabel eventLabel, reqLabel, infoLatitude, infoLongitude, lastUpdate;
    DbLibrary db;
    MyLibrary library;

    public PanelRefresh(JLabel eventLabel, JLabel reqLabel, JLabel infoLatitude, JLabel infoLongitude, JLabel lastUpdate, DbLibrary db, MyLibrary library) {
        this.eventLabel = eventLabel;
        this.reqLabel = reqLabel;
        this.infoLatitude = infoLatitude;
        this.infoLongitude = infoLongitude;
        this.lastUpdate = lastUpdate;
        this.db = db;
        this.library = library;
        start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                eventLabel.setText(db.dbgetEventsNum(library));
                reqLabel.setText(db.dbgetFrndNum(library));
                db.dbgetInfo(library);
                infoLatitude.setText(library.getLatitude());
                infoLongitude.setText(library.getLongitude());
                lastUpdate.setText(library.getDate());
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(PanelRefresh.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
