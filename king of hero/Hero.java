package king;

public class Hero extends Unit{
	int mp,exp,level;
	boolean redBuff,blueBuff,burn,bound;
	public Hero()
	{
		super();
		mp=0;
		exp=0;
		level=1;
		redBuff=false;
		blueBuff=false;
		burn=false;
		bound=false;
	}
	public Hero(int hp,int mp,int ad,int def,int posx,int posy,int exp,int level,char mark,String name)
	{
		this.hp=hp;
 	   this.ad=ad;
 	   this.def=def;
 	   this.posx=posx;
 	   this.posy=posy;
 	   this.name=name;
		this.mp=mp;
		this.exp=exp;
		this.mark=mark;
		this.level=level;
		redBuff=false;
		blueBuff=false;
		burn=false;
		bound=false;
	}
	public void setValue(int hp,int mp,int ad,int def,int posx,int posy,int exp,int level,char mark,String name)
	{
		this.hp=hp;
 	   this.ad=ad;
 	   this.def=def;
 	   this.posx=posx;
 	   this.posy=posy;
 	   this.name=name;
		this.mp=mp;
		this.exp=exp;
		this.mark=mark;
		this.level=level;
	}
	public void buff()
	{
		if(blueBuff)
			mp+=level*5;
		if(burn)
			hp-=level*10;
	}
	public void passiveSkill()
	{
		exp++;
		hp+=level*2;
		mp+=level;
		buff();
		levelup();
	}
	public void skillQ(Unit other)
	{
		other.demage(level*100);
	}
	public void skillW()
	{
		hp+=level*10;
	}
    public void skillE(int dx,int dy)
    {
    	posx+=dx;
    	posy+=dy;
    }
    public void skillR()
    {
    	mark+='A'-'a';
    	exp+=100000;
    }
    public void levelup()
    {
    	while(level<18&&exp>=level*3)
    	{
    		exp-=level*3;
    		level++;
    		hp+=100;
    		mp+=50;
    		ad+=10;
    		def+=5;
    	}
    	
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
 		   level=0;
 		   return true;
 		}
        
 	   return false;
    }
    public void state()
    {
    	
    	System.out.println(name+" :");
    	System.out.println("生命值 "+hp);
    	System.out.println("魔法值 "+mp);
    	System.out.println("攻击力 "+ad);
    	System.out.println("护甲 "+def);
        System.out.println("等级 "+level);
    }
    public boolean demage(int hurt,boolean debuff)
    {
 	   if(hurt>def)
 	   {
 		   hp=hp+def-hurt;
 		   if(debuff)
 			   burn=true;
 	   }
 	   if(hp<=0)
 		{
 		   hp=-9999;
 		   level=0;
 		   return true;
 		}
        
 	   return false;
    }
}
