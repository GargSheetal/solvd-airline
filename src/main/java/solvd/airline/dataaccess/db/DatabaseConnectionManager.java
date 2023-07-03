package solvd.airline.dataaccess.db;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author sheetal
 *
 */

public class DatabaseConnectionManager {
	private BasicDataSource dataSource;
	private String url;
    private String username;
    private String password;
 
    public DatabaseConnectionManager() throws IOException {
		this.loadProperties();
        this.setDataSource();
    }
    
    private void loadProperties() throws IOException {
    	try(InputStream input = new FileInputStream("./src/main/resources/db.properties")){
	        Properties prop = new Properties();
	        prop.load(input);
	        this.url = prop.getProperty("url");
	        this.username = prop.getProperty("username");
	        this.password = prop.getProperty("password");
    	}
    }
    
    private void setDataSource() {
    	BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        dataSource.setMinIdle(20);
        dataSource.setMaxIdle(50);
        dataSource.setMaxOpenPreparedStatements(100);
        dataSource.setMaxWaitMillis(20000); 
        this.dataSource = dataSource;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
    
	public void releaseConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.setAutoCommit(true); // Reset auto-commit to true
            connection.close(); // Close the connection
        }
    }
	
	public void closeConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
	
	public void beginTransaction(Connection connection) throws SQLException {
		if (connection != null) {
            connection.setAutoCommit(false);
        }
    }

    public void commitTransaction(Connection connection) throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
    }

    public void rollbackTransaction(Connection connection) throws SQLException {
        if (connection != null) {
        	connection.rollback();
        	connection.setAutoCommit(true);
        }
    }
}