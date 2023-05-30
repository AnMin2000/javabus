import javax.swing.*;
import java.sql.*;
import java.text.ParseException;

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
    public ResultSet print(String selectName, String tableName) throws SQLException {

        String sql;
        sql = "SELECT " + selectName + " FROM " + tableName;
        pstmt = conn.prepareStatement(sql);
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

}
