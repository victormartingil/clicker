import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

public class Main {


    public static void main (String[] args) throws AWTException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Cada cuantos segundos desea hacer click?");
        int period = sc.nextInt() * 1000;
        Click click = new Click();
        click.letsClick(period);
    }

}
