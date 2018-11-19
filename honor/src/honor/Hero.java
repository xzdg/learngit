package honor;
/**
 * Ӣ����
 * <p>
 * Ӣ���е�λӵ�е�һ�����Ժͷ���������������ֵmp������exp�͵ȼ�level����������ܺ�buffЧ��
 * �����Խ���Ҳ�������ˣ�����ֵ���Զ������������ڵ�ͼ��ı���������
 * @author ���춫��
 *@since 2018/10/28
 */
public class Hero extends Unit{
	public int mp,exp,level;
	public boolean redBuff,blueBuff,burn,bound;
	
	/**
	 * �޲ι��캯��
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
	 * ���ι��캯��
	 * @param hp ����ֵ
	 * @param mp ����ֵ
	 * @param ad ������
	 * @param def ������
	 * @param posx x����
	 * @param posy y����
	 * @param exp ����ֵ
	 * @param level �ȼ�
	 * @param mark �����ַ�
	 * @param name ����
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
	 * buffЧ��
	 */
	public void buff()
	{
		if(blueBuff)
			mp+=level*50;
		if(burn)
			hp-=level*10;
	}
	/**
	 * ��������
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
	 * q����
	 * @param other ������ĳ��Ӣ��
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
	 * w����
	 * 
	 */
	public void skillW()
	{
		hp+=level*10;
	}
	/**
	 * e����
	 * @param dx x�ĸı���
	 * @param dy y�ĸı���
	 */
    public void skillE(int dx,int dy)
    {
    	posx+=dx;
    	posy+=dy;
    }
    /**
     * r����
     */
    public void skillR()
    {
    	if(mark>='a')
    	mark+='A'-'a';
    	exp+=100000;
    }
    /**
     * ����
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
     * @param hurt �˺�
     * @return true Ӣ������
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
     * Ӣ�۵�ǰ״̬
     */
    public void state()
    {
    	
    	System.out.println(name+" :");
    	System.out.println("����ֵ "+hp);
    	System.out.println("ħ��ֵ "+mp);
    	System.out.println("������ "+ad);
    	System.out.println("���� "+def);
        System.out.println("�ȼ� "+level);
    }
    /**
     * ��ɱ����
     * @param hero ���ܵ�Ӣ��
     */
    public void killRewards(Hero hero)
    {
 	   exp+=hero.level;
    }
    /**
     * ��ɱ����
     * @param monster ���ܵ�Ұ��
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
     * ����
     * @param hurt �˺�
     * @param debuff ����Ч��
     * @return true Ӣ������
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

