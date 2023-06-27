package org.com.commands;

import org.com.constants.Constants;
import org.com.models.SipPayments;

public class SipCommand {
    public SipPayments processCommand(String line) {
        String []tokens = line.split(Constants.EMPTYSTRING);
        SipPayments sipPayments = new SipPayments();
        sipPayments.setEquity(Integer.parseInt(tokens[1]));
        sipPayments.setDebt(Integer.parseInt(tokens[2]));
        sipPayments.setGold(Integer.parseInt(tokens[3]));
        return sipPayments;
    }
}
