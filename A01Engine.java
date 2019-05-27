package A01;

/**
 * A01 Engine is the brain of A01UI
 *
 * @Author: Austin Duran 5/16/2019
 */
public class A01Engine {

    public String tempWord;
    public String spacedTempWord;
    public char[] charArr;
    public char[] spacedCharArr;
    public String word;
    public String spacedWord;
    public int lengthOfWord = 0;
    public int lives = 0;
    public int spacedLives = 0;
    public boolean success = false;
    public boolean gameOver = false;

    /**
     * A01Engine Constructor instantiates this.word upon object creation
     */
    public A01Engine(String word){
        this.word = word;
        setLengthOfWord();
        setLives();
        this.charArr = new char[lengthOfWord];
        this.spacedCharArr = new char[lengthOfWord * 2];
        setCharArr();
        setSpacedCharArr();
        this.spacedWord = setSpacedWord();
        this.tempWord = new String(this.charArr);
        this.spacedTempWord = new String(this.spacedCharArr);
    }

    /**
     * setSpacedWord uses this.word and creates a string with spaces included of the same word
     */
    private String setSpacedWord(){
        String s;
        char tempSpacedCharArr[];
        tempSpacedCharArr = new char[lengthOfWord * 2];
        int temp = 0;
        for (int i = 0; i < lengthOfWord * 2; i++){
            tempSpacedCharArr[i] = word.charAt(temp);
            tempSpacedCharArr[++i] = A01UI.SPACE;
            temp++;
        }
        s = new String(tempSpacedCharArr);
        return s;

    }

    /**
     * setLengthOfWord sets the length of the word
     */
    private void setLengthOfWord(){
        this.lengthOfWord = this.word.length();
    }

    /**
     * setLives sets the number of lives
     */
    private void setLives(){
        this.lives = 6;
        this.spacedLives = 6;
    }

    /**
     * setCharArr inserts underscores for the length of the word inside of a char list
     */
    private void setCharArr(){
        for (int i = 0; i < this.charArr.length; i ++){
            this.charArr[i] = A01UI.UNDERSCORE;
        }
    }

    /**
     * setSpacedCharArr inserts underscores and spaces for a more readable word inside of a char list
     */
    private void setSpacedCharArr(){
        for (int i = 0; i < this.spacedCharArr.length; i ++){
            this.spacedCharArr[i] = A01UI.UNDERSCORE;
            this.spacedCharArr[++i] = A01UI.SPACE;
        }
    }

    /**
     * checkGameOver Checks for game over, if lives = 0
     */
    public void checkGameOver(){
        if (this.lives == 0) {A01UI.displayGameOver(); this.gameOver = true;}
    }

    /**
     * checkYouWin Checks for winning if tempWord equals the word provided
     */
    public void checkYouWin(){
        if (this.tempWord.equals(this.word)){ A01UI.displayYouWin(); this.success = true;}
    }

    /**
     * checkProgress checks the current state of guesses and how close you are to guessing the word
     */
    public void checkProgress(char c){
        checkCharacterInString(c);
        changeTempWord();
        changeTempSpacedWord();
        checkYouWin();
        checkGameOver();
        return;
    }

    /**
     * changeTempWord converts the charArr to a string
     */
    public void changeTempWord(){
        String s = new String();
        s = new String(this.charArr);
        this.tempWord = s;
    }

    /**
     * changeTempSpacedWord converts the spacedCharArr to a string
     */
    public void changeTempSpacedWord(){
        String s = new String();
        s = new String(this.spacedCharArr);
        this.spacedTempWord = s;
    }

    /**
     * checkCharacterInString checks if the character provided by user is in the word
     */
    public void checkCharacterInString(char c){
        //this is for charArr
        boolean exist = false;
        boolean existForSpace = false;
        for(int i = 0; i < lengthOfWord; i++){
            if(c == word.charAt(i)){
                this.charArr[i] = c;
                exist = true;
            }
        }
        if (!exist) this.lives--;

        //this is for spacedCharArr
        for(int i = 0; i < lengthOfWord * 2; i+=2){
            if(c == spacedWord.charAt(i)){
                this.spacedCharArr[i] = c;
                existForSpace = true;
            }
        }
        if (!existForSpace) this.spacedLives--;
    }
}
