package reelcounter;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Reel Counter");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(550, 700);
            frame.setLocationRelativeTo(null);

            frame.add(new SwipePanel());

            frame.setVisible(true);

        });

    }

}