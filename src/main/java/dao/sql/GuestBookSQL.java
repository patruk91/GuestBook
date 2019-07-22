package dao.sql;

import dao.IConnectionPool;
import dao.IGuestBookDao;
import model.GuestForm;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GuestBookSQL implements IGuestBookDao {
    private IConnectionPool connectionPool;

    public GuestBookSQL(IConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void insertRecord(String name, String message) {
        try {
            Connection connection = connectionPool.getConnection();
            addRecordToDataBase(name, message, connection);
        } catch (SQLException e) {
        System.err.println("SQLException: " + e.getMessage()
                + "\nSQLState: " + e.getSQLState()
                + "\nVendorError: " + e.getErrorCode());
        }
    }

    private void addRecordToDataBase(String name, String message, Connection connection) throws SQLException {
        LocalDateTime localDate =  LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(localDate);
        try(PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO records(name, message, date) VALUES(?, ?, ?)")) {
            stmt.setString(1, name);
            stmt.setString(2, message);
            stmt.setTimestamp(3, timestamp);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<GuestForm> getAllRecords() {
        List<GuestForm> guestsForms = new ArrayList<>();
        try {
            Connection connection = connectionPool.getConnection();
            addRecords(connection, guestsForms);
            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorError: " + e.getErrorCode());
        }
        return guestsForms;
    }

    private void addRecords(Connection connection, List<GuestForm> guestsForms) throws SQLException {
        try(PreparedStatement stmt = connection.prepareStatement(
                "SELECT * FROM records ORDER BY id DESC")) {
            addRecordsToList(stmt, guestsForms);
        }
    }

    private void addRecordsToList(PreparedStatement stmt, List<GuestForm> guestsForms) throws SQLException {
        try(ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String message = resultSet.getString("message");
                Timestamp date = resultSet.getTimestamp("date");
                GuestForm guestForm = new GuestForm(name, message, date);
                guestsForms.add(guestForm);
            }
        }
    }
}
