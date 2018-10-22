package king;

public class Map {
	public char map[][]=new char[35][101];
	public Hero rng[]=new Hero[5];
	public Hero edg[]=new Hero[5];
	public Unit monster[]=new Unit[3];
	public Map()
	{
		int i,j;
		for(i=1;i<=34;i++)
			for(j=1;j<=100;j++)
			{
				map[i][j]=' ';
				if(i==1||j==1||i==34||j==100)
					map[i][j]='#';
			}
		for(i=31;i<=33;i++)
			map[i][5]='|';
		for(j=2;j<=5;j++)
			map[30][j]='-';
		for(i=2;i<=4;i++)
			map[i][96]='|';
		for(j=96;j<=99;j++)
			map[5][j]='-';
		
		for(i=8;i<=10;i++)
			map[i][20]='#';
		for(j=21;j<=22;j++)
			map[10][j]='#';
		map[9][21]='1';
		monster[0]=new Unit(1000+100*1,50, 2,9,21, '1', "À¶°Ö°Ö");
		
		for(i=25;i<=27;i++)
			map[i][80]='#';
		for(j=81;j<=82;j++)
			map[27][j]='#';
			map[26][81]='3';
		monster[2]=new Unit(1000+100*1, 100, 1,26,81,'3', "ºì°Ö°Ö");
		
		for(j=48;j<=52;j++)
			map[15][j]='#';
		for(i=16;i<=19;i++)
			map[i][52]='#';
		map[17][50]='2';
			monster[1]=new Unit(100000,  1000, 20,17, 50,'2', "´óÁú");
		map[32][2]='a';
		rng[0]=new Hero(500, 300, 60, 30,32,2, 0, 1, 'a', "°Â¶÷");
		
		map[31][3]='b';
		rng[1]=new Hero(500, 300, 60, 30,31,3, 0, 1, 'b', "ÃÎ÷Ê");
		
		map[32][3]='c';
		rng[2]=new Hero(500, 300, 60, 30,32,3, 0, 1, 'c', "ÎüÑª¹í");
		
		map[32][4]='d';
		rng[3]=new Hero(500, 300, 60, 30,32,4, 0, 1, 'd', "Þ±¶÷");
		
		map[33][3]='e';
		rng[4]=new Hero(500, 300, 60, 30,33,3, 0, 1, 'e', "¾ÆÍ°");
		
        map[3][97]='f';
		edg[0]=new Hero(500, 300, 60, 30,3,97, 0, 1, 'f', "Èü¶÷");
		
		map[2][98]='g';
		edg[1]=new Hero(500, 300, 60, 30,2,98, 0, 1, 'g', "Ã¤É®");
		
		map[3][98]='h';
		edg[2]=new Hero(500, 300, 60, 30, 3,98,0, 1, 'h', "×ôÒÁ");
		
		map[3][99]='i';
		edg[3]=new Hero(500, 300, 60, 30,3,99, 0, 1, 'i', "¿¨É¯");
		
		map[4][98]='j';
		edg[4]=new Hero(500, 300, 60, 30, 4,98,0, 1, 'j', "Âå");
		
		
	}
     public void show()
     {
    	 int i,j;
    	 for(i=0;i<5;i++)
    	 {
    		 if(rng[i].hp<=0)
    			 map[rng[i].posx][rng[i].posy]=' ';
    		 rng[i].passiveSkill();
    	 }
    	 for(i=0;i<5;i++)
    	 {
    		 if(edg[i].hp<=0)
    			 map[edg[i].posx][edg[i].posy]=' ';
    		 edg[i].passiveSkill();
    	 }
    	 for(i=0;i<3;i++)
    	 {
    		 if(monster[i].hp<=0)
    			 map[monster[i].posx][monster[i].posy]=' ';
       	 }
    	
    	 for(i=1;i<=34;i++)
    	 {
    		 
    		 for(j=1;j<=100;j++)
    			 System.out.print(map[i][j]);
    		 
    		 System.out.println();
    	 }
     }
     public void move(Hero operationObject,int dx,int dy)
     {
    	 
    	 if(map[operationObject.posx+dx][operationObject.posy+dy]==' '||map[operationObject.posx+dx][operationObject.posy+dy]=='-'||map[operationObject.posx+dx][operationObject.posy+dy]=='|')
    		 {
    		
    		 map[operationObject.posx][operationObject.posy]=' ';
    		 map[operationObject.posx+dx][operationObject.posy+dy]=operationObject.mark;
    		 operationObject.posx+=dx;
    		 operationObject.posy+=dy;
    		
    		 }
     }
     public void attack(Hero operationObject)
     {
    	 int i;
    	 for(i=0;i<5;i++)
    		 if(attackRange(operationObject.posx,operationObject.posy,rng[i].posx,rng[i].posy))
    			 {
    			 rng[i].demage(operationObject.ad,operationObject.redBuff);
    			 
    			 }
    	        
    	 for(i=0;i<5;i++)
    		 if(attackRange(operationObject.posx,operationObject.posy,edg[i].posx,edg[i].posy))
    		 { 
    			 edg[i].demage(operationObject.ad,operationObject.redBuff);
    	 
	         }
    	 for(i=0;i<3;i++)
    		 if(attackRange(operationObject.posx,operationObject.posy,monster[i].posx,monster[i].posy))
    			 {
    			 monster[i].demage(operationObject.ad);
    	 
	              }
    	 
     }
     public boolean attackRange(int posx,int posy,int otherPosx,int otherPosy)
     {
  	   if(otherPosx-posx<=1&&otherPosx-posx>=-1&&otherPosy-posy<=1&&otherPosy-posy>=-1)
  		   if(otherPosx!=posx||otherPosy!=posy)
  	   return true;
  	  return false;
     }
}
