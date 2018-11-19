package picture;
import honor.*;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.*;

public class MyFrame extends JFrame {
	static MyPanel panel;
	
	MyFrame() {
		panel = new MyPanel();
		this.add(panel);
		this.addKeyListener(panel);	//监控面板的键盘输入
		
		this.setTitle("王者荣耀");
		this.setVisible(true);
		
		this.setSize(1300, 1200);
		this.setLocation(300, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String [] args) {
		/*Runnable runnable = new Runnable() {
			public void run() {
				MyFrame frame = new MyFrame();
			}
		};
		javax.swing.SwingUtilities.invokeLater (runnable);*/
		MyFrame frame=new MyFrame();
	    new Thread(new Player(frame)).start();
	    new Thread(new PositiveComputer(frame)).start();
	    new Thread(new PassiveComputer(frame)).start();
	}
}
class Player implements Runnable{
	static MyFrame frame;
	public Player(MyFrame frame)
	{
		this.frame=frame;
	}
	public void run()
	{
		
	}
}
class PositiveComputer implements Runnable{
	static MyFrame frame;
	public PositiveComputer(MyFrame frame)
	{
		this.frame=frame;
	}
	public void run()
	{
		while(true)
		{
			attack();
			try {
			Thread.sleep(100);
		    } 
			catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		     }
		}
	}
	public void attack() {
		
	}
}
class PassiveComputer implements Runnable{
	static MyFrame frame;
	public PassiveComputer(MyFrame frame)
	{
		this.frame=frame;
	}
	public void run()
	{
		while(true)
		{
			espace();
			try {
			Thread.sleep(100);
		    } 
			catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		     }
		}
		
	}
	public void espace() {
		
	}
}
