package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ProcessFile {
    public static String[][] getMapFromFile(String filename){
        try{
            Scanner input = new Scanner(new File(filename));
            //get the first line including rows and columns
            String line = input.nextLine();
            String[] values = line.split(" ");
            int row = Integer.parseInt(values[0]);
            int col = Integer.parseInt(values[1]);

            String[][] map = new String[row][col];
            for(int i = 0; i < map.length; i++){
                for(int k = 0; k < map[i].length; k++){
                    if(input.hasNext()) map[i][k] = input.next().trim();
                }
            }

            return map;
        } catch (FileNotFoundException e){
            System.out.println("Cannot read file, check your filename");
        } catch(NumberFormatException e){
            System.out.println("Wrong values (float in place of integer?)");
        } catch(Exception e){
            System.out.println("Data is corrupted, wrong format");
        }
        return null;
    }

    public static void createTest() {
        try {
            File myObj = new File("test.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
