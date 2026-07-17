package reelcounter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class HistoryFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private Database db;

    public HistoryFrame() {

        setTitle("Swipe History");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        db = new Database();

        model = new DefaultTableModel();
        model.addColumn("Date");
        model.addColumn("Count");

        table = new JTable(model);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        loadHistory();
    }

    private void loadHistory() {

        model.setRowCount(0);

        ArrayList<String[]> history = db.getHistory();

        for (String[] row : history) {
            model.addRow(row);
        }

    }

}