import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;


public class MainUi {
    private JPanel panel1;
    private javax.swing.JPanel JPanel;
    private JButton ReserveButton;
    private JButton LoginButton;
    private JButton ChangeButton;
    private JButton PwButton;
    private JButton CancleButton;
    private JButton InquiryButton;
    private JButton ModifyButton;
    private JButton SignupButton;

    MainUi(String userId) {
        JFrame c = new JFrame();
        c.setSize(300, 350);
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setLocation(550, 180);
        c.setTitle("버스 예매 프로그램");
        ReserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    c.dispose();
                    new ReserveUi(userId);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        c.add(panel1);
        c.setVisible(true);
    }

    public static void main(String[] args) {
        new MainUi("Null");
    }
}
