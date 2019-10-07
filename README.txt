README.txt
Name: Ki Cooley-Winters
UNI: nhc2116
Assignment: Project 4

===============================================================================
<Poker Game>
===============================================================================
Design Document should include:

Instructions for running your code and any tests you may have written
Rules for your card game, if not one of the three listed above
A brief explanation of your design choices and any data structures or algorithms that you implemented
Choice of tooling (language, libraries, test runner, etc.) and rationale behind those choices.



    In the card class, I included an instance variable (sortByRank) that determines how the cards are
compared (by rank or by suit). This was set to true by default, as every check method except for
one suited the ards by rank. In the card class, I overrode the equals method to allow me to use the remove
method from arrays. The method getRank() is used only to determine if the first or second card in the hand is a 10
for the straight and royalflush checking methods, but I decided to use the more general getRank() method, as it would
be more versatile in other applications of the Card class.

    In the deck class, I made made an arraylist copy of cards[] using Array.asList so that I could use
Collections.shuffle, as Array.asList writes changes back to the original array.
    
    In the game class, I implemented checkHand using individual methods for each type of hand. CheckHand
checks the hands from highest to lowest payout, as the individual check methods often call each other and thus
are not mutually exclusive. I did this because many of the operations were identical.