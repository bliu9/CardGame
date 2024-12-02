public class Main
{
    public static void main(String args[])
    {
        Game game = new Game();

        game.printRules();
    }
}

/*
 Card game: uno
 - cards (special ability, color, value)
 - player: human, AI: (hand, can play a card, can draw a card)

 Rules:
 - color match or number match to play a card
 - if you don't have a card to play, you draw one card and end turn
 - +2 and +4 cards that have color and allow you to give the other player cards
 - wild card that allows you to change the color of the deck (is all colors)

 Computer / AI player:
 - if there is a set of cards that the computer can play, it will choose a random card from that set to play
 - if there is not a card in the set of cards that the computer can play, it will draw a card and end turn

 Calling uno:
 - when you reach 1 card remaining in your deck, you have 3 seconds to type uno into the terminal; if not, then you are forced to draw a card
 - computer will have a 66% chance to call uno before 3 seconds and 33% chance to not

 Winning:
 - if you have 0 cards in your deck, you win

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Game
- getName
- printRules*************
- checkIsUno*************
- checkCanPlayCard*******
- playGame***************

Player
- getters
- setters
- addCard******************
- removeCard
- playCard*****************

Deck
- deal
- shuffle

Card
-


*/