package com.nac.game.Utilities;

import java.util.Random;

/**
 * Created by HappySaila on 10/29/16.
 * used for comman static tasks
 */
public class utils {
    public static int generate(int i, int j){
//        will generate a number between x in range (0,i]
        Random rand = new Random();
        return rand.nextInt(j-i)+i;
    }
}
