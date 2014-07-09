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
/**
 *  This is the game canvas 
 */
private HangmanCanvas canvas;
/**
 * What the word currently looks like.
 */
private char[] CurrentWord;
/**
 * Letter that the user guessed.
 */
private char UserGuess;
/**
 * The user's lives.
 */
private int Lives = 8;
/**
 * Runs the program.
 */
    public void run() {   
    	canvas.reset();
    	
    	//Starts the game
    	setTitle("Hangman");
    	println("Welcome to hangman!");
    	println("Guess a letter, any letter. Use capital letters.");
    	//Initializes the game.
    	rg = RandomGenerator.getInstance();
    	Word = chooseWord();
    	currentWordGenerator(Word);
    	println(Word);
    	println(CharArrayToString(CurrentWord));
    	while(Lives > 0 && checkIfWon(CurrentWord, Word) == false) {
    		UserGuess = getCharacter();
    		println("You guessed: " + UserGuess);
    		if(checkForLetter(UserGuess, Word) == true) {
    			println("YOU ARE CORRECT");
    			updateCurrentWord(UserGuess, Word);
    		}
    		else {
    			canvas.noteIncorrectGuess(UserGuess);
    			Lives--;
    			println("You are incorect");
    		}
    		println("Lives remaining: " + Lives);
    		updateCurrentWord(UserGuess, Word);
    		println("The word now looks like this: " + CharArrayToString(CurrentWord) );
    	}
    	//Prints "You win" if you win and "You lose" if you lose.
    	if(Lives > 0) {
    		println("YOU WIN!!! :-D");
    	}
    	else {
    		println("YOU LOSE!! :(");
    	}
    }
    
/**
 *Initializes the program.
 *(Lexicon and Canvas)
*/
    public void init() {
    	this.setSize(1200,800);
    	canvas = new HangmanCanvas();
    	
    	add(canvas);
    	lexicon = new HangmanLexicon();
    }
    
/**
 * Chooses the word from the lexicon.
 	* @return The word to be used in the game
*/
    private String chooseWord() {
    	int lexiconLength;
    	int wordNumber;
    	String TheWord;
    	lexiconLength = lexicon.getWordCount();
    	wordNumber = rg.nextInt(0, lexiconLength) - 1;
    	TheWord = lexicon.getWord(wordNumber);
     return TheWord;
    }
    
/**
 * Generates the char[] of characters that the user guessed.
 	* @param Word The word that is being guessed.
*/
    private void currentWordGenerator(String Word) {
    	int WordLength = Word.length();
    	CurrentWord = new char[WordLength];
    	for(int CharacterInArray = 0; CharacterInArray < WordLength; CharacterInArray++) {
    		CurrentWord[CharacterInArray] = '-';
    	}
    }
/**
 * Gets a character from the user.
 	* @return The character that the player entered.
*/
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
    
/**
 * Returns which characters in a word match the Guessed character.
 	* @param Guess Is the character that the user guessed.
 	* @param Word The word which has its character's searched for matching ones.
 	* @return A boolean array as long as the word. The places that are true are the places that have matching letters.
*/
    private boolean[] checkWhichLetter(char Guess, String Word) {
    	boolean[] WhereAreLetters = new boolean[Word.length()];
    	for(int characters = 0; characters < Word.length(); characters++) {
    		if(Word.charAt(characters) == Guess) {
    			WhereAreLetters[characters] = true;
    		}
    		else {
    			WhereAreLetters[characters]  = false;
    		}
    	}
		return WhereAreLetters;
    }
/**
 * Updates the CurrentWord char[]
	* @param Guess The user's guess
	* @param Word The word that the user is guessing;
*/
    private void updateCurrentWord(char Guess, String Word) {
        boolean[] GuessIsAt = checkWhichLetter(Guess, Word);
        for(int Characters = 0; Characters < Word.length(); Characters++) {
        	if(GuessIsAt[Characters] == true) {
        		CurrentWord[Characters] = Guess;
        	}
        }
    }
/**
 * Turns a char[] to a string.
 	* @param CharacterArray The char[] to be converted.
 	* @return The converted char[] that is now a string.
*/
    private String CharArrayToString(char[] CharacterArray) {
    	String TheCurrentWord = new String(CharacterArray);
    	return TheCurrentWord;
    }
    
/**
 * Returns true if the player has won the game.
 	* @param TheCurrentWord The char[] of letters the player has revealed
 	* @param Word The word that is trying to be guessed.
 	* @return Returns true if the player appears to have won the game and returns false if the player has not won the game
*/
    private boolean checkIfWon(char[] TheCurrentWord, String Word) {
		for(int Characters = 0; Characters < Word.length(); Characters++) {
			if(TheCurrentWord[Characters] == '-') {
				return false;
			}
		}
		return true;
    }
}