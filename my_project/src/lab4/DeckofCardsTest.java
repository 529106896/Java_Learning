package lab4;

import lab4.DeckofCards.cardsCombination;

public class DeckofCardsTest 
{
	private static int countHighCard = 0;
	private static int countPair = 0;
	private static int countTwoPair = 0;
	private static int countThreeOfKind = 0;
	private static int countStraight = 0;
	private static int countFlush = 0;
	private static int countFullHouse = 0;
	private static int countFourOfKind = 0;
	
	public static void main(String[] args)
	{
		int times = 10000;
		DeckofCards myDeckofCards = new DeckofCards();
		myDeckofCards.shuffle();
		Card[] hand = new Card[5];
		cardsCombination checkFor = cardsCombination.HighCard;
		
		//while(checkFor != cardsCombination.FullHouse)
		while(times-- > 0)
		{
			switch(checkFor)
			{
				case HighCard:
					countHighCard++;
					break;
					
				case Pair:
					countPair++;
					break;
					
				case TwoPair:
					countTwoPair++;
					break;
					
				case ThreeOfKind:
					countThreeOfKind++;
					break;
					
				case Straight:
					countStraight++;
					break;
					
				case Flush:
					countFlush++;
					break;
					
				case FullHouse:
					countFullHouse++;
					break;
					
				case FourOfKind:
					countFourOfKind++;
					break;
			}
			
			for(int i=0; i<5; i++)
			{
				hand[i] = myDeckofCards.dealCard();
				System.out.printf("%-19s", hand[i]);
			}
			
			cardsCombination combination = myDeckofCards.check(hand);
			System.out.println(combination);
			checkFor = combination;
			myDeckofCards.shuffle();
		}
		System.out.println("\nThe results are shown following:\n");
		System.out.println("HighCard:" + countHighCard);
		System.out.println("Pair:" + countPair);
		System.out.println("Two Pairs:" + countTwoPair);
		System.out.println("Three of a Kind:" + countThreeOfKind);
		System.out.println("Straight:" + countStraight);
		System.out.println("Flush:" + countFlush);
		System.out.println("Full House:" + countFullHouse);
		System.out.println("Four of a Kind:" + countFourOfKind);
	}
}
