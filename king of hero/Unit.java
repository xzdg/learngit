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
       	System.out.println("����ֵ "+hp);
       	//System.out.println("ħ��ֵ "+mp);
       	System.out.println("������ "+ad);
       	System.out.println("���� "+def);
           //System.out.println("�ȼ� "+level);
       }
}
