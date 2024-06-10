package com.hmc.his;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HisApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }

    @Test
    void getConnection() throws SQLException {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            assertFalse(conn.isClosed());
        } catch (SQLException e) {
            throw e;
        } finally {
            conn.close();
        }
    }
}
