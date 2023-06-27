package org.com;


import org.com.error.FileError;
import org.com.processor.DataValidationProcessor;
import org.com.processor.FileProcessor;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class MainApp {

    public static void main(String[] args) throws IOException {

        //check if the input parameter is provided or not
        if (args.length < 1) {
            System.out.println("Input file is not available. Please provide file name as input parameter");
            return;
        }

        // Check if the path provided is valid or not.
       
        String fileName = args[0];
      
        try {
            Paths.get(fileName);
        } catch (InvalidPathException | NullPointerException ex) {
            System.out.println("Provided input filepath is invalid");
            return;
        }


        DataValidationProcessor dataValidationProcesor = new DataValidationProcessor();
        FileError er = dataValidationProcesor.checkForValidData(fileName);

        if (er.getLineNumber() != 0) {
            System.out.println("The input line is invalid " + er.getLineNumber());
            System.out.println("The error is " + er.getErrorString());
            return;
        }

        FileProcessor fileProcessor = new FileProcessor();
        fileProcessor.processData(fileName);
    }


}

