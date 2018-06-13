package edu.greenriver.it;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringJoiner;

public class aKwicApp
{

    private static ArrayList<String> fileLines;

    public static void main(String[] args)
    {
        fileLines = new ArrayList<>();
        Scanner scanner;

        try
        {
            scanner = new Scanner(new File("kwicwords.txt"));
            while (scanner.hasNextLine())
            {
                fileLines.add(scanner.nextLine());
            }

            scanner.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        shiftLines();
    }

    private static void shiftLines()
    {
        int count = 0;
        for (String fileLine : fileLines)
        {
            fileLines.set(count, circleShift(fileLine));
            count++;
        }
        alphaSort();
    }

    private static String circleShift(String mrShifty)
    {
        String[] stringArray = mrShifty.split(" ");
        String firstWord = stringArray[0];

        StringJoiner stringJoiner = new StringJoiner(" ");

        for (int i = 1; i < stringArray.length; i++)
        {
            stringJoiner.add(stringArray[i]);
        }

        stringJoiner.add(firstWord);
        return stringJoiner.toString();
    }

    private static void alphaSort()
    {
        Collections.sort(fileLines);
        writeLines();
    }

    private static void writeLines()
    {
        FileWriter fileWriter;

        try
        {
            fileWriter = new FileWriter("ciwkwords.txt");

            for (String line : fileLines)
            {
                fileWriter.write(line);
                System.out.println(line);
            }

            fileWriter.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
