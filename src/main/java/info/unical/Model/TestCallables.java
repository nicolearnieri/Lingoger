package info.unical.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
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

        }
        return -1;
    }
}

class GetTestQuestionsEngCallable implements Callable<Vector<Object>> {
    private int id;

    public GetTestQuestionsEngCallable(int id) {
        this.id = id;
    }

    @Override
        public Vector<Object> call() throws Exception {
            Vector<Object> result = new Vector<>();
        String query = "SELECT * FROM domandeInglese where test = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    result.add(resultSet.getString("domanda"));
                    result.add(resultSet.getInt("codice"));
                    result.add(resultSet.getString("risposta"));
                }
            }

        }
        return result;
    }
}

class GetTestQuestionsFrenchCallable implements Callable<Vector<Object>> {
    private int id;

    public GetTestQuestionsFrenchCallable(int id) {
        this.id = id;
    }

    @Override
    public Vector<Object> call() throws Exception {
        Vector<Object> result = new Vector<>();
        String query = "SELECT * FROM domandeFrancese where test = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(resultSet.getString("domanda"));
                    result.add(resultSet.getInt("codice"));
                    result.add(resultSet.getString("risposta"));
                }
            }

        }
        return result;
    }
}

class GetTestQuestionsSpanishCallable implements Callable<Vector<Object>> {
    private int id;

    public GetTestQuestionsSpanishCallable(int id) {
        this.id = id;
    }

    @Override
    public Vector<Object> call() throws Exception {
        Vector<Object> result = new Vector<>();
        String query = "SELECT * FROM domandeSpagnolo where test = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
               while (resultSet.next()) {
                    result.add(resultSet.getString("domanda"));
                    result.add(resultSet.getInt("codice"));
                    result.add(resultSet.getString("risposta"));
                }
            }

        }
        return result;
    }
}

class GetTestQuestionsPortugueseCallable implements Callable<Vector<Object>> {
    private int id;

    public GetTestQuestionsPortugueseCallable(int id) {
        this.id = id;
    }

    @Override
    public Vector<Object> call() throws Exception {
        Vector<Object> result = new Vector<>();
        String query = "SELECT * FROM domandePortoghese where test = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
               while (resultSet.next()) {
                    result.add(resultSet.getString("domanda"));
                    result.add(resultSet.getInt("codice"));
                    result.add(resultSet.getString("risposta"));
                }
            }

        }
        return result;
    }
}

class GetAnswersTestEngCallable implements Callable<Vector<String>> {
    int test;

    GetAnswersTestEngCallable(int test) {
        this.test = test;
    }

    @Override
    public Vector<String> call() throws Exception {
        Vector<String> result = new Vector<>();
        String query = "SELECT * FROM risposteInglese where test = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, test);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
               while (resultSet.next()) {
                    result.add(resultSet.getString("risposta1"));
                    result.add(resultSet.getString("risposta2"));
                    result.add(resultSet.getString("risposta3"));
                    //result.add(resultSet.getString("rispostaOK"));
                }
            }
            return result;
        }
    }
}


class GetAnswersTestSpanishCallable implements Callable<Vector<String>> {
    int test;

    GetAnswersTestSpanishCallable(int test) {
        this.test = test;
    }

    @Override
    public Vector<String> call() throws Exception {
        Vector<String> result = new Vector<>();
        String query = "SELECT * FROM risposteSpagnolo where test = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt( 1, test);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(resultSet.getString("risposta1"));
                    result.add(resultSet.getString("risposta2"));
                    result.add(resultSet.getString("risposta3"));
                    //result.add(resultSet.getString("rispostaOK"));
                }
            }

            return result;
        }
    }
}

class GetAnswersTestFrenchCallable implements Callable<Vector<String>> {
    int test;

    GetAnswersTestFrenchCallable(int test) {
        this.test = test;
    }

    @Override
    public Vector<String> call() throws Exception {
        Vector<String> result = new Vector<>();
        String query = "SELECT * FROM risposteFrancese where test = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, test);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(resultSet.getString("risposta1"));
                    result.add(resultSet.getString("risposta2"));
                    result.add(resultSet.getString("risposta3"));
                    //result.add(resultSet.getString("rispostaOK"));
                }
            }

            return result;
        }
    }
}

class GetAnswersTestPortCallable implements Callable<Vector<String>> {
    int test;

    GetAnswersTestPortCallable(int test) {
        this.test = test;
    }

    @Override
    public Vector<String> call() throws Exception {
        Vector<String> result = new Vector<>();
        String query = "SELECT * FROM rispostePortoghese where test = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt( 1, test);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(resultSet.getString("risposta1"));
                    result.add(resultSet.getString("risposta2"));
                    result.add(resultSet.getString("risposta3"));
                    //result.add(resultSet.getString("rispostaOK"));
                }
            }
            return result;
        }
    }
}


class GetTestInfosEnglishCallable implements Callable<Vector<String>> {

    public GetTestInfosEnglishCallable() {}

    @Override
    public Vector<String> call() throws Exception {
        Vector<String> result = new Vector<>();
        String query = "SELECT * FROM testInglese";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(resultSet.getString("nome"));
                }
            }
        }
        return result;
    }
}


class GetTestInfosFrenchCallable implements Callable<Vector<String>> {

    public GetTestInfosFrenchCallable() {}

    @Override
    public Vector<String> call() throws Exception {
        Vector<String> result = new Vector<>();
        String query = "SELECT * FROM testFrancese";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(resultSet.getString("nome"));
                }
            }
        }
        return result;
    }
}


class GetTestInfosSpanishCallable implements Callable<Vector<String>> {

    public GetTestInfosSpanishCallable() {}

    @Override
    public Vector<String> call() throws Exception {
        Vector<String> result = new Vector<>();
        String query = "SELECT * FROM testSpagnolo";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(resultSet.getString("nome"));
                }
            }
        }
        return result;
    }
}


class GetTestInfosPortugueseCallable implements Callable<Vector<String>> {

    public GetTestInfosPortugueseCallable() {}

    @Override
    public Vector<String> call() throws Exception {
        Vector<String> result = new Vector<>();
        String query = "SELECT * FROM testPortoghese";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(resultSet.getString("nome"));
                }
            }
        }
        return result;
    }
}

class GetIfTestHasBeenDoneCallable implements Callable<Boolean> {
    private int test;
    private String language, user;

    public GetIfTestHasBeenDoneCallable(int test, String language, String user) {
        this.test = test;
        this.language  = language;
        this.user = user;
    }

    @Override
    public Boolean call() throws Exception {
        String query = "SELECT * FROM testSvolti WHERE test = ? AND utente = ? AND lingua = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, test);
            preparedStatement.setString(2, user);
            preparedStatement.setString(3, language);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }
        }
        return false;
    }
}


class AddTestDoneCallable implements Callable<Boolean> {
    private int test;
    private String language, user;

    public AddTestDoneCallable(int test, String language, String user) {
        this.test = test;
        this.language = language;
        this.user = user;
    }

    @Override
    public Boolean call() throws Exception {
        String query = "INSERT INTO testSvolti (test, user, lingua) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, test);
            preparedStatement.setString(2, user);
            preparedStatement.setString(3, language);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.err.println("Errore durante l'inserimento dell'utente: " + e.getMessage());
            return false;
        }
    }
}