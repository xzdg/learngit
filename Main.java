package test;
import java.util.*;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
/**
 * Unit���ǽ�ɫ�ࣨ��λ�ࣩ
 * ÿ����ɫӵ�й�����������ֵ��ad��health��
 * ���������������(hurt)
 * ���ĸ�������ҲӦͨ��ĳ�ַ���������֪��getAd��getHealth)
 * 
 */
class Unit{
     private int ad,health;/**adΪ��λ�Ĺ�������healthΪ��λ������ֵ
                            */
     public Unit()
     {
    	 ad=0;
    	 health=0;
     }
	public void setValue(int ad,int health) {
		this.ad=ad;
		this.health=health;
		
	}
	public int getAd() {
		return ad;
	}
	public int getHealth() {
		return health;
	}
	public void hurt(int demage)
	{
		health-=demage;
		
	}
	public void copy(Unit otherUnit)//���ڸı��ɫλ��ʱ���Եĸ���
	{
		ad=otherUnit.ad;
		health=otherUnit.health;
		
	}
}
/**
 * Player���������
 * ���һ��ʼӵ��һ��Ӣ�ۣ�unit��0����
 * ���Դ���һ��������ӣ�summon��
 * Ӣ���������Ҳ������ⳡ��Ϸ��isDead��
 * ��ӿ��Թ����Է��κ�Ŀ�꣨attack��
 * 
 */
class Player{
	public Unit unit[];/**����ຬ�����ɫ����
	*/
	
	private int number;
	private int magic;
	public Player()
	{
		unit=new Unit[8];
		int loop;
		for(loop=0;loop<8;loop++)
			unit[loop]=new Unit();
		number=1;
		unit[0].setValue(0, 30);
		magic=0;
	}
	public void restore()
	{
		magic=100;
	}
	public void magic(int level)
	{
		magic-=level*15;
	}
	public int getMagic()
	{
		return magic;
	}
	public void attack(int positionAttacker,int positionDefender,int demage)
	{
		unit[positionAttacker].hurt(demage);
		
	}
	public boolean isDead(int position)
	{
		if(unit[position].getHealth()<=0)
			return true;
		else return false;
	}
	public void remove(int position)
	{
		int loop;
		if(position!=0)
		{
		for(loop=position;loop<number-1;loop++)
			unit[loop].copy(unit[loop+1]);
		number--;
		}
		
	}
	public void summon(int position,int ad,int health)
	{
		int loop;
		for(loop=number-1;loop>=position;loop--)
		   unit[loop+1].copy(unit[loop]);
		unit[position].setValue(ad, health);
		
		number++;
	}
	public int getNumber()
	{
		return number;
	}
	public void show()
	{
		System.out.println(unit[0].getHealth());
		int numberSoldier;
		if(number==1)
			numberSoldier=0;
		else numberSoldier=number-1;
		System.out.print(numberSoldier);
		int loop;
		for(loop=1;loop<=numberSoldier;loop++)
			System.out.print(" "+unit[loop].getHealth());
		System.out.println();
		
	}
}
/**
 * 
 * @author ���춫��
 * ����
 * ������Ϸ�Ľ���
 * ��Ϸ��ʼ������ĵط�
 *
 */
