package king;

public class Unit {
       public int hp,ad,def,posx,posy;
       public char mark;
       String name;
       public Unit()
       {
    	   hp=0;
    	   ad=0;
    	   def=0;
    	   posx=0;
    	   posy=0;
    	   name="";
    	   mark=' ';
       }
       public Unit(int hp,int ad,int def,int posx,int posy,char mark,String name)
       {
    	   this.hp=hp;
    	   this.ad=ad;
    	   this.def=def;
    	   this.posx=posx;
    	   this.posy=posy;
    	   this.name=name;
    	   this.mark=mark;
       }
       public void setValue(int hp,int ad,int def,int posx,int posy,char mark,String name)
       {
    	   this.hp=hp;
    	   this.ad=ad;
    	   this.def=def;
    	   this.posx=posx;
    	   this.posy=posy;
    	   this.name=name;
    	   this.mark=mark;
       }
       public void change(char mark)
       {
    	   this.mark=mark;
       }
       
       public boolean demage(int hurt)
       {
    	   if(hurt>def)
    	   {
    		   hp=hp+def-hurt;
    		   
    	   }
    	   if(hp<=0)
    		{
    		   hp=-9999;
    		   
    		   return true;
    		}
           
    	   return false;
       }
       
       public void state()
       {
       	
       	System.out.println(name+" :");
       	System.out.println("生命值 "+hp);
       	//System.out.println("魔法值 "+mp);
       	System.out.println("攻击力 "+ad);
       	System.out.println("护甲 "+def);
           //System.out.println("等级 "+level);
       }
}
