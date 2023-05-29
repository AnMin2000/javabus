import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.YearMonth;

public class Calendar extends JFrame {
    private YearMonth currentYearMonth;
    private JPanel calendarPanel;

    public Calendar(String userID, String startRe, String endRe) {
        super("달력");
        currentYearMonth = YearMonth.now();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocation(550, 180);
        setResizable(false);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // 상단 패널 - 년월 표시
        JPanel monthPanel = new JPanel();
        monthPanel.setBackground(Color.WHITE);
        monthPanel.setLayout(new BorderLayout());

        JLabel monthLabel = new JLabel("", SwingConstants.CENTER);
        monthLabel.setFont(new Font("Arial", Font.BOLD, 20));
        updateMonthLabel(monthLabel);

        JButton prevButton = new JButton("◀");
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentYearMonth = currentYearMonth.minusMonths(1);
                updateMonthLabel(monthLabel);
                updateCalendarPanel(container);
            }
        });

        JButton nextButton = new JButton("▶");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentYearMonth = currentYearMonth.plusMonths(1);
                updateMonthLabel(monthLabel);
                updateCalendarPanel(container);
            }
        });

        monthPanel.add(prevButton, BorderLayout.WEST);
        monthPanel.add(monthLabel, BorderLayout.CENTER);
        monthPanel.add(nextButton, BorderLayout.EAST);

        // 중앙 패널 - 달력
        calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(0, 7)); // 0으로 설정하여 자동으로 행 개수 조정
        calendarPanel.setBackground(Color.WHITE);


        updateCalendarPanel(container);

        container.add(monthPanel, BorderLayout.NORTH);
        container.add(calendarPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void updateMonthLabel(JLabel label) {
        String monthText = currentYearMonth.getYear() + ". " + currentYearMonth.getMonthValue() + ". ";
        label.setText(monthText);
    }

    private void updateCalendarPanel(Container container) {
        calendarPanel.removeAll();

        LocalDate firstDayOfMonth = currentYearMonth.atDay(1);
        int firstDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue();
        int daysInMonth = currentYearMonth.lengthOfMonth();

        // 요일 레이블 추가 (일요일부터 토요일까지)
        String[] daysOfWeek = {"일", "월", "화", "수", "목", "금", "토"};
        for (String day : daysOfWeek) {
            JLabel dayLabel = new JLabel(day, SwingConstants.CENTER);
            calendarPanel.add(dayLabel);
        }

        // 첫 번째 날짜의 인덱스 계산
        int startIndex = (firstDayOfWeek == 7) ? 0 : firstDayOfWeek;

        // 첫째 주에서 첫 번째 날 이전의 빈 칸 추가
        for (int i = 0; i < startIndex; i++) {
            JLabel emptyLabel = new JLabel("");
            calendarPanel.add(emptyLabel);
        }

        // 날짜 버튼 추가
        LocalDate currentDate = LocalDate.now();
        for (int i = 1; i <= daysInMonth; i++) {
            JButton dayButton = new JButton(String.valueOf(i));
            dayButton.setFont(new Font("Arial", Font.PLAIN, 14));
            dayButton.setFocusPainted(false);

            // 요일에 따라 색상 설정
            LocalDate date = currentYearMonth.atDay(i);
            int dayOfWeek = date.getDayOfWeek().getValue();
            if (date.isBefore(currentDate)) { // 지나간 날
                dayButton.setForeground(Color.LIGHT_GRAY);
            }else if (dayOfWeek == 6) { // 토요일
                dayButton.setForeground(Color.BLUE);
            }else if (dayOfWeek == 7) { // 일요일
                dayButton.setForeground(Color.RED);
            }

            dayButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();
                    int selectedDay = Integer.parseInt(clickedButton.getText());
                    LocalDate selectedDate = currentYearMonth.atDay(selectedDay);

                    /////////////////////////////////////////////////////////////////////// 지나간날 코드 없애려면 여기 없애면 됨
                    LocalDate currentDate = LocalDate.now();
                    if (!selectedDate.isBefore(currentDate)) {
                        System.out.println("선택된 날짜: " + selectedDate);
                        dispose();
                        // new (userID, startRe, endRe, selectedDate) 던져주기
                        // 원하는 동작을 수행하도록 구현하세요.
                    }
                    ////////////////////////////////////////////////////////////////////////
                }
            });


            calendarPanel.add(dayButton);
        }

        container.validate();
        container.repaint();
    }


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calendar("Null","Null","Null");
            }
        });
    }
}
