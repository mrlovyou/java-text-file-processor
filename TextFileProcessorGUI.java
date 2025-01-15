/*
 * Love Pavlicek
 * COSC 2430.701
 * Programming Assignment 6
 * May 2, 2024
 * This program will create a window and let the user type text
 * and then it will replace all double whitespaces with a single one, and capitalize letters after "!", ".", and "?."
 * It will then create the text file with the correctly formatted text. 
 */

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class TextFileProcessorGUI extends JFrame
{
    // instantiate all the buttons and the text area
    private JTextArea textArea;
    private JButton saveButton;
    private JButton clearButton;

    public TextFileProcessorGUI()
    {
        setTitle("Text File Processor Demo"); // i don't know what else to name the window
        setSize( 400, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close button closes window

        textArea = new JTextArea();
        JScrollPane scrollBar = new JScrollPane(textArea); // a scroller in case there is too much text

        saveButton = new JButton("Save"); // create save button object
        saveButton.addActionListener(new ActionListener() // add a listener
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                File outputFile = new File("input.txt"); // after user presses save, create a file called input.txt
                textToFile(outputFile); // then, using a method, for simplification, save the text to the file that was just made
                try
                {
                    TextFileProcessorDemo.main(); // then, the modified demo class automatically calls its parent class on the file input.txt
                }
                catch(IOException e2)
                {
                    e2.getMessage();
                }
            }
        });

        clearButton = new JButton("Clear"); // button to clear text
        clearButton.addActionListener(new ActionListener() // add a listener
        {
           @Override
           public void actionPerformed(ActionEvent e)
           {
            textArea.setText(""); // set the text in the text area to nothing
           } 
        });

        // create a button section
        JPanel buttonSection = new JPanel();
        buttonSection.add(saveButton); // add save to the section
        buttonSection.add(clearButton);// add clear to the section


        getContentPane().add(scrollBar, "Center");
        getContentPane().add(buttonSection, "South"); // put the button section on the bottom of the window
    }

    private void textToFile(File outputFile)
    {
        try
        {
            PrintWriter printWriter = new PrintWriter(outputFile);
            String text =  textArea.getText(); // get text that the user wrote
            printWriter.print(text); // print the text to the text file
            printWriter.flush(); // flush input stream

            JOptionPane.showMessageDialog(this, "Text saved!"); // using built in function, inform user that text has been saves successfully
            printWriter.close(); // NO RESOURCE LEAK
        }
        catch (IOException e)
        {
            
        }
    }

    public static void main(String args[])
    {
        SwingUtilities.invokeLater(() -> {
            TextFileProcessorGUI bestGUI = new TextFileProcessorGUI(); // create object of this class
            bestGUI.setVisible(true); //create the window
        });
    }

}

