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
            }
        }
        return null;
    }
}

class RetrieveEnglishDescriptionCallable implements Callable<Vector<String>>
{
    public RetrieveEnglishDescriptionCallable() {}

    @Override
    public Vector<String> call() throws Exception {
        Vector<String> lessonInfos = new Vector<String>();
        String query = "SELECT descrizione FROM lezioniInglese";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    lessonInfos.add(resultSet.getString(1));
                }
            }
        }
        return lessonInfos;

    }
}

class RetrieveSpanishDescriptionCallable implements Callable<Vector<String>>
{
    public RetrieveSpanishDescriptionCallable() {}

    @Override
    public Vector<String> call() throws Exception {
        Vector<String> lessonInfos = new Vector<String>();
        String query = "SELECT descrizione FROM lezioniSpagnolo";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    lessonInfos.add(resultSet.getString(1));
                }
            }
        }
        return lessonInfos;

    }
}
class RetrieveFrenchDescriptionCallable implements Callable<Vector<String>>
{
    public RetrieveFrenchDescriptionCallable() {}

    @Override
    public Vector<String> call() throws Exception {
        Vector<String> lessonInfos = new Vector<String>();
        String query = "SELECT descrizione FROM lezioniFrancese";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    lessonInfos.add(resultSet.getString(1));
                }
            }
        }
        return lessonInfos;

    }
}

class RetrievePortugueseDescriptionCallable implements Callable<Vector<String>>
{
    public RetrievePortugueseDescriptionCallable() {}

    @Override
    public Vector<String> call() throws Exception {
        Vector<String> lessonInfos = new Vector<String>();
        String query = "SELECT descrizione FROM lezioniPortoghese";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    lessonInfos.add(resultSet.getString(1));
                }
            }
        }
        return lessonInfos;

    }
}


class GetIfLessonHasBeenDoneCallable implements Callable<Boolean> {
    private int lesson;
    private String language, user;

    public GetIfLessonHasBeenDoneCallable(int lesson, String language, String user) {
        this.lesson = lesson;
        this.language  = language;
        this.user = user;
    }

    @Override
    public Boolean call() throws Exception {
        String query = "SELECT * FROM lezioniSvolte WHERE lezione = ? AND user = ? AND lingua = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, lesson);
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


class AddLessonDoneCallable implements Callable<Boolean> {
    private int lesson;
    private String language, user;

    public AddLessonDoneCallable(int lesson, String language, String user) {
        this.lesson = lesson;
        this.language  = language;
        this.user = user;
    }

    @Override
    public Boolean call() throws Exception {
        String query = "INSERT INTO lezioniSvolte (lezione, user, lingua) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, lesson);
            preparedStatement.setString(2, user);
            preparedStatement.setString(3, language);
            int rowsAffected= preparedStatement.executeUpdate();
            return rowsAffected>0;
        }
        catch (Exception e) {
            System.err.println("Errore durante l'inserimento dell'utente: " + e.getMessage());
            return false;}
    }

}


