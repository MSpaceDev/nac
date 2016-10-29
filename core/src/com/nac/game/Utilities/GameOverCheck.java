package com.nac.game.Utilities;

import com.nac.game.GameObjects.Block;
import com.nac.game.GameObjects.Board;

/**
 * Created by HappySaila on 10/29/16.
 * will check if a given board state is game over or not
 */
public class GameOverCheck {
    public static boolean isGameOver(Board board){
        boolean gameOver = false;
        Block block;
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j <  board.getSize(); j++) {
                block = board.getGrid()[i][j];
                if (traverse(block, board) == true){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean traverse(Block block, Board board){
        int x = block.getX();
        int y = block.getY();
        int val = block.getVal();
        int count = 0; //if count = 3 then we have a
        boolean gameOver = false;

        //traverse right
        while(!gameOver){
            try {
                int newVal = board.getGrid()[x++][y].getVal();
                if (newVal == val){
                    count++;
                }else{
                    count = 1;
                }
                if (count == 3){
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                gameOver = false;
                break;
            }
        }

        //traverse diagonal
        while(!gameOver){
            try {
                int newVal = board.getGrid()[x++][y++].getVal();
                if (newVal == val){
                    count++;
                }else{
                    count = 1;
                }
                if (count == 3){
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                gameOver = false;
                break;
            }
        }

        //traverse down
        while(!gameOver){
            try {
                int newVal = board.getGrid()[x][y++].getVal();
                if (newVal == val){
                    count++;
                }else{
                    count = 1;
                }
                if (count == 3){
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                gameOver = false;
                break;
            }
        }
        return gameOver;
    }
}



