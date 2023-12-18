package info.unical.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.Callable;

import static info.unical.Model.DataBaseManager.getConnection;

class RetrieveTestsEnglishCallable implements Callable<Integer> {

    public RetrieveTestsEnglishCallable() {}

    @Override
    public Integer call() throws Exception {
        String query = "SELECT COUNT(*) FROM testInglese";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
            finally {
                DataBaseManager.closeConnection();
            }
        }
        return -1;
    }
}

class RetrieveTestsFrenchCallable implements Callable<Integer> {

    public RetrieveTestsFrenchCallable() {}

    @Override
    public Integer call() throws Exception {
        String query = "SELECT COUNT(*) FROM testFrancese";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
            finally {
                DataBaseManager.closeConnection();
            }
        }
        return -1;
    }
}

class RetrieveTestsSpanishCallable implements Callable<Integer> {

    public RetrieveTestsSpanishCallable() {}

    @Override
    public Integer call() throws Exception {
        String query = "SELECT COUNT(*) FROM testSpagnolo";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
            finally {
                DataBaseManager.closeConnection();
            }
        }
        return -1;
    }
}

class RetrieveTestsPortugueseCallable implements Callable<Integer> {

    public RetrieveTestsPortugueseCallable() {}

    @Override
    public Integer call() throws Exception {
        String query = "SELECT COUNT(*) FROM testPortoghese";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
            finally {
                DataBaseManager.closeConnection();
            }
        }
        return -1;
    }
}