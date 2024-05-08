package com.mitake.camel.fetnp.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.sql.DataSource;

public class ExportSMCDR412Dao extends JdbcTemplate {

    private static final DateTimeFormatter _yyMMddHHmmss = DateTimeFormatter.ofPattern("yyMMddHHmmss");
    private static final DateTimeFormatter _yyyyMMddHHmmss = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");


    public ExportSMCDR412Dao(DataSource dataSource) {
        super(dataSource);
    }

    public Object export412ChtSMCDRToFile(String fileName) {
        StringBuilder sql = new StringBuilder(100);

        sql.append(" SELECT DstNo FROM dbo.SMCDR with(nolock) ");
        sql.append(" WHERE SrcStamp >= ? AND SrcStamp < ? ");
        sql.append(" AND LEN(DstNo)=12 ");
        sql.append(" AND LEFT(DstNo,3) = '886' ");
        sql.append(" AND Cause='100C' ");

        return this.query(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(sql.toString());
            }
        }, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setTimestamp(1, Timestamp.valueOf(LocalDate.now().minusDays(1).atStartOfDay()));
                ps.setTimestamp(2, Timestamp.valueOf(LocalDate.now().atStartOfDay()));
            }
        }, new ExportSMCDRResultSetExtractor(fileName));

    }

    public Object export412FetSMCDRToFile(String fileName) {
        StringBuilder sql = new StringBuilder(100);

        sql.append(" SELECT DstNo FROM dbo.SMCDRFet with(nolock) ");
        sql.append(" WHERE SubmitDate >= ? AND SubmitDate < ? ");
        sql.append(" AND ( (LEN(DstNo)=10 AND LEFT(DstNo,2)='09') ");
        sql.append(" OR (LEN(DstNo) = 12 AND LEFT(DstNo , 3) = '886') ) ");
        sql.append(" AND DeliveryStatus = 'delivered' ");

        return this.query(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(sql.toString());
            }
        }, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, _yyMMddHHmmss.format(LocalDate.now().minusDays(1).atStartOfDay()));
                ps.setString(2, _yyMMddHHmmss.format(LocalDate.now().atStartOfDay()));
            }
        }, new ExportSMCDRResultSetExtractor(fileName));

    }

    public Object export412TWMSMCDRToFile(String fileName) {
        StringBuilder sql = new StringBuilder(100);

        sql.append(" SELECT dstaddr FROM dbo.SMCDRTWM with(nolock) ");
        sql.append(" WHERE donetime >= ? AND donetime < ? ");
        sql.append(" AND ( (len(dstaddr)=10 AND left(dstaddr,2)='09') ");
        sql.append(" OR (len(dstaddr)=12 AND left(dstaddr,4)='8869') ) ");
        sql.append(" AND statuscode='0' ");

        return this.query(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(sql.toString());
            }
        }, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, _yyyyMMddHHmmss.format(LocalDate.now().minusDays(1).atStartOfDay()));
                ps.setString(2, _yyyyMMddHHmmss.format(LocalDate.now().atStartOfDay()));
            }
        }, new ExportSMCDRResultSetExtractor(fileName));

    }
}
