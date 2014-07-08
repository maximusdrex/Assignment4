/*
* File: Hangman.java
* ------------------
* This program will eventually play the Hangman game from
* Assignment #4.
*/

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;

public class Hangman extends ConsoleProgram {
/** Random Generator */
private RandomGenerator rg;
/** lexicon for the game */
private HangmanLexicon lexicon;
/** The secret word used in the game */
private String Word;
/** This is the game canvas */
private HangmanCanvas canvas;
/** This is what the word currently looks like */
private String CurrentWord;
/** Letter that the user guessed */
private char UserGuess;
/** runs the program */
private int Lives = 8;
    public void run() {
     //Starts the game
     setTitle("Hangman");
     println("Welcome to hangman!");
     println("Guess a letter, any letter. Use capital letters.");
     //Initializes the game.
     rg = RandomGenerator.getInstance();
     init();
     Word = chooseWord();
     currentWordGenerator();
     println(Word);
     println(CurrentWord);
     
     while(Lives > 0) {
    	 UserGuess = getCharacter();
     println("You guessed: " + UserGuess);
     boolean isTrue = checkForLetter(UserGuess, Word);
     if(isTrue == true) {
     println("YOU ARE CORRECT");
     updateCurrentWord(UserGuess);
     }
     else {
     canvas.noteIncorrectGuess(UserGuess);
     Lives--;
     println("You are incorect");
     }
     println("Lives remaining: " + Lives);
     println("The word now looks like this: " + CurrentWord);
     }
}
/** initializes the canvas and lexicon */
    public void init() {
     canvas = new HangmanCanvas();
     canvas.reset();
     add(canvas);
     lexicon = new HangmanLexicon();
     }
/** chooses the next word */
    private String chooseWord() {
int lexiconLength;
int wordNumber;
String THE_WORD;
lexiconLength = lexicon.getWordCount();
wordNumber = rg.nextInt(0, lexiconLength) - 1;
THE_WORD = lexicon.getWord(wordNumber);
     return THE_WORD;
    }
    /** Generates the currentWord String */
    private void currentWordGenerator() {
     int wordLength = Word.length();
     CurrentWord = "-";
     for(int i = 1; i < wordLength; i++) {
     CurrentWord = CurrentWord + "-";
     }
    }
    private char getCharacter() {
     String LetterGuesed;
     char letterGuesed;
     LetterGuesed = readLine("guess a letter then press enter -->");
     letterGuesed = LetterGuesed.charAt(0);
     return letterGuesed;
    }
    
    private boolean checkForLetter(char Guess, String Word) {
    	for(int characters = 0; characters < Word.length(); characters++) {
    		if(Word.charAt(characters) == Guess) {
    			return true;
    		}
    	}
    return false;
    }
    
    private int checkWhichLetter(char Guess, String Word) {
    	for(int characters = 0; characters < Word.length(); characters++) {
    		if(Word.charAt(characters) == Guess) {
    			return characters;
    		}
    	}
    	return 0;
    }
    
    private void updateCurrentWord(char Guess) {
        int GuessIsAt = checkWhichLetter(UserGuess, Word);
    }
}