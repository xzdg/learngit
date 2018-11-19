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
	//ս��
	public Round battle;
	public JLabel battleLabel;
	JLabel redLabel,blueLabel;
	
	//��ɫ�����������뱻����
	LinkedList<JRadioButtonMenuItem> redHeros,blueHeros;		//Ϊ�˷��ʵķ���
	ButtonGroup redGroup,blueGroup;		//Ϊ��ʵ�ֵ�ѡ
	Container redGroupContainer,blueGroupContainer;	//Ϊ�˻�ȡ��ս�ӵķ���
	
	//������ť
	JButton aButton;
	
	//�򵥹���
	
	//��������
	private String operatingHero;	//��ǰ�ڲ�����Ӣ��
	
	MyPanel() {
		int i;
		JRadioButtonMenuItem tempButton;
		
		//��ʾ��
		blueLabel = new JLabel("��ɫ����");
		
		//��ɫ��ѡ�򣬵�ǰ��ս��
		redGroup = new ButtonGroup();
		redGroupContainer = new Container();
		redHeros = new LinkedList<JRadioButtonMenuItem>();
		
		blueGroup = new ButtonGroup();
		blueGroupContainer = new Container();
		blueHeros = new LinkedList<JRadioButtonMenuItem>();		
		
		//ս������
		battle = new Round(this);
		battleLabel = new JLabel(battle.getMap());
		
		blueLabel = new JLabel("��ɫ����");
		blueGroupContainer.add(blueLabel);
		redLabel = new JLabel("��ɫ����");
		redGroupContainer.add(redLabel);
		//�����׶Ӱ�ť
		int halfLength = 5;
		for(i=0 ; i<halfLength ; i++) {
			tempButton = new JRadioButtonMenuItem(""+(char)('A'+i));
			blueHeros.add(tempButton);	
			blueGroup.add(tempButton);	//�ﵽ��ѡĿ��
			blueGroupContainer.add(tempButton);	//��Ӣ�ۼӵ�����
			
			tempButton.setActionCommand(""+(char)('A'+i));	//������ť�ļ�����׼
			tempButton.addActionListener(this);			//������ť����
		}
		
		
		
		//�����ҶӰ�ť
		halfLength = 5;
		for(i=0 ; i<halfLength ; i++) {
			tempButton = new JRadioButtonMenuItem(""+(char)('F'+i));
			redHeros.add(tempButton);
			redGroup.add(tempButton);		
			redGroupContainer.add(tempButton);
			tempButton.setActionCommand(""+(char)('F'+i));
			tempButton.addActionListener(this);
		}
	
		
		//��ʼ��
		operatingHero = blueHeros.get(0).getActionCommand();
		blueHeros.get(0).setSelected(true);
		redHeros.get(0).setSelected(true);
		blueGroupContainer.setLayout(new FlowLayout());
		redGroupContainer.setLayout(new FlowLayout());
		
		//��ť
		aButton = new JButton("������");
		aButton.addActionListener(this);
		aButton.setFocusable(false);
		
		
		
		/*decideButton = new JButton("״̬");
		decideButton.addActionListener(this);	//������ť
		decideButton.setFocusable(false); 		//��ťʧ��
		*/
		
		
		
		
		
		
		this.add(blueGroupContainer);
		this.add(redGroupContainer);
		this.add(aButton);
		
		battleLabel.setFont(new Font(Font.DIALOG,Font.BOLD,18));
		this.add(battleLabel);
		
		
		
	}
	
	
	//����ʵ�֣�
	//������ǰ�����Ľ�ɫ
	public void actionPerformed(ActionEvent e) {
		int i;
		
		//ѡ���˵�ǰ��������
		for(i=0 ; i<blueHeros.size() ; i++) {
			if(e.getSource() == blueHeros.get(i)) {
				operatingHero = e.getActionCommand();	//���ĵ�ǰӢ��
				
				break;
			}
		}
		
		
		for(i=0 ; i<redHeros.size() ; i++) {
			if(e.getSource() == redHeros.get(i)) {
				operatingHero = redHeros.get(i).getActionCommand();
				break;
			}
		}
		
		//���˽�����ť
		/*if(e.getSource() == changeButton) {
			try {
				exchangeGroup();
				System.out.println("a");
			} catch (InterruptedException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
		}
		*/
		//����ȷ���򵥹�����ť
		 if(e.getSource() == aButton) {
				battle.understand(operatingHero,"attack");		//��������
		}
	}
	
	//����������������
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

	//�������ӵĲ���
	//������ս��
/*	private void exchangeGroup() throws InterruptedException {
		int i;
		
		//battle�ϳ����ڲ��Ľ�����
		//battle.understandOrder("������ս��");
		
		//ͼ�ν����ϵĽ�����
		this.swap();
		
		//���ĵ�ѡ��ť
		this.add(activeGroupContainer,1);
		
		//���������Ľ���
		operatingHero = activeHeros.get(0).getActionCommand();
		beAttackedHero = passiveHeros.get(0).getActionCommand();
		attacker.setText(operatingHero+"�򵥹���");
		
		activeHeros.get(0).setSelected(true);
		passiveHeros.get(0).setSelected(true);
	}
	
	//��������
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
		
		//���ļ򵥹�������
		simpleAttackContainer.add(passiveGroupContainer, 1);
	}

	//�ж�Ӣ���Ƿ�����
	public void manageDeath(char name) {
		int i;
		
		for(i=0 ; i<passiveHeros.size() ; i++) {
			if(passiveHeros.get(i).getActionCommand().equals(""+name)) {
				passiveGroup.remove(passiveHeros.get(i));
				passiveGroupContainer.remove(passiveHeros.get(i));
				passiveHeros.remove(passiveHeros.get(i));	//�����������Ƴ�
			}
		}
		
		passiveHeros.get(0).setSelected(true);	//�ٴ�ѡ����������Ӣ��
		beAttackedHero = passiveHeros.get(0).getActionCommand();	//�����µı�������Ӣ��
	}
	*/
}


