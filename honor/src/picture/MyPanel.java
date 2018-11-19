package picture;
import honor.*;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;


public class MyPanel extends JPanel implements KeyListener,ActionListener {
	//战场
	public Round battle;
	public JLabel battleLabel;
	JLabel redLabel,blueLabel;
	
	//角色控制主动方与被动方
	LinkedList<JRadioButtonMenuItem> redHeros,blueHeros;		//为了访问的方便
	ButtonGroup redGroup,blueGroup;		//为了实现单选
	Container redGroupContainer,blueGroupContainer;	//为了换取作战队的方便
	
	//攻击按钮
	JButton aButton;
	
	//简单攻击
	
	//辅助变量
	private String operatingHero;	//当前在操作的英雄
	
	MyPanel() {
		int i;
		JRadioButtonMenuItem tempButton;
		
		//提示符
		blueLabel = new JLabel("蓝色方：");
		
		//角色单选框，当前作战队
		redGroup = new ButtonGroup();
		redGroupContainer = new Container();
		redHeros = new LinkedList<JRadioButtonMenuItem>();
		
		blueGroup = new ButtonGroup();
		blueGroupContainer = new Container();
		blueHeros = new LinkedList<JRadioButtonMenuItem>();		
		
		//战场构建
		battle = new Round(this);
		battleLabel = new JLabel(battle.getMap());
		
		blueLabel = new JLabel("蓝色方：");
		blueGroupContainer.add(blueLabel);
		redLabel = new JLabel("红色方：");
		redGroupContainer.add(redLabel);
		//构建甲队按钮
		int halfLength = 5;
		for(i=0 ; i<halfLength ; i++) {
			tempButton = new JRadioButtonMenuItem(""+(char)('A'+i));
			blueHeros.add(tempButton);	
			blueGroup.add(tempButton);	//达到单选目的
			blueGroupContainer.add(tempButton);	//把英雄加到容器
			
			tempButton.setActionCommand(""+(char)('A'+i));	//监听按钮的激发标准
			tempButton.addActionListener(this);			//监听按钮队象
		}
		
		
		
		//构建乙队按钮
		halfLength = 5;
		for(i=0 ; i<halfLength ; i++) {
			tempButton = new JRadioButtonMenuItem(""+(char)('F'+i));
			redHeros.add(tempButton);
			redGroup.add(tempButton);		
			redGroupContainer.add(tempButton);
			tempButton.setActionCommand(""+(char)('F'+i));
			tempButton.addActionListener(this);
		}
	
		
		//初始化
		operatingHero = blueHeros.get(0).getActionCommand();
		blueHeros.get(0).setSelected(true);
		redHeros.get(0).setSelected(true);
		blueGroupContainer.setLayout(new FlowLayout());
		redGroupContainer.setLayout(new FlowLayout());
		
		//按钮
		aButton = new JButton("近身搏斗");
		aButton.addActionListener(this);
		aButton.setFocusable(false);
		
		
		
		/*decideButton = new JButton("状态");
		decideButton.addActionListener(this);	//监听按钮
		decideButton.setFocusable(false); 		//按钮失焦
		*/
		
		
		
		
		
		
		this.add(blueGroupContainer);
		this.add(redGroupContainer);
		this.add(aButton);
		
		battleLabel.setFont(new Font(Font.DIALOG,Font.BOLD,18));
		this.add(battleLabel);
		
		
		
	}
	
	
	//监听实现：
	//监听当前操作的角色
	public void actionPerformed(ActionEvent e) {
		int i;
		
		//选择了当前操作对象
		for(i=0 ; i<blueHeros.size() ; i++) {
			if(e.getSource() == blueHeros.get(i)) {
				operatingHero = e.getActionCommand();	//更改当前英雄
				
				break;
			}
		}
		
		
		for(i=0 ; i<redHeros.size() ; i++) {
			if(e.getSource() == redHeros.get(i)) {
				operatingHero = redHeros.get(i).getActionCommand();
				break;
			}
		}
		
		//按了交换按钮
		/*if(e.getSource() == changeButton) {
			try {
				exchangeGroup();
				System.out.println("a");
			} catch (InterruptedException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
		*/
		//按了确定简单攻击按钮
		 if(e.getSource() == aButton) {
				battle.understand(operatingHero,"attack");		//发动攻击
		}
	}
	
	//监听键盘上下左右
	public void keyPressed(KeyEvent e) {
		String order="";
		
		if(e.getKeyCode() == KeyEvent.VK_W) {
			order = "moveup";
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_S) {
			order = "movedown";
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_A) {
			order =  "moveleft";
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			order = "moveright";
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			order = "down";
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			order = "left";
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			order = "right";
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP) {
			order = "up";
		}
		else if(e.getKeyCode() == KeyEvent.VK_1) {
			order = "q";
		}
		else if(e.getKeyCode() == KeyEvent.VK_2) {
			order = "w";
		}
		else if(e.getKeyCode() == KeyEvent.VK_3) {
			order = "e";
		}
		else if(e.getKeyCode() == KeyEvent.VK_4) {
			order = "r";
		}
		
			battle.understand(operatingHero,order);
		
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}

	//监听复杂的操作
	//交换作战队
/*	private void exchangeGroup() throws InterruptedException {
		int i;
		
		//battle上程序内部的交换：
		//battle.understandOrder("交换作战队");
		
		//图形界面上的交换：
		this.swap();
		
		//更改单选按钮
		this.add(activeGroupContainer,1);
		
		//辅助变量的交换
		operatingHero = activeHeros.get(0).getActionCommand();
		beAttackedHero = passiveHeros.get(0).getActionCommand();
		attacker.setText(operatingHero+"简单攻击");
		
		activeHeros.get(0).setSelected(true);
		passiveHeros.get(0).setSelected(true);
	}
	
	//交换函数
	private void swap() {
		LinkedList<JRadioButtonMenuItem> temp1;
		temp1 = activeHeros;
		activeHeros = passiveHeros;
		passiveHeros = temp1;
		
		ButtonGroup temp2;
		temp2 = activeGroup;
		activeGroup = passiveGroup;
		passiveGroup = temp2;
		
		Container temp3;
		temp3 = activeGroupContainer;
		activeGroupContainer = passiveGroupContainer;
		passiveGroupContainer = temp3;
		
		//更改简单攻击对象
		simpleAttackContainer.add(passiveGroupContainer, 1);
	}

	//判断英雄是否死亡
	public void manageDeath(char name) {
		int i;
		
		for(i=0 ; i<passiveHeros.size() ; i++) {
			if(passiveHeros.get(i).getActionCommand().equals(""+name)) {
				passiveGroup.remove(passiveHeros.get(i));
				passiveGroupContainer.remove(passiveHeros.get(i));
				passiveHeros.remove(passiveHeros.get(i));	//最后才在链表移除
			}
		}
		
		passiveHeros.get(0).setSelected(true);	//再次选定被攻击的英雄
		beAttackedHero = passiveHeros.get(0).getActionCommand();	//设置新的被攻击的英雄
	}
	*/
}


