package org.com.commands;

import org.com.constants.Constants;
import org.com.models.IntitialInvestments;

public class AllocateCommand {

    public IntitialInvestments processCommand(String line) {
        String []tokens = line.split(Constants.EMPTYSTRING);
        IntitialInvestments initialInvestments = new IntitialInvestments();
        initialInvestments.setEquity(Integer.parseInt(tokens[1]));
        initialInvestments.setDebt(Integer.parseInt(tokens[2]));
        initialInvestments.setGold(Integer.parseInt(tokens[3]));
        return initialInvestments;
    }
}
