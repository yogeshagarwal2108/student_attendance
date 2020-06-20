package attend;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class loginStudent implements ActionListener
{

	JFrame f3;
	JLabel l, l1, l2, l3;
	JTextField t1, t2, t3;
	JButton b1, b2;
	static String id= ""; 
	String name= "", password="";
	PreparedStatement ps;
	ResultSet rs;
	Connection co;

	loginStudent()
	{
		try
		{
			f3= new JFrame("Login Student");
			l= new JLabel("Login Student");
			l.setFont(new Font("Serif", Font.BOLD, 60));
			l1= new JLabel("ID");
			l1.setFont(new Font("Serif", Font.BOLD, 25));
			l2= new JLabel("Name");
			l2.setFont(new Font("Serif", Font.BOLD, 25));
			l3= new JLabel("Password");
			l3.setFont(new Font("Serif", Font.BOLD, 25));
			t1= new JTextField();
			t2= new JTextField();
			t3= new JTextField();
			b1= new JButton("Login");
			b2= new JButton("Back");

			l.setBounds(710, 130, 600, 80);
			l1.setBounds(740, 300, 100, 40);
			l2.setBounds(740, 360, 100, 40);
			l3.setBounds(740, 420, 150, 40);
			t1.setBounds(900, 300, 150, 40);
			t2.setBounds(900, 360, 150, 40);
			t3.setBounds(900, 420, 150, 40);
			b1.setBounds(730, 520, 150, 40);
			b2.setBounds(910, 520, 150, 40);

			f3.setLayout(null);
			f3.add(l);
			f3.add(l1);
			f3.add(l2);
			f3.add(l3);
			f3.add(t1);
			f3.add(t2);
			f3.add(t3);
			f3.add(b1);
			f3.add(b2);

			b1.addActionListener(this);
			b2.addActionListener(this);
			f3.setSize(1800, 1000);
			f3.setVisible(true);


			Class.forName("com.mysql.jdbc.Driver");
			co=DriverManager.getConnection("jdbc:mysql://localhost:3306/student_attendance", "root", null);

		}catch(Exception ee)
		{
			System .out.println("Exception : "+ee);
		}
	}


	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource()== b1)
		{
			int count=0;
			
			try
			{
				id= t1.getText();
				name= t2.getText();
				password= t3.getText();

				ps= co.prepareStatement("select count(*) from login_test where user_id=? and user_name=? and password=?");
				ps.setString(1, id);
				ps.setString(2, name);
				ps.setString(3, password);
				rs= ps.executeQuery();

				while(rs.next())
				{
					count= rs.getInt(1);
				}
			
				if(count== 0)
				{
				JOptionPane.showMessageDialog(f3, "Invalid Entry", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					f3.setVisible(false);
					new submitAttendance();
				}
			}catch(Exception ee)
			{
				JOptionPane.showMessageDialog(f3, "Enter Valid Entries ", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
			}
		}

		else if(e.getSource()== b2)
		{
			f3.setVisible(false);
			new mainPage();
		}
	}


	public static void main(String[] args) 
	{
		new	loginStudent();
	}
}