import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Click {

    Robot bot = new Robot();
    Timer timer = new Timer();
    int mask = InputEvent.BUTTON1_DOWN_MASK;
    int cont = 0;

    Click() throws AWTException {
    }

    public void letsClick() {
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                bot.mousePress(mask);
                bot.mouseRelease(mask);
                cont ++;
                System.out.println("Click -> " + cont);
            }
        }, 10000, 59000);
    }

}
