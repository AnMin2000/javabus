import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

public class SignUpUi {
    private JButton SignUpButton;
    private JPanel panel1;
    private JTextField NameTextField1;
    private JTextField NumberTextField;
    private JLabel name;
    private JLabel number;
    private JLabel ID;
    private JTextField IDTextField;
    private JButton Overlap;
    private JLabel PassWord;
    private JTextField PassWordTextField;
    String UserID, UserPassWd, Username, Usernumber;
    public boolean check;
    public SignUpUi() throws SQLException, ClassNotFoundException {
        DB connect = new DB();
        JFrame c = new JFrame();
        c.setSize(400,230);
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setLocation(550,180);
        c.add(panel1);
        c.setVisible(true);

        Overlap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    UserID = IDTextField.getText();
                    try {
                        check = connect.Overlap(UserID);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
            }
        });


        SignUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(check == false) return;

                UserID = IDTextField.getText();
                UserPassWd = PassWordTextField.getText();
                Username = NameTextField1.getText();
                Usernumber = NumberTextField.getText();

                String[] PrArr = new String[]{UserID,UserPassWd,Username,Usernumber};
                try {
                    connect.insert("client",4,PrArr);
                    c.dispose();
                    new MainUi();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        new SignUpUi();
    }
}
