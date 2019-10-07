README.txt
Name: Ki Cooley-Winters

===============================================================================
Command Line Five Card Draw
===============================================================================
This is an implementation of single-player video poker, five card draw. The player
bets between 1-5 tokens, and is then dealt 5 cards. They then have the opportunity
to redraw and replace some or all of their cards. Their hand is then scored and a
payout is made based on their bet and the odds of their hand.

The program can be run using PokerTest. 
The program is written in java using only standard library functions. I chose Java
for its object-oriented characteristics, and split the classes up to mimic the actual
physical division between a card, deck, player, and game.

In the card class, I included an instance variable (ByRank) that determines how the cards are
compared (by rank or by suit). This was set to true by default, as every hand-checking method except for
one suited the cards by rank. In the card class, I overrode the equals method to allow me to use the remove
method from arrays. The method getRank() is used only to determine if the first or second card in the hand is a 10
for the straight and royalflush checking methods, but I decided to use the more general getRank() method, as it would
be more versatile in other applications of the Card class.

In the deck class, I made made an arraylist copy of cards[] using Array.asList so that I could use
Collections.shuffle, as Array.asList writes changes back to the original array.
    
In the game class, I implemented checkHand using individual methods for each type of hand. CheckHand
checks the hands from highest to lowest payout, as the individual check methods often call each other and thus
are not mutually exclusive. I did this because many of the operations were identical.
