package com.nac.game.Utilities;

import com.nac.game.GameObjects.Block;
import com.nac.game.GameObjects.Board;
import com.nac.game.Screens.GameScreen;

/**
 * Created by HappySaila on 10/29/16.
 * will check if a given board state is game over or not
 */
public class GameOverCheck {

    public static boolean isGameOver(Board board, GameScreen gameScreen){
        Block block;
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j <  board.getSize(); j++) {
                block = board.getGrid()[i][j];
                if (traverse(block, board, gameScreen) == true){
                    //a win was found
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean traverse(Block block, Board board, GameScreen gameScreen){
        int x = block.getY();
        int y = block.getX();
        int val = block.getVal();
        int count = 0; //if count = 3 then we have a winner
        boolean gameOver = false;

        //traverse up
        while(!gameOver){
            try {
                int newVal = board.getGrid()[x][y++].getVal();
                if (newVal == val){
                    if (val!=0){
                        count++;
                    }
                }else{
                    val = newVal;
                    count = 1;
                }
                if (count == 3){
                    gameScreen.setPlayerOneWon(checkWinner(val));
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                gameOver = false;
                break;
            }
        }

        x = block.getY();
        y = block.getX();
        val = block.getVal();
        count = 0; //if count = 3 then we have a winner
        gameOver = false;
        //traverse right
        while(!gameOver){
            try {
                int newVal = board.getGrid()[x++][y].getVal();
                if (newVal == val){
                    if (val!=0){
                        count++;
                    }
                }else{
                    val = newVal;
                    count = 1;
                }
                if (count == 3){
                    gameScreen.setPlayerOneWon(checkWinner(val));
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }

        x = block.getY();
        y = block.getX();
        val = block.getVal();
        count = 0; //if count = 3 then we have a winner
        gameOver = false;
        //traverse diagonal up
        while(!gameOver){
            try {
                int newVal = board.getGrid()[x++][y++].getVal();
                if (newVal == val){
                    if (val!=0){
                        count++;
                    }
                }else{
                    val = newVal;
                    count = 1;
                }
                if (count == 3){
                    gameScreen.setPlayerOneWon(checkWinner(val));
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }

        x = block.getY();
        y = block.getX();
        val = block.getVal();
        count = 0; //if count = 3 then we have a winner
        gameOver = false;
        //traverse diagonal down
        while(!gameOver){
            try {
                int newVal = board.getGrid()[x++][board.getSize()-1 - y++].getVal();
                if (newVal == val){
                    if (val!=0){
                        count++;
                    }
                }else{
                    val = newVal;
                    count = 1;
                }
                if (count == 3){
                    gameScreen.setPlayerOneWon(checkWinner(val));
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        return gameOver;
    }

    private static boolean checkWinner(int val){
        if (val == 1){
            return true;
        }else{
            return false;
        }
    }
}



