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
    String clicker = "Bienvenid@ a Clicker!\n";
    String formatoFecha = "kk:mm:ss";
    SimpleDateFormat fechaConFormato = new SimpleDateFormat(formatoFecha);
    JTextArea ta;
    JScrollPane sp;

    public MyFrame(int segundos) throws AWTException {
        setLayout(null);
        setVisible(true);
        setBounds(50, 50, 300, 200);
        ta = new JTextArea();
        sp = new JScrollPane(ta);
        ta.append(clicker);
        sp.setBounds(0, 0, 250, 160);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(sp);
        letsClick(segundos);
    }

    public void letsClick(int period) {
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                bot.mousePress(mask);
                bot.mouseRelease(mask);
                cont++;
                clicker = "Click nº " + cont + " - " + fechaConFormato.format(new Date());
                ta.append("\n" + clicker);
                int len = ta.getDocument().getLength();
                ta.setCaretPosition(len);
            }
        }, 1000, period * 1000);
    }
}
