/*
 * Love Pavlicek
 * COSC 2430.701
 * Programming Assignment 6
 * May 2, 2024
 * This program will create a window and let the user type text
 * and then it will replace all double whitespaces with a single one, and capitalize letters after "!", ".", and "?."
 * It will then create the text file with the correctly formatted text. 
 */
import java.io.*;
public class TextFileProcessor
{
    public static void formatText(String inputName, String outputName)
    {
        try
        {
            BufferedReader inputStream = new BufferedReader(new FileReader(inputName)); // create a buffered reader
            PrintWriter outputStream = new PrintWriter(new FileOutputStream(outputName)); // create a printwriter

            String currentLine = inputStream.readLine(); // current line that will be iterated in the while loop

            boolean capitalizeNext = true; // boolean to know whether next character should be capitalized.
            
            int count = 0;
            
            
            while (currentLine != null)
            {
                count++;

                // using built in replace function, replace two or more whitespaces with one whitespace
                currentLine = currentLine.replaceAll("\\s+", " ");

                StringBuilder stringBuilder = new StringBuilder(currentLine.length()); // Stringbuiler object

                char previousChar = ' '; // this will be used for testing if there is whitespace followed by punctuation

                for (int i = 0; i < currentLine.length(); i++)
                {
                    char currentChar = currentLine.charAt(i);

                
                    if (capitalizeNext) // if the next character is supposed to be capitalizd, then capitalize it
                    {
                        stringBuilder.append(Character.toUpperCase(currentChar));
                        capitalizeNext = false; /// after capitalizing, set to false. 
                    }
                    else {
                        stringBuilder.append(currentChar); // otherwise, just append the character.
                    }

                    if ((".?!".indexOf(previousChar) != -1) && (currentChar == ' ') ) // check if there is whitespace followed by punctuation
                    {
                        // if there is, then the next letter should be capitalized and on a new line. 
                        stringBuilder.append("\n");
                        capitalizeNext = true;
                    }
                    
                    previousChar = currentChar; // for the next iteration, make the current char be the previous char

                    

                }
                outputStream.print(stringBuilder.toString()); // converts the character sequence into a String and then prints      
                
                currentLine = inputStream.readLine();// iterates the current line
                
            }
            // NO MEMORY LEAKS ALLOWED
            inputStream.close();
            outputStream.close(); 


            System.out.printf("%d lines written to " + outputName, count);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Input file not found");
        }
        catch(IOException e)
        {
            System.out.println("Errr... an error occured");
        }
    }
}
