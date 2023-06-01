import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DB {
    PreparedStatement pstmt;
    Connection conn = null;
    DB() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionUrl =
                "jdbc:sqlserver://localhost;"
                        + "encrypt=true;"
                        + "instanceName=SQLEXPRESS;"
                        + "integratedSecurity=true;"
                        + "database=Bus;"
                        + "trustServerCertificate=true;";
        conn = DriverManager.getConnection(connectionUrl);
    }
    public void insert(String name, int number, String[] PrName) throws SQLException, ParseException {
        //name : 테이블 이름, number : 해당 디비 컬럼 갯수, PrName : 디비에 입력 할 값들
        String sql;
        String ValuesVar = "?";

        for (int i = 0; i < number - 1; i++) {
            ValuesVar += ", ?";
        }

        sql = "INSERT INTO " + name + " VALUES (" + ValuesVar + ") ";

        pstmt = conn.prepareStatement(sql);


        for (int i = 0; i < PrName.length; i++) {
            pstmt.setString(i + 1, PrName[i]);
        }
        pstmt.executeUpdate();
    }
    public ResultSet print(String selectName, String tableName, String sqlName, String data, String sqlName2, String data2 ) throws SQLException {

        String ValuesVar = "?";

        String sql;
        if(sqlName.equals("Null")) {
            sql = "SELECT " + selectName + " FROM " + tableName;
            pstmt = conn.prepareStatement(sql);
        }
        else if(sqlName2.equals("Null")){
            sql = "SELECT " + selectName + " FROM " + tableName + " WHERE " + sqlName + " = " +  "?" ;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, data);
        }
        else{
            sql = "SELECT " + selectName + " FROM " + tableName +
                    " WHERE " + sqlName + " =  +  ? and " + sqlName2 + " =  + ?";
//            " = " +  "? and " + sqlName2 + " = " + "?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, data);
            pstmt.setString(2, data2);
        }
        ResultSet rs = pstmt.executeQuery();

        return rs;
    }


    public ResultSet selectUser(String Id) throws SQLException {
        String sql = "select * from dbo.client where userID = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, Id);
        ResultSet rs = pstmt.executeQuery();

        return rs;
    }
    public boolean Overlap(String ID) throws SQLException {

        ResultSet rs = selectUser(ID);

        while (rs.next()) {
            //System.out.println(rs.getString(2));
            if (rs.getString(1).equals(ID)) {
                JOptionPane.showMessageDialog(null, "아이디 중복");
                return false;
            }
        }
        JOptionPane.showMessageDialog(null, "사용 가능 아이디");
        return true;
    }


    public boolean Login(String Id, String Pw) throws SQLException {

        ResultSet rs = selectUser(Id);
        while (rs.next()) {
            if (rs.getString(2).equals(Pw)) {
                JOptionPane.showMessageDialog(null, "로그인 성공");
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "로그인 실패");
        return false;
    }
    public boolean checkReserve(String Id) throws SQLException {
        String sql = "select reserveID from dbo.reserve where reserveID = ? ";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, Id);
        ResultSet rs = pstmt.executeQuery();

        String search = "Null";
        while (rs.next()) {
            search = rs.getString(1);
        }
        if(search.equals("Null")){
            return true;
        }
        return false;
    }

    public void updateSeat(String reserveID, String seatID, String busID) throws SQLException {
        String sql = "update seat set reserveID = ? where seatID = ? and busID = ? ";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, reserveID);
        pstmt.setString(2, seatID);
        pstmt.setString(3, busID);
        pstmt.executeUpdate();
    }
    public List<String> getReservedSeats() throws SQLException {
        List<String> reservedSeats = new ArrayList<>();

        String query = "SELECT seatID FROM seat WHERE reserveID IS NOT NULL ";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            String seatNumber = rs.getString("seatID");
            reservedSeats.add(seatNumber);
        }

        rs.close();
        pstmt.close();

        return reservedSeats;
    }

}
