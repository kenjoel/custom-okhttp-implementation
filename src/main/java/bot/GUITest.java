package bot;

import bot.Register;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Provider;
import java.security.Security;
import java.text.NumberFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import bot.Register;
import org.conscrypt.Conscrypt;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GUITest extends JFrame {
    private JLabel totalRegisteredLabel;
    private JLabel captchaCountLabel;
    private JLabel noCaptchaCountLabel;
    private JProgressBar progressBar;
    private JButton startButton;
    private JButton stopButton;
    private JSpinner threadCountSpinner;
    private JSpinner maxAttemptsSpinner;
    private volatile boolean isRunning = false;
    private Thread generatorThread;
    private ScheduledExecutorService updateService;
    private Register register;

    public GUITest() {
        setupFrame();
        initializeComponents();
        layoutComponents();
        setupUpdateTimer();
    }

    private void setupFrame() {
        setTitle("Spotify Account Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(30, 30, 30));
    }

    private void initializeComponents() {
        // Initialize labels with modern styling
        totalRegisteredLabel = createStyledLabel("Total Registered: 0");
        captchaCountLabel = createStyledLabel("Captcha Encountered/Registered: 0/0");
        noCaptchaCountLabel = createStyledLabel("No Captcha Count: 0");

        // Progress bar with custom styling
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(30, 215, 96)); // Spotify green
        progressBar.setBackground(new Color(40, 40, 40));
        progressBar.setFont(new Font("Arial", Font.PLAIN, 12));

        // Spinners for configuration
        SpinnerNumberModel threadModel = new SpinnerNumberModel(1, 1, 1000, 1);
        threadCountSpinner = new JSpinner(threadModel);
        threadCountSpinner.setFont(new Font("Arial", Font.PLAIN, 12));

        SpinnerNumberModel attemptsModel = new SpinnerNumberModel(5, 1, 100000, 1);
        maxAttemptsSpinner = new JSpinner(attemptsModel);
        maxAttemptsSpinner.setFont(new Font("Arial", Font.PLAIN, 12));

        // Buttons with modern styling
        startButton = createStyledButton("Start Generation", new Color(30, 215, 96));
        stopButton = createStyledButton("Stop Generation", new Color(229, 9, 20));
        stopButton.setEnabled(false);

        // Add button listeners
        startButton.addActionListener(e -> startGeneration());
        stopButton.addActionListener(e -> stopGeneration());
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(30, 30, 30));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Configuration panel
        JPanel configPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        configPanel.setBackground(new Color(30, 30, 30));
        configPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(60, 60, 60)),
                "Configuration",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 12),
                Color.WHITE
        ));

        configPanel.add(createStyledLabel("Thread Count:"));
        configPanel.add(threadCountSpinner);
        configPanel.add(createStyledLabel("Max Attempts:"));
        configPanel.add(maxAttemptsSpinner);

        // Stats panel
        JPanel statsPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        statsPanel.setBackground(new Color(30, 30, 30));
        statsPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(60, 60, 60)),
                "Statistics",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 12),
                Color.WHITE
        ));

        statsPanel.add(totalRegisteredLabel);
        statsPanel.add(captchaCountLabel);
        statsPanel.add(noCaptchaCountLabel);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(30, 30, 30));
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);

        // Add components to main panel
        mainPanel.add(configPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(statsPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(progressBar);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(buttonPanel);

        add(mainPanel, BorderLayout.CENTER);
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 12));
        label.setForeground(Color.WHITE);
        return label;
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        return button;
    }

    private void setupUpdateTimer() {
        updateService = Executors.newSingleThreadScheduledExecutor();
        updateService.scheduleAtFixedRate(() -> {
            if (isRunning) {
                SwingUtilities.invokeLater(() -> {
                    updateStatistics();
                    updateProgressBar();
                });
            }
        }, 0, 500, TimeUnit.MILLISECONDS);
    }

    private void startGeneration() {
        if (!isRunning) {
            isRunning = true;
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            threadCountSpinner.setEnabled(false);
            maxAttemptsSpinner.setEnabled(false);

            // Create new Register instance with configured values
            register = new Register(
                    (Integer) threadCountSpinner.getValue(),
                    (Integer) maxAttemptsSpinner.getValue()
            );

            // Reset counters
            Register.captchaCount.set(0);
            Register.nocaptchaCount.set(0);

            generatorThread = new Thread(() -> {
                try {
                    register.startGeneration();
                } catch (Exception e) {
                    e.printStackTrace();
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(
                                this,
                                "Error during generation: " + e.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        stopGeneration();
                    });
                }
            });
            generatorThread.start();
        }
    }

    private void stopGeneration() {
        if (isRunning) {
            isRunning = false;
            if (register != null) {
                register.stopGeneration();
            }
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            threadCountSpinner.setEnabled(true);
            maxAttemptsSpinner.setEnabled(true);
        }
    }

    private void updateStatistics() {
        int captcha = Register.captchaCount.get();
        int noCaptcha = Register.nocaptchaCount.get();
        int captchaEncountered = Register.captchaEncountered.get();
        int total = captcha + noCaptcha;

        totalRegisteredLabel.setText("Total Registered: " + NumberFormat.getInstance().format(total));
        captchaCountLabel.setText("Captcha Encountered/Registered: " + NumberFormat.getInstance().format(captchaEncountered) + "/" + NumberFormat.getInstance().format(captcha));
        noCaptchaCountLabel.setText("No Captcha Count: " + NumberFormat.getInstance().format(noCaptcha));
    }

    private void updateProgressBar() {
        int maxAttempts = (Integer) maxAttemptsSpinner.getValue();
        int currentTotal = Register.captchaCount.get() + Register.nocaptchaCount.get();
        int progress = (int) ((double) currentTotal / maxAttempts * 100);
        progressBar.setValue(Math.min(progress, 100));
        progressBar.setString(progress + "%");
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            GUITest gui = new GUITest();
            gui.setVisible(true);
        });
    }
}