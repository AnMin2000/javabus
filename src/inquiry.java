import javax.swing.*;

public class inquiry {
    private JPanel panel1;
    private JPanel regionPanel;
    private JLabel region;
    private JLabel start;
    private JLabel end;
    private JLabel nameNumber;
    private JLabel seatPrice;

    inquiry(String userID){
        JFrame c = new JFrame();
        c.setSize(280,380);
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setLocationRelativeTo(null);
        c.add(panel1);
        c.setVisible(true);
    }
    public static void main(String[] args){

        //출발 지역 -> 도착지역
        //출발 시간 -> 도착시간
        //이름,전화번호//좌석,가격
       // new inquiry();
    }
}
