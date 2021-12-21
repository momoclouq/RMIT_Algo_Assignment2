package com.company;

import java.util.Arrays;
import java.util.Stack;

public class DynamicSearch {
    private String[][] map;
    private int x; //number of horizontal moves or the limit of position
    private int y; //number of vertical moves or the limit of position
    private Solution solution;

    //dynamic map to store previous results
    private int[][] resultMap;

    public DynamicSearch(String[][] map) throws Exception{
        this.map = map;

        //check if map is empty or not
        if(map == null) throw new Exception("Map does not exist");
        if(map.length == 0 || map[0].length == 0){
            throw new Exception("Map is empty");
        }

        this.x = map[0].length;
        this.y = map.length;
        this.solution = new Solution();
        this.resultMap = new int[y][x];

        //initialize solution
        for(int i = 0; i < resultMap.length; i++){
            for(int k = 0; k < resultMap[i].length; k++){
                resultMap[i][k] = -1;
            }
        }
    }

    public void search(){
        //always start at (0,0)
        search(0,0, 0, new Stack<String>());
    }

    private void search(int posY, int posX, int initialGold, Stack<String> path){
        int value = calValue(posY, posX);

        switch(value){
            case -1:
                //reach a wall or a rock, return
                return;
            default:
                //update the best and current solution if needed
                int newGold = initialGold + value;
                if(newGold > solution.getGoldAmount()){
                    solution.updateSolution(path, newGold);
                }
                else if(newGold == solution.getGoldAmount() && path.size() < solution.getPath().size()){
                    //when the amount of gold is the same, compare the solution path size vs the current path size
                    solution.updateSolution(path, newGold);
                }

                //check previous results in the result map
                if(newGold > resultMap[posY][posX]){
                    //find a better solution
                    resultMap[posY][posX] = newGold;

                    //every other cases
                    //move to the right
                    path.push("R");
                    search(posY, posX + 1, newGold, path);
                    path.pop();

                    //move to bottom
                    path.push("D");
                    search(posY + 1, posX, newGold, path);
                    path.pop();
                } else {
                    //this solution is not better than the one we found from this position onward
                    //escape the recursion
                    return;
                }
        }
    }

    /**
     * Display the solution using the solution stored inside
     */
    public void displaySolution(){
        System.out.println("Solution Found: ");
        System.out.println("Path: " + Arrays.toString(solution.getPath().toArray()));
        System.out.println("Path size: " + solution.getPath().size());
        System.out.println("Gold: " + solution.getGoldAmount());
    }

    //////////////////
    //helper functions

    /**
     *
     * @param posX
     * @param posY
     * Description: Calculate the points of the new position
     * @return
     *     Wall or rock(X): -1
     *     Ground(.): 0
     *     Gold: value of gold
     */
    private int calValue(int posY, int posX) throws NumberFormatException{
        //reach the wall
        //only check positive value (not reaching -1 etc) as possible moves only increase the position
        if(posX >= x || posY >= y) return -1;

        //Rock
        if(map[posY][posX].equals("X")) return -1;

        //ground
        if(map[posY][posX].equals(".")) return 0;

        //gold
        int value = Integer.parseInt(map[posY][posX]);
        return value;
    }
}
