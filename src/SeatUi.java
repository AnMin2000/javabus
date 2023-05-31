import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeatUi {
    private JPanel panel1;
    private JButton button1;
    private JButton append;
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

    private List<String> clickedButtons;
    SeatUi(String userId, String startRe, String endRe, String selectedDate, String timeID) throws SQLException, ClassNotFoundException {
        DB connect = new DB();
        JFrame c = new JFrame();
        c.setSize(200, 400);
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setLocation(550, 180);
        c.setTitle("좌석 예약");

        class SeatButtonActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource();
                String buttonText = clickedButton.getText();

                if (clickedButtons.contains(buttonText)) {
                    clickedButtons.remove(buttonText);
                    clickedButton.setEnabled(true);
                } else {
                    clickedButtons.add(buttonText);
                    clickedButton.setEnabled(false);
                }

                if (clickedButton == append) {
                    JOptionPane.showMessageDialog(null, "좌석 예약 완료 되었습니다.");
                    c.dispose();

                    Object[] objectArray = clickedButtons.toArray();

                    String[] PrArr;
                    String tmp = "@";
                    try {
                        while(true) {
                            if (!connect.checkReserve(tmp)) {
                                tmp += "@";
                            }
                            else{
                                // 예약 ID 비어 있으면 OK -> 버튼 클릭한 좌석 번호 두개 넣기,
                                PrArr = new String[]{tmp, userId, timeID}; // 예약ID, 사용자ID, 예약버스번호
                                connect.insert("reserve", 3, PrArr);
                                for(int i =0; i<objectArray.length - 1; i++) {
                                    connect.updateSeat(tmp, (String)objectArray[i], "1");
                                }
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
        }

        clickedButtons = new ArrayList<>();
        SeatButtonActionListener actionListener = new SeatButtonActionListener();

        button1.addActionListener(actionListener);
        button2.addActionListener(actionListener);
        button3.addActionListener(actionListener);
        button4.addActionListener(actionListener);
        button5.addActionListener(actionListener);
        button6.addActionListener(actionListener);
        button7.addActionListener(actionListener);
        button8.addActionListener(actionListener);
        button9.addActionListener(actionListener);
        button10.addActionListener(actionListener);
        button11.addActionListener(actionListener);
        button12.addActionListener(actionListener);
        button13.addActionListener(actionListener);
        button14.addActionListener(actionListener);
        button15.addActionListener(actionListener);
        button16.addActionListener(actionListener);
        button17.addActionListener(actionListener);
        button18.addActionListener(actionListener);
        button19.addActionListener(actionListener);
        button20.addActionListener(actionListener);
        append.addActionListener(actionListener);

        c.add(panel1);
        c.setVisible(true);
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new SeatUi("NULL","NULL","NULL","NULL","NULL");
    }


}
