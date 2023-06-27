package org.com.processor;

import org.com.constants.Constants;
import org.com.commands.*;
import org.com.models.IntitialInvestments;
import org.com.models.MonthlyChange;
import org.com.models.SipPayments;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.com.constants.Constants.*;


public class FileProcessor {

    IntitialInvestments intitalInvestments;
    SipPayments sipPayments;
    Map<String, MonthlyChange> monthChangeMap = new HashMap<String, MonthlyChange>();

    public void processData(String fileName) throws IOException {

        File file = new File(fileName);
        BufferedReader br
                = new BufferedReader(new FileReader(file));
        String st;
        int lineNumber = 0;
        while ((st = br.readLine()) != null) {
            String line = st.trim();
            String tokens[] = line.split(Constants.EMPTYSTRING);
            switch (tokens[0]) {
                case ALLOCATE:
                    AllocateCommand command = new AllocateCommand();
                    intitalInvestments = command.processCommand(line);
                    break;
                case SIP:
                    SipCommand sipCommand = new SipCommand();
                    sipPayments = sipCommand.processCommand(line);
                   break;
                case CHANGE:
                    ChangeCommand changeCommand = new ChangeCommand();
                    MonthlyChange mc = changeCommand.processCommand(line);
                    monthChangeMap.put(mc.getMonth(), mc);
                    break;
                case BALANCE:
                    BalanceCommand balanceCommand = new BalanceCommand();
                    balanceCommand.processCommand(line, intitalInvestments, sipPayments, monthChangeMap);
                    break;
                case REBALANCE:
                    BalanceCommand balanceCommand1 = new BalanceCommand();
                    balanceCommand1.rebalance(line, intitalInvestments, sipPayments, monthChangeMap);
            }
        }
    }
}






