package Class;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

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

//    public void letsClick(int period) {
//        String formatoFecha = "kk:mm:ss";
//        SimpleDateFormat fechaConFormato = new SimpleDateFormat(formatoFecha);
////        String click = "Class.Click -> " + cont + " - " + fechaConFormato.format(new Date());
//        JOptionPane.showMessageDialog(null, click);
//        timer.scheduleAtFixedRate(new TimerTask() {
//            public void run() {
//                bot.mousePress(mask);
//                bot.mouseRelease(mask);
//                cont++;
//                click = "Class.Click -> " + cont + " - " + fechaConFormato.format(new Date());
////                System.out.println(String.format("Class.Click -> " + cont + " - " + fechaConFormato.format(new Date())));
//            }
//        }, 1000, period * 1000);
//    }

}
