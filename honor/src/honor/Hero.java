package honor;
/**
 * 英雄类
 * <p>
 * 英雄有单位拥有的一切属性和方法，还新增法力值mp，经验exp和等级level，有五个技能和buff效果
 * ，可以进攻也可以受伤，经验值满自动升级，可以在地图类改变自身属性
 * @author 徐朱东光
 *@since 2018/10/28
 */
public class Hero extends Unit{
	public int mp,exp,level;
	public boolean redBuff,blueBuff,burn,bound;
	
	/**
	 * 无参构造函数
	 */
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
	/**
	 * 含参构造函数
	 * @param hp 生命值
	 * @param mp 法力值
	 * @param ad 攻击力
	 * @param def 防御力
	 * @param posx x坐标
	 * @param posy y坐标
	 * @param exp 经验值
	 * @param level 等级
	 * @param mark 人物字符
	 * @param name 名字
	 */
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
	/**
	 * buff效果
	 */
	public void buff()
	{
		if(blueBuff)
			mp+=level*50;
		if(burn)
			hp-=level*10;
	}
	/**
	 * 被动技能
	 */
	public void passiveSkill()
	{
		exp++;
		hp+=level*2;
		mp+=level*10;
		buff();
		levelup();
	}
	/**
	 * q技能
	 * @param other 作用于某个英雄
	 */
	public boolean skillQ(Unit other)
	{
		if(other.demage(level*100))
		{
			killRewards(other);
			return true;
		}
		return false;
	}
	/**
	 * w技能
	 * 
	 */
	public void skillW()
	{
		hp+=level*10;
	}
	/**
	 * e技能
	 * @param dx x的改变量
	 * @param dy y的改变量
	 */
    public void skillE(int dx,int dy)
    {
    	posx+=dx;
    	posy+=dy;
    }
    /**
     * r技能
     */
    public void skillR()
    {
    	if(mark>='a')
    	mark+='A'-'a';
    	exp+=100000;
    }
    /**
     * 升级
     */
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
    /**
     * @param hurt 伤害
     * @return true 英雄死亡
     */
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
    /**
     * 英雄当前状态
     */
    public void state()
    {
    	
    	System.out.println(name+" :");
    	System.out.println("生命值 "+hp);
    	System.out.println("魔法值 "+mp);
    	System.out.println("攻击力 "+ad);
    	System.out.println("护甲 "+def);
        System.out.println("等级 "+level);
    }
    /**
     * 击杀奖励
     * @param hero 击败的英雄
     */
    public void killRewards(Hero hero)
    {
 	   exp+=hero.level;
    }
    /**
     * 击杀奖励
     * @param monster 击败的野怪
     */
    public void killRewards(Unit monster)
    {
 	  if(monster.mark=='1')
 		  redBuff=true;
 	 if(monster.mark=='3')
		  blueBuff=true;
 	if(monster.mark=='2')
 	{
 		ad+=200;
 		def+=100;
 		hp+=1000;
 		mp+=10000;
 	}
 	  
    }
    /**
     * 受伤
     * @param hurt 伤害
     * @param debuff 减益效果
     * @return true 英雄阵亡
     */
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

