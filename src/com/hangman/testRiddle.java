package com.hangman;

import com.hangman.Game;
import com.hangman.GraphicalRiddle;
import com.hangman.Riddle;

public class testRiddle {
    public static void main (String[] args){
        Riddle riddle = new Riddle("Computer",7);
        GraphicalRiddle graphicalRiddle = new GraphicalRiddle("Computer",7);
        riddle.guess('a');
        System.out.println(riddle);
        riddle.guess('u');
        System.out.println(riddle);
        riddle.guess('o');
        System.out.println(riddle);
        riddle.guess('c');
        System.out.println(riddle);
        System.out.println(graphicalRiddle);

        Game game = new Game(5);
        game.display();
    }
}


