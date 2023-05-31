import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    SeatUi(){
        JFrame c = new JFrame();
        c.setSize(200, 350);
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setLocation(550, 180);
        c.setTitle("버스 예매 프로그램");

        class SeatButtonActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource();
                if(clickedButton == button1) {
                    System.out.println(1);
                }


                // 클릭된 버튼에 대한 처리 로직 작성
                // 예: 선택 여부 확인, 선택 상태 변경 등
            }
        }
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
        c.add(panel1);
        c.setVisible(true);
    }
    public static void main(String[] args){
        new SeatUi();
    }


}
