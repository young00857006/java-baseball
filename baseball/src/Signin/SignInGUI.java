package Signin;

import ability.createDB.ConnectDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

// 資料庫
public class SignInGUI {

    private final JFrame frame;
    private final JPanel panel1,panel2,panel3,panel4,panel5;
    private final JTextField account, password,name,mail;
    private final JButton confirm, back;
    private final JLabel text,label1,label2,label3,label4;
    private final ImageIcon img1,img2,img3,img4;
    public SignInGUI() {
        frame = new JFrame("註冊介面");
        frame.setLayout(new GridLayout(6, 1));
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        text = new JLabel("球隊管理系統-註冊",0);
        text.setFont(new Font("標楷體", Font.BOLD, 16));
        img1 = new ImageIcon();
        img2 = new ImageIcon();
        img3 = new ImageIcon();
        img4 = new ImageIcon();
        //----------資料庫----------------------------------------------
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //註冊driver
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=utf-8",
                    "root","Warren0702");
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
        //-----------------------------------------------------------
        account = new JTextField("帳號",20);
        Image image = new ImageIcon("C:\\Users\\ACER\\IdeaProjects\\GUI\\src\\登入icon.png").getImage();
        image = image.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        img1.setImage(image);
        label1 = new JLabel(img1);
        panel1.add(label1);
        panel1.add(account);
        //-----------------------------------------------------------
        password = new JTextField("密碼",20);
        Image image2 = new ImageIcon("C:\\Users\\ACER\\IdeaProjects\\GUI\\src\\密碼icon.png").getImage();
        image2 = image2.getScaledInstance(40,40,Image.SCALE_DEFAULT);
        img2.setImage(image2);
        label2 = new JLabel(img2);
        panel2.add(label2);
        panel2.add(password);
        //-----------------------------------------------------------
        name = new JTextField("真實姓名",20);
        Image image3 = new ImageIcon("C:\\Users\\ACER\\IdeaProjects\\GUI\\src\\筆.png").getImage();
        image3 = image3.getScaledInstance(40,40,Image.SCALE_DEFAULT);
        img3.setImage(image3);
        label3 = new JLabel(img3);
        panel3.add(label3);
        panel3.add(name);
        //-----------------------------------------------------------
        mail = new JTextField("電子信箱",20);
        Image image4 = new ImageIcon("C:\\Users\\ACER\\IdeaProjects\\GUI\\src\\信箱.png").getImage();
        image4 = image4.getScaledInstance(40,40,Image.SCALE_DEFAULT);
        img4.setImage(image4);
        label4 = new JLabel(img4);
        panel4.add(label4);
        panel4.add(mail);
        //-----------------------------------------------------------
        ActionListener listener1 = new MyEventListener();
        ActionListener listener2 = new MyEventListener();
        confirm = new JButton("確認");
        confirm.addActionListener(listener1);
        back = new JButton("退出");
        back.addActionListener(listener2);
        panel5.add(confirm);
        panel5.add(back);
        //-----------------------------------------------------------
        frame.add(text);
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
        frame.add(panel5);
        frame.setBounds(120,120,380,320);
        frame.setVisible(true);
    }
    String sqlstr ="",accountIn="",passwordIn="", nameIn="", mailIn="";

    public class MyEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            int test=0;
            MysqlJdbc s = new MysqlJdbc();
            String buttonName = e.getActionCommand();
            if (buttonName.equals("確認")){
                try {
                    //System.out.printf("%s\n", s.checkAccount2(account.getText(), password.getText()));
                    if(account.getText().equals("帳號")||account.getText().equals("")) {
                        test=1;
                        JOptionPane.showMessageDialog(frame,"註冊失敗(帳號有誤)");
                    }
                    else if(account.getText().equals(s.checkAccount2(account.getText(), password.getText()))){
                        test=1;
                        JOptionPane.showMessageDialog(frame,"帳號已被使用");
                    }
                    else if(password.getText().equals("密碼")||password.getText().equals("")){
                        test=1;
                        JOptionPane.showMessageDialog(frame,"註冊失敗(密碼有誤)");
                    }
                    else if(name.getText().equals("真實姓名")||name.getText().equals("")){
                        test=1;
                        JOptionPane.showMessageDialog(frame,"註冊失敗(真實姓名有誤)");
                    }
                    else if(mail.getText().equals("電子信箱")||mail.getText().equals("")){
                        test=1;
                        JOptionPane.showMessageDialog(frame,"註冊失敗(電子信箱有誤)");
                    }
                    else{
                        //存入資料庫 TODO//
                        //System.out.print("in sql!");
                        accountIn = account.getText();
                        passwordIn = password.getText();
                        nameIn = name.getText();
                        mailIn = mail.getText();
                        sqlstr = "INSERT INTO user (account,password,name,mail) VALUES ('" + accountIn  + "','"+ passwordIn + "','"+ nameIn + "','"+ mailIn + "'" + ")";
                        ConnectDB infieldDB,outfieldDB,runDB,sidehitDB,hitDB;
                        //創建屬於這位使用者的table 模擬register;
                        infieldDB = new ConnectDB("infield","CREATE TABLE " + nameIn + " ( year INTEGER,month INTEGER,day INTEGER, ball INTEGER, ground INTEGER, process DOUBLE, correct INTEGER)");
                        outfieldDB = new ConnectDB("outfield","CREATE TABLE " + nameIn + " (year INTEGER,month INTEGER,day INTEGER, ball INTEGER, fly INTEGER, process DOUBLE, correct INTEGER)");
                        runDB = new ConnectDB("run","CREATE TABLE " + nameIn + " (year INTEGER,month INTEGER,day INTEGER, 0to1 DOUBLE, 0to2 DOUBLE, 1to3 DOUBLE, 2to0 DOUBLE, 100m DOUBLE)");
                        sidehitDB = new ConnectDB("sidehit","CREATE TABLE " + nameIn + " (year INTEGER,month INTEGER,day INTEGER, ball INTEGER, hit INTEGER, distance DOUBLE )");
                        hitDB = new ConnectDB("hit","CREATE TABLE " + nameIn + " (year INTEGER,month INTEGER,day INTEGER, ball INTEGER, hit INTEGER, distance DOUBLE )");
                        JOptionPane.showMessageDialog(frame,"註冊成功");

                    }
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
                try {
                    if(test!=1){
                        Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=utf-8",
                            "root","Warren0702");
                        if(!con.isClosed())
                            System.out.println("資料庫連線成功");

                        Statement sm = con.createStatement();
                        sm.execute(sqlstr);
                        ResultSet rs = sm.executeQuery("SELECT * FROM user");
                        ResultSetMetaData rsmd = rs.getMetaData();
                        for(int i=1; i <= rsmd.getColumnCount(); i++){
                            System.out.print(rsmd.getColumnName(i)+"\t");
                        }
                        System.out.println("\n---------------------");
                        while(rs.next()){
                            System.out.print(rs.getString(1) + "\t" +rs.getString(2)+ "\t" +rs.getString(3)+ "\t" +rs.getString(4));
                            System.out.println();
                        }
                        sm.close();
                        con.close();
                    }
                }
                catch(SQLException e1) {
                    System.out.println("資料庫連線失敗123");
                }
            }
            else if(buttonName.equals("退出")){
                frame.setVisible(false);
            }
        }

    }

}
