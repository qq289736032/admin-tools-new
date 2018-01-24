package com.mokylin.cabal.common.persistence;

import com.mokylin.cabal.common.utils.ResourcesUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlProvider;
import org.springframework.jdbc.core.StatementCallback;

import javax.sql.DataSource;
import java.io.*;
import java.nio.charset.Charset;
import java.sql.*;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/6/25
 * admin-tools
 */
public class MyJdbcTemplate extends JdbcTemplate {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public MyJdbcTemplate(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    public int[] batchUpdate(final String[] sql) throws DataAccessException {
        if (ArrayUtils.isEmpty(sql)) {
            throw new NullPointerException("SQL array must not be empty");
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Executing SQL batch update of {" + sql.length + "} statements");
        }
        //
        class BatchUpdateStatementCallback implements StatementCallback<int[]>, SqlProvider {
            private String currSql;

            @Override
            public int[] doInStatement(final Statement stmt) throws SQLException {
                DatabaseMetaData dbmd = stmt.getConnection().getMetaData();
                int[] rowsAffected = new int[sql.length];
                if (dbmd.supportsBatchUpdates()) {
                    /*连接支持批处理*/
                    for (String sqlStmt : sql) {
                        this.currSql = sqlStmt;
                        stmt.addBatch(sqlStmt);
                    }
                    rowsAffected = stmt.executeBatch();
                } else
                    /*连接不支持批处理*/
                    for (int i = 0; i < sql.length; i++) {
                        this.currSql = sql[i];
                        if (!stmt.execute(sql[i])) {
                            rowsAffected[i] = stmt.getUpdateCount();
                        } else {
                            throw new SQLException("Invalid batch SQL statement: " + sql[i]);
                        }
                    }
                return rowsAffected;
            }

            @Override
            public String getSql() {
                return this.currSql;
            }
        }
        return this.execute(new BatchUpdateStatementCallback());
    }


    /**
     * 判断表是否已经存在
     */
    public boolean tableExist(final String name) throws SQLException {
        return this.execute(new ConnectionCallback<Boolean>() {
            @Override
            public Boolean doInConnection(final Connection con) throws SQLException {
                DatabaseMetaData metaData = con.getMetaData();
                ResultSet rs = metaData.getTables(null, null, name.toUpperCase(), new String[]{"TABLE"});
                return rs.next();
            }
        });
    }

    public void loadSQL(final String sqlResource) throws IOException, SQLException {
        this.loadSQL("UTF-8", sqlResource);
    }

    public void loadSQL(final String charsetName, final String sqlResource) throws IOException, SQLException {
        InputStream inStream = ResourcesUtils.getResourceAsStream(sqlResource);
        if (inStream == null) {
            throw new IOException("can't find :" + sqlResource);
        }
        InputStreamReader reader = new InputStreamReader(inStream, Charset.forName(charsetName));
        this.loadSQL(reader);
    }

    public void loadSQL(final Reader sqlReader) throws IOException, SQLException {
        StringWriter outWriter = new StringWriter();
        IOUtils.copy(sqlReader, outWriter);
        this.execute(outWriter.toString());
    }
}
