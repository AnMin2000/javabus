import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Time {
    private JPanel panel1;
    private JPanel Look;
    private JPanel ListPanel;
    private JButton ListButton;
    private JList AddrerssList;

    Time(String userId, String startRe, String endRe, String selectedDate) throws SQLException, ClassNotFoundException {

        DB connect = new DB();
        JFrame c = new JFrame();
        c.setSize(300, 350);
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setLocation(550, 180);
        c.setTitle("시간 선택");

        DefaultListModel model = new DefaultListModel();
        ResultSet rs = connect.print(" startTime, endTime ", " timetable ",
                " startRegion ", startRe, " endRegion ", endRe);

        while (rs.next()) {
            if (rs.getString(1).indexOf(selectedDate) != -1) {
                model.addElement(rs.getString(1) + "┃" + " " + "┃" + rs.getString(2));
            }
           // System.out.println(rs.getString(1));
        }
        //  rs.beforeFirst();
        AddrerssList.setModel(model);

        c.add(panel1);
        c.setVisible(true);

    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new Time("Null","Null","Null","Null");
    }
}
