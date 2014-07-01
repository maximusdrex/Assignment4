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
private RandomGenerator rg;
private HangmanLexicon lexicon;
private String Word;
private HangmanCanvas canvas;
private String CurrentWord;
    public void run() {
    	rg = RandomGenerator.getInstance();
    	init();
    	Word = chooseWord();
    	currentWordGenerator();
    	println(Word);
    	println(CurrentWord);
	}
/** initializes the canvas */    
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
    // need to complete
    private void currentWordGenerator() {
    	int wordLength = Word.length();
    	CurrentWord = "-";
    	for(int i = 1; i < wordLength; i++) {
    		CurrentWord = CurrentWord + "-";
    	}
    }
    
}
