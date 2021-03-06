package com.clicker.app.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainSwing extends JFrame {

    // Variables para el timer
    Robot bot = new Robot();
    java.util.Timer timer;

    // La fecha sólo mostrara la hora (24h:mm:ss)
    String formatoFecha = "kk:mm:ss";
    SimpleDateFormat fechaConFormato = new SimpleDateFormat(formatoFecha);

    String consoleMessage;
    int cont;

    int posX = 0;
    int posY = 0;

    private JPanel mainPanel;

    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel tittleButtonPanel;
    private JButton closeButton;
    private JButton minButton;

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

    private JRadioButton clickRatonRadioButton;
    private JRadioButton teclaCtrlRadioButton;
    private JPanel radioButtonPanel;
    ButtonGroup group = new ButtonGroup();


    public MainSwing(String title) throws AWTException {
        super(title);

        setUndecorated(true);
        setIcon();
        setContentPane(mainPanel);
        pack();

        stopButton.setEnabled(false);
        inputTextField.setText("59");
        resultLabel.setText("Presione Start para comenzar");

        group.add(clickRatonRadioButton);
        group.add(teclaCtrlRadioButton);
        teclaCtrlRadioButton.setSelected(true);

        JFrame frame = this;

        // Mover Undecorated JFrame
        titlePanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                posX = e.getX();
                posY = e.getY();
            }
        });

        titlePanel.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent evt) {
                //sets frame position when mouse dragged
                setLocation(evt.getXOnScreen() - posX, evt.getYOnScreen() - posY);

            }
        });

        // Acciones al pulsar botones

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        minButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                minimizar(frame);
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    letsClick();
                } catch (Exception ex) {
                    resultLabel.setForeground(Color.MAGENTA);
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
                    resultLabel.setForeground(Color.RED);
                    resultLabel.setText("Stopped!");
                    startButton.setEnabled(true);
                    stopButton.setEnabled(false);
                } catch (Exception ex) {
                    resultLabel.setForeground(Color.MAGENTA);
                    resultLabel.setText("Se ha producido un error!");
                }
            }
        });


        // Efecto Hover sobre los botones

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

    public static void main(String[] args) throws AWTException {
        // Construye el JFrame con lo establecido en el Constructor
        MainSwing frame = new MainSwing("Clicker");
        // Ajustes del JFrame
        frame.frameDetails(frame);
        // Si queremos que al abrir arranque y se minimice automaticamente
        // frame.letsClick();
        // frame.minimizar(frame);

    }

    public void frameDetails(MainSwing frame) {
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(400, 275);
        frame.setLocationRelativeTo(null);

        // Más ajustes del JFrame
        designDetails();
    }

    public void designDetails() {
        closeButton.setBorderPainted(false);
        minButton.setBorderPainted(false);
        startButton.setBorderPainted(false);
        stopButton.setBorderPainted(false);
    }

    // Método que inicia el autoClick

    public void letsClick() {
        timer = new Timer();
        cont = 0;
        double secs = Double.parseDouble(inputTextField.getText());
        secs = secs * 1000;
        int milisecs = (int) secs;

        startButton.setEnabled(false);
        stopButton.setEnabled(true);
        resultLabel.setText("Start!!!");


        resultLabel.setForeground(new Color(235, 218, 42));
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (teclaCtrlRadioButton.isSelected()) {
                    bot.keyPress(KeyEvent.VK_CONTROL);
                    bot.keyRelease(KeyEvent.VK_CONTROL);
                } else {
                    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                }
                cont++;
                consoleMessage = "Click nº " + cont + " - " + fechaConFormato.format(new Date());
                resultLabel.setText(consoleMessage);
                System.out.println(consoleMessage);
            }
        }, 1000, milisecs);
    }

    public void setIcon() {
        URL urlImage = ClassLoader.getSystemResource("img/mouse-icon-2.png");
        setIconImage(Toolkit.getDefaultToolkit().createImage(urlImage));
    }

    public void minimizar(JFrame frame) {
        setState(Frame.ICONIFIED);
        frame.setVisible(false);
        // Añadir el icono al System Tray
        moveToSystemTray(frame);
    }

    public void moveToSystemTray(JFrame frame) {
        if (!SystemTray.isSupported()) {
            System.out.println("System tray is not supported !!! ");
            return;
        }
        URL urlImage = ClassLoader.getSystemResource("img/mouse-icon-1.png");
        Image image = Toolkit.getDefaultToolkit().createImage(urlImage);

        SystemTray systemTray = SystemTray.getSystemTray();
        PopupMenu trayPopupMenu = new PopupMenu();

        TrayIcon trayIcon = new TrayIcon(image, "Clicker", trayPopupMenu);
        trayIcon.setImageAutoSize(true);

        //1t menuitem for popupmenu
        MenuItem action = new MenuItem("Open");
        action.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setExtendedState(JFrame.NORMAL);
                frame.setVisible(true);
                systemTray.remove(trayIcon);
            }
        });
        trayPopupMenu.add(action);

        //2nd menuitem of popupmenu
        MenuItem close = new MenuItem("Close");
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        trayPopupMenu.add(close);

        //Open on double click
        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 1) {
                    frame.setExtendedState(JFrame.NORMAL);
                    frame.setVisible(true);
                    frame.toFront();
                    systemTray.remove(trayIcon);
                }
            }
        });


        try {
            systemTray.add(trayIcon);
        } catch (AWTException awtException) {
            awtException.printStackTrace();
        }
    }


}
