/*
 * A class representing a game of poker
 * 
 * @author: Ki Cooley-Winters
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game {

	private Player user;
	private Deck gameDeck;
    
	private boolean keepPlaying;
    	//keeps track of whether player wants to keep playing
    
	private boolean isTest; 
    	//test instance variable indicates whether game
	//was constructed using the test constructor
	
	public Game(String[] testHand){
        
        	user = new Player();
	        gameDeck = new Deck();
	        keepPlaying = true;
	        isTest = true;
        
        	//fills user's hand with the cards they specified in the command line
	        for (int i = 0; i < 5; i++){
            
        	    user.addCard(stringToCard(testHand[i]));
        	}
        
	}    
        
                           
	public Game(){
		// This no-argument constructor is to actually play a normal game
		user = new Player();
        	gameDeck = new Deck();
        
        	keepPlaying = true;
        	isTest = false;
	}
    
	private void printHand() {
        
        	ArrayList<Card> hand = user.getHand();
        
        	System.out.println("Your hand: ");
        
        	for (int i = 0; i < hand.size(); i++) {
            
            		System.out.println(hand.get(i));
        	}	
   	}	
    
	
	public void play(){
		
        	Scanner sc = new Scanner(System.in);
        
        	while(keepPlaying) {  
		
        
            		int amt = 0;
            		while(amt < 1 || amt > 5) {
                
                	//gets a bet vlaue between 1 and 5 tokens from the user
                
                		System.out.println("Bet amount (between 1 and 5 tokens): ");
                
                		amt = sc.nextInt();
            		}
        
            		user.bets((double) amt);
        
			if(!isTest) {
            
                		//executes only if not playing a test round
                		gameDeck.shuffle();
                		user.clearHand();
                
                		//deals 5 cards to player
                		for (int i = 0; i < 5; i++) {
                    
                    			Card a = gameDeck.deal();
                    			user.addCard(a);
                		
				}
                
                		printHand();
        
                		//discards tracks which cards the user wants to replace
                		ArrayList<Card> discards = new ArrayList<Card>(); 
        
                		System.out.println("Which cards would you like to discard? \n" +
                               				"Press enter after each selection, " +
                                   			"and type done when finished. \n" + 
                                   			"Use the format sr, where s is the first " +
                                   			"letter of the suit and r is the " + 
                                   			"integer value of the rank");
                           
                		while (sc.hasNext()) {
                    
                    			//input represents the card to be replaced
                    			String input = sc.next();
                    
                    			//if the user has typed done, the loop terminates
                    			if (!input.toLowerCase().equals("done")) {
            
                        			//adds the specified card to discards
                        			discards.add(stringToCard(input)); 
                    			}
                    			else {
                        			break;
                    			}
                    
                		}
                
                		redraw(discards);
                
                		printHand();
			}
        
            		payout();
            
            		System.out.println("Do you want to play again? (y/n)");
            
            		if (sc.hasNext()) {
                		if (sc.next().equals("n")) {
                	
                    			keepPlaying = false;
                		}
            		}
        
        	}
    	}

	
	private String checkHand(ArrayList<Card> hand){
		
		if (royalFlush(hand)) {
		    return "Royal Flush";
		}
		
		else if (straightFlush(hand)) {
		    return "Straight Flush";
		}
		
		else if (fourOfKind(hand)) {
		    return "Four of a Kind";
		}
		
		else if (fullHouse(hand)) {
		    return "Full House";
		}
		
		else if (flush(hand)) {
		    return "Flush";
		}
		
		else if (straight(hand)) {
		    return "Straight";
		}
		
		else if (threeOfKind(hand)) {
		    return "Three of a Kind";
		}
		
		else if (twoPairs(hand)) {
		    return "Two Pairs";
		}
		
		else if (pair(hand)) {
		    return "One Pair";
		}
		
		else {
		    return "No Pair";
		}
        
	}
	
    	private void redraw(ArrayList<Card> discards) {
     
		//removes all of the specified cards from the player's hand
		for (int i = 0; i < discards.size(); i++) {
		    
		    user.removeCard(discards.get(i));
		}
		
		//replaces all of the removed cards from the deck
		for (int i = 0; i < discards.size(); i++) {
		    
		    user.addCard(gameDeck.deal());
		    
		}
	}
        
	private Card stringToCard(String in) {
    
		//converts a string input into card form
		int suit = 0;
		int rank = 0;
		
		switch(in.substring(0, 1)) {
			    
		    case "c": 
			suit = 1;
			break;
		    case "d": 
			suit = 2; 
			break;
		    case "h": 
			suit = 3;
			break;
		    case "s": 
			suit = 4;
			break;
		}
		
		//if the rank is double-digit
		if (in.length() == 3) {
		    
		    rank = Integer.parseInt(in.substring(1, 3));
		}
		
		//if the rank is single-digit
		else if (in.length() == 2) {
		    
		    rank = Integer.parseInt(in.substring(1, 2));
		}
		
		Card c = new Card(suit, rank);
		return c;
	}
    
	private void payout() {
    
		//determines the payout of the player's hand 
		//and pays them using user.winnings
		double payout = 0;
		
		switch(checkHand(user.getHand())) {
		    case "Royal Flush" : 
			payout = 250;
			break;
		    case "Straight Flush" : 
			payout = 50;
			break;
		    case "Four of a Kind" : 
			payout = 25;
			break;
		    case "Full House" : 
			payout = 6;
			break;
		    case "Flush" : 
			payout = 5;
			break;
		    case "Straight" : 
			payout = 4;
			break;
		    case "Three of a Kind" : 
			payout = 3;
			break;
		    case "Two Pairs" : 
			payout = 2;
			break;
		    case "One Pair" : 
			payout = 1;
			break;
		    case "No Pair" : 
			payout = 0;
			break;
		}   
		 
		System.out.println("Your hand is: "+checkHand(user.getHand()));
		
		user.winnings(payout);
		System.out.println("You now have $"+user.getBankroll());
		 
	  
	}		
    
	private void changeSort(boolean change, ArrayList<Card> hand) {

		//changes sortByRank for all cards in the hand
		for (int i = 0; i < 5; i++) {
		    
		    hand.get(i).changeCardSort(change);
		}
	}
	       

	private boolean royalFlush(ArrayList<Card> hand) {

		//the second (1st index) element must be 10, since the ace
		//will be sorted at the front of the list
		return (straightFlush(hand) && (hand.get(1).getRank() == 10));
	}

	private boolean straightFlush(ArrayList<Card> hand) {

		return (straight(hand) && flush(hand));
	}

	private boolean fourOfKind(ArrayList<Card> hand) {

		//first and fourth or second and fifth cards must match
		//(since hand is sorted)
		return(duplicates(hand, 0, 3) || duplicates(hand, 1, 4));

	}

	private boolean fullHouse(ArrayList<Card> hand) {

		//one of the pairs will be part of the three of a kind, the other
		//must not (since the pairs are not identical)
		return (threeOfKind(hand) && twoPairs(hand) && !fourOfKind(hand));
	}

	private boolean flush(ArrayList<Card> hand) {

		//sorts the hand by suit
		changeSort(false, hand);
		Collections.sort(hand);

		//if the suit of all the cards is the same, 
		//the first and last must be the same
		boolean temp = (hand.get(0).compareTo(hand.get(4)) == 0);

		//resorts the hand by rank
		changeSort(true, hand);
		Collections.sort(hand);

		return temp;
	}

	private boolean straight(ArrayList<Card> hand) {

		int difference = hand.get(4).compareTo(hand.get(0));

		boolean secondIs10 = (hand.get(1).getRank() == 10);

		//if straight, the first card will be four less than the last (assuming
		//that there are no pairs. 
		//If it is a high ace straight, the difference will be 12, since 
		//(King - Ace) = (13 - 1) = 12, and the second card will be 10
		return(!pair(hand) && ((difference == 4) || 
			       (secondIs10 && difference == 12)));

	}
	       
	private boolean threeOfKind(ArrayList<Card> hand) {

		//the 1st and 3rd, 2nd and 4th, or 3rd and 5th cards must match
		for(int i = 0; i < 3; i++) {

		    if (duplicates(hand, i, i+2)) {
			return true;
		    }
		}
		return false;

	}
	       
	private boolean twoPairs(ArrayList<Card> hand) {

		ArrayList<Card> firstHalf = new ArrayList<Card>(hand.subList(0, 3));
		ArrayList<Card> secondHalf = new ArrayList<Card>(hand.subList(2, 5));

		//there must be a pair in both the first three cards and the last
		//three cards of the hand
		return(pair(firstHalf) && pair(secondHalf));

	}
	       
	private boolean pair(ArrayList<Card> hand) {

		for(int i = 0; i < hand.size() - 1; i++) {

		    if (duplicates(hand, i, i+1)) {
			return true;
		    }
		}
		return false;

	}

	private boolean duplicates(ArrayList<Card> hand, int start, int end) {

		//determines if the cards at the specified indices are equal
		return((hand.get(start)).compareTo(hand.get(end)) == 0);
	}
}
