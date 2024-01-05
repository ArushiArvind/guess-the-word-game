import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class GuessTheWordGame {
    private String[] wordList={"book","cap","cook","apple","mindera"};

    String currentWord;
    private String displayedWord;

    private int lives;

    private Set<Character> usedLetters;

    /*
    * This is constructor of the class called from main method
    * this will set the lives for the player
    * will call startGame method
    * */
    public GuessTheWordGame(){
        this.lives=5;
        this.usedLetters=new HashSet<>();
        startGame();
    }

    /*
    * This will call pickRandomWord method which return random word from the wordList
    * once word is guessed will display the word
    * Will call playGame method
    * */
    private void startGame() {
        pickRandomWord();
        System.out.println("Guess the word:");
        displayWord();
        playGame();
    }

    /*
    * Takes input from user from terminal for guessing the word.
    * */
    private void playGame() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a letter:");
            char guessedLetter = scanner.next().charAt(0);

            if (usedLetters.contains(guessedLetter)) {
                System.out.println("You have already used this letter");
                continue;
            }
            usedLetters.add(guessedLetter);
            if (currentWord.contains(String.valueOf(guessedLetter))) {
                System.out.println("Correct!!");
                updateDisplayedWord(guessedLetter);
                displayWord();
                if (displayedWord.equals(currentWord)) {
                    System.out.println("Congratulations you won the game!! The word was:" + currentWord);
                    break;
                }
            }else {
            lives--;
            System.out.println("Incorrect! 1 life lost, " + lives + " remaining.");
            if (lives == 0) {
                System.out.println("Sorry, you are out of lives. The computer wins. The word was: " + currentWord);
                break;
            }
        }
    }
    }

    /*
    * If the letter gusses correct updates the displayed word in terminal.
    * */
    private void updateDisplayedWord(char guessedLetter) {
        StringBuilder updatedWord=new StringBuilder();
        for(int i=0;i<currentWord.length();i++){
            if (guessedLetter == currentWord.charAt(i) || displayedWord.charAt(i) != '*') {
                updatedWord.append(currentWord.charAt(i));
            }else{
                updatedWord.append('*');
            }
        }

        displayedWord=updatedWord.toString();
    }

    /*
    * Will show the word selected from random class.
    * */
    private void displayWord() {
        System.out.println("Current Word:" +displayedWord);
    }

    /*
    * random class used to generate random word from wordList
    * and show *** to the user
    *
    * */
    private void pickRandomWord() {
        Random random=new Random();
        currentWord=wordList[random.nextInt(wordList.length)];
        displayedWord="*".repeat(currentWord.length());
    }

    public static void main(String[] args){
        new GuessTheWordGame();

    }
}
