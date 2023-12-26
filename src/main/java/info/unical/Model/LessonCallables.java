package info.unical.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.Callable;

import static info.unical.Model.DataBaseManager.getConnection;

class RetrieveEnglishLessonsCallable implements Callable<Integer> {
    public RetrieveEnglishLessonsCallable() {
    }

    @Override
    public Integer call() throws Exception
    {
        String query = "SELECT COUNT(*) FROM lezioniInglese";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            } finally {
                DataBaseManager.closeConnection();
            }
        }
        return -1;
    }
}

class RetrieveFrenchLessonsCallable implements Callable<Integer> {
    public RetrieveFrenchLessonsCallable() {
    }

    @Override
    public Integer call() throws Exception
    {
        String query = "SELECT COUNT(*) FROM lezioniFrancese";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            } finally {
                DataBaseManager.closeConnection();
            }
        }
        return -1;
    }
}

class RetrievePortugueseLessonsCallable implements Callable<Integer> {
    public RetrievePortugueseLessonsCallable() {
    }

    @Override
    public Integer call() throws Exception
    {
        String query = "SELECT COUNT(*) FROM lezioniPortoghese";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            } finally {
                DataBaseManager.closeConnection();
            }
        }
        return -1;
    }
}

class RetrieveSpanishLessonsCallable implements Callable<Integer> {
    public RetrieveSpanishLessonsCallable() {
    }

    @Override
    public Integer call() throws Exception
    {
        String query = "SELECT COUNT(*) FROM lezioniSpagnolo";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            } finally {
                DataBaseManager.closeConnection();
            }
        }
        return -1;
    }
}
