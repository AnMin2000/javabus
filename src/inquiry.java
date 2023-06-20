import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class inquiry {
    private JPanel panel1;
    private JPanel regionPanel;
    private JLabel region;
    private JLabel startT;
    private JLabel nameNumber;
    private JLabel seat;
    private JLabel endT;
    private JButton BackButton;

    inquiry(String userID, String reserveID) throws SQLException, ClassNotFoundException {
        DB connect = new DB();
       // "<html><body><center>"+값1+"<br>"+값2+"</center></body></html>"
        JFrame c = new JFrame();
        c.setSize(280,380);
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setLocationRelativeTo(null);

        ResultSet rs = connect.join(userID);    // r.reserveID, startRegion, endRegion, startTime, endTime, seatID, price



        String[] VstartR = new String[100];
        String[] VendR = new String[100];
        String[] VstartT = new String[100];
        String[] VendT = new String[100];
        String[] VseatI = new String[100];
        String[] Vprice = new String[100];

        int i =0;

        while(rs.next()) {

            VstartR[i] = rs.getString(2);
            VendR[i] = rs.getString(3);
            VstartT[i] = rs.getString(4);
            VendT[i] = rs.getString(5);
            VseatI[i] = rs.getString(6);
            Vprice[i] = rs.getString(7);

            i++;
        }

        String[] Vname = new String[100];
        String[] Vphone = new String[100];

        ResultSet rs2 = connect.print("name, phone","client","Null","Null","Null","Null");

        int j =0;
        while(rs2.next()) {
            Vname[j] = rs2.getString(1);
            Vphone[j] = rs2.getString(2);
        }
// r.reserveID, startRegion, endRegion, startTime, endTime, seatID, price
        region.setText(VstartR[0] + " -> " + VendR[0]);
        startT.setText(VstartT[0]);
        endT.setText(VendT[0]);
        nameNumber.setText(Vname[0] + Vphone[0]);
        seat.setText(VseatI[0]);
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.dispose();
                new MainUi(userID);
            }
        });

        c.add(panel1);
        c.setVisible(true);
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //출발 지역 -> 도착지역
        //출발 시간 -> 도착시간
        //이름,전화번호//좌석,가격
        //new inquiry("dksejrrms");
    }
}
