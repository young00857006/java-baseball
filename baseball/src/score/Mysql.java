package score;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Mysql {
    private Connection con = null; //Database objects
    private Statement stat = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private String dropdbSQL = "DROP TABLE user ";
    private String createdbSQL = "CREATE TABLE user (" +
            "    date     VARCHAR(50) " +
            "  , player1    VARCHAR(20) " +
            "  , p11    VARCHAR(20) " +
            "  , p12    VARCHAR(20) " +
            "  , p13    VARCHAR(20) " +
            "  , p14    VARCHAR(20) " +
            "  , p15    VARCHAR(20) " +
            "  , p16    VARCHAR(20) " +
            "  , p17    VARCHAR(20) " +
            "  , p18    VARCHAR(20) " +
            "  , p19    VARCHAR(20) " +
            "  , p1r    VARCHAR(20) " +
            "  , p1h    VARCHAR(20) " +
            "  , p1e    VARCHAR(20) " +
            "  , player2   VARCHAR(20) " +
            "  , p21    VARCHAR(20) " +
            "  , p22    VARCHAR(20) " +
            "  , p23    VARCHAR(20) " +
            "  , p24    VARCHAR(20) " +
            "  , p25    VARCHAR(20) " +
            "  , p26    VARCHAR(20) " +
            "  , p27    VARCHAR(20) " +
            "  , p28    VARCHAR(20) " +
            "  , p29    VARCHAR(20) " +
            "  , p2r    VARCHAR(20) " +
            "  , p2h    VARCHAR(20) " +
            "  , p2e    VARCHAR(20))" ;
    private String insertdbSQL = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private String selectSQL = "select * from user ";

    public Mysql()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //註冊driver
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/score?useUnicode=true&characterEncoding=utf-8",
                    "root","Warren0702"); //00857006為密碼
            //取得connection
            //jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=Big5
            //localhost是主機名,test是database名
            //useUnicode=true&characterEncoding=Big5使用的編碼
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("DriverClassNotFound :"+e.toString());
        }//有可能會產生sqlexception
        catch(SQLException x) {
            System.out.println("Exception :"+x.toString());
        }
    }
    //建立table的方式
    //可以看看Statement的使用方式
    public void createTable()
    {
        try
        {
            stat = con.createStatement();
            stat.executeUpdate(createdbSQL);
        }
        catch(SQLException e)
        {
            System.out.println("CreateDB Exception :" + e.toString());
        }
        finally
        {
            Close();
        }
    }
    public void insertTable(String date,
                            String player1, String p11, String p12, String p13, String p14, String p15, String p16, String p17, String p18, String p19, String p1r, String p1h, String p1e,
                            String player2, String p21, String p22, String p23, String p24, String p25, String p26, String p27, String p28, String p29, String p2r, String p2h, String p2e)
    {
        try {
            pst = con.prepareStatement(insertdbSQL);
            pst.setString(1, date);
            pst.setString(2, player1);
            pst.setString(3, p11);
            pst.setString(4, p12);
            pst.setString(5, p13);
            pst.setString(6, p14);
            pst.setString(7, p15);
            pst.setString(8, p16);
            pst.setString(9, p17);
            pst.setString(10, p18);
            pst.setString(11, p19);
            pst.setString(12, p1r);
            pst.setString(13, p1h);
            pst.setString(14, p1e);
            pst.setString(15, player2);
            pst.setString(16, p21);
            pst.setString(17, p22);
            pst.setString(18, p23);
            pst.setString(19, p24);
            pst.setString(20, p25);
            pst.setString(21, p26);
            pst.setString(22, p27);
            pst.setString(23, p28);
            pst.setString(24, p29);
            pst.setString(25, p2r);
            pst.setString(26, p2h);
            pst.setString(27, p2e);
            pst.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println("InsertDB Exception :" + e.toString());
        }
        finally {
            Close();
        }
    }
    //刪除Table,
    //跟建立table很像
    public void dropTable()
    {
        try
        {
            stat = con.createStatement();
            stat.executeUpdate(dropdbSQL);
        }
        catch(SQLException e)
        {
            System.out.println("DropDB Exception :" + e.toString());
        }
        finally
        {
            Close();
        }
    }

    //刪除某一欄
    public void delcol(String date)
    {
        try
        {
            stat = con.createStatement();
            stat.executeUpdate("DELETE FROM user WHERE date='"+date+"'"); //DELETE FROM 表的名字 WHERE 名稱='名稱的value';
        }
        catch(SQLException e)
        {
            System.out.println("DropDB Exception :" + e.toString());
        }
        finally
        {
            Close();
        }
    }
    //查詢資料
    //可以看看回傳結果集及取得資料方式
    public void SelectTable() {
        try {
            stat = con.createStatement();
            rs = stat.executeQuery(selectSQL);
            System.out.println("date player1 player2");
            System.out.println("-----------------------");
            while(rs.next()) {
                System.out.println(rs.getString("date")+"    "+rs.getString("player1")+"    "+rs.getString("player2"));
            }
        }
        catch(SQLException e) {
            System.out.println("DropDB Exception :" + e.toString());
        }
        finally {
            Close();
        }
    }
    //完整使用完資料庫後,記得要關閉所有Object
    //否則在等待Timeout時,可能會有Connection poor的狀況
    private void Close() {
        try {
            if(rs!=null) {
                rs.close();
                rs = null;
            }
            if(stat!=null) {
                stat.close();
                stat = null;
            }
            if(pst!=null) {
                pst.close();
                pst = null;
            }
        }
        catch(SQLException e) {
            System.out.println("Close Exception :" + e.toString());
        }
    }

    public void start()
    {
        //測看看是否正常
        Mysql test = new Mysql();
        //test.dropTable();
        System.out.print("---建表測試---\n");
        test.createTable();
        test.insertTable("6/14",
                "cs","0","0","0","0","3","0","0","0","0","0","0","0",
                "la","0","2","0","0","2","0","0","0","3","0","0","0");
        test.SelectTable();
        System.out.print("---刪除測試---\n");
        test.delcol("6/14");
        test.SelectTable();
        //SignInGUI test1 = new SignInGUI();
    }
}