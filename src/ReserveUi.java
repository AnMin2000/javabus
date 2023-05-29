import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class ReserveUi {

    private JPanel panel1;
    private JPanel Look;
    private JPanel ListPanel;
    private JButton SortButton;
    private JButton ListButton;
    private JList AddrerssList;
    private JButton SearchButton;
    private JTextField SearchTextField;
    private JLabel JName;
    private JLabel one;


    ReserveUi(String userID) throws SQLException, ClassNotFoundException {

        String clientID = userID;
        DB connect = new DB();
        JFrame c = new JFrame();
        c.setSize(300, 350);
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setLocation(550, 180);
        c.setTitle("버스 예매 프로그램");

        DefaultListModel model = new DefaultListModel();
        ResultSet rs = connect.print("*", "bus", 0);
        ResultSet rs2 = connect.print("*", "bus", 0);

        while (rs.next()) {
            model.addElement(rs.getString(2));
        }
      //  rs.beforeFirst();
        AddrerssList.setModel(model);

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
        // 검색하기
        AddrerssList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               int i = 0;
                if (e.getClickCount() == 2) { // 더블 클릭 이벤트 감지
                    String selectedValue = (String) AddrerssList.getSelectedValue();
                    // 한번 클릭 했을 때만 출발지 -> 도착지로 바뀌게 코딩
                    if(i==0) {
                        model.clear();
                        i++;
                    }
                    try {
                        //출발지와 도착지가 같지 않을 경우만 출력
                    while (rs2.next()) {
                        if(!selectedValue.equals(rs2.getString(3))) {
                            model.addElement(rs2.getString(3));
                        }
                    }
                        AddrerssList.setModel(model);
                    } catch (SQLException ex) {
                           throw new RuntimeException(ex);
                        }

                    }
                }

            });
            c.add(panel1);
            c.setVisible(true);



    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new ReserveUi("dlgywjd");
    }
}