public class Main {
	/*
	 * �ж���Ϸʤ��
	 */
	public static boolean isWin(Player firstPlayer,Player secondPlayer)
	{
		if(firstPlayer.unit[0].getHealth()<=0)
		  System.out.println("-1");
		else if(secondPlayer.unit[0].getHealth()<=0)
			System.out.println("1");
		else  return false;
		   
		return true;
	}
	/*
	 * ��Ϸ������
	 */
	 public static void main(String[] args) {
		 int t,position,ad,health,round=1;//tΪ��������
		 boolean gameOver=false;
		 String action;
		 Player firstPlayer,secondPlayer;
		 firstPlayer=new Player();
		 secondPlayer=new Player();
		 Scanner input=new Scanner(System.in);
		 t=input.nextInt();
		 while(t!=0)
		 {
			 action=input.next();
			 Logger logger = Logger.getLogger(Main.class);
			  
		        BasicConfigurator.configure();
		        logger.setLevel(Level.WARN);
		        logger.info("����Ĳ���Ϊ��"+action);//��ʱ�������
		        
		        
		        logger.debug("debug:");//��ʱ�������
		       
            /*
             * ��һ��Ӣ����������Ȼ�������Ĳ�����û������
             */
			 if(!gameOver)
			 {
				 logger.info("��ǰ�غϣ�"+round);
			 	if(round==1)
			 {
			 		
			 		
		
			    if(action.equals("summon"))
			    {
			    	position=input.nextInt();
			    	ad=input.nextInt();
			    	health=input.nextInt();
				 firstPlayer.summon(position, ad, health);
			    }
			    else if(action.equals("attack"))
			    {
			    	int positionAttacker,positionDefender;
			    	positionAttacker=input.nextInt();
			    	positionDefender=input.nextInt();
			    	/*
			    	 * ˫�������ܵ��˺����ҿ��ܻ�����ɫ������
			    	 */
			    	firstPlayer.attack(positionAttacker, positionDefender, secondPlayer.unit[positionDefender].getAd());
			    	logger.info("��������ɫ����ֵ��"+firstPlayer.unit[positionAttacker].getHealth());
			    	
			    	secondPlayer.attack(positionDefender, positionAttacker, firstPlayer.unit[positionAttacker].getAd());
			    	logger.info("����������ɫ����ֵ��"+secondPlayer.unit[positionDefender].getHealth());
			    	
			    	if(firstPlayer.isDead(positionAttacker))
			    		firstPlayer.remove(positionAttacker);
			    	if(secondPlayer.isDead(positionDefender))
			    		secondPlayer.remove(positionDefender);
			    	
			    }
			    else if(action.equals("end"))
			        {
			    	round=2;//��һ�غ�
			    	firstPlayer.restore();
			    	secondPlayer.restore();
			        }
			    else  logger.warn("���������Ϣ����");
			    
			 }
			 else {
				 if(action.equals("summon"))
				    {
				    	position=input.nextInt();
				    	ad=input.nextInt();
				    	health=input.nextInt();
					 secondPlayer.summon(position, ad, health);
				    }
				    if(action.equals("attack"))
				    {
				    	int positionAttacker,positionDefender;
				    	positionAttacker=input.nextInt();
				    	positionDefender=input.nextInt();
				    	/*
				    	 * ˫�������ܵ��˺����ҿ��ܻ�����ɫ������
				    	 */
				    	firstPlayer.attack(positionDefender, positionAttacker, secondPlayer.unit[positionAttacker].getAd());
				    	logger.info("����������ɫ����ֵ��"+firstPlayer.unit[positionDefender].getHealth());
				    	
				    	secondPlayer.attack( positionAttacker, positionDefender,firstPlayer.unit[positionDefender].getAd());
				    	logger.info("��������ɫ����ֵ��"+secondPlayer.unit[positionAttacker].getHealth());
				    	
				    	if(secondPlayer.isDead(positionAttacker))
				    		secondPlayer.remove(positionAttacker);
				    	if(firstPlayer.isDead(positionDefender))
				    		firstPlayer.remove(positionDefender);
				    }
				    if(action.equals("end"))
				        round=1;//��һ�غ�
				    
				 }
				 
				 if(isWin(firstPlayer,secondPlayer))
					 gameOver=true;
			 }
			 else logger.warn("�������Ĳ���û������");
				 
			 t--;
			 
		 }
		 /*
		  * ������
		  */
		 if(!gameOver)
			 System.out.println("0");
		 firstPlayer.show();
	       secondPlayer.show();
	       
	       input.close();
	    }
}

