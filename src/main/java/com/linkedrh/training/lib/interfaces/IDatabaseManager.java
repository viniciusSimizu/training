package com.linkedrh.training.lib.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabaseManager {

    public Connection getConnection() throws SQLException;
}
