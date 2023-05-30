//AddrerssList.addMouseListener(new MouseAdapter() {
//@Override
//public void mouseClicked(MouseEvent e) {
//
//        if (e.getClickCount() == 2) { // 더블 클릭 이벤트 감지
//        String selectedValue = (String) AddrerssList.getSelectedValue();
//
//        if(addressCount == 1){ // 도착지 선택시
//        endRe = (String) AddrerssList.getSelectedValue();
//
//        /////////////////////////////////////////////////////////////////////////////
//        try {
//        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        SwingUtilities.invokeLater(new Runnable() {
//@Override
//public void run() {
//        c.dispose();
//        new Calendar(userID, startRE, endRe);//
//        }
//        });
//        } catch (Exception c) {
//        c.printStackTrace();
//        }
//        //////////////////////////////////////////////////////////////////////////////
//        }
//        else if(addressCount == 0) { // 출발지 선택시
//        startRE = (String) AddrerssList.getSelectedValue();
//        model.clear();
//        addressCount++;
//        }
//        try {
//        //출발지와 도착지가 같지 않을 경우만 출력
//        ResultSet rs = connect.print("*", "bus", 0);
//        while (rs.next()) {
//        // 먼가 변수를 줘서 앞에서 선택한 출발지의 테이블의 도착지만 불러오기로 해야됨
//        if(!selectedValue.equals(rs.getString(3))) {
//        model.addElement(rs.getString(3));
//        }
//        }
//        AddrerssList.setModel(model);
//        } catch (SQLException ex) {
//        throw new RuntimeException(ex);
//        }
//
//        }
//        }
//
//        });
//////////////////////////////////////////////////////////////////////////////////////////////////////
// 검색하기
//        AddrerssList.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//
//                if (e.getClickCount() == 2) { // 더블 클릭 이벤트 감지
//                    if(addressCount == 1){
//                        new Calendar(userID,startRe,endRe)
//                    }
//                    String selectedValue = (String) AddrerssList.getSelectedValue();
//                    System.out.println(selectedValue);
//                    try {
//
//                        // 여기서 같은 행 출력 해야 됨
//                        ResultSet rs = connect.print(" distinct endRegion ", "timetable", "startRegion",selectedValue); // 이거 수정
//                        model.clear();
//                        while (rs.next()) {
//                            model.addElement(rs.getString(1));
//                        }
//                        AddrerssList.setModel(model);
//                    } catch (SQLException ex) {
//                        throw new RuntimeException(ex);
//                    }
//
//                }
//                addressCount++;
//            }
//        });