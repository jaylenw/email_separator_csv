package email_separator;

import java.awt.HeadlessException;//importing the necessary tools to catch the HeadlessException
import java.io.File;//importing for ability to take in file to be read by scanner
import java.io.FileNotFoundException;//importing for the ability to catch FileNotFoundException
import javax.swing.JFileChooser; //importing for ability to select files from a gui navigator
import javax.swing.filechooser.FileNameExtensionFilter; //import ability to have only certain files shown with a certain extension
import java.util.Scanner;//importing scanner utility
import java.io.PrintWriter; //importing Printwriter class


public class main {

	public static void main(String[] args) {

		// TODO Auto-generated method stub


				JFileChooser file_chooser = new JFileChooser(); //creating new instance for JFileChooser

				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt files only", "txt"); //creating new instance for the filter
				/*
				 * we don't need to check for exception of FileNameExtensionFilter as
				 * it's throw is..IllegalArgumentException - if extensions is null, empty, contains null, or contains an empty string
				 * But our parameters are hard coded.  If it required user input then we would catch the errors
				 */

				//filter will default only showing files with ".text"
				file_chooser.setFileFilter(filter);//setting the extension to JFileChooser
				
				try{//for showOpenDialog error handling
					int returnVal = file_chooser.showOpenDialog(null); //opening window has no parent...passed in null because of that.
					//above, showOpenDialog can throw...HeadlessException - if GraphicsEnvironment.isHeadless() returns true. returnVal stores
					//that value
					
					/*
					 * APPROVE_OPTION returns value if approve(yes, ok) is chosen, if not we don't continue to open a file
					 */
					if (returnVal == JFileChooser.APPROVE_OPTION){ //if return value equals the return value of JFileChooser.APPROVE_OPTION the process can continue to open the file.

						File selected_file = file_chooser.getSelectedFile(); //getting selected file from filechooser and assigning it to File object
						//no need to throw File's exeption..NullPointerException - If the pathname argument is null
						//because in the if statement resulted in no file it would end the program by skipping this logic. 

						System.out.println("You chose to open this file: " + selected_file.getName()); //printing out the name of selected file
						
						try{//for Scanner input of a file to create a new instance and for its error handling
							
							Scanner emails_in = new Scanner(selected_file); //creating instance of file we imported with scanner so that we can read it
							
							PrintWriter emails_out = new PrintWriter("emails_out.txt");
							
							while(emails_in.hasNext()){
								emails_out.println(emails_in.next() + ",");
							}
							
							System.out.println("Output is in file of 'emails_out.txt in programs directory.");
						emails_in.close();
						emails_out.close();
						}
						catch(FileNotFoundException exception){//below print statements if Scanner throws error
							System.out.println("File can not be found.  Was it renamed, deleted, or permissions changed?");
						}	
					}//marker..end of upper if statement
				}
				catch(HeadlessException exception){//below print statements if JFileChooser throws error
					System.out.println("Program ended as your environment is missing a keyboard, display, and/or mouse.");
					
					/*
					 * Thrown when code that is dependent on a keyboard, display, or mouse is called in an 
					 * environment that does not support a keyboard, display, or mouse. 
					 */
				}
		
		
	}

}
