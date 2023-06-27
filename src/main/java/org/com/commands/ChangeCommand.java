package org.com.commands;

import org.com.constants.Constants;
import org.com.models.MonthlyChange;

public class ChangeCommand {
    public MonthlyChange processCommand(String line) {
        String[] tokens = line.split(Constants.EMPTYSTRING);
        MonthlyChange monthlyChange = new MonthlyChange();
        monthlyChange.setEquityChange(getDoubleValyeFromPercentage(tokens[1]));
        monthlyChange.setDebtChange(getDoubleValyeFromPercentage(tokens[2]));
        monthlyChange.setGoldChange(getDoubleValyeFromPercentage(tokens[3]));
        monthlyChange.setMonth(tokens[4]);
        return monthlyChange;
    }

    private Double getDoubleValyeFromPercentage(String token) {
        String removedPercentage = token.substring(0, token.length() - 1);
        return Double.parseDouble(removedPercentage);
    }
}
