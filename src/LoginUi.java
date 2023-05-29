import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginUi {
    private JPanel JPanel1;
    private JLabel IdLabel;
    private JLabel PwLabel;
    private JTextField IdTextField;
    private JTextField PwTextField;
    private JButton LoginButton;

    public LoginUi(){
        JFrame c = new JFrame();
        c.setSize(400,230);
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setLocation(550,180);
        c.add(JPanel1);
        c.setVisible(true);

        LoginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String UserId = IdTextField.getText();
                    String UserPw = PwTextField.getText();

                    boolean state = new DB().Login(UserId, UserPw);

                    if(state == true){
                        c.dispose();
                        new MainUi(UserId);

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
        new LoginUi();
    }
}
