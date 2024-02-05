import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Creating a Swing frame
        JFrame frame = new JFrame("Timer App");
        frame.setSize(300, 150); // Reduced height to make it look better
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating a panel to display messages and the button
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10); // Adding padding

        JLabel label = new JLabel("Acțiune executată la fiecare 2 secunde");
        panel.add(label, constraints);

        constraints.gridy = 1;
        JButton button = new JButton("Adaugă secunde");
        panel.add(button, constraints);

        // Centering the panel
        frame.add(panel, BorderLayout.CENTER);

        // Displaying the frame in the center of the screen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Creating a Timer object to update the button
        Timer buttonTimer = new Timer();

        // Scheduling the task to update the button every 2 seconds
        buttonTimer.schedule(new TimerTask() {
            int seconds = 2; // Number of seconds

            @Override
            public void run() {
                button.setText("" + seconds + "");
                seconds += 2;
            }
        }, 0, 2000); // Scheduling updates every 2 seconds

        // Adding an event listener for the button
        button.addActionListener(e -> {
            String text = label.getText();
            String[] parts = text.split(" ");
            int currentSeconds = Integer.parseInt(parts[parts.length - 2]);
            currentSeconds += 2;
            label.setText("Acțiune executată la fiecare 2 secunde");
        });

        // Creating and starting a separate execution thread to keep the application active
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100); // Waiting for 100 milliseconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
