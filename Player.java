/*
 * A class representing a poker player
 * 
 * @author: Ki Cooley-Winters
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class Player {

	
		
	private ArrayList<Card> hand; //the player's cards
 	private double bankroll; //the player's money
	private double bet; 
    
	public Player(){
		
		hand = new ArrayList<Card>();
        
        	bet = 0.0;
        	bankroll= 100.0;
	}
    
	public ArrayList<Card> getHand() {
            
        	//sorts and returns the player's hand
        	Collections.sort(hand);
        	
		return hand;
        
    	}
        
	public void addCard(Card c){
	    
        	hand.add(c);
        
	}

	public void removeCard(Card c){
	    
        	// remove the card c from the player's hand
        	hand.remove(c);
    
	}
		
	public void bets(double amt){
            
        	bet = amt;
        	bankroll -= bet;
            
    	}

    	public void winnings(double odds){
            
		//updates the player's bankroll based on bet
        	bankroll += (bet * odds);
            
    	}
    
    	public void clearHand() {
     		
        	hand.clear();
    	}

	public double getBankroll(){
        	//return current balance of bankroll
        	return bankroll;
    	}	

}
