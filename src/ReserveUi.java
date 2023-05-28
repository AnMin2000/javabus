import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ReserveUi extends JPanel{

    private JPanel panel1;
    private JPanel Look;
    private JPanel ListPanel;
    private JButton SortButton;
    private JButton ListButton;
    private JList AddrerssList;
    private JButton SearchButton;
    private JTextField SearchTextField;
    private JLabel one;
    EtchedBorder eborder;


    ReserveUi(){
        JFrame c = new JFrame();
        c.setSize(300, 350);
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setLocation(550, 180);
        c.setTitle("버스 예매 프로그램");

        DefaultListModel model = new DefaultListModel();
        SortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultListModel SortedModel = new DefaultListModel();
                Object[] objectArray = model.toArray();

                Arrays.sort(objectArray);

                for(Object element : objectArray){
                    SortedModel.addElement(element);
                }
                AddrerssList.setModel(SortedModel);
            }
        });

        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel SearchModel = new DefaultListModel();
                Object []ListArray = model.toArray();
                String SearchName = SearchTextField.getText();
                String []tmp = new String[ListArray.length];
                for(int i = 0; i< ListArray.length;i++)
                    tmp[i] = (String) ListArray[i];


                for(int i =0; i<tmp.length; i++) {
                    if (tmp[i].indexOf(SearchName) != -1){
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

        c.add(panel1);
        c.setVisible(true);



    }
    public static void main(String[] args){
        new ReserveUi();
    }
}
