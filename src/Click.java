import java.awt.*;
import java.awt.event.InputEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Click {

    Robot bot = new Robot();
    Timer timer = new Timer();
    int mask = InputEvent.BUTTON1_DOWN_MASK;
    int cont = 0;

    Click() throws AWTException {
    }

    public void letsClick(int period) {
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                bot.mousePress(mask);
                bot.mouseRelease(mask);
                cont ++;
                String formatoFecha = "kk:mm:ss";
                SimpleDateFormat fechaConFormato = new SimpleDateFormat(formatoFecha);
                System.out.println(String.format("Click -> " + cont + " - " + fechaConFormato.format(new Date())));
            }
        }, 1000, period);
    }

}
