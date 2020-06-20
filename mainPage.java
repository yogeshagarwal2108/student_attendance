package attend;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class mainPage implements ActionListener
{

	JFrame f1;
	JButton b1, b2;
	JLabel l;


	mainPage()
	{
		try
		{
			f1= new JFrame("Main Page");
			l= new JLabel("Student Attendance System");
			l.setFont(new Font("Serif", Font.BOLD, 60));
			b1= new JButton("New Student");
			b2= new JButton("Already a Student");

			l.setBounds(580, 200, 800, 80);
			b1.setBounds(800, 400, 200, 40);
			b2.setBounds(800, 550, 200, 40);

			f1.setLayout(null);
			f1.add(l);
			f1.add(b1);
			f1.add(b2);

			b1.addActionListener(this);
			b2.addActionListener(this);
			f1.setSize(1800, 1000);
			f1.setVisible(true);
		}catch(Exception ee)
		{
			System .out.println("Exception : "+ee);
		}
	}


	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()== b1)
		{
			f1.setVisible(false);
			new addStudent();
		}
		else if(e.getSource()== b2)
		{
			f1.setVisible(false);
			new loginStudent();
		}
	}


	public static void main(String[] args) {
		
		new mainPage();
	}
}