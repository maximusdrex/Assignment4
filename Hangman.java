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
/** runs the program */
    public void run() {
    	println("Welcome to hangman!");
    	println("Guess a letter, any letter.");
    	rg = RandomGenerator.getInstance();
    	init();
    	Word = chooseWord();
    	currentWordGenerator();
    	println(Word);
    	println(CurrentWord);
	}
/** initializes the canvas and lexicon */    
    public void init() {
    	canvas = new HangmanCanvas();
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
    
}
