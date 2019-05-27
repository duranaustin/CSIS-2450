package A01;

import java.util.Scanner;

/**
 * A01UI handles what the user will see
 * reaches out to A01Engine to handle IO
 *
 * @Author: Austin Duran 5/16/2019
 */
public class A01UI{

    public static final char UNDERSCORE = '_';
    public static final char SPACE = ' ';
    public final String livesLeft = "Lives left: ";
    public String input = null;
    Scanner scan;
    public char inputChar = ' ';
    A01Engine engine;

    /**
     * A01UI Constructor kicks off the welcome screen
     */
    public A01UI(){
        this.engine = new A01Engine("MISSISSIPPI");
        welcome();
        input();
    }

    /**
     * welcome method starts console welcome screen
     */
    public void welcome(){
        System.out.println();
        System.out.println("*****Welcome to Hang Man!*****");
        System.out.println("***Guess the word I'm thinking of***");
        System.out.println("*****and I'll cut the noose!*****");
        System.out.println();
        System.out.println();
    }

    /**
     * input method handles input from the user, making frequent calls to this.engine
     */
    public void input(){
        scan = new Scanner(System.in);
        this.input = scan.nextLine();
        this.input = this.input.toUpperCase();
        this.inputChar = this.input.charAt(0);
        System.out.println("Your guess: " + input);

        //checkProgress on word with this.engine.checkProgress
        this.engine.checkProgress(this.inputChar);

        if(this.engine.success || this.engine.gameOver){
            System.out.println("The word was " + this.engine.spacedWord);
        }

        //controls output
        while (this.engine.success == false && this.engine.gameOver == false) {
            System.out.println(this.livesLeft + this.engine.lives);
            System.out.println(this.engine.spacedTempWord);
            input();
        }
    }

    /**
     * displayGameOver does at is says
     */
    public static void displayGameOver(){
        System.out.println("*****GAME OVER*****");

    }

    /**
     * displayYouWin does as it says
     */
    public static void displayYouWin(){
        System.out.println("*****YOU WIN!*****");

    }
}
