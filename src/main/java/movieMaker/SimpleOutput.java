package movieMaker;

import javax.swing.*;
import java.text.*;

/**
 * Class to make it easy to do output to the user
 * using JOptionPane
 * 
 * Copyright Georgia Institute of Technology 2004
 * @author Barb Ericson ericson@cc.gatech.edu
 */
public class SimpleOutput
{
  
  /**
   * Method to show a warning to a user
   * @param message the message to display
   */
  public static void showWarning(String message)
  {
    message = addNewLines(message);
    JOptionPane.showMessageDialog(null,message,Messages.getString("SimpleOutput.0"), //$NON-NLS-1$
                                  JOptionPane.WARNING_MESSAGE);
  }
  
  /**
   * Method to show an error to a user
   * @param message the message to display
   */
  public static void showError(String message)
  {
    message = addNewLines(message);
    JOptionPane.showMessageDialog(null,message,Messages.getString("SimpleOutput.1"), //$NON-NLS-1$
                                  JOptionPane.ERROR_MESSAGE);
  }
  
  /**
   * Method to show information to the user
   * @param message the message to display
   */
  public static void showInformation(String message)
  {
    message = addNewLines(message);
    JOptionPane.showMessageDialog(null, message, Messages.getString("SimpleOutput.2"), //$NON-NLS-1$
                                  JOptionPane.INFORMATION_MESSAGE);
  }
  
  /**
   * Method to add new line character if the message 
   * is too long
   * @param message the input message
   * @return the message with new lines added if needed
   */
  public static String addNewLines(String message)
  {
    BreakIterator boundary = 
      BreakIterator.getLineInstance();
    boundary.setText(message);
    int start = boundary.first();
    String result = ""; //$NON-NLS-1$
    String currLine = ""; //$NON-NLS-1$
    String temp = null;
    
    // loop till no more possible line breaks
    for (int end = boundary.next();
          end != BreakIterator.DONE;
          start = end, end = boundary.next()) 
    {
      // get string between start and end
      temp = message.substring(start,end);
      
      /* if adding that to the current line 
       * would make it too long then add current
       * to result followed by a newline and 
       * reset current 
       */
      if (temp.length() + currLine.length() > 100)
      {
        result = result + currLine + "\n"; //$NON-NLS-1$
        currLine = temp;
      }
      // else add the segment to the current line
      else 
        currLine = currLine + temp;
    }
 
    // if no line breaks use the original message
    if (result.length() == 0)
      result = message;
    // else add any leftover parts
    else
      result = result + currLine;
    
    return result;
  }
  
} // end of SimpleOutput class