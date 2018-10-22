package king;

import java.util.*;

public class Round {
	Map caylon=new Map();
	boolean flag;
      public Round()
      {
    	  flag=true;
      }
      public void in()
      {
    	  Scanner input=new Scanner(System.in);
    	  
    	  while(true)
    	  {
    		  flag=true;
    	  String operationObject=input.next();
    	  String behavior=input.next();
    	  if(operationObject.equals("a")||operationObject.equals("A"))
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
    	  if(flag)
    	  show();
    	  }
    	  
      }
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
    	  else if(behavior.equals("qup"))
    	  {
    		  skillQ(hero,-1,0);
    	  }
    	  else if(behavior.equals("qdown"))
    	  {
    		  skillQ(hero,1,0);
    	  }
    	  else if(behavior.equals("qleft"))
    	  {
    		  skillQ(hero,0,-1);
    	  }
    	  else if(behavior.equals("qright"))
    	  {
    		  skillQ(hero,0,1);
    	  }
    	  else if(behavior.equals("w"))
    	  {
    		  skillW(hero);
    	  }
    	  else if(behavior.equals("eup"))
    	  {
    		  skillE(hero,-1,0);
    	  }
    	  else if(behavior.equals("edown"))
    	  {
    		  skillE(hero,1,0);
    	  }
    	  else if(behavior.equals("eleft"))
    	  {
    		  skillE(hero,0,-1);
    	  }
    	  else if(behavior.equals("eright"))
    	  {
    		  skillE(hero,0,1);
    	  }
    	  else if(behavior.equals("r"))
    	  {
    		  skillR(hero);
    	  }
      }
      public void show()
      {
    	  try {
			  Thread.sleep(100);
		  }
		  catch(Exception e)
		  {
			  
		  }
    	  caylon.show();
      }
      public void skillQ(Hero hero,int dx,int dy)
      {
    	  if(hero.mp>=100)
    	  {
    	  int posx=hero.posx,posy=hero.posy;
    	  int i;
    	  int number=0;
    	  while(true)
    	  {
    		  if(number!=0)
    			  caylon.map[posx][posy]=' ';
    		  number++;
    		  if( caylon.map[posx+dx][posy+dy]==' ')
    			  {
    			  posx+=dx;
    			  posy+=dy;
    			  caylon.map[posx][posy]='*';
    			  }
    		  else {
    			      for(i=0;i<5;i++)
    			    	  if(caylon.rng[i].posx==posx+dx&&caylon.rng[i].posy==posy+dy)
    			    		  hero.skillQ(caylon.rng[i]);
    			      for(i=0;i<5;i++)
    			    	  if(caylon.edg[i].posx==posx+dx&&caylon.edg[i].posy==posy+dy)
    			    		  hero.skillQ(caylon.edg[i]);
    			      for(i=0;i<3;i++)
    			    	  if(caylon.monster[i].posx==posx+dx&&caylon.monster[i].posy==posy+dy)
    			    		  hero.skillQ(caylon.monster[i]);
    			      break;
    		       }
    		 
    		  show();
    	  }
    	  hero.mp-=100;
    	  }
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
    		  if(caylon.map[hero.posx+dx][hero.posy+dy]==' ')
    			  {
    			  caylon.map[hero.posx][hero.posy]=' ';
    			  hero.skillE(dx,dy);
    			  caylon.map[hero.posx][hero.posy]=hero.mark;
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
    		  caylon.map[hero.posx][hero.posy]=hero.mark;
    		  hero.mp-=500;
    	  }
      }
}
