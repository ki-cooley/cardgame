/*
 * A class representing a deck of playing cards
 * 
 * @author: Ki Cooley-Winters
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	private Card[] cards;
	private int top; // the index of the top of the deck

	public Deck(){
        
        
		// makes and fills a 52 card deck
		cards = new Card[52];
        	top = 0;
        
        	for (int i = 0; i < 4; i++) {
                
            		for (int j = 0; j < 13; j++) {
                
                	//the j+(13*i) ensures that the indices cover every value 0-51
                	cards[j+(13 * i)] = new Card((i+1), (j+1));
            		
			}
        	}	
        
	}
	
	public void shuffle(){
		// shuffles the deck, resetting the top index
		
        	Collections.shuffle(Arrays.asList(cards));
        	//converts the deck to an ArrayList so that Collections.shuffle may be called on it
        	
		top = 0;
	}
	
	public Card deal(){
		
		// deals the top card in the deck
		Card deal = cards[top];
        	
		top++;
        	return deal;
	}

}
