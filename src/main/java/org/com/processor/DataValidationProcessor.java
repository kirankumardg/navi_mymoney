package org.com.processor;

import org.com.error.FileError;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.com.constants.Constants.*;

public class DataValidationProcessor {


    public FileError checkForValidData(String fileName) throws IOException {
        // Processing the file
        File file = new File(fileName);

        BufferedReader br
                = new BufferedReader(new FileReader(file));

        String st;

        int lineNumber = 0;

        boolean isValid = false;

        FileError er = new FileError();


        while ((st = br.readLine()) != null) {
            lineNumber = lineNumber + 1;
            String line = st.trim();
            String tokens[] = line.split(EMPTYSTRING);

            switch (tokens[0]) {
                case ALLOCATE:
                    if (tokens.length != 4) {
                        er.setErrorString("number of tokens did not match");
                        er.setLineNumber(lineNumber);
                        return er;
                    }
                    break;
                case SIP:
                    if (tokens.length != 4) {
                        er.setErrorString("number of tokens are more or less");
                        er.setLineNumber(lineNumber);
                        return er;
                    }
                    break;
                case CHANGE:
                    if (tokens.length != 5) {
                        er.setErrorString("number of tokens are more or less");
                        er.setLineNumber(lineNumber);
                        return er;
                    }

                    break;
                case BALANCE:
                    if (tokens.length != 2) {
                        er.setErrorString("number of tokens are more or less");
                        er.setLineNumber(lineNumber);
                        return er;
                    }
                    break;
                case REBALANCE:
                    if (tokens.length != 1) {
                        er.setErrorString("number of tokens are more or less");
                        er.setLineNumber(lineNumber);
                        return er;
                    }
                    break;
                default:
                    er.setErrorString("Line is invalid as no command matched");
                    er.setLineNumber(lineNumber);
                    break;

            }


        }
        return er;
    }

}
