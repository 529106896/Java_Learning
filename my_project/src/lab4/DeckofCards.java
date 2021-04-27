package lab4;

import java.security.SecureRandom;

public class DeckofCards
{
	//一共可能会出现六种结果 : 啥都没有 + 题目中给的五种结果
	public enum cardsCombination
	{
		HighCard, Pair, TwoPair, ThreeOfKind, 
		FourOfKind, Flush, Straight, FullHouse;
	}
	
	//花色(Suit)中英参考：
	//黑桃♠ —— Spades
	//红心♥	—— Hearts
	//钻石♦	—— Diamonds	
	//黑梅♣	—— Clubs

	//牌面(Face)中英参考：
	//A：Ace		2：Deuce		3：Three		4：Four
	//5：Five	6：Six		7：Seven		8：Eight
	//9：Nine	10：Ten		J：Jack		Q：Queen
	//K：King
	
	String[] faces = {"Ace", "Deuce", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack",
			"Queen", "King"};

	String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
	
	private Card[] deck;		//用数组来表示一副牌
	private int currentCard;	//用来表示下一张要处理哪张牌
	private static final int cardsNumber = 52;	//一共52张牌
	private static final SecureRandom rand = new SecureRandom();
	
	//构造函数
	public DeckofCards()
	{
		
		deck = new Card[cardsNumber];
		currentCard = 0;
		
		for(int count = 0; count < deck.length; count++)
		{
			deck[count] = new Card(faces[count % 13], suits[count / 13]);
			//在52张牌里，同样花色的有13张，同样牌面的有4张
		}
	}
	
	//洗牌函数，用数组改写一下原来的list
	public void shuffle()
	{
		currentCard = 0;
		
		for(int i = 0; i < deck.length; i++)
		{
			//在0-51的元素里随便挑一张牌，随机交换，模拟洗牌过程
			int j = rand.nextInt(cardsNumber);
			Card temp = deck[i];
			deck[i] = deck[j];
			deck[j] = temp;
		}
	}
	
	public Card dealCard()
	{
		if(currentCard < deck.length)
			return deck[currentCard++];
		else
			return null;
	}
	
	//用来检查手牌是不是好牌
	public cardsCombination check(Card[] hand)
	{
		//检查是不是同花
		boolean suitCheck = true;
		for(int i = 0; i < 4; i++)
		{
			suitCheck = true;
			for(int j = 0; j < 5; j++)
			{
				if(hand[j].getSuit() != suits[i])//把五张手牌挨个拿出来看是不是同一花色
					suitCheck = false;
			}
			if(suitCheck == true)
				return cardsCombination.Flush;
		}
		
		//检查是不是四条
		boolean ifFour = false;
		for(int i = 0; i < 5; i++)
		{
			for(int j = i + 1; j < 5; j++)
			{
				if(hand[i].getFace() == hand[j].getFace())
				{
					for(int k = 0; k < 5; k++)
					{
						if(k != i && k != j && hand[k].getFace() == hand[i].getFace())
						{
							for(int l = 0; l < 5; l++)
							{
								if(l != i && l != j && l != k && hand[l].getFace() == hand[i].getFace())
									ifFour = true;
							}
						}
					}
				}
			}
			if(ifFour == true)
				return cardsCombination.FourOfKind;
		}
		
		//检查是不是顺子
		//先把手牌排个序
		int swap = 0;
		Card[] sortedHand = new Card[5];
		for(int i=0; i<faces.length; i++)
		{
			for(int j=0; j<5; j++)
			{
				if(hand[j].getFace() == faces[i])
				{
					sortedHand[swap++] = hand[j];
				}
			}
		}
		
		//然后检查是不是顺子
		for(int i=0; i<5; i++)
		{
			for(int j=0; j<9; j++)	//j<9是控制比较的范围不超过faces[12]，即King
			{
				if(sortedHand[i].getFace() == faces[j])
				{
					int count = 0;
					for(int k=1; k<5; k++)
					{
						if(sortedHand[k].getFace() == faces[j+k])
						{
							count++;
						}
					}
					if(count == 4 || ((sortedHand[0].getFace() == "Ten") && (sortedHand[4].getFace() == "Ace")))
					{
						return cardsCombination.Straight;
					}
				}
			}
		}
		
		//检查是不是葫芦
		boolean isFull = false;
		for(int i = 0; i < 5; i++)
		{
			for(int j = i + 1; j < 5; j++)
			{
				if(hand[i].getFace() == hand[j].getFace())
				{
					for(int k = 0; k < 5; k++)
					{
						if (k != i && k != j && hand[k].getFace() == hand[i].getFace())
						{
							for (int l = 0; l < 5; l++)
							{
								for(int m = l + 1; m < 5; m++)
								{
									if(hand[l].getFace() == hand[m].getFace() 
											&& (l != i && l != j && l != k)
											&& (m != i && m != j && m != k ))
										isFull = true;
										
								}
							}
						}
								
					}
				}
			}
			if(isFull == true)
				return cardsCombination.FullHouse;
		}
		
		//检查是不是三条(改动一下上面的四条即可)
		boolean isThree = false;
		for(int i = 0; i < 5; i++)
		{
			for(int j = i + 1; j < 5; j++)
			{
				if(hand[i].getFace() == hand[j].getFace())
				{
					for(int k = 0; k < 5; k++)
					{
						if(k != i && k != j && hand[k].getFace() == hand[i].getFace())
						{
							isThree = true;
						}
					}
				}
			}
			if(isThree == true)
				return cardsCombination.ThreeOfKind;
		}
		
		//检查是不是两对
		boolean isTwoPair = false;
		for(int i=0; i<5; i++)
		{
			for(int j=i+1; j<5; j++)
			{
				if(hand[i].getFace() == hand[j].getFace())
				{
					for(int k=0; k<5; k++)
					{
						for(int l=k+1; l<5; l++)
						{
							if(hand[k].getFace() == hand[l].getFace() && (k!=i && k!=j) && (l!=i && l!=j))
								isTwoPair = true;
						}
					}
				}
			}
			if(isTwoPair == true)
				return cardsCombination.TwoPair;
		}
		
		//检查是不是一对
		boolean isPair = false;
		for (int i=0; i<5; i++) {
			for (int j= i+1; j<5; j++) {
				if (hand[i].getFace() == hand[j].getFace())
					isPair = true;
			}
			if (isPair == true)
				return cardsCombination.Pair;
		}
		
		//上面的都没有，就返回HighCard
		return cardsCombination.HighCard;
		
	}//end of check cardsCombination
	
}//end of class
