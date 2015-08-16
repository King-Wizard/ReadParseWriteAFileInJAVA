/*
* @King-Wizard
*/
package com.lambda.readparsewrite;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Starter {

	private static String PATH_TEXT_FILE_TO_READ = "/Users/King-Wizard/Documents/text-files-management/";
	
	private static String FILE_NAME_WITH_EXTENSION_TEXT_FILE_TO_READ = "file.txt";
	
	private static String PATH_TEXT_FILE_TO_CREATE = "/Users/King-Wizard/Documents/text-files-management/";
	
	private static String FILE_NAME_WITH_EXTENSION_TEXT_FILE_TO_CREATE = "file-output.txt";
	
	private static String FLAG = "A|a";
	
	private static String STR_REPLACEMENT_FIRST_LINE = "SP_2021";
	
	private static String STR_REPLACEMENT_OTHER_LINES = System.lineSeparator() + "SP_2021";
	
	public static void main(String[] args) {
		Starter starter = new Starter();
		String strTextFileContent = starter.getStrTextFileContent();
		System.out.println("strTextFileContent equals:\n" + strTextFileContent + "\n");
		starter.createNewTextFile(strTextFileContent);
	}

	private void createNewTextFile(String strTextOutput) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(PATH_TEXT_FILE_TO_CREATE + FILE_NAME_WITH_EXTENSION_TEXT_FILE_TO_CREATE, "UTF-8");
			writer.print(strTextOutput);
			writer.close();
		} catch (FileNotFoundException e) {
			System.err.println("Error: When trying to create the text file to this following path (wrong path provided): "
			           + PATH_TEXT_FILE_TO_CREATE + FILE_NAME_WITH_EXTENSION_TEXT_FILE_TO_CREATE
			           + "!!!");
		} catch (UnsupportedEncodingException e) {
			System.err.println("Error: With the output file's encoding!!!");
		}
	}
	
	private String getStrTextFileContent() {
	    BufferedReader br = null;
	    String strTextFileContent = null;
	    
	    try {
	    	br = new BufferedReader(new FileReader(PATH_TEXT_FILE_TO_READ + FILE_NAME_WITH_EXTENSION_TEXT_FILE_TO_READ));
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        // Counter used to check if we are reading the
	        // first line or another line of the text file.
	        int i = 1;
	        
	        while (line != null) {
	        	if (i == 1) {
	        		line = line.replaceAll(FLAG, STR_REPLACEMENT_FIRST_LINE);
	        	} else {
	        		line = line.replaceAll(FLAG, STR_REPLACEMENT_OTHER_LINES);
	        	}

	        	sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
	        	++i;
	        }
	        
	        strTextFileContent = sb.toString();
	    } catch (FileNotFoundException e) {
			System.err.println("Error: Please provide a valid path to the text file to read (wrong path provided)!!!");
			System.err.println("Wrong text file's path provided: " + PATH_TEXT_FILE_TO_READ + FILE_NAME_WITH_EXTENSION_TEXT_FILE_TO_READ);
		} catch (IOException e) {
			System.err.println("Error: Opening the "
					           + PATH_TEXT_FILE_TO_READ + FILE_NAME_WITH_EXTENSION_TEXT_FILE_TO_READ
					           + "!!!");
		} finally {
	        try {
				br.close();
			} catch (IOException e) {
				System.err.println("Error: After having tried to close the "
						           + PATH_TEXT_FILE_TO_READ + FILE_NAME_WITH_EXTENSION_TEXT_FILE_TO_READ
						           + "!!!");
			}
	    }
	    
	    return strTextFileContent;
	}
	
}
