import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class F_inquiry {
    private JPanel panel1;
    private JLabel JName;
    private JPanel Look;
    private JPanel ListPanel;
    private JButton SortButton;
    private JButton ListButton;
    private JList AddrerssList;
    private JButton SearchButton;
    private JTextField SearchTextField;
    private JButton BackButton;

    F_inquiry(String userID) throws SQLException, ClassNotFoundException {
        DB connect = new DB();
        JFrame c = new JFrame();

        c.setSize(430, 250);
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setLocationRelativeTo(null);
        c.setTitle("예매 조회");

        DefaultListModel model = new DefaultListModel();
        AddrerssList.setModel(model);



        try {
            ResultSet rs = connect.join(userID);    // r.reserveID, startRegion, endRegion, startTime, endTime, seatID, price
            while(rs.next()){
                model.addElement("["+rs.getString(1)+"]" + rs.getString(2) + " -> " + rs.getString(3) + " | 시간 : " + rs.getString(4) + " " + rs.getString(5));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        SortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultListModel SortedModel = new DefaultListModel();
                Object[] objectArray = model.toArray();

                Arrays.sort(objectArray);

                for (Object element : objectArray) {
                    SortedModel.addElement(element);
                }
                AddrerssList.setModel(SortedModel);
            }
        });

        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel SearchModel = new DefaultListModel();
                Object[] ListArray = model.toArray();
                String SearchName = SearchTextField.getText();
                String[] tmp = new String[ListArray.length];
                for (int i = 0; i < ListArray.length; i++)
                    tmp[i] = (String) ListArray[i];


                for (int i = 0; i < tmp.length; i++) {
                    if (tmp[i].indexOf(SearchName) != -1) {
                        SearchModel.addElement(tmp[i]);
                    }
                }
                AddrerssList.setModel(model);
                AddrerssList.setModel(SearchModel);
            }
        });
        ListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AddrerssList.setModel(model);
            }
        });
        AddrerssList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) { // 더블 클릭 이벤트 감지
                    String selectedValue = (String) AddrerssList.getSelectedValue();
                    System.out.println(AddrerssList.getSelectedValue());

                    String reserveID = selectedValue.substring(selectedValue.indexOf('[') + 1, selectedValue.indexOf(']'));
                    try {
                        c.dispose();
                        new inquiry(userID,reserveID);


                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    System.out.println(reserveID);

                }
            }
        });
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
}
