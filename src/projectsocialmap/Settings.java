/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectsocialmap;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author M
 */
public class Settings {

    public Rectangle loginFormAlign(int x, int y) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Dimension windowSize = login.getSize();

        int windowX = ((screenSize.width - x) / 2);
        int windowY = ((screenSize.height - y) / 2);

        //System.out.println(screenSize.width + " " + login.getName());
        return new java.awt.Rectangle(windowX, windowY, x, y);
    }
}
