package Class;

import javax.swing.*;
import java.awt.*;

public class Click {

    public Click() throws AWTException {
    }

    int segundos;

    public void mainClick() throws AWTException {
        segundos = Integer.parseInt(JOptionPane.showInputDialog("Cada cuantos segundos desea hacer click?"));
        buildFrame(segundos);

    }

    public void buildFrame(int segundos) throws AWTException {
        MyFrame myFrame = new MyFrame(segundos);
        myFrame.setTitle("Clicker");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
