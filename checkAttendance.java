package attend;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class checkAttendance implements ActionListener
{

	JFrame f5;
	JLabel l, l1;
	JButton b1, b2;
	PreparedStatement ps;
	ResultSet rs;
	Connection co;
	String status="";

	checkAttendance()
	{
		try
		{

			Class.forName("com.mysql.jdbc.Driver");
			co=DriverManager.getConnection("jdbc:mysql://localhost:3306/student_attendance", "root", null);

			status= attend.submitAttendance.status;


			f5= new JFrame("check Attendance");
			l= new JLabel("Check Attendance");
			l.setFont(new Font("Serif", Font.BOLD, 60));
			l1= new JLabel();
			b1= new JButton("Submit Attendance");
			b2= new JButton("Back");

			l.setBounds(660, 200, 600, 60);
			l1.setBounds(530, 400, 800,60);
			b1.setBounds(790, 500, 200, 40);
			b2.setBounds(790, 500, 200, 40);
			
			f5.setLayout(null);
			f5.add(l);
			f5.add(l1);
			
			b1.addActionListener(this);
			b2.addActionListener(this);
			f5.setSize(1800, 1000);
			f5.setVisible(true);


			if(status.equals("present"))
			{
				l1.setText("Your attendance is done and successfully submitted");
				l1.setFont(new Font("Serif", Font.BOLD, 30));
				f5.add(b2);
			}
			else
			{
				l1.setText("Attendance not submitted yet, submit your attendance");
				l1.setFont(new Font("Serif", Font.BOLD, 30));
				f5.add(b1);
			}

		}
		catch(Exception ee)
		{
			System.out.println("Exception : "+ee);
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()== b1 || e.getSource()== b2)
		{
			f5.setVisible(false);
			new submitAttendance();
		}
	}

	public static void main(String[] args) {
		new checkAttendance();
	}
}