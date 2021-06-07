package com.fragma.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

public class MainDto {

    static Logger LOG = LoggerFactory.getLogger(MainDto.class);

    public Map<Integer, Outstanding_Loan> map = new HashMap<>();
    public Map<String, Outstanding_Loan> summaryMap = new HashMap<>();

    public Set<String> maptoset = new LinkedHashSet<>();

    public LocalDate todayDate;

    public LocalDate getTodayDate() {
        return todayDate;
    }


    public void setTodayDate(LocalDate todayDate) {
        this.todayDate = todayDate;
    }

    public Map<Integer, Outstanding_Loan> getMap() {
        return map;
    }

    public void addAccountNumberToSet(String accountNumber) {
        System.out.println("AccountNumber:"+accountNumber);
        maptoset.add(accountNumber);
    }

    public Set<String> getMaptoset() {
        return maptoset;
    }

    public Outstanding_Loan getPayCheque(String accountNumber) {
        if (summaryMap.get(accountNumber) != null) {
            return summaryMap.get(accountNumber);
        }
        else

            return new Outstanding_Loan();
    }


    public String doubleToString(double amount)
    {
        if(Double.compare(amount,0.0D)==0)
        {
            return "0.00";
        }
        else {
            DecimalFormat df = new DecimalFormat("#,###.00"); // or pattern "###,###.##$"
            return df.format(amount);
        }
    }


    public String isNullOrEmpty(String  value)
    {
        if(value == null || value.isEmpty() || value.equals("\"\""))
        {
            return "-";
        }
        else
            return  value;
    }



    public void populateData(int SLNo, String accountNumber, String branchCode, String customerId, String productCode, String productCatagory, String valueDate, String maturityDate, String amountFinanced, String currency, String primaryApplicantName, String liquidationMode, String accountStatus, String altAccNo, String partialLiquidation, String fieldChar1, String fieldChar2, String fieldChar3, String billRefNo, String amtAvailable, String figRefNo, String lcNumber) {

        Outstanding_Loan outstandingLoan = map.get(SLNo);

        if (outstandingLoan == null) {
            outstandingLoan = new Outstanding_Loan();
        }

       outstandingLoan.setAccountNumber(accountNumber);
        outstandingLoan.setBranchCode(branchCode);
        outstandingLoan.setCustomerId(customerId);
        outstandingLoan.setProductCode(productCode);
        outstandingLoan.setProductCatagory(productCatagory);
        outstandingLoan.setValueDate(valueDate);
        outstandingLoan.setMaturityDate(maturityDate);
        outstandingLoan.setAmountFinanced(amountFinanced);
        outstandingLoan.setCurrency(currency);
        outstandingLoan.setPrimaryApplicantName(primaryApplicantName);
        outstandingLoan.setLiquidationMode(liquidationMode);
        outstandingLoan.setAccountStatus(accountStatus);
        outstandingLoan.setAltAccNo(altAccNo);
        outstandingLoan.setPartialLiquidation(partialLiquidation);
        outstandingLoan.setFieldChar1(fieldChar1);
        outstandingLoan.setFieldChar2(fieldChar2);
        outstandingLoan.setFieldChar3(fieldChar3);
        outstandingLoan.setBillRefNo(billRefNo);
        outstandingLoan.setAmtAvailable(amtAvailable);
        outstandingLoan.setFigRefNo(figRefNo);
        outstandingLoan.setLcNumber(lcNumber);

        map.put(SLNo, outstandingLoan);

    }



    public String intToString(int count)
    {
        if(count==0)
        {
            return "0";
        }
        else {
            return String.valueOf(count);

        }
    }

    int sr = 1;


    public int serialNum() {
        return sr++;
    }



}
