import javax.swing.*;

public class SeatUi {
    SeatUi(){
        JFrame c = new JFrame();
        c.setSize(300, 350);
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setLocation(550, 180);
        c.setTitle("버스 예매 프로그램");
    }
    public static void main(String[] args){
        new SeatUi();
    }
}
