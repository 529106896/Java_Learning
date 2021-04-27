package lab4;

//class to represent a Card in a deck of cards
class Card 
{
	
	private final String face; 
	private final String suit; 

	// constructor
	public Card(String face, String suit) 
	{
	    this.face = face; 
	    this.suit = suit; 
	} 
	
	// return face of the card
	public String getFace() 
	{
		return face;
	}
	
	// return suit of Card
	public String getSuit() 
	{
		return suit;
	}
	
	// return String representation of Card
	public String toString() 
	{
	   return String.format("%s of %s", face, suit);
	} 
} 
	
	