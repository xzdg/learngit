package honor;

import java.util.*;
import picture.*;
/**
 * 回合类
 * <P>
 * 控制游戏进程，建一个地图，然后根据玩家的指令，改变地图内的信息（伴随有动作产生）.
 * @author 徐朱东光
 *@since 2018/10/28
 */
public class Round{
	public Map caylon=new Map();
	public static MyPanel panel = null;
	public int direction;
	public boolean flag;
	/**
	 * 无参构造函数
	 */
	public Round()
    {
		direction=1;
  	  flag=true;
    }
      public Round(MyPanel panel)
      {
    	  direction=1;
    	  this.panel=panel;
    	  flag=true;
      }
      /**
       * 玩家输入指令
       */
      public  void in()
      {
    	  Scanner input=new Scanner(System.in);
            while(true)
            {
    		  flag=true;
    	  String operationObject=input.next();
    	  String behavior=input.next();
    	  if(operationObject.equals("1")||operationObject.equals("2"))
    		  {
    		  caylon.monster[0].state();
    		  caylon.monster[1].state();
    		  caylon.monster[2].state();
    		  }
    	  else if(operationObject.equals("a")||operationObject.equals("A"))
    		  action(caylon.rng[0],behavior);
    	  else if(operationObject.equals("b")||operationObject.equals("B"))
    		  action(caylon.rng[1],behavior);
    	  else if(operationObject.equals("c")||operationObject.equals("C"))
    		  action(caylon.rng[2],behavior);
    	  else if(operationObject.equals("d")||operationObject.equals("D"))
    		  action(caylon.rng[3],behavior);
    	  else if(operationObject.equals("e")||operationObject.equals("E"))
    		  action(caylon.rng[4],behavior);
    	  else if(operationObject.equals("f")||operationObject.equals("F"))
    		  action(caylon.edg[0],behavior);
    	  else if(operationObject.equals("g")||operationObject.equals("G"))
    		  action(caylon.edg[1],behavior);
    	  else if(operationObject.equals("h")||operationObject.equals("H"))
    		  action(caylon.edg[2],behavior);
    	  else if(operationObject.equals("i")||operationObject.equals("I"))
    		  action(caylon.edg[3],behavior);
    	  else if(operationObject.equals("j")||operationObject.equals("J"))
    		  action(caylon.edg[4],behavior);
    	  if(flag)//如果上一条指令不是静态指令，则输出，并表示过了一单位时间
    	  show();
    	  }
    		  
    	}
      public void understand(String operationObject,String behavior)
      {
    	  if(operationObject.equals("1")||operationObject.equals("2"))
		  {
		  caylon.monster[0].state();
		  caylon.monster[1].state();
		  caylon.monster[2].state();
		  }
	  else if((operationObject.equals("a")||operationObject.equals("A"))&&caylon.rng[0].hp>0)
		  action(caylon.rng[0],behavior);
	  else if((operationObject.equals("b")||operationObject.equals("B"))&&caylon.rng[1].hp>0)
		  action(caylon.rng[1],behavior);
	  else if((operationObject.equals("c")||operationObject.equals("C"))&&caylon.rng[2].hp>0)
		  action(caylon.rng[2],behavior);
	  else if((operationObject.equals("d")||operationObject.equals("D"))&&caylon.rng[3].hp>0)
		  action(caylon.rng[3],behavior);
	  else if((operationObject.equals("e")||operationObject.equals("E"))&&caylon.rng[4].hp>0)
		  action(caylon.rng[4],behavior);
	  else if((operationObject.equals("f")||operationObject.equals("F"))&&caylon.edg[0].hp>0)
		  action(caylon.edg[0],behavior);
	  else if((operationObject.equals("g")||operationObject.equals("G"))&&caylon.edg[1].hp>0)
		  action(caylon.edg[1],behavior);
	  else if((operationObject.equals("h")||operationObject.equals("H"))&&caylon.edg[2].hp>0)
		  action(caylon.edg[2],behavior);
	  else if((operationObject.equals("i")||operationObject.equals("I"))&&caylon.edg[3].hp>0)
		  action(caylon.edg[3],behavior);
	  else if((operationObject.equals("j")||operationObject.equals("J"))&&caylon.edg[4].hp>0)
		  action(caylon.edg[4],behavior);
    	  restore();
    	  panel.battleLabel.setText(panel.battle.getMap());
      }
      public void restore()
      {
    	  int i;
     	 for(i=0;i<5;i++)
     	 {
     		 caylon.rng[i].passiveSkill();
     	 }
     	 for(i=0;i<5;i++)
     	 {
     		 caylon.edg[i].passiveSkill();
     	 }
      }
      /**
       * 行为
       * <p>解读指令，来产生动作
       * @param hero 操作对象
       * @param behavior 行为指令
       */
      public void action(Hero hero,String behavior)
      {
    	 
    	  if(behavior.equals("moveup"))
    		  caylon.move(hero,-1,0);
    	  else if(behavior.equals("movedown"))
    		  caylon.move(hero,1,0);
    	  else if(behavior.equals("moveleft"))
    		  caylon.move(hero,0,-1);
    	  else if(behavior.equals("moveright"))
    		  caylon.move(hero,0,1);
    	  else if(behavior.equals("attack"))
    		  caylon.attack(hero);
    	  else if(behavior.equals("state"))
    	  {
    		  hero.state();
    		  flag=false;
    	  }
    	  else if(behavior.equals("q"))
    	  {
    		  if(direction==1)
    		  skillQ(hero,-1,0);
    		  else if(direction==2)
    			  skillQ(hero,1,0);
    		  else if(direction==3)
    			  skillQ(hero,0,-1);
    		  else if(direction==4)
    			  skillQ(hero,0,1);
    	  }
    	  else if(behavior.equals("w"))
    	  {
    		  skillW(hero);
    	  }
    	  else if(behavior.equals("e"))
    	  {
    		  if(direction==1)
    		  skillE(hero,-1,0);
    		  else if(direction==2)
    			  skillE(hero,1,0);
    		  else if(direction==3)
    			  skillE(hero,0,-1);
    		  else if(direction==4)
    			  skillE(hero,0,1);
    	  }
    	  else if(behavior.equals("r"))
    	  {
    		  skillR(hero);
    	  }
    	  else if(behavior.equals("up"))
    		  direction=1;
    	  else if(behavior.equals("down"))
    		  direction=2;
    	  else if(behavior.equals("left"))
    		  direction=3;
    	  else if(behavior.equals("right"))
    		  direction=4;
    	  
      }
      public void show()
      {
    	 
    	  caylon.show();
      }
      public void skillQ(Hero hero,int dx,int dy)
      {
    	  if(hero.mp>=100)
    	  {
    		  new Thread(new Runnable() {
    	      		public void run() {
    	  int posx=hero.posx,posy=hero.posy;
    	  int i;
    	  int number=0;
    	  while(true)
    	  {
    		  if(number!=0)
    			  caylon.map[posx].setCharAt(posy, ' ');
    		  number++;
    		  if( caylon.map[posx+dx].charAt(posy+dy)==' ')
    			  {
    			  posx+=dx;
    			  posy+=dy;
    			  caylon.map[posx].setCharAt(posy, '●');
    			  }
    		  else {
    			  if(caylon.map[posx+dx].charAt(posy+dy)=='#'&&posx+dx!=0&&posy+dy!=0&&posx+dx!=33&&posy+dy!=99)
    				  caylon.map[posx+dx].setCharAt(posy+dy, ' ');
    			      for(i=0;i<5;i++)
    			    	  if(caylon.rng[i].posx==posx+dx&&caylon.rng[i].posy==posy+dy)
    			    		  if(hero.skillQ(caylon.rng[i]))
    			    			  caylon.map[caylon.rng[i].posx].setCharAt(caylon.rng[i].posy, ' ');
    			      for(i=0;i<5;i++)
    			    	  if(caylon.edg[i].posx==posx+dx&&caylon.edg[i].posy==posy+dy)
    			    		  if(hero.skillQ(caylon.edg[i]))
    			    			  caylon.map[caylon.edg[i].posx].setCharAt(caylon.edg[i].posy, ' ');
    			      for(i=0;i<3;i++)
    			    	  if(caylon.monster[i].posx==posx+dx&&caylon.monster[i].posy==posy+dy)
    			    		  if(hero.skillQ(caylon.monster[i]))
    			    			  caylon.map[caylon.monster[i].posx].setCharAt(caylon.monster[i].posy, ' ');
    			      panel.battleLabel.setText(panel.battle.getMap());
    					panel.updateUI();
    			      break;
    		       }
    		  panel.battleLabel.setText(panel.battle.getMap());
				panel.updateUI();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
    	  }
    		
    	      	    }
    	      	}).start();
    	  hero.mp-=100;
    	  }
      }
      public String getMap()
      {
    	  return caylon.becomHtml(caylon.map);
      }
      public void skillW(Hero hero)
      {
    	  if(hero.mp>=100)
    	  {
    		  hero.skillW();
    		  hero.mp-=100;
    	  }
      }
      public void skillE(Hero hero,int dx,int dy)
      {
    	  int number=5;
    	  if(hero.mp>=100)
    	  {
    		  while(number--!=0)
    		  if(caylon.map[hero.posx+dx].charAt(hero.posy+dy)==' ')
    			  {
    			  caylon.map[hero.posx].setCharAt(hero.posy, ' ');
    			  hero.skillE(dx,dy);
    			  caylon.map[hero.posx].setCharAt(hero.posy, hero.mark);
    			  }
    		  else break;
    		  hero.mp-=100;
    	  }
      }
      public void skillR(Hero hero)
      {
    	  if(hero.mp>500)
    	  {
    		  hero.skillR();
    		  caylon.map[hero.posx].setCharAt(hero.posy, hero.mark);
    		  hero.mp-=500;
    	  }
      }
}
