package reelcounter;

import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDate;

public class Database {

    private Connection con;

    public Database() {

        try {

            Class.forName("org.sqlite.JDBC");

            con = DriverManager.getConnection("jdbc:sqlite:reel.db");

            Statement st = con.createStatement();

            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS reel_history ("
                    + "date TEXT PRIMARY KEY,"
                    + "count INTEGER)");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Save Today's Count
    public void save(String date, int count) {

        try {

            PreparedStatement ps = con.prepareStatement(
                    "INSERT OR REPLACE INTO reel_history(date,count) VALUES(?,?)");

            ps.setString(1, date);
            ps.setInt(2, count);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Load Today's Count
    public int load(String date) {

        fillMissingDates(date);

        try {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT count FROM reel_history WHERE date=?");

            ps.setString(1, date);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("count");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    // Automatically fill skipped dates with 0
    private void fillMissingDates(String today) {

        try {

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT date FROM reel_history ORDER BY date DESC LIMIT 1");

            if (!rs.next()) {
                return;
            }

            LocalDate lastDate = LocalDate.parse(rs.getString("date"));
            LocalDate currentDate = LocalDate.parse(today);

            lastDate = lastDate.plusDays(1);

            while (lastDate.isBefore(currentDate)) {

                PreparedStatement ps = con.prepareStatement(
                        "INSERT OR IGNORE INTO reel_history(date,count) VALUES(?,0)");

                ps.setString(1, lastDate.toString());
                ps.executeUpdate();

                lastDate = lastDate.plusDays(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Get All History
    public ArrayList<String[]> getHistory() {

        ArrayList<String[]> list = new ArrayList<>();

        try {

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT * FROM reel_history ORDER BY date DESC");

            while (rs.next()) {

                String[] row = new String[2];

                row[0] = rs.getString("date");
                row[1] = String.valueOf(rs.getInt("count"));

                list.add(row);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}