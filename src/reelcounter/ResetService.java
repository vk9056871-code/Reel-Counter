package reelcounter;

import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;

public class ResetService {

    private LocalDate currentDate;

    public ResetService(SwipePanel panel) {

        currentDate = LocalDate.now();

        Timer timer = new Timer(true);

        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                LocalDate today = LocalDate.now();

                if (!today.equals(currentDate)) {

                    currentDate = today;

                    javax.swing.SwingUtilities.invokeLater(() -> {
                        panel.reset();
                    });

                }

            }

        }, 60000, 60000); // हर 1 मिनट में चेक करेगा

    }

}