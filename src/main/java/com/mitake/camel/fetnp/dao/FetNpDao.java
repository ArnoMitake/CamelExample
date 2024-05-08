package com.mitake.camel.fetnp.dao;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

public class FetNpDao extends JdbcTemplate {

    public FetNpDao(DataSource dataSource) {
        super(dataSource);
    }

    public int batchInsertReceiverListData(List<String> listData){
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
                        .prepareStatement("INSERT INTO dbo.WaitToUpdateNP (dstno, nc) " +
                                " SELECT dstno, nc FROM ? ");

                sourceDataTable = new SQLServerDataTable();

                sourceDataTable.addColumnMetadata("dstno", java.sql.Types.VARCHAR);
                sourceDataTable.addColumnMetadata("nc", java.sql.Types.TINYINT);

                for (String destNo : listData) {
                    sourceDataTable.addRow(destNo, 88);
                }

                pStmt.setStructured(1, "dbo.WaitToUpdateNPType", sourceDataTable);
                result = pStmt.executeUpdate();

                pStmt.clearParameters();
                sourceDataTable.clear();
            }
        } catch (Exception e) {
            throw new RuntimeException("batchInsertReceiverListData fail", e);
        } finally {
            DataSourceUtils.releaseConnection(conn, ds);
        }

        return result;
    }
}
