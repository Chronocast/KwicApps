package edu.greenriver.it;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringJoiner;

public class AnotherKwicApp
{
    public ArrayList<String> lines;
    private static final String IN = "kwicwords.txt";
    private static final String OUT = "ciwkwords.txt";

    public AnotherKwicApp()
    {
        this.lines = readLines();
    }

    public static void main(String[] args)
    {
        AnotherKwicApp kwic = new AnotherKwicApp();

        kwic.shiftLines(kwic.lines);
        kwic.alphaSort(kwic.lines);
        kwic.writeLines(kwic.lines);
    }

    private ArrayList<String> readLines()
    {
        ArrayList<String> result = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(IN)))
        {

            while (scanner.hasNextLine())
            {
                result.add(scanner.nextLine());
            }
        } catch (FileNotFoundException exc)
        {
            exc.printStackTrace();
        }
        return result;
    }

    public void shiftLines(ArrayList<String> lines)
    {
        for (int i = 0; i < lines.size(); i++) {
            lines.set(i, circleShift(lines.get(i)));
        }
    }

    private String circleShift(String line)
    {
        String[] words = line.split(" ");
        String firstWord = words[0];

        StringJoiner stringJoiner = new StringJoiner(" ");

        for (int i = 1; i < words.length; i++)
        {
            if(words[i].length() > 0)
            {
                stringJoiner.add(words[i]);
            }
        }

        stringJoiner.add(firstWord);
        return stringJoiner.toString();
    }

    public void alphaSort(ArrayList<String> lines)
    {
        Collections.sort(lines);
    }

    public void writeLines(ArrayList<String> lines)
    {
        try (PrintWriter printWriter = new PrintWriter(new File(OUT)))
        {
            for (String line : lines) {
                printWriter.println(line);
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}