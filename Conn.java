// import java.io.FileInputStream;
// import java.io.IOException;
// import java.sql.*;
// import java.util.Properties;

// public class Conn {
//     Connection c;
//     Statement s;

//     public Conn() {
//         try {
//             // Class.forName(com.mysql.cj.jdbc.Driver);
//             Properties properties = new Properties();
//             try (FileInputStream fis = new FileInputStream("database.properties")) {
//                 properties.load(fis);

//                 String dbUrl = properties.getProperty("db.url");
//                 String dbName = properties.getProperty("db.name");
//                 String dbUser = properties.getProperty("db.user");
//                 String dbPassword = properties.getProperty("db.password");
//                 c = DriverManager.getConnection(dbUrl + dbName, dbUser, dbPassword);
//                 s = c.createStatement();
//                 System.out.println("Database connection established successfully!");

//             } catch (IOException | SQLException e) {
//                 e.printStackTrace();
//             }

//         } catch (Exception e) {
//             System.out.println(e);
//         }
//     }
// }

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Conn {
    private Connection connection;

    public Conn() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("database.properties")) {
            properties.load(fis);

            String dbUrl = properties.getProperty("db.url");
            String dbName = properties.getProperty("db.name");
            String dbUser = properties.getProperty("db.user");
            String dbPassword = properties.getProperty("db.password");

            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection using properties from the file
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            boolean databaseExists = checkDatabaseExists(connection, dbName);
            if (!databaseExists) {
                initDatabase("init.sql");
                System.out.println("Database and tables created successfully!");
            } else {
                System.out.println("Database already exists!");
                DatabaseMetaData metaData = connection.getMetaData();

                // Check if the required tables exist
                boolean loginTableExists = checkTableExists(metaData, "login");
                boolean signupTableExists = checkTableExists(metaData, "signup");
                boolean signup2TableExists = checkTableExists(metaData, "signup2");
                boolean signup3TableExists = checkTableExists(metaData, "signup3");
                boolean bankTableExists = checkTableExists(metaData, "bank");

                if (loginTableExists && signupTableExists && signup2TableExists
                        && signup3TableExists && bankTableExists) {
                    System.out.println("All required tables exist.");
                    connection.close();
                    // Establish the database connection to the specific database
                    connection = DriverManager.getConnection(dbUrl + dbName, dbUser, dbPassword);

                } else {
                    System.out.println("Some or all required tables do not exist.");
                    initDatabase("init.sql");
                    System.out.println("Database and tables created successfully!");
                }
            }

        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Handle file loading, driver loading, or connection error
        }
    }

    private boolean checkDatabaseExists(Connection connection, String dbName) {
        try {
            // Check if the database exists
            PreparedStatement statement = connection
                    .prepareStatement("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = ?");
            statement.setString(1, dbName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL error
        }
        return false;
    }

    private static boolean checkTableExists(DatabaseMetaData metaData, String tableName) throws SQLException {
        try (ResultSet resultSet = metaData.getTables(null, null, tableName, null)) {
            return resultSet.next();
        }
    }

    private void insertDummyData() {
        try {
            executeBatchedSQL("project/resources/dummy-data.sql", connection, 10);
        } catch (Exception e) {
            e.printStackTrace(); // Handle SQL error
        }
    }

    private void initDatabase(String filePath) {
        try {
            executeBatchedSQL("init.sql", connection, 10);
        } catch (Exception e) {
            e.printStackTrace(); // Handle SQL error
        }
    }

    private static void executeBatchedSQL(String scriptFilePath, Connection connection, int batchSize)
            throws Exception {
        List<String> sqlStatements = parseSQLScript(scriptFilePath);
        executeSQLBatches(connection, sqlStatements, batchSize);
    }

    private static List<String> parseSQLScript(String scriptFilePath) throws IOException {
        List<String> sqlStatements = new ArrayList<>();
        Pattern COMMENT_PATTERN = Pattern.compile("-.*|/\\*(.|[\\r\\n])*?\\/");
        try (BufferedReader reader = new BufferedReader(new FileReader(scriptFilePath))) {
            StringBuilder currentStatement = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher commentMatcher = COMMENT_PATTERN.matcher(line);
                line = commentMatcher.replaceAll("");

                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                currentStatement.append(line).append(" ");

                if (line.endsWith(";")) {
                    sqlStatements.add(currentStatement.toString());
                    // logger.info(currentStatement.toString());
                    currentStatement.setLength(0);
                }
            }
        } catch (IOException e) {
            throw e;
        }
        return sqlStatements;
    }

    private static void executeSQLBatches(Connection connection, List<String> sqlStatements, int batchSize)
            throws SQLException {
        int count = 0;
        Statement statement = connection.createStatement();

        for (String sql : sqlStatements) {
            statement.addBatch(sql);
            count++;

            if (count % batchSize == 0) {
                // logger.info("Executing batch");
                statement.executeBatch();
                statement.clearBatch();
            }
        }
        if (count % batchSize != 0) {
            statement.executeBatch();
        }
        // connection.commit();
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL error
        }
    }

    public Connection getConnection() {
        return connection;
    }

    // Other methods for database operations
    public static void main(String[] args) {
        Conn dbManager = new Conn();
        // dbManager.insertDummyData();
    }
}
