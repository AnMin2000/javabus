import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class inquiry {
    private JPanel panel1;
    private JPanel regionPanel;
    private JLabel region;
    private JLabel startT;
    private JLabel nameNumber;
    private JLabel seat;
    private JLabel endT;

    inquiry(String userID) throws SQLException, ClassNotFoundException {
        DB connect = new DB();
       // "<html><body><center>"+값1+"<br>"+값2+"</center></body></html>"
        JFrame c = new JFrame();
        c.setSize(280,380);
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setLocationRelativeTo(null);

        ResultSet rs = connect.join(userID);    // r.reserveID, startRegion, endRegion, startTime, endTime, seatID, price

        while(rs.next()) {
            System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getString(6)+rs.getString(7));
        }


        c.add(panel1);
        c.setVisible(true);
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //출발 지역 -> 도착지역
        //출발 시간 -> 도착시간
        //이름,전화번호//좌석,가격
        new inquiry("dksals");
    }
}
