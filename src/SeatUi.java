import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeatUi {
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JButton button17;
    private JButton button18;
    private JButton button19;
    private JButton button20;
    private JButton append;
    String buttonText;

    private List<String> clickedButtons;
    SeatUi(String userId, String startRe, String endRe, String selectedDate, String timeID) throws SQLException, ClassNotFoundException {
        DB connect = new DB();
        JFrame c = new JFrame();
        c.setSize(200, 400);
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setLocationRelativeTo(null);
        c.setTitle("좌석 예약");

        List<String> reservedSeats = connect.getReservedSeats();// 이걸로 reserveSeats 얻어와서 마무리 지으면 됨
        // 디비에서는 받아 왔고 이걸로 버튼에 따라서 수정 해야 됨 그리고 버스 ID에 따라서 DB 새로 설정을 해야 됨
        //System.out.println(reservedSeats.toString());
        buttonText = reservedSeats.toString();
        List<JButton> buttons = Arrays.asList(
                button1, button2, button3, button4, button5,
                button6, button7, button8, button9, button10,
                button11, button12, button13, button14, button15,
                button16, button17, button18, button19, button20
        );

        for (JButton button : buttons) {
            button.setEnabled(!buttonText.contains(button.getText()));
        }




        // buttonText가 존재하면
        ActionListener SeatButtonActionListener= new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                JButton clickedButton = (JButton) e.getSource();
                buttonText = clickedButton.getText();

                if (clickedButtons.contains(buttonText)) {
                    clickedButtons.remove(buttonText);
                    clickedButton.setEnabled(true);

                } else {
                    clickedButtons.add(buttonText);
                    clickedButton.setEnabled(false);
                    System.out.println(buttonText); //이 버튼 변수로 버튼 막기
                }

                if (clickedButton == append) {
                    JOptionPane.showMessageDialog(null, "좌석 예약 완료 되었습니다.");
                    c.dispose();

                    Object[] objectArray = clickedButtons.toArray();

                    String[] PrArr;
                    int reserveID = 1;
                    try {
                        while (true) {
                            if (!connect.checkReserve(reserveID)) {
                                reserveID += 1;
                            } else {
                                // 예약 ID 비어 있으면 OK -> 버튼 클릭한 좌석 번호 두개 넣기,
                                //timeID로 셀렉해서 버스번호 뽑아오기
                                ResultSet rs = connect.print("busID", "timetable","timeID",timeID,"Null","Null");
                                rs.next();
                                String VbusID = rs.getString(1);

                                //System.out.println(rs.getString(1));
                                PrArr = new String[]{String.valueOf(reserveID), userId, timeID}; // 예약ID, 사용자ID, 예약버스번호
                                connect.insert("reserve", 3, PrArr);
                                for (int i = 0; i < objectArray.length - 1; i++) {

                                    connect.updateSeat(String.valueOf(reserveID), (String) objectArray[i], VbusID);
                                }
                                c.dispose();
                                new MainUi(userId);
                                break;
                            }
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
        };

        clickedButtons = new ArrayList<>();


        button1.addActionListener(SeatButtonActionListener);
        button2.addActionListener(SeatButtonActionListener);
        button3.addActionListener(SeatButtonActionListener);
        button4.addActionListener(SeatButtonActionListener);
        button5.addActionListener(SeatButtonActionListener);
        button6.addActionListener(SeatButtonActionListener);
        button7.addActionListener(SeatButtonActionListener);
        button8.addActionListener(SeatButtonActionListener);
        button9.addActionListener(SeatButtonActionListener);
        button10.addActionListener(SeatButtonActionListener);
        button11.addActionListener(SeatButtonActionListener);
        button12.addActionListener(SeatButtonActionListener);
        button13.addActionListener(SeatButtonActionListener);
        button14.addActionListener(SeatButtonActionListener);
        button15.addActionListener(SeatButtonActionListener);
        button16.addActionListener(SeatButtonActionListener);
        button17.addActionListener(SeatButtonActionListener);
        button18.addActionListener(SeatButtonActionListener);
        button19.addActionListener(SeatButtonActionListener);
        button20.addActionListener(SeatButtonActionListener);
        append.addActionListener(SeatButtonActionListener);


        c.add(panel1);
        c.setVisible(true);
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new SeatUi("NULL","NULL","NULL","NULL","NULL");
    }


}