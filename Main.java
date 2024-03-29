package Team4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends JFrame {

    private Timer timer1; // Timer for the first task
    private Timer timer2; // Timer for the second task
    private Timer timer3; // Timer for the third task

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;

    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;

    private JButton stopButton2;
    private JButton startButton3;

    public Main() {
        setTitle("Timers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new GridLayout(3, 1));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());

        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());

        // Panel 1 - Timer 1
        textField1 = new JTextField(10);
        JButton startButton1 = new JButton("Start");
        label1 = new JLabel("Timer 1: ");

        startButton1.addActionListener(e -> startTimer1());

        panel1.add(new JLabel("Timer la un anumit timp "));
        panel1.add(textField1);
        panel1.add(startButton1);
        panel1.add(label1);

        // Panel 2 - Timer 2
        textField2 = new JTextField(10);
        JButton startButton2 = new JButton("Start");
        stopButton2 = new JButton("Stop");
        label2 = new JLabel("Timer 2: ");

        startButton2.addActionListener(e -> startTimer2());
        stopButton2.addActionListener(e -> stopTimer2());

        panel2.add(new JLabel("Timer cu un interval: "));
        panel2.add(textField2);
        panel2.add(startButton2);
        panel2.add(stopButton2);
        panel2.add(label2);

        // Panel 3 - Timer 3
        textField3 = new JTextField(10);
        startButton3 = new JButton("Start");
        label3 = new JLabel("Timer 3: ");

        startButton3.addActionListener(e -> startTimer3());

        panel3.add(new JLabel("Timer cu o ora anumita (HH:mm:ss): "));
        panel3.add(textField3);
        panel3.add(startButton3);
        panel3.add(label3);

        // Add panels to frame
        add(panel1);
        add(panel2);
        add(panel3);
    }

    private void startTimer1() {
        try {
            if (timer1 != null) {
                timer1.cancel(); // Cancels the previous timer if exists
            }

            int seconds = Integer.parseInt(textField1.getText());
            timer1 = new Timer(); // Creates a new Timer
            timer1.scheduleAtFixedRate(new TimerTask() { // Schedules a task to be run at fixed intervals
                int secondsElapsed = 1;

                @Override
                public void run() {
                    if (secondsElapsed < seconds) {
                        label1.setText("Secunde trecute: " + secondsElapsed++);
                    } else {
                        timer1.cancel(); // Cancels the timer when time is up
                        label1.setText("Timpul a expirat!");
                    }
                }
            }, 0, 1000); // Initial delay 0, repeat every 1000 milliseconds (1 second)
        } catch (NumberFormatException e) {
            showErrorDialog("Introduceți un număr valid pentru timp.");
        }
    }

    private void startTimer2() {
        try {
            if (timer2 != null) {
                timer2.cancel(); // Cancels the previous timer if exists
            }

            int totalTime = Integer.parseInt(textField2.getText());
            timer2 = new Timer(); // Creates a new Timer
            timer2.scheduleAtFixedRate(new TimerTask() { // Schedules a task to be run at fixed intervals
                int elapsedTime = 1;

                @Override
                public void run() {
                    if (elapsedTime <= totalTime) {
                        label2.setText("Timp scurs: " + elapsedTime + " secunde");
                        elapsedTime++;
                    } else {
                        label2.setText("Timpul a expirat!");
                        elapsedTime = 1; // Resetează numărătoarea
                    }
                }
            }, 0, 1000); // Initial delay 0, repeat every 1000 milliseconds (1 second)
        } catch (NumberFormatException e) {
            showErrorDialog("Introduceți un număr valid pentru timp.");
        }
    }

    private void startTimer3() {
        try {
            if (timer3 != null) {
                timer3.cancel(); // Cancels the previous timer if exists
            }

            timer3 = new Timer(); // Creates a new Timer
            timer3.scheduleAtFixedRate(new TimerTask() { // Schedules a task to be run at fixed intervals
                @Override
                public void run() {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    String currentTime = sdf.format(new Date());
                    label3.setText("Ora curentă: " + currentTime);

                    if (currentTime.equals(textField3.getText())) {
                        label3.setText("Timpul a expirat la ora " + currentTime);
                        timer3.cancel(); // Cancels the timer when specified time is reached
                    }
                }
            }, 0, 1000); // Initial delay 0, repeat every 1000 milliseconds (1 second)
        } catch (Exception e) {
            showErrorDialog("Introduceți o oră validă (HH:mm:ss).");
        }
    }

    private void stopTimer2() {
        if (timer2 != null) {
            timer2.cancel(); // Cancels the second timer
        }
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Eroare", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
