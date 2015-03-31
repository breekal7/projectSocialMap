/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectsocialmap;

/**
 *
 * @author M
 */
public class Map extends MyLibrary {
    double center[] = new double[2];
    String label;
    int zoom = 15;
    int screenSize[] = {800, 600};
    String mapLoader;
    
    Map(String latitude, String longitude, String label){
        this.center[0] = Double.valueOf(latitude);
        this.center[1] = Double.valueOf(longitude);
        this.label = label;
        this.mapLoader = "<html><head></head><body>"
                        + "<img src='http://maps.googleapis.com/maps/api/staticmap?center=" + latitude + "," + longitude 
                        + "&zoom=" + this.zoom + "&size=" + this.screenSize[0] + "x" + this.screenSize[1] + "&markers=color:blue%7Clabel:" + label + "%7C" + latitude + "," + longitude + "&sensor=false'>"
                        + "</body></html>";
    }
    
    public void setMap(){
        this.mapLoader = "<html><head></head><body>"
                        + "<img src='http://maps.googleapis.com/maps/api/staticmap?center=" + this.center[0] + "," + this.center[1] 
                        + "&zoom=" + this.zoom + "&size=" + this.screenSize[0] + "x" + this.screenSize[1] + "&markers=color:blue%7Clabel:" + label + "%7C" + this.center[0] + "," + this.center[1] + "&sensor=false'>"
                        + "</body></html>";
    }
    
    public int getZoom(){
        return this.zoom;
    }
    
    public void setZoom(int i){
        this.zoom = i;
    }
    
    double getX(){
        return this.center[0];
    }
    
    double getY(){
        return this.center[1];
    }
    
    public String mapLoader(){
        return this.mapLoader;
    }
    /*String initialText = "<html><head></head><body>"
                                + "<img src = 'http://maps.googleapis.com/maps/api/staticmap?center=Brooklyn+Bridge,New+York,NY&zoom=10&size=512x512&maptype=roadmap"
                                + "&markers=color:blue%7Clabel:M%7C40.702147,-74.015794&markers=color:green%7Clabel:G%7C40.711614,-74.012318"
                                + "&markers=color:red%7Ccolor:red%7Clabel:C%7C40.718217,-73.998284&sensor=false'>"
                                + "</body></html>";*/
}
