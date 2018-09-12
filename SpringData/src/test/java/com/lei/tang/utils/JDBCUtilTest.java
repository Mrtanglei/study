package com.lei.tang.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * @author tanglei
 * @date 18/9/12
 */
public class JDBCUtilTest {

    @Test
    public void getConnection() throws IOException, SQLException, ClassNotFoundException {
        Connection connection = JDBCUtil.getConnection();
        Assert.assertNotNull(connection);
    }
}