/*
 * A class representing a playing card
 * 
 * @author: Ki Cooley-Winters
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class Card implements Comparable<Card>{
	
	private int suit; // uses integers 1-4 to encode the suit
	private int rank; // uses integers 1-13 to encode the rank
    
    private boolean sortByRank; 
    //instance variable that determines whether 
    //cards are being sorted by rank or suit
	
	public Card(int s, int r){
		
        suit = s;
        rank = r;
        
        sortByRank = true;
        //default is sorted by rank
	}
	
    
    public void changeCardSort(boolean byRank) {
        
        sortByRank = byRank;
        
    }
    
    public boolean equals(Object other) {
        
        //overrides default equals method, so that
        //cards can be checked for logical (rather than reference) equality
        
        Card c = (Card)other; 
        boolean sameRank = (this.rank == c.rank);
        boolean sameSuit = (this.suit == c.suit);
        
        return(sameRank && sameSuit);
    }
    
	
    public int compareTo(Card c){
		
        if(this.sortByRank) {
            
                 return (this.rank - c.rank); 
             
        }
        
        else {
            
                 return (this.suit - c.suit); 
             
        }
        	
    }
	
	
    public String toString(){
        //makes cards printable in the standard "rank of suit" format
		
		return this.rankString(rank)+" of "+this.suitString(suit); 
	}
    
    private String suitString(int a) {
    
        //converts an integer to its suit equivalent in string form
        switch(a) {
            case 1: 
                return "Clubs";
            case 2: 
                return "Diamonds";       
            case 3: 
                return "Hearts";
            case 4: 
                return "Spades";
            default: return null;    
        }
        
    }
    
    private String rankString(int a) {
        //converts an integer to its rank equivalent in string form
     
        //if the rank is between 2 and 10 (inclusive), 
        //a string verison of that number is returned
        for (int i = 2; i <= 10; i++) {
        
            if (a == i) {
            
                return Integer.toString(a);
            }
        }
        
        //otherwise, it is one of the face cards
        switch (a) {
        
            case 1:
                return "Ace";
            case 11:  
                return "Jack";
            case 12:
                return "Queen";
            case 13:  
                return "King";
            default: return null;
                
        }
        
    }
	
    public int getRank() {
    
        return rank;
    }
}
