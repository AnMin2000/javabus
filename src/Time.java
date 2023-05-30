import javax.swing.*;
import java.sql.SQLException;

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



    }
}
