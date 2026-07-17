package reelcounter;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.time.LocalDate;

public class SwipePanel extends JPanel {

    private int count;

    private Database db = new Database();

    private JLabel counterLabel;
    private JButton swipeButton;
    private JButton historyButton;
    private JPanel scrollPanel;

    public SwipePanel() {

        count = db.load(LocalDate.now().toString());

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // ---------- TOP PANEL ----------
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        topPanel.add(Box.createVerticalStrut(20));

        swipeButton = new JButton(
                "<html><center><font size='6'>▶ Swipe Reel</font><br>"
                + "<font size='4'>Press here to swipe the reels</font></center></html>");

        swipeButton.setPreferredSize(new Dimension(420, 120));
        swipeButton.setMaximumSize(new Dimension(420, 120));

        swipeButton.setBackground(new Color(33, 118, 255));
        swipeButton.setForeground(Color.WHITE);
        swipeButton.setFocusPainted(false);
        swipeButton.setFont(new Font("Arial", Font.BOLD, 22));
        swipeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        swipeButton.addActionListener(e -> incrementCount());

        topPanel.add(swipeButton);
        topPanel.add(Box.createVerticalStrut(30));

        counterLabel = new JLabel("Today's Swipe : " + count);
        counterLabel.setFont(new Font("Arial", Font.BOLD, 34));
        counterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(counterLabel);

        add(topPanel, BorderLayout.NORTH);

        // ---------- SCROLL PANEL ----------

        scrollPanel = new JPanel();
        scrollPanel.setLayout(new BorderLayout());
        scrollPanel.setBackground(new Color(245, 247, 250));

        scrollPanel.setBorder(new CompoundBorder(
                new LineBorder(new Color(70, 130, 255), 2, true),
                new EmptyBorder(40, 20, 40, 20)));

        JLabel mouseIcon = new JLabel("🖱", SwingConstants.CENTER);
        mouseIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 60));

        JLabel info = new JLabel(
                "<html><center>"
                + "<font size='5'><b>Put your cursor here</b></font><br><br>"
                + "<font size='4'>Scroll the Mouse Wheel</font>"
                + "</center></html>",
                SwingConstants.CENTER);

        scrollPanel.add(mouseIcon, BorderLayout.NORTH);
        scrollPanel.add(info, BorderLayout.CENTER);

        scrollPanel.addMouseWheelListener(e -> incrementCount());

        scrollPanel.setPreferredSize(new Dimension(430, 260));

        // ---------- BOTTOM PANEL ----------

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        scrollPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        bottomPanel.add(scrollPanel);
        bottomPanel.add(Box.createVerticalStrut(15));

        historyButton = new JButton("📜 History");
        historyButton.setFont(new Font("Arial", Font.BOLD, 18));
        historyButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        historyButton.addActionListener(e -> {
            new HistoryFrame().setVisible(true);
        });

        bottomPanel.add(historyButton);

        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(Color.WHITE);

        center.add(bottomPanel);

        add(center, BorderLayout.CENTER);
    }

    private void incrementCount() {

        count++;

        counterLabel.setText("Today's Swipe : " + count);

        db.save(LocalDate.now().toString(), count);

        swipeButton.setEnabled(false);

        Timer timer = new Timer(120, e -> swipeButton.setEnabled(true));
        timer.setRepeats(false);
        timer.start();
    }

    public int getCount() {
        return count;
    }

    public void reset() {

        count = 0;

        counterLabel.setText("Today's Swipe : 0");

        db.save(LocalDate.now().toString(), count);
    }
}