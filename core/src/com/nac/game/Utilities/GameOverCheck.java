package com.nac.game.Utilities;

import com.nac.game.GameObjects.Block;
import com.nac.game.GameObjects.Board;
import com.nac.game.Screens.GameScreen;

/**
 * Created by HappySaila on 10/29/16.
 * will check if a given board state is game over or not
 */
public class GameOverCheck {

    public static boolean isGameOver(Board board){
        Block block;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <  3; j++) {
                block = board.getGrid()[i][j];
                if (traverse(block, board) == true){
                    //a win was found
                    return true;
                }
            }
        }
        board.setWinner(0);
        return false;
    }

    private static boolean traverse(Block block, Board board){
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
                    board.setWinner(val);
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
                    board.setWinner(val);
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
                    board.setWinner(val);
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
                int newVal = board.getGrid()[x++][2 - y++].getVal();
                if (newVal == val){
                    if (val!=0){
                        count++;
                    }
                }else{
                    val = newVal;
                    count = 1;
                }
                if (count == 3){
                    board.setWinner(val);
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        return gameOver;
    }

    public static boolean isBoardFull(Board board){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <  2; j++) {
                if (board.getGrid()[i][j].getVal() == 0){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isMegaFull(Board[][] boards){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!isBoardFull(boards[i][j])){
                    return false;
                }
            }
        }
        return true;
    }
}



