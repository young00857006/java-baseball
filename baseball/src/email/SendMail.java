package email;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class SendMail {
	private ArrayList<String> emailAddressList = new ArrayList(); 
	private Connection con = null; //Database objects  
	private Statement stat = null; 
	private ResultSet rs = null; 
	private PreparedStatement pst = null; 
	private Connection con1 = null; //Database objects  
	private Statement stat1 = null; 
	private ResultSet rs1 = null; 
	private PreparedStatement pst1 = null; 
	private String Subject,Text;
	
	public SendMail(String Subject,String Text) {
		this.Subject = Subject;
		this.Text = Text;
		try { 
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			//註�?�driver 
			con = DriverManager.getConnection( 
			"jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=utf-8", 
			"root","Warren0702");
		} 
		catch(ClassNotFoundException e) 
		{ 
			System.out.println("DriverClassNotFound :"+e.toString()); 
		}
		catch(SQLException x) { 
			System.out.println("Exception :"+x.toString()); 
		} 
		try 
		{ 
			stat = con.createStatement(); 
			rs = stat.executeQuery("select mail from user"); 
			while(rs.next()) 
			{ 
				emailAddressList.add(rs.getString("mail"));
			} 
		} 
		catch(SQLException e) 
		{ 
			System.out.println("DropDB Exception :" + e.toString()); 
		} 
		finally 
		{ 
			try 
			{ 
				if(rs!=null) 
				{ 
					rs.close(); 
					rs = null; 
				} 
				if(stat!=null) 
				{ 
					stat.close(); 
					stat = null; 
				} 
				if(pst!=null) 
				{ 
					pst.close(); 
					pst = null; 
				} 
			} 
			catch(SQLException e) 
			{ 
				System.out.println("Close Exception :" + e.toString()); 
			} 
		} 
		startSend();
	}
	public SendMail(String Subject,int week) {
		this.Subject = Subject;
		this.Text ="";
		try { 
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			//註�?�driver 
			con = DriverManager.getConnection( 
			"jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=utf-8", 
			"root","Warren0702");
		} 
		catch(ClassNotFoundException e) 
		{ 
			System.out.println("DriverClassNotFound :"+e.toString()); 
		}
		catch(SQLException x) { 
			System.out.println("Exception :"+x.toString()); 
		} 
		try 
		{ 
			stat = con.createStatement(); 
			rs = stat.executeQuery("select mail from user"); 
			while(rs.next()) 
			{ 
				emailAddressList.add(rs.getString("mail"));
			} 
		} 
		catch(SQLException e) 
		{ 
			System.out.println("DropDB Exception :" + e.toString()); 
		} 
		finally 
		{ 
			try 
			{ 
				if(rs!=null) 
				{ 
					rs.close(); 
					rs = null; 
				} 
				if(stat!=null) 
				{ 
					stat.close(); 
					stat = null; 
				} 
				if(pst!=null) 
				{ 
					pst.close(); 
					pst = null; 
				} 
			} 
			catch(SQLException e) 
			{ 
				System.out.println("Close Exception :" + e.toString()); 
			} 
		} 
	
		try { 
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			//註�?�driver 
			con1 = DriverManager.getConnection( 
			"jdbc:mysql://localhost/contest?useUnicode=true&characterEncoding=utf-8", 
			"root","Warren0702");
		} 
		catch(ClassNotFoundException e) 
		{ 
			System.out.println("DriverClassNotFound :"+e.toString()); 
		}
		catch(SQLException x) { 
			System.out.println("Exception :"+x.toString()); 
		} 
		try 
		{ 
			
			stat1 = con1.createStatement();
            rs1 = stat1.executeQuery("select * from week"+week);
            while(rs1.next()){
            	if(!rs1.getString("W1_1").equalsIgnoreCase("x"))
            		this.Text+="週一早上對"+rs1.getString("W1_1")+'\n';
            	if(!rs1.getString("W1_2").equalsIgnoreCase("x"))
            		this.Text+="週一中午對"+rs1.getString("W1_2")+'\n';
            	if(!rs1.getString("W2_1").equalsIgnoreCase("x"))
            		this.Text+="週二早上對"+rs1.getString("W2_1")+'\n';
            	if(!rs1.getString("W2_2").equalsIgnoreCase("x"))
            		this.Text+="週二中午對"+rs1.getString("W2_2")+'\n';
            	if(!rs1.getString("W3_1").equalsIgnoreCase("x"))
            		this.Text+="週三早上對"+rs1.getString("W3_1")+'\n';
            	if(!rs1.getString("W3_2").equalsIgnoreCase("x"))
            		this.Text+="週三中午對"+rs1.getString("W3_2")+'\n';
            	if(!rs1.getString("W4_1").equalsIgnoreCase("x"))
            		this.Text+="週四早上對"+rs1.getString("W4_1")+'\n';
            	if(!rs1.getString("W4_2").equalsIgnoreCase("x"))
            		this.Text+="週四中午對"+rs1.getString("W4_2")+'\n';
            	if(!rs1.getString("W5_1").equalsIgnoreCase("x"))
            		this.Text+="週五早上對"+rs1.getString("W5_1")+'\n';
            	if(!rs1.getString("W4_2").equalsIgnoreCase("x"))
            		this.Text+="週五中午對"+rs1.getString("W5_2")+'\n';
            }
            this.Text+="大家記得準時比賽!!";
		} 
		catch(SQLException e) 
		{ 
			System.out.println("DropDB Exception :" + e.toString()); 
		} 
		finally 
		{ 
			try 
			{ 
				if(rs1!=null) 
				{ 
					rs1.close(); 
					rs1 = null; 
				} 
				if(stat1!=null) 
				{ 
					stat1.close(); 
					stat1 = null; 
				} 
				if(pst1!=null) 
				{ 
					pst1.close(); 
					pst1 = null; 
				} 
			} 
			catch(SQLException e) 
			{ 
				System.out.println("Close Exception :" + e.toString()); 
			} 
		} 
		startSend();
	}
	/*陳威融 寄信給還沒交表的*/
	public SendMail(String Subject,ArrayList<String> totallist,int week){
		emailAddressList = new ArrayList();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=utf-8",
					"root","Warren0702");
		}
		catch(ClassNotFoundException e) {
			System.out.println("DriverClassNotFound :"+e.toString());
		}
		catch(SQLException x) {
			System.out.println("Exception :"+x.toString());
		}
		try
		{
			stat = con.createStatement();
			for(int i=0;i<totallist.size();i++){
				rs = stat.executeQuery("select * from user");
				while(rs.next()){
					if(rs.getString("name").equals(totallist.get(i))){
						emailAddressList.add(rs.getString("mail"));
						break;
					}
				}
			}
		}
		catch(SQLException e)
		{
			System.out.println("DropDB Exception :" + e.toString());
		}
		finally
		{
			try
			{
				if(rs!=null)
				{
					rs.close();
					rs = null;
				}
				if(stat!=null)
				{
					stat.close();
					stat = null;
				}
				if(pst!=null)
				{
					pst.close();
					pst = null;
				}
			}
			catch(SQLException e)
			{
				System.out.println("Close Exception :" + e.toString());
			}
		}
		this.Subject = Subject;
		this.Text = "趕快交第"+week+"週的時間表! 全部人都在等你!";
		startSend();
	}
	/*陳威融*/
	public void startSend() {
		
        String from = "softballjava@gmail.com";
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("softballjava@gmail.com", "getchampion");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
        	for(int i=0;i<emailAddressList.size();i++) {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddressList.get(i)));

            // Set Subject: header field
            //message.setSubject("官廷洋Test Java");
            message.setSubject(Subject);
            // Now set the actual message
            //message.setText("成功了，回傳照片");
            message.setText(Text);
            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        	}
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
		
			
		
	}

}