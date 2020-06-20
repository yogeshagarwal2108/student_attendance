package attend;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class addStudent implements ActionListener
{

	JFrame f2;
	JLabel l, l1, l2, l3, l4, l5;
	JTextField t1, t2, t3, t4, t5;
	JButton b1, b2;
	String name="", password="", contact="", email="", address="";
	PreparedStatement ps;
	ResultSet rs;
	Connection co;

	addStudent()
	{
		try
		{
			f2= new JFrame("Main Page");
			l= new JLabel("Add Student");
			l.setFont(new Font("Serif", Font.BOLD, 60));
			l1= new JLabel("Name");
			l1.setFont(new Font("Serif", Font.BOLD, 25));
			l2= new JLabel("Password");
			l2.setFont(new Font("Serif", Font.BOLD, 25));
			l3= new JLabel("Contact");
			l3.setFont(new Font("Serif", Font.BOLD, 25));
			l4= new JLabel("Email");
			l4.setFont(new Font("Serif", Font.BOLD, 25));
			l5= new JLabel("Address");
			l5.setFont(new Font("Serif", Font.BOLD, 25));
			t1= new JTextField();
			t2= new JTextField();
			t3= new JTextField();
			t4= new JTextField();
			t5= new JTextField();
			b1= new JButton("Add");
			b2= new JButton("Back");

			l.setBounds(760, 100, 600, 60);
			l1.setBounds(750, 200, 150, 40);
			l2.setBounds(750, 260, 150, 40);
			l3.setBounds(750, 320, 150, 40);
			l4.setBounds(750, 380, 150, 40);
			l5.setBounds(750, 440, 150, 40);
			t1.setBounds(950, 200, 150, 40);
			t2.setBounds(950, 260, 150, 40);
			t3.setBounds(950, 320, 150, 40);
			t4.setBounds(950, 380, 150, 40);
			t5.setBounds(950, 440, 150, 40);
			b1.setBounds(700, 520, 200, 40);
			b2.setBounds(940, 520, 200, 40);

			f2.setLayout(null);
			f2.add(l);
			f2.add(l1);
			f2.add(l2);
			f2.add(l3);
			f2.add(l4);
			f2.add(l5);
			f2.add(t1);
			f2.add(t2);
			f2.add(t3);
			f2.add(t4);
			f2.add(t5);
			f2.add(b1);
			f2.add(b2);

			b1.addActionListener(this);
			b2.addActionListener(this);
			f2.setSize(1800, 1000);
			f2.setVisible(true);


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
			try
			{
				name= t1.getText();
				password= t2.getText();
				contact= t3.getText();
				email= t4.getText();
				address= t5.getText();

				String email_regex= "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$"; 
				Pattern pt= Pattern.compile(email_regex);
				if(!pt.matcher(email).matches())
				{
					JOptionPane.showMessageDialog(f2, "Enter valid Email", "Incorrect Email", JOptionPane.ERROR_MESSAGE);
				}
				else if(name== "" || password== "" || contact== "" || email== "" || address== "")
				{	
					JOptionPane.showMessageDialog(f2, "Enter All Entries", "Empty Entry", JOptionPane.INFORMATION_MESSAGE);	
				}
				else
				{
					int count=0;

					ps= co.prepareStatement("select count(*) from login_test where password=? or email=?");
					ps.setString(1, password);
					ps.setString(2, email);
					rs= ps.executeQuery();
					while(rs.next()){
						count= rs.getInt(1);
					}

					if(count!= 0)
					{
						JOptionPane.showMessageDialog(f2, "Please try another email or password", "Invalid Entry", JOptionPane.INFORMATION_MESSAGE);	
					}
					else
					{
						ps= co.prepareStatement("insert into login_test(user_name, password, contact, email, address, status) values(?, ?, ?, ?, ?, ?)");
						ps.setString(1, name);
						ps.setString(2, password);
						ps.setString(3, contact);
						ps.setString(4, email);
						ps.setString(5, address);
						ps.setString(6, "absent");
				
						ps.executeUpdate();
						ps.close();

						ps= co.prepareStatement("select * from login_test where password= ?");
						ps.setString(1, password);

						rs= ps.executeQuery();

						int s=0;
						while(rs.next()){
							s= rs.getInt(1);
						}
				
						JOptionPane.showMessageDialog(f2, "you successfully added and your ID is "+s, "Student Added", JOptionPane.INFORMATION_MESSAGE);	
						new mainPage();
					}
				
				}
			
			}catch(Exception ee)
			{	
				JOptionPane.showMessageDialog(f2, "Enter Valid Entries ", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getSource()== b2)
		{
			f2.setVisible(false);
			new mainPage();
		}
	}


	public static void main(String[] args) {
		
		new addStudent();
	}
}