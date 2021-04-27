package my_project;

import java.util.*;
import java.lang.Math;

public class bing_dubo
{
	private static boolean test = false;			//测试接口
	private static int[] PrizeCount = {0,1,2,4,8,16,32};
	private static int[] timetag;
	private static int[][] Champion,Prizeid;
 	private static Scanner scan = new Scanner(System.in);
	private static int Player_Num;
	private static String[] Zhuangyuan = {"","状元插金花","六博红","六博黑","五王","五子登科","状元"};
	private static String[] Other_Prize = {"无","状元","对堂","三红","四进","二举","一秀"};
	
	private static int Max(int[] ans)
	{
		int ret = 0;
		for(int i = 0; i < 6; i++)
			ret = Math.max(ret, ans[i]);
		return ret;
	}
	
	private static int[] RandomDice()
	{
		var ans = new int[7];
		Random rnd = new Random();//随机种子是time
		for(int i=0; i<6; i++)
		{
			ans[rnd.nextInt(6)+1]++;  //rnd.nextInt(6) 产生6以内随机数
		}
		ans[0] = Max(ans);
		return ans;
	}
	
	private static boolean IsChampion(int[] dice)
	{
		return dice[4]>=4 || dice[0] >=5;
	}
	
	private static int Set_Player_Num()
	{
		int num = scan.nextInt();
		while(true)
		{
			if(num > 10 || num < 6)
			{
				System.out.println("设置不正确！请重新输入一个6-10的数字:");
			}
			else
			{
				return num;
			}
			num = scan.nextInt();
		}
	}
	
	private static boolean GameOver()
	{
		int sum = 0;
		for(int i : PrizeCount)
			sum += i;
		if(sum == 0)
			return true;
		else
			return false;
	}
	
	private static int[] GetRnd()
	{
		int[] rndAns;
		if(test == false)
		{
			rndAns = RandomDice();
			for(int i=1; i<=6; i++)
			{
				for(int j=1; j<=rndAns[i]; j++)
					System.out.printf("%d ",i);
			}
			System.out.print('\n');
		}
		else
		{
			rndAns = new int[7];
			for(int i=0; i<6; i++)
			{
				int temp = scan.nextInt();
				if(temp<1||temp>6)
				{
					System.out.println("游戏结束");
					return null;
				}
				else
					rndAns[temp]++;
			}
			rndAns[0]=Max(rndAns);
		}
		return rndAns;
	}
	
	private static int[] CalPrize(int[] rnd)
    {
        if (IsChampion(rnd))
        {
            if (rnd[4]==1)//五王带一秀
            {
                int[] ret={1,6};
                return ret;
            }
            else 
            {
                int [] ret={1};
                return ret;
            }
        }
        else
        {
            if (rnd[0]==1)//对堂
            {
                int [] ret={2};
                return ret;
            }
            else if (rnd[0]==4)//四进
            {
                if (rnd[4]>0)
                {
                    int [] ret={4,7-rnd[4]};
                    return ret;
                }
                else
                {
                    int [] ret={4};
                    return ret;
                }
            }
            else if (rnd[4]>0) //三红二举一秀
            {
                int [] ret={7-rnd[4]};
                if (rnd[4]==3) --ret[0];
                return ret;
            }
            else //没有奖
            {
                int [] ret={0};
                return ret;
            }
        }
    }
	
    private static int[] ChampionType(int[] rnd)
    {
        int[] ret=new int[3];
        if (rnd[1]==2)//状元插金花
            ret[0]=1;
        else if (rnd[4]==6)//六博红
            ret[0]=2;
        else if (rnd[0]==6)//六博黑
        {
            ret[0]=3;
            for (int i=1;i<=6;++i)
            {
                if (rnd[i]==6)
                {
                    ret[1]=i;
                    break;
                }
            }
        }
        else if (rnd[4]==5)//五王
        {
            ret[0]=4;
            for (int i=1;i<=6;++i)
            {
                if (rnd[i]!=0&&i!=4)
                {
                    ret[1]=i;
                    break;
                }
            }
        }
        else if (rnd[0]==5) //五子登科
        {
            ret[0]=5;
            for (int i=1;i<=6;++i)
            {
                if (rnd[i]==5)
                {
                    ret[1]=i;
                    break;
                }
            }
            for (int i=1;i<=6;++i)
            {
                if (rnd[i]!=0&&rnd[i]!=5)
                {
                    ret[2]=i;
                    break;
                }
            }
        }
        else
        {
            ret[0]=6;
            for (int i=1;i<=6;++i)
            {
                if (rnd[i]==4) continue;
                ret[1]+=i*rnd[i];
            }
        }
        return ret;
    }
    
    private static int GetBigChampion()
    {
        int ret=0,id=-1;
        int[] bigChampion={7,0,0};
        for (int[] i : Champion) 
        {
            ++id;
            if (i==null) continue;
            int rank[]=ChampionType(i);
            if (rank[0]>bigChampion[0]) continue;
            else if (rank[0]==bigChampion[0])
            {
                if (rank[1]<bigChampion[1]) continue;
                else if (rank[1]==bigChampion[1])
                {
                    if (rank[2]<bigChampion[2]) continue;
                    else if (rank[2]==bigChampion[2])
                    {
                        if (timetag[id]>timetag[ret]) continue;
                    }
                }
            }
            ret=id;
            bigChampion=rank;
        }
        return ret;
    }
	
	public static void main(String[] args)
	{
		System.out.println("是否需要手动测试？1:是  0:否");
		int select = scan.nextInt();
		
		if(select == 1)
		{
			test = true;
		}
		
		System.out.println("请输入玩家的数量(6-10)：");
		Player_Num = Set_Player_Num();
		
		Champion = new int[Player_Num+1][];
        Prizeid = new int[Player_Num+1][7];
        timetag = new int[Player_Num+1];

		int round = 1;
		int id = 0;
		
		while(!GameOver())
		{
			id++;
			if(id == Player_Num + 1)
			{
				id = 1;
				round++;
			}
			System.out.printf("第%d轮，第%d位玩家的点数是：",round,id);
			int[] rndAns = GetRnd();
			if(rndAns == null)
				break;
			
			int[] prizeids = CalPrize(rndAns);
			System.out.print("奖项：");
			for(int prizeid:prizeids)
			{
				if(prizeid == 1)
				{
					var temp = ChampionType(rndAns);
					System.out.printf("%s ",Zhuangyuan[temp[0]]);
					Champion[id]=rndAns;
					PrizeCount[1]=0;
                    timetag[id]=round*(Player_Num+1)+id;
                }
                else 
                {
                    System.out.format("%s ",Other_Prize[prizeid]);
                    if (prizeid>0)
                    {
                        if (PrizeCount[prizeid]>0)
                        {
                            --PrizeCount[prizeid];
                            ++Prizeid[id][prizeid];
                        }
                        else
                            System.out.format("（%s已领空） ",Other_Prize[prizeid]);
                    }
                }
            }
            System.out.print('\n');
        }
        int finalChampion=GetBigChampion();
        if (finalChampion>=1&&finalChampion<=6)
        {
            System.out.format("最终的状元是：Player %d，在第%d轮时投出了%s,点数为："
                            ,finalChampion,timetag[finalChampion]/(Player_Num+1),
                            Zhuangyuan[ChampionType(Champion[finalChampion])[0]]);
            for (int i=1;i<=6;++i)
                for (int j=1;j<=Champion[finalChampion][i];++j)
                    System.out.format("%d ",i);
            System.out.print('\n');
        }
        else System.out.println("当前还没有产生状元");
        ++Prizeid[finalChampion][1];
        for (int i=1;i<=Player_Num;++i)
        {
            System.out.format("Player %d获得:",i);
            for (int j=1;j<=6;++j) 
            {
                if (Prizeid[i][j]>0)
                {
                    System.out.format("%d个%s ",Prizeid[i][j],Other_Prize[j]);
                }
            }
            System.out.print('\n');
        }
		
		scan.close();
	}
}
