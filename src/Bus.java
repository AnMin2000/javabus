import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Bus{
    private JPanel panel1;
    private JLabel Title;
    private JPanel Append;
    private JTextField BusNum;
    private JTextField StartRe;
    private JLabel Number_Label;
    private JLabel NameLabel;
    private JButton AppendButton;
    private JTextField EndRe;
    private JLabel DefaultLabel;
    private JPanel Look;
    private JPanel ListPanel;
    private JButton SortButton;
    private JButton ListButton;
    private JList AddrerssList;
    private JButton SearchButton;
    private JTextField SearchTextField;
    private JTextField StratT;
    private JTextField EndT;
    private JTextField TimeID;
    private JButton back;
    String tmp;
    public Bus() throws SQLException, ClassNotFoundException {
        DB connect = new DB();

        JFrame c = new JFrame();
        c.setSize(800, 500);

        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setLocationRelativeTo(null);

        DefaultListModel model = new DefaultListModel();

        ResultSet rs = connect.print(" * ", " timetable ",
                "Null", "Null", "Null", "Null");

        while (rs.next()){
        model.addElement(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4)+ " " + rs.getString(5) + " " + rs.getString(6));
        }
        AddrerssList.setModel(model);
        AppendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String VtimeId = TimeID.getText();
                    String VBusNum = BusNum.getText();   // 안민
                    String VStartRe = StartRe.getText(); // 1번
                    String VEndRe = EndRe.getText();
                    String VStartT = StratT.getText();
                    String VEndT = EndT.getText();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    Date startDate;
                    Date endDate;
                    try {
                        startDate = dateFormat.parse(VStartT);
                        endDate = dateFormat.parse(VEndT);
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                    // datetime 형식으로 변환
                    String formattedStartT = dateFormat.format(startDate);
                    String formattedEndT = dateFormat.format(endDate);

                    String[] PrArr = new String[]{VtimeId, VBusNum, VStartRe, VEndRe, formattedStartT, formattedEndT};
                    connect.insert("timetable", 6, PrArr);

                    model.addElement(VtimeId + " " + VBusNum + " " + VStartRe + " " + VEndRe + " " + VStartT + " " + VEndT);

                    AddrerssList.setModel(model);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

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
                // AddrerssList.setModel(model);
                AddrerssList.setModel(SearchModel);
            }
        });
        ListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AddrerssList.setModel(model);
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.dispose();
                new MainUi("Null");
            }
        });

        c.add(panel1);

        c.setVisible(true);
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new Bus();
    }
}
