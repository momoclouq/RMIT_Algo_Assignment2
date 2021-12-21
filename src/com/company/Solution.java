package com.company;

import java.util.Stack;

public class Solution {
    private Stack<String> path;
    private int goldAmount;

    public Solution(){
        path = new Stack<>();
        goldAmount = 0;
    }

    /**
     *
     * @param newPath
     * @param newGold
     * Description: Update the solution with the new path and gold
     * with path, we copy the values so that adjustment will not affect the saved solution
     */
    public void updateSolution(Stack<String> newPath, int newGold){
        //clone the stack with String will create a new stack with the elements String, as String is immutable
        //we do not have to concern about the problem with shallow copy
        path = (Stack<String>) newPath.clone();
        goldAmount = newGold;
    }

    public Stack<String> getPath() {
        return path;
    }

    public void setPath(Stack<String> path) {
        this.path = path;
    }

    public int getGoldAmount() {
        return goldAmount;
    }

    public void setGoldAmount(int goldAmount) {
        this.goldAmount = goldAmount;
    }
}
