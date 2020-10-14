package com.clicker.app.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainSwing extends JFrame {

    Robot bot = new Robot();
    java.util.Timer timer;
    int mask = InputEvent.BUTTON1_DOWN_MASK;
    double period;

    String formatoFecha = "kk:mm:ss";
    SimpleDateFormat fechaConFormato = new SimpleDateFormat(formatoFecha);

    String clicker;
    int cont;

    private JPanel mainPanel;


    private JPanel decriptionPanel;
    private JLabel descriptionLabel;

    private JPanel inputPanel;
    private JTextField inputTextField;
    private JLabel segundosLabel;

    private JPanel consolePanel;
    private JLabel resultLabel;

    private JPanel buttonPanel;
    private JPanel startPanel;
    private JButton startButton;
    private JPanel stopPanel;
    private JButton stopButton;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JButton closeButton;
    private JButton minButton;
    private JPanel tittleButtonPanel;


    public MainSwing(String title) throws AWTException {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setContentPane(mainPanel);
        this.pack();
        stopButton.setEnabled(false);

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    period = Double.parseDouble(inputTextField.getText());
                    letsClick(period);
                    startButton.setEnabled(false);
                    stopButton.setEnabled(true);
                    resultLabel.setText("Start!!!");
                } catch (Exception ex) {
                    resultLabel.setForeground(Color.magenta);
                    resultLabel.setText("Debe introducir un número positivo.");
                    System.out.println(ex.getMessage());
                }
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    timer.cancel();
//                    timer.purge();
                    resultLabel.setForeground(Color.RED);
                    resultLabel.setText("Stopped!");
                    startButton.setEnabled(true);
                    stopButton.setEnabled(false);
                } catch (Exception ex) {
                    resultLabel.setForeground(Color.ORANGE);
                    resultLabel.setText("Todavía no ha iniciado Clicker!");
                }
            }
        });

        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closeButton.setBackground(new Color(25, 150, 225));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                closeButton.setBackground(new Color(25, 125, 225));
            }
        });

        minButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                minButton.setBackground(new Color(25, 150, 225));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                minButton.setBackground(new Color(25, 125, 225));
            }
        });

        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setBackground(new Color(50, 135, 225));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startButton.setBackground(new Color(25, 125, 225));
            }
        });

        stopButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                stopButton.setBackground(new Color(200, 51, 29));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                stopButton.setBackground(new Color(225, 41, 29));
            }
        });
    }

    public void letsClick(double secs) {
        timer = new Timer();
        cont = 0;
        secs = secs * 1000;
        int milisecs = (int) secs;
        resultLabel.setForeground(new Color(235, 218, 42));
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                bot.mousePress(mask);
                bot.mouseRelease(mask);
                cont++;
                clicker = "Click nº " + cont + " - " + fechaConFormato.format(new Date());
                resultLabel.setText(clicker);
                System.out.println(clicker);
            }
        }, 1000, milisecs);
    }

    public static void main(String[] args) throws AWTException {
        MainSwing frame = new MainSwing("Clicker");
        frame.frameDetails(frame);
    }

    public void frameDetails(MainSwing frame) {
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);

        designDetails();
    }

    public void designDetails() {
        closeButton.setBorderPainted(false);
        minButton.setBorderPainted(false);
        startButton.setBorderPainted(false);
        stopButton.setBorderPainted(false);
    }


}
