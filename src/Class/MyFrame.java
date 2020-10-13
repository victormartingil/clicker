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

    String formatoFecha = "kk:mm:ss";
    SimpleDateFormat fechaConFormato = new SimpleDateFormat(formatoFecha);

    String clicker;
    int cont = 0;

    JTextPane tp;
    JScrollPane sp;

//    EmptyBorder eb = new EmptyBorder(new Insets(10, 10, 10, 10));

    public MyFrame(int segundos) throws AWTException {
        tp = new JTextPane();
        sp = new JScrollPane(tp);

        setLayout(null);
        setVisible(true);
        setBounds(50, 50, 300, 150);

        sp.setBounds(-1, 0, 300, 150);
        tp.setBackground(Color.black);
        tp.setForeground(Color.yellow);


        add(sp);

        letsClick(segundos);
    }

    public void letsClick(int period) {
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                bot.mousePress(mask);
                bot.mouseRelease(mask);
                cont++;
                clicker = "Click nº " + cont + " - " + fechaConFormato.format(new Date());
                tp.setText(clicker);
            }
        }, 1000, period * 1000);
    }
}
