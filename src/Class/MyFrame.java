package Class;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyFrame extends JFrame {

    Robot bot = new Robot();
    java.util.Timer timer = new Timer();
    int mask = InputEvent.BUTTON1_DOWN_MASK;
    int cont = 0;
    String clicker = "Bienvenid@ a Clicker!";
    String formatoFecha = "kk:mm:ss";
    SimpleDateFormat fechaConFormato = new SimpleDateFormat(formatoFecha);
    JTextArea label;

    public MyFrame(int segundos) throws AWTException {
        setLayout(null);
        setVisible(true);
        setBounds(50, 50, 300,200);
        label = new JTextArea();
        label.append("\n"+clicker);
        label.setBounds(10,10, 300, 200);
        add(label);
        letsClick(segundos);
    }

    public void letsClick(int period) {
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                bot.mousePress(mask);
                bot.mouseRelease(mask);
                cont++;
                clicker = "Class.Click -> " + cont + " - " + fechaConFormato.format(new Date());
                label.append("\n" + clicker);
            }
        }, 1000, period * 1000);
    }
}
