package com.fragma.dao;

import com.fragma.config.ConfigurationHelper;
import com.fragma.dto.MainDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.*;

@Repository
public class ReportDao {

    static Logger LOG = LoggerFactory.getLogger(ReportDao.class);

    private final JdbcTemplate jdbcTemplate;
    private final ConfigurationHelper configurationHelper;
    int SLNo=0;

    @Autowired
    public ReportDao(@Qualifier("hiveJdbcTemplate") JdbcTemplate jdbcTemplate, ConfigurationHelper configurationHelper) {
        this.jdbcTemplate = jdbcTemplate;
        this.configurationHelper = configurationHelper;

    }

    public void getData(MainDto mainDto){
        LOG.info("***** executing getData *****");
        jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                LOG.info("Query = "+ ConfigurationHelper.getQuery() );
                PreparedStatement ps = connection.prepareStatement(ConfigurationHelper.getQuery());

                return ps;
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

                String accountNumber = isNullOrEmpty(resultSet.getString("account_number"));
                String branchCode = isNullOrEmpty(resultSet.getString("branch_code"));
                String customerId = isNullOrEmpty(resultSet.getString("customer_id"));
                String productCode = isNullOrEmpty(resultSet.getString("product_code"));
                String productCatagory = isNullOrEmpty(resultSet.getString("product_category"));
                String valueDate = isNullOrEmpty(resultSet.getString("value_date"));
                String maturityDate = isNullOrEmpty(resultSet.getString("maturity_date"));
                String amountFinanced = isNullOrEmpty(resultSet.getString("amount_financed"));
                String currency = isNullOrEmpty(resultSet.getString("currency"));
                String primaryApplicantName = isNullOrEmpty(resultSet.getString("primary_applicant_name"));
                String liquidationMode = isNullOrEmpty(resultSet.getString("liquidation_mode"));
                String accountStatus = isNullOrEmpty(resultSet.getString("account_status"));
                String altAccNo = isNullOrEmpty(resultSet.getString("alt_acc_no"));
                String partialLiquidation = isNullOrEmpty(resultSet.getString("partial_liquidation"));
                String fieldChar1 = isNullOrEmpty(resultSet.getString("field_char_1"));
                String fieldChar2 = isNullOrEmpty(resultSet.getString("field_char_2"));
                String fieldChar3 = isNullOrEmpty(resultSet.getString("field_char_3"));
                String billRefNo = isNullOrEmpty(resultSet.getString("bill_ref_no"));
                String amtAvailable = isNullOrEmpty(resultSet.getString("amt_available"));
                String figRefNo = isNullOrEmpty(resultSet.getString("fig_ref_no"));
                String lcNumber = isNullOrEmpty(resultSet.getString("lc_number"));





               LOG.info("accountNumber:"+accountNumber+"branchCode:"+branchCode+"customerId:"+customerId+"productCode:"+productCode+"productCatagory:"+productCatagory+"valueDate:"+valueDate+"maturityDate"+maturityDate+"amountFinanced"+amountFinanced+"currency"+currency+"primaryApplicantName"+primaryApplicantName+"liquidationMode"+liquidationMode+"accountStatus"+accountStatus+"altAccNo"+altAccNo+"partialLiquidation"+partialLiquidation+"fieldChar1"+fieldChar1+"fieldChar2"+fieldChar2+"fieldChar3"+fieldChar3+"billRefNo"+billRefNo+"amtAvailable"+amtAvailable+"figRefNo"+figRefNo+"lcNumber"+lcNumber);



                mainDto.populateData(++SLNo,accountNumber,branchCode,customerId,productCode,productCatagory,valueDate,maturityDate,amountFinanced,currency,primaryApplicantName,liquidationMode,accountStatus,altAccNo,partialLiquidation,fieldChar1,fieldChar2,fieldChar3,billRefNo,amtAvailable,figRefNo,lcNumber);
            }
        });
    }

    public String isNullOrEmpty(String value) {
        if (value == null || value.isEmpty() || value.equalsIgnoreCase("\"\"")) {
            return " ";
        } else
            return value;
    }

}
