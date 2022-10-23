package project;
import java.sql.*;
public class DeleteAllData {
    public DeleteAllData() {
        Connection con = null; //Database objects//連接object
        Connection con1 = null; //Database objects//連接object
        Connection con2 = null; //Database objects//連接object
        Connection con3 = null; //Database objects//連接object
        Connection con4 = null; //Database objects//連接object
        Connection con5 = null; //Database objects//連接object
        Statement stat = null;//執行,傳入之sql為完整字串
        Statement stat1 = null;//執行,傳入之sql為完整字串
        Statement stat2 = null;//執行,傳入之sql為完整字串
        Statement stat3 = null;//執行,傳入之sql為完整字串
        Statement stat4 = null;//執行,傳入之sql為完整字串
        Statement stat5 = null;//執行,傳入之sql為完整字串
        ResultSet rs = null;//結果集
        ResultSet rs1 = null;//結果集
        PreparedStatement pst = null;//執行,傳入之sql為預儲之字申,需要傳入變數之位置
        PreparedStatement pst1 = null;//執行,傳入之sql為預儲之字申,需要傳入變數之位置
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/week?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost/gameschedule?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
            con2 = DriverManager.getConnection("jdbc:mysql://localhost/contest?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
            con3 = DriverManager.getConnection("jdbc:mysql://localhost/training?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
            //con4 = DriverManager.getConnection("jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
            con5 = DriverManager.getConnection("jdbc:mysql://localhost/notice?useUnicode=true&characterEncoding=utf-8", "root","Warren0702");
        }
        catch(ClassNotFoundException e) { System.out.println("DriverClassNotFound :"+e.toString()); }//有可能會產生sqlexception
        catch(SQLException x) { System.out.println("Exception :"+x.toString()); }
        try {
            stat = con.createStatement();
            stat1 = con1.createStatement();
            stat.executeUpdate("DROP TABLE week");
            for(int i=1;i<20;i++){
                stat1.executeUpdate("DROP TABLE week"+i);
            }

        }
        catch(SQLException e) { System.out.println("DropDB Exception :" + e.toString()); }
        finally { try {
            if(rs!=null) {
                rs.close();
                rs = null; }
            if(stat!=null) {
                stat.close();
                stat = null; }
            if(pst!=null) {
                pst.close();
                pst = null; } }
        catch(SQLException e) { System.out.println("Close Exception :" + e.toString()); } }
        try {
            stat2 = con2.createStatement();
            for(int i=1;i<20;i++)
                stat2.executeUpdate("DROP TABLE week"+i);
        }
        catch(SQLException e) { System.out.println("DropDB Exception :" + e.toString()); }
        finally { try {
            if(rs!=null) {
                rs.close();
                rs = null; }
            if(stat!=null) {
                stat.close();
                stat = null; }
            if(pst!=null) {
                pst.close();
                pst = null; } }
        catch(SQLException e) { System.out.println("Close Exception :" + e.toString()); } }
        try {
            stat3 = con3.createStatement();
            for(int i=1;i<20;i++)
                stat3.executeUpdate("DROP TABLE week"+i);
        }
        catch(SQLException e) { System.out.println("DropDB Exception :" + e.toString()); }
        finally { try {
            if(rs!=null) {
                rs.close();
                rs = null; }
            if(stat!=null) {
                stat.close();
                stat = null; }
            if(pst!=null) {
                pst.close();
                pst = null; } }
        catch(SQLException e) { System.out.println("Close Exception :" + e.toString()); } }

        try {
            stat5 = con5.createStatement();
            stat5.executeUpdate("DROP TABLE text");
        }
        catch(SQLException e) { System.out.println("DropDB Exception :" + e.toString()); }
        finally { try {
            if(rs!=null) {
                rs.close();
                rs = null; }
            if(stat!=null) {
                stat.close();
                stat = null; }
            if(pst!=null) {
                pst.close();
                pst = null; } }
        catch(SQLException e) { System.out.println("Close Exception :" + e.toString()); } }
    }
}
