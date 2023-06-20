import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminUi {
    private JPanel JPanel1;
    private JLabel IdLabel;
    private JLabel PwLabel;
    private JTextField IdTextField;
    private JButton LoginButton;
    private JPasswordField PwTextField;
    public AdminUi(){
        JFrame c = new JFrame();
        c.setSize(400,230);
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setLocationRelativeTo(null);
        c.add(JPanel1);
        c.setVisible(true);

        LoginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String UserId = IdTextField.getText();
                    String UserPw = PwTextField.getText();

                    boolean state = new DB().AdminLogin(UserId, UserPw);

                    if(state == true){
                        c.dispose();
                        new Bus();
                    }
                    else {
                        return;
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public static void main(String[] args){
        new AdminUi();
    }
}
