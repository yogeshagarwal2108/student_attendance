package attend;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class submitAttendance implements ActionListener
{

	JFrame f4;
	JLabel l, l1, l2, l3, l4, l5, l6, l7, l8, l9;
	JRadioButton r;
	JButton b1, b2, b3;
	String id="", name="", email="", contact="";
	static String status= "";
	PreparedStatement ps;
	ResultSet rs;
	Connection co;

	submitAttendance()
	{
		try
		{

			Class.forName("com.mysql.jdbc.Driver");
			co=DriverManager.getConnection("jdbc:mysql://localhost:3306/student_attendance", "root", null);

			id= attend.loginStudent.id;
			
			ps= co.prepareStatement("select * from login_test where user_id= ?");
			ps.setString(1, id);
			rs= ps.executeQuery();
			while(rs.next())
			{
				id= rs.getString(1);
				name= rs.getString(2);
				email= rs.getString(5);
				contact= rs.getString(4);
				status= rs.getString(7);
			}

			f4= new JFrame("submit Attendance");
			l= new JLabel("Attendance");
			l.setFont(new Font("Serif", Font.BOLD, 60));
			l1= new JLabel("ID");
			l1.setFont(new Font("Serif", Font.BOLD, 30));
			l2= new JLabel("Name");
			l2.setFont(new Font("Serif", Font.BOLD, 30));
			l3= new JLabel("Email");
			l3.setFont(new Font("Serif", Font.BOLD, 30));
			l4= new JLabel("Contact No.");
			l4.setFont(new Font("Serif", Font.BOLD, 30));
			l5= new JLabel("Attendance");
			l5.setFont(new Font("Serif", Font.BOLD, 30));
			l6= new JLabel();
			l6.setText(id);
			l6.setFont(new Font("Serif", Font.BOLD, 25));
			l7= new JLabel();
			l7.setText(name);
			l7.setFont(new Font("Serif", Font.BOLD, 25));
			l8= new JLabel();
			l8.setText(email);
			l8.setFont(new Font("Serif", Font.BOLD, 25));
			l9= new JLabel();
			l9.setText(contact);
			l9.setFont(new Font("Serif", Font.BOLD, 25));
			r= new JRadioButton();
			b1= new JButton("Submit");
			b2= new JButton("Check Attendance");
			b3= new JButton("Back");

			l.setBounds(730, 100, 600, 60);
			l1.setBounds(720, 200, 150, 40);
			l2.setBounds(720, 260, 150, 40);
			l3.setBounds(720, 320, 150, 40);
			l4.setBounds(720, 380, 170, 40);
			l5.setBounds(720, 440, 150, 40);
			l6.setBounds(1000, 200, 150, 40);
			l7.setBounds(1000, 260, 250, 40);
			l8.setBounds(1000, 320, 400, 40);
			l9.setBounds(1000, 380, 200, 40);
			r.setBounds(1000, 440, 150, 40);
			b1.setBounds(700, 520, 200, 40);
			b2.setBounds(940, 520, 200, 40);
			b3.setBounds(850, 580, 200, 40);

			f4.setLayout(null);
			f4.add(l);
			f4.add(l1);
			f4.add(l2);
			f4.add(l3);
			f4.add(l4);
			f4.add(l5);
			f4.add(l6);
			f4.add(l7);
			f4.add(l8);
			f4.add(l9);
			f4.add(r);
			f4.add(b1);
			f4.add(b2);
			f4.add(b3);

			b1.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);
			f4.setSize(1800, 1000);
			f4.setVisible(true);


			if(status.equals("absent"))
			{
				r.setText("Absent");
			r.setFont(new Font("Serif", Font.BOLD, 25));
			}
			else
			{
				r.setText("Present");
			r.setFont(new Font("Serif", Font.BOLD, 25));
			}

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
				if(r.isSelected())
				{
					if(status.equals("absent"))
					{
						r.setText("Present");
						status= "present";
					
						ps= co.prepareStatement("update login_test set status=? where user_id=?");
						ps.setString(1, status);
						ps.setString(2, id);
						ps.executeUpdate();

						JOptionPane.showMessageDialog(f4, "You attendance is successfully updated as Present", "Status", JOptionPane.INFORMATION_MESSAGE);	
					}
					else if(status.equals("present"))
					{
						JOptionPane.showMessageDialog(f4, "You already submitted attendance", "Status", JOptionPane.INFORMATION_MESSAGE);	
					}
									
				}
				else
				{
						JOptionPane.showMessageDialog(f4, "Your attendance is not Updated, please click the attendence button", "Status", JOptionPane.INFORMATION_MESSAGE);		
				}
			}
			catch(Exception ee)
			{
				System .out.println("Exception : "+ee);
			}
		}
		else if(e.getSource()== b2)
		{
			f4.setVisible(false);
			new checkAttendance();
		}
		else if(e.getSource()== b3)
		{
			f4.setVisible(false);
			new loginStudent();
		}
	}


	public static void main(String[] args) {
		new submitAttendance();
	}
}