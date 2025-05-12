import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DigitalClock extends JFrame {

    private JLabel timeLabel;  // Label to display time
    private SimpleDateFormat timeFormat;  // Time format
    private Timer timer;  // Timer to update clock
    private JTextField alarmTimeField;  // Field to set the alarm
    private JButton setAlarmButton;  // Button to set the alarm
    private String alarmTime = "";  // Stores alarm time

    public DigitalClock() {
        // Set up JFrame
        setTitle("Digital Clock");
        setSize(400, 200);  // Set window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window

        // Initialize the time label and set font and color
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Verdana", Font.BOLD, 50));  // Large font size
        timeLabel.setForeground(Color.BLACK);  // Text color
        timeLabel.setHorizontalAlignment(JLabel.CENTER);  // Center text

        // Initialize time format
        timeFormat = new SimpleDateFormat("HH:mm:ss");  // Hours:Minutes:Seconds

        // Initialize alarm input field and button
        alarmTimeField = new JTextField(5);  // Set a reasonable width for the alarm input
        setAlarmButton = new JButton("Set Alarm");

        // Set up the layout
        setLayout(new BorderLayout());
        add(timeLabel, BorderLayout.CENTER);
        
        JPanel panel = new JPanel();  // Panel for alarm time input
        panel.add(new JLabel("Set Alarm (HH:mm:ss):"));
        panel.add(alarmTimeField);
        panel.add(setAlarmButton);
        add(panel, BorderLayout.SOUTH);

        // Set up the timer to update time every 1000ms (1 second)
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTime();  // Update the time every second
            }
        });
        timer.start();  // Start the timer

        updateTime();  // Initialize the time when the program starts

        // Set up alarm functionality
        setAlarmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                alarmTime = alarmTimeField.getText().trim();
                if (!alarmTime.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Alarm set for: " + alarmTime);
                }
            }
        });
    }

    // Update the current time
    private void updateTime() {
        Date now = new Date();  // Get current time
        String timeString = timeFormat.format(now);  // Format the time to string
        timeLabel.setText(timeString);  // Set the formatted time on the label

        // Check if it's time for the alarm
        if (alarmTime.equals(timeString)) {
            JOptionPane.showMessageDialog(null, "ALARM! Time's up: " + timeString);
            alarmTime = "";  // Reset alarm after it goes off
        }
    }

    public static void main(String[] args) {
        // Create the clock and make it visible
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DigitalClock().setVisible(true);
            }
        });
    }
}
