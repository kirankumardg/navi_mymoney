package org.com.commands;

import org.com.constants.Constants;
import org.com.models.IntitialInvestments;
import org.com.models.MonthlyChange;
import org.com.models.SipPayments;
import org.com.util.HelperUtil;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class BalanceCommand {

    public static final String EQUITY = "EQUITY";
    public static final String DEBT = "DEBT";
    public static final String GOLD = "GOLD";

    public void processCommand(String line, IntitialInvestments intitalInvestments, SipPayments sipPayments, Map<String, MonthlyChange> monthChangeMap) {
        String[] tokens = line.split(Constants.EMPTYSTRING);
        String month = tokens[1];
        int monthIndex = 0;
        if (HelperUtil.getMonths().containsKey(month)) {
            monthIndex = HelperUtil.getMonths().get(month);
        } else {
            System.out.println("Required values are missing");
            return;
        }

        Map<String, Integer> currentValueMap = getCurrentValueMap(monthIndex, intitalInvestments, sipPayments, monthChangeMap);

        System.out.print(currentValueMap.get(EQUITY));
        System.out.print("\t");
        System.out.print(currentValueMap.get(DEBT));
        System.out.print("\t");
        System.out.print(currentValueMap.get(GOLD));
        System.out.print("\t");
        System.out.println();


    }

    private Map<String, Integer> getCurrentValueMap(int monthIndex, IntitialInvestments intitalInvestments, SipPayments sipPayments, Map<String, MonthlyChange> monthChangeMap) {

        int equityBalance = 0;
        int debtBalance = 0;
        int goldBalance = 0;

        Map<String, Integer> currentValues = new HashMap<String, Integer>();


        for (int i = 1; i <= monthIndex; i++) {
            String tempMonth = null;
            if (HelperUtil.reverseMonths().containsKey(i)) {
                tempMonth = HelperUtil.reverseMonths().get(i);
            } else {
                System.out.println("Required Values not available");
                return null;
            }

            MonthlyChange mp = null;
            if (monthChangeMap.containsKey(tempMonth)) {
                mp = monthChangeMap.get(tempMonth);
            } else {
                System.out.println("Required Values not available");
                return null;
            }


            if (i == 1) {
                equityBalance = (int) (equityBalance + intitalInvestments.getEquity() + (intitalInvestments.getEquity() * mp.getEquityChange()) / 100);
                debtBalance = (int) (debtBalance + intitalInvestments.getDebt() + (intitalInvestments.getDebt() * mp.getDebtChange()) / 100);
                goldBalance = (int) (goldBalance + intitalInvestments.getGold() + (intitalInvestments.getGold() * mp.getGoldChange()) / 100);
            } else {
                int tempEquityVal = equityBalance + sipPayments.getEquity();
                int tempDebtVal = debtBalance + sipPayments.getDebt();
                int tempGoldValue = goldBalance + sipPayments.getGold();
                equityBalance = (int) (tempEquityVal + ((tempEquityVal * mp.getEquityChange()) / 100));
                debtBalance = (int) (tempDebtVal + ((tempDebtVal * mp.getDebtChange()) / 100));
                goldBalance = (int) (tempGoldValue + ((tempGoldValue * mp.getGoldChange()) / 100));
            }


        }
        currentValues.put(EQUITY, equityBalance);
        currentValues.put(DEBT, debtBalance);
        currentValues.put(GOLD, goldBalance);
        return currentValues;

    }


    public void rebalance(String line, IntitialInvestments intitalInvestments, SipPayments sipPayments, Map<String, MonthlyChange> monthChangeMap) {

        int monthIndex = Calendar.getInstance().get(Calendar.MONTH) + 1;
        if (monthIndex == 6 || monthIndex == 12) {

            Map<String, Integer> currentValueMap = getCurrentValueMap(monthIndex, intitalInvestments, sipPayments, monthChangeMap);


            int currentTotal = currentValueMap.get(EQUITY) + currentValueMap.get(DEBT) + currentValueMap.get(GOLD);
            int initialTotal = intitalInvestments.getEquity() + intitalInvestments.getGold() + intitalInvestments.getDebt();
            int balancedEquityAllocation = (int) ((intitalInvestments.getEquity() * currentTotal) / initialTotal);
            int balancedDebtAllocation = (int) ((intitalInvestments.getDebt() * currentTotal) / initialTotal);
            int balancedGoldAllocation = (int) ((intitalInvestments.getGold() * currentTotal) / initialTotal);

            System.out.println();
            System.out.print(balancedEquityAllocation);
            System.out.print("\t");
            System.out.print(balancedDebtAllocation);
            System.out.print("\t");
            System.out.print(balancedGoldAllocation);
            System.out.print("\t");

        } else {
            System.out.println("CAN BE COMPUTED ONLY FOR JUNE and DEC MONTH");
            return;
        }
    }

}
