/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package ca.sheridancollege.project;

import ca.sheridancollege.project.Card.Value;
import java.util.ArrayList;
import java.util.Scanner; 

/**
 * The class that models your game. You should create a more specific
 * child of this class and instantiate the methods given.
 * @author dancye, 2018
 */


public class Game {
    private static int money;
    /**
     * This method starts the game and has infinite loop until user not lose money or want to exit
     */
    public static void main(String[] args) {

        Game game = new Game();
        String play = "y";
        Scanner console = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Welcome to our club! For the security purposes we will ask your to make couple exercise. Let's start.\n");
        System.out.println("Enter player name (Must contain only letters):");

        String userName = console.nextLine(); // Read user input

        if(game.isNumeric(userName)){
            System.out.println("Your name is valid!");
        }
        else {
            System.out.println("Not today! You cannot enter to our club");
            System.exit(0); //user cannot enter to our club
        }

        System.out.println("Enter number of your money which you have with you! Note: should be more then 100 or you cannot enter to our game!");

         money = console.nextInt();



         if(game.money(money)){
             System.out.println("Welcome to the game: " + userName);
        }
        else {
             System.out.println("Not today!");
             System.exit(0); //user cannot play in our game
         }

        Player _player = new Player(userName, money); //object of player
        Dealer _dealer = new Dealer("Dealer", money); //object of dealer

        while (play.equals("y")) {
            System.out.println("Make a bet!");

            double bet = 0.0;
            try {
                bet = console.nextDouble();
            } catch (Exception e) {
                System.out.println("Ooops, wrong number been entered, please try again!");
                console.nextLine();
                continue;
            }

            if (_player.getMoney() < bet) {
                bet = _player.getMoney();
                System.out.println("Dear, " + userName + " no money for that bet! You are betting what you have left instead: " + bet);
                break; //if user doesn't have enough money then user cannot play
            }

            ShuffleDeal dealing = new ShuffleDeal(); //object of ShuffleDeal

            dealing.shuffle(_player); //shuffle for the player
            dealing.shuffle(_dealer); //shuffle for the dealer


            System.out.println("The value of your cards are: " + handValue(_player.get_hand()));
            System.out.println("Dealer shows: " + _dealer.get_hand().showCards().get(0).getValue() + " of " + _dealer.get_hand().showCards().get(0).getSuit());

            System.out.println("Hit? Type y for Hit and n for Stay");
            console.nextLine();
            String confirm = console.nextLine();
            int i = 2;
            while (confirm.equals("y")) {

                dealing.deal(_player);
                System.out.println("You drew: " + _player.get_hand().showCards().get(i).getValue() + " of " + _player.get_hand().showCards().get(i).getSuit());
                i++;
                System.out.println("The value of your cards are: " + handValue(_player.get_hand()));

                if (handValue(_player.get_hand()) > 21) {
                    declareWinner(_player, bet, _dealer);
                    confirm = "";
                } else {
                    System.out.println("Hit? Type y for Hit and n for Stay");
                    confirm = console.nextLine();
                }
            }

            if (handValue(_player.get_hand()) <= 21) {

                while (handValue(_dealer.get_hand()) < 16) {
                    System.out.println("dealer under 16, hitting");
                    dealing.deal(_dealer);
                }

                declareWinner(_player, bet, _dealer);
            }

            if (_player.getMoney() > 0) {

                System.out.println("Play again? Type y for yes, n for no");
                play = console.nextLine();
            } else {
                play = "";
                System.out.println("Dear, " + userName + " You out of money! Please visit the nearest ATM or loan shark!"); //user doesn't have the money

            }

        }
        System.out.println("Goodluck " + userName + "!");
    }

    /**
     * This method calculate cards value in the hand of player
     */

    static public int handValue(Hand _hand) {

        int value = 0;

        for (int i = 0; i < _hand.showCards().size(); i++) {

            int tmp = _hand.showCards().get(i).getValue().ordinal();

            if (tmp == 10 || tmp == 11 || tmp == 12) {
                tmp = 9;
            }

            if (tmp == 0 && value + 11 <= 21) {
                tmp = 10;
            }


            value = value + tmp + 1;
        }

        return value;

    }

    /**
     * This method generate the result according the values which gamer and dealer have.
     */


    static public void declareWinner(Player player, double playbet, Player dealer) {
        int valuePlayer = handValue(player.get_hand()); //get hand of player
        int valueDealer = handValue(dealer.get_hand()); //get hand of dealer

        if (valuePlayer > 21) {
            System.out.println("You lose, busted out! Your value:" + valuePlayer);
            player.setMoney(player.getMoney() - playbet);
            System.out.println("Your money " + player.getMoney());
        } else if (valueDealer > 21) {
            System.out.println("You win, dealer busted out! Dealer value:" + valueDealer);
            player.setMoney(player.getMoney() + playbet);
            System.out.println("Your money " + player.getMoney());
        } else if (valuePlayer > valueDealer) {
            System.out.println("You win! You got " + valuePlayer + " and dealer got " + valueDealer + "!");
            player.setMoney(player.getMoney() + playbet);
            System.out.println("Your money " + player.getMoney());
        } else {
            System.out.println("You lose! You got " + valuePlayer + " and dealer got " + valueDealer + "!");
            player.setMoney(player.getMoney() - playbet);
            System.out.println("Your money " + player.getMoney());
        }

    }
    /**
     * This method allows player to enter money for the game
     */

    public boolean money(int money){
        //Scanner console = new Scanner(System.in);
        // money = console.nextInt();
        if(money >= 100){
            return true;
        }else {
            return false;
        }
    }
    /**
     * This method check that user enter a valid name
     */
    public boolean isNumeric(String userName)
    {
        if(!userName.matches("-?\\d+(\\.\\d+)?")){
            return true;
        }else return false;
    }
}
