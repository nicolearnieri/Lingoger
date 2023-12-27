package info.unical.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
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


class GetEnglishLessonInfo implements Callable<Vector<String>> {
    private int cod ;

    public GetEnglishLessonInfo(int cod )
    { this.cod = cod; }

    @Override
    public Vector<String> call() throws Exception
    {
        Vector<String> lessonInfos = new Vector<String>();
        String query = "SELECT nome, descrizione, contenuto FROM lezioniInglese WHERE codice = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, cod);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    lessonInfos.add(resultSet.getString("nome"));
                    lessonInfos.add(resultSet.getString("descrizione"));
                    lessonInfos.add(resultSet.getString("contenuto"));
                    return lessonInfos;
                }
            } finally {
                DataBaseManager.closeConnection();
            }
        }
        return null;
    }
}


class GetFrenchLessonInfo implements Callable<Vector<String>> {
    private int cod ;

    public GetFrenchLessonInfo(int cod )
    { this.cod = cod; }

    @Override
    public Vector<String> call() throws Exception
    {
        Vector<String> lessonInfos = new Vector<String>();
        String query = "SELECT nome, descrizione, contenuto FROM lezioniFrancese WHERE codice = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, cod);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    lessonInfos.add(resultSet.getString("nome"));
                    lessonInfos.add(resultSet.getString("descrizione"));
                    lessonInfos.add(resultSet.getString("contenuto"));
                    return lessonInfos;
                }
            } finally {
                DataBaseManager.closeConnection();
            }
        }
        return null;
    }
}


class GetPortugueseLessonInfo implements Callable<Vector<String>> {
    private int cod ;

    public GetPortugueseLessonInfo(int cod )
    { this.cod = cod; }

    @Override
    public Vector<String> call() throws Exception
    {
        Vector<String> lessonInfos = new Vector<String>();
        String query = "SELECT nome, descrizione, contenuto FROM lezioniPortoghese WHERE codice = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, cod);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    lessonInfos.add(resultSet.getString("nome"));
                    lessonInfos.add(resultSet.getString("descrizione"));
                    lessonInfos.add(resultSet.getString("contenuto"));
                    return lessonInfos;
                }
            } finally {
                DataBaseManager.closeConnection();
            }
        }
        return null;
    }
}

class GetSpanishLessonInfo implements Callable<Vector<String>> {
    private int cod ;

    public GetSpanishLessonInfo(int cod )
    { this.cod = cod; }

    @Override
    public Vector<String> call() throws Exception
    {
        Vector<String> lessonInfos = new Vector<String>();
        String query = "SELECT nome, descrizione, contenuto FROM lezioniSpagnolo WHERE codice = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, cod);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    lessonInfos.add(resultSet.getString("nome"));
                    lessonInfos.add(resultSet.getString("descrizione"));
                    lessonInfos.add(resultSet.getString("contenuto"));
                    return lessonInfos;
                }
            } finally {
                DataBaseManager.closeConnection();
            }
        }
        return null;
    }
}