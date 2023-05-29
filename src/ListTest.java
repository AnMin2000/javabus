import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class ListTest extends JFrame implements ListSelectionListener {

    //FIELDS
    private JTextField selectedList = new JTextField(10); //10글자까지 가능
    private JPanel listPanel = new JPanel();
    private JPanel labelPanel = new JPanel();
    private JList list;
    private JScrollPane scroll;
    private String[] names = {"김철수", "김영희", "김숙자", "김자몽"};

    //CONSTRUCTOR
    public ListTest() {
        setTitle("리스트 예제");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //프레임을 껐을 때 완전한 종료
        JLabel label = new JLabel();

        list = new JList(names);

        //리스트의 경계선 설정
        list.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        //리스트에 스크롤바 추가
        scroll = new JScrollPane(list);
        scroll.setPreferredSize(new Dimension(300, 300));

        //리스트의 단일 선택 모드 변경
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //리스트 선택 리스너 등록
        list.addListSelectionListener(this);

        label.setText("선택된 항목 : ");
        selectedList.setEditable(false); //텍스트 필드 수정 불가상태로 바꾸기

        listPanel.add(scroll); //리스트를 패널에 추가
        labelPanel.add(label); //라벨을 패널에 추가
        labelPanel.add(selectedList); //텍스트 필드를 패널에 추가

        add(listPanel, BorderLayout.CENTER); //프레임 중앙에 리스트를 배치
        add(labelPanel, BorderLayout.SOUTH); //프레임 하단에 라벨과 텍스트 필드 배치

        setVisible(true);
    }

    //METHODS
    @Override
    public void valueChanged(ListSelectionEvent e) {
        // 선택된 이름 얻기
        String name = (String) list.getSelectedValue();

        selectedList.setText(name);
    }

    public static void main(String[] args) {
        new ListTest();
    }
}
