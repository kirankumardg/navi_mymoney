package org.com.commands;

import org.com.constants.Constants;
import org.com.models.IntitialInvestments;
import org.com.models.MonthlyChange;
import org.com.models.SipPayments;
import org.com.util.HelperUtil;

import java.util.Calendar;
import java.util.Map;


public class BalanceCommand {

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
        int equityBalance = 0;
        int debtBalance = 0;
        int goldBalance = 0;


        for (int i = 1; i <= monthIndex; i++) {
            String tempMonth = null;
            if (HelperUtil.reverseMonths().containsKey(i)) {
                tempMonth = HelperUtil.reverseMonths().get(i);
            } else {
                System.out.println("Required Values not available");
                return;
            }

            MonthlyChange mp = null;
            if (monthChangeMap.containsKey(tempMonth)) {
                mp = monthChangeMap.get(tempMonth);
            } else {
                System.out.println("Required Values not available");
                return;
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
        System.out.print(equityBalance);
        System.out.print("\t");
        System.out.print(debtBalance);
        System.out.print("\t");
        System.out.print(goldBalance);
        System.out.print("\t");
        System.out.println();


    }

    public void rebalance(String line, IntitialInvestments intitalInvestments, SipPayments sipPayments, Map<String, MonthlyChange> monthChangeMap) {

        int monthIndex = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int equityBalance = 0;
        int debtBalance = 0;
        int goldBalance = 0;


        for (int i = 1; i <= monthIndex; i++) {
            String tempMonth = null;
            if (HelperUtil.reverseMonths().containsKey(i)) {
                tempMonth = HelperUtil.reverseMonths().get(i);
            } else {
                System.out.println("CANNOT_REBALANCE");
                return;
            }

            MonthlyChange mp = null;
            if (monthChangeMap.containsKey(tempMonth)) {
                mp = monthChangeMap.get(tempMonth);
            } else {
                System.out.println("CANNOT_REBALANCE");
                return;
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

        int currentTotal = equityBalance + debtBalance + goldBalance;
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

    }

}
