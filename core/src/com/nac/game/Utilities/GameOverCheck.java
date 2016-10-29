package com.nac.game.Utilities;

import com.nac.game.GameObjects.Board;

/**
 * Created by HappySaila on 10/29/16.
 * will check if a given board state is game over or not
 */
public class GameOverCheck {
    public static boolean isGameOver(Board board){
//        Tree tree = generateTree(board);
        return false;
    }
}

class Node{
    Node parent;
    Node child1;
    Node child2;
    Node child3;

    public Node(Node parent, Node child1, Node child2, Node child3) {
        this.parent = parent;
        this.child1 = child1;
        this.child2 = child2;
        this.child3 = child3;
    }
}

class Tree{
    Node root;

    public Tree(Node root) {
        this.root = root;
    }
}
