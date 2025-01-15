/*
 * Love Pavlicek
 * COSC 2430.701
 * Programming Assignment 5
 * April 7, 2024
 * This program will take an input text file from the user, a name for a file to be created,
 * and then it will replace all double whitespaces with a single one, and capitalize letters after "!", ".", and "?."
 * It will then create the text file with the correctly formatted text. 
 */

import java.io.*;

public class TextFileProcessorDemo extends TextFileProcessor
{
    public static void main() throws IOException
    {
        // modified demo class only takes input from file called input.txt and outputs to output.txt
        TextFileProcessor.formatText("input.txt", "output.txt"); 
    }
}
