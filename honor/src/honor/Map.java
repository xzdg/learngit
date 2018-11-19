package honor;


/**
 * 地图类
 * <p>
 * 地图含有障碍物、英雄和野怪等，以字符形式出现，游戏进程主要是以改变地图的信息再输出的形式表现，一开始有10个英雄，
 * 他们是不同的英雄类型，初始属性不一致。障碍物有边界，防止英雄走出外面（越界），有野怪，英雄击败后可以获得buff
 * @author 徐朱东光
 *@since 2018/10/28
 */
public class Map{
	public StringBuffer map[]=new StringBuffer[34];
	public Hero rng[]=new Hero[5];//红方阵营
	public Hero edg[]=new Hero[5];//蓝方阵营
	public Unit monster[]=new Unit[3];//野怪
	/**
	 * 初始化地图
	 */
	public Map()
	{
		int i,j;
		for(i=0;i<34;i++)
		{
			map[i]=new StringBuffer(100);
			for(j=0;j<100;j++)
				map[i].append(' ');
			for(j=0;j<100;j++)
				if(i==0||j==0||i==33||j==99)
					map[i].setCharAt(j, '#');
		}
		for(i=7;i<11;i++)
			map[i].setCharAt(19, '#');
		for(j=20;j<22;j++)
			map[10].setCharAt(j, '#');
		map[9].setCharAt(20, '1');
		for(i=10;i<19;i++)
			map[i].setCharAt(21, '#');
		for(j=22;j<51;j++)
			map[18].setCharAt(j, '#');
		for(i=13;i>=10;i--)
			map[i].setCharAt(47, '#');
		for(j=48;j<80;j++)
			map[10].setCharAt(j, '#');
		for(i=11;i<24;i++)
			map[i].setCharAt(79, '#');
		monster[0]=new Unit(1000+100*1,50, 2,9,20, '1', "蓝爸爸");
		
		for(i=24;i<27;i++)
			map[i].setCharAt(79, '#');
		for(j=80;j<82;j++)
			map[26].setCharAt(j, '#');
		map[25].setCharAt(80, '3');
		monster[2]=new Unit(1000+100*1, 100, 1,25,80,'3', "红爸爸");
		
		for(j=47;j<52;j++)
			map[14].setCharAt(j, '#');
		for(i=15;i<19;i++)
			map[i].setCharAt(51, '#');
		map[16].setCharAt(49, '2');
			monster[1]=new Unit(100000,  1000, 20,16, 49,'2', "大龙");
			map[31].setCharAt(1, 'a');
		rng[0]=new Hero(500, 300, 60, 30,31,1, 0, 1, 'a', "奥恩");
		
		map[30].setCharAt(2, 'b');
		rng[1]=new Hero(500, 300, 60, 30,30,2, 0, 1, 'b', "梦魇");
		
		map[31].setCharAt(2, 'c');
		rng[2]=new Hero(500, 300, 60, 30,31,2, 0, 1, 'c', "吸血鬼");
		
		map[31].setCharAt(3, 'd');
		rng[3]=new Hero(500, 300, 60, 30,31,3, 0, 1, 'd', "薇恩");
		
		map[32].setCharAt(2, 'e');
		rng[4]=new Hero(500, 300, 60, 30,32,2, 0, 1, 'e', "酒桶");
		
		map[2].setCharAt(96, 'f');
		edg[0]=new Hero(500, 300, 60, 30,2,96, 0, 1, 'f', "赛恩");
		
		map[1].setCharAt(97, 'g');
		edg[1]=new Hero(500, 300, 60, 30,1,97, 0, 1, 'g', "盲僧");
		
		map[2].setCharAt(97, 'h');
		edg[2]=new Hero(500, 300, 60, 30, 2,97,0, 1, 'h', "佐伊");
		
		map[2].setCharAt(98, 'i');
		edg[3]=new Hero(500, 300, 616, 30,2,98, 0, 1, 'i', "卡莎");
		
		map[3].setCharAt(97, 'j');
		edg[4]=new Hero(500, 300, 60, 30, 3,97,0, 1, 'j', "洛");
		
		
	}
    /**
     * 地图展示
     */
     public  void show()
     {

    	 int i,j;
    	 for(i=0;i<5;i++)
    	 {
  
    		 if(rng[i].hp<=0)
    			 map[rng[i].posx].setCharAt(rng[i].posy, ' ');
    		 rng[i].passiveSkill();
    		
    	 }
    	 for(i=0;i<5;i++)
    	 {
    		 if(edg[i].hp<=0)
    			 map[edg[i].posx].setCharAt(edg[i].posy, ' ');
    		 edg[i].passiveSkill();
    	 }
    	 for(i=0;i<3;i++)
    	 {
    		 if(monster[i].hp<=0)
    			 map[monster[i].posx].setCharAt(monster[i].posy, ' ');
       	 }
    	
    	 for(i=0;i<34;i++)
    			 System.out.println(map[i]);
 		
     }
     /**
      * 
      * @param operationObject 对象
      * @param dx x的改变量
      * @param dy y的改变量
      */
     public void move(Hero operationObject,int dx,int dy)
     {
    	 //判定移动是否合法
    	 if(map[operationObject.posx+dx].charAt(operationObject.posy+dy)==' ')
    		 {
    		
    		 map[operationObject.posx].setCharAt(operationObject.posy, ' ');
    		 map[operationObject.posx+dx].setCharAt(operationObject.posy+dy,operationObject.mark);
    		 operationObject.posx+=dx;
    		 operationObject.posy+=dy;
    		
    		 }
     }
     /**
      * 进攻
      * @param operationObject 对象
      */
     public void attack(Hero operationObject)
     {
    	 int i;
    	 for(i=0;i<5;i++)
    		 //是否在攻击范围内
    		 if(attackRange(operationObject.posx,operationObject.posy,rng[i].posx,rng[i].posy))
    			 if(rng[i].demage(operationObject.ad,operationObject.redBuff))
    			{
    				 operationObject.killRewards(rng[i]);
    				 map[rng[i].posx].setCharAt(rng[i].posy,' ');
    			}
    			 
    			
    	        
    	 for(i=0;i<5;i++)
    		 if(attackRange(operationObject.posx,operationObject.posy,edg[i].posx,edg[i].posy))
    			 if(edg[i].demage(operationObject.ad,operationObject.redBuff))
    			 {
				 operationObject.killRewards(edg[i]);
    			 map[edg[i].posx].setCharAt(edg[i].posy,' ');
	             }
    	 for(i=0;i<3;i++)
    		 if(attackRange(operationObject.posx,operationObject.posy,monster[i].posx,monster[i].posy))
    			 if(monster[i].demage(operationObject.ad))
				 {
    				 operationObject.killRewards(monster[i]);
    			 map[monster[i].posx].setCharAt(monster[i].posy,' ');
	              }
    	 
     }
     /**
      * 判定攻击范围
      * @param posx 对象x坐标
      * @param posy 对象y坐标
      * @param otherPosx 其他英雄x坐标
      * @param otherPosy 其他英雄y坐标
      * @return 是否阵亡
      */
     public boolean attackRange(int posx,int posy,int otherPosx,int otherPosy)
     {
  	   if(otherPosx-posx<=1&&otherPosx-posx>=-1&&otherPosy-posy<=1&&otherPosy-posy>=-1)
  		   if(otherPosx!=posx||otherPosy!=posy)
  	   return true;
  	  return false;
     }
     public String getMap() {
 		return becomHtml(map);
 	}
     public static String becomHtml(StringBuffer[] mapLine) {
 		int i;
 		String result = "<html>";
 		
 		for(i=0 ; i<mapLine.length ; i++) {
 			result = result + "<p>" + tanslateSpaceCode(mapLine[i]) +"</p>";
 		}
 		
 		result = result +"</html>";
 		
 		return result;
 	}
     private static String tanslateSpaceCode(StringBuffer str) {
 		int i;
 		char tempCh;
 		String result = "";
 		
 		for(i=0 ; i<str.length() ; i++) {
 			tempCh = str.charAt(i);
 			
 			if(tempCh == ' ')
 				result = result + "&ensp;";
 			
 			else {
 				if(tempCh == '#') {
 					result = result + tempCh;
 				}
 				
 				else if((tempCh>='A'&&tempCh<='E') || (tempCh>='a'&&tempCh<='e')) {
 					result = result +("<span style=\"color: blue\">"+tempCh+"</span>");
 				}
 				
 				else if((tempCh>='F'&&tempCh<='J') || (tempCh>='f'&&tempCh<='j')) {
 					result = result +("<span style=\"color: red\">"+tempCh+"</span>");
 				}
 				
 				else if(tempCh=='1' || tempCh=='2' || tempCh=='3' ) {
 					result = result +("<span style=\"color: black\">"+tempCh+"</span>");
 				}
 				else if(tempCh=='●' ) {
					result = result +("<span style=\"color: black\">"+tempCh+"</span>");
				}
 			}
 				
 		}
 		
 		return result;	
 	}
}
