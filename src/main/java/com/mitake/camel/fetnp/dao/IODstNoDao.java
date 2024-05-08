package com.mitake.camel.fetnp.dao;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;

import java.sql.Connection;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class IODstNoDao extends JdbcTemplate {
    public IODstNoDao(DataSource dataSource) {
        super(dataSource);
    }

    public int batchInsertDstNo(Set<String> listData) {
        int result = -1;
        DataSource ds = null;
        Connection conn = null;
        Connection nativeCon = null;
        SQLServerDataTable sourceDataTable = null;
        SQLServerPreparedStatement pStmt = null;
        try {
            ds = this.getDataSource();
            conn = DataSourceUtils.doGetConnection(this.getDataSource());
            nativeCon = conn.unwrap(SQLServerConnection.class);

            if (listData != null && !listData.isEmpty()) {
                pStmt = (SQLServerPreparedStatement) nativeCon
                        .prepareStatement("INSERT INTO dbo.CDRDeliveryDestNoList_New (DestNo) " +
                                " SELECT DestNo FROM ? ");

                sourceDataTable = new SQLServerDataTable();

                sourceDataTable.addColumnMetadata("DestNo", java.sql.Types.CHAR);

                for (String destNo : listData) {
                    sourceDataTable.addRow(destNo.replaceAll("\r", ""));
                }

                pStmt.setStructured(1, "dbo.CDRDeliveryDestNoListType", sourceDataTable);
                result = pStmt.executeUpdate();

                pStmt.clearParameters();
                sourceDataTable.clear();
            }
        } catch (Exception e) {
            throw new RuntimeException("batchInsertDstNo fail", e);
        } finally {
            DataSourceUtils.releaseConnection(conn, ds);
        }

        return result;

    }

    public Object outPutDstNo(String fileName) {
        StringBuilder sql = new StringBuilder(250);

        sql.append(" SELECT DestNo FROM dbo.CDRDeliveryDestNoList_New with(nolock) ");

        return this.query(sql.toString(),
                new ExportSMCDRResultSetExtractor(fileName));

    }

}
