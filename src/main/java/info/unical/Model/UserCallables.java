package info.unical.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.util.Vector;
import java.util.concurrent.Callable;

import static info.unical.Model.DataBaseManager.getConnection;

//ok
class InsertUserCallable implements Callable<Boolean> {
    private String username, email, password, lingua;

    public InsertUserCallable(String username, String email, String password, String lingua) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.lingua = lingua;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            PreparedStatement insertQuery = getConnection().prepareStatement("INSERT INTO user (username, email, password, language) VALUES (?, ?, ?, ?)");
            insertQuery.setString(1, username);
            insertQuery.setString(2, email);
            insertQuery.setString(3, password);
            insertQuery.setString(4, lingua);

            int rowsAffected = insertQuery.executeUpdate();
            //System.out.println("sto eseguendo");
            return rowsAffected > 0;

        } catch (Exception e) {
            System.err.println("Errore durante l'inserimento dell'utente: " + e.getMessage());
            return false;
        } finally {
            DataBaseManager.closeConnection();
        }
    }
}


//ok
class UsernameNotExistsCallable implements Callable<Boolean> {
    private String username;

    public UsernameNotExistsCallable(String username) {
        this.username = username;
    }

    @Override
    public Boolean call() throws Exception {
        String query = "SELECT COUNT(*) FROM user WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count == 0;
                }
            }
            finally {
                DataBaseManager.closeConnection();
            }
        }
        return true;
    }
}

//ok
class EmailNotExistsCallable implements Callable<Boolean> {
    private String email;

    public EmailNotExistsCallable(String email) {
        this.email = email;
    }

    @Override
    public Boolean call() throws Exception {
        String query = "SELECT COUNT(*) FROM user WHERE email = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count == 0;
                }
            }
            finally {
                DataBaseManager.closeConnection();
            }
        }
        return true;
    }
}

//ok
class GetPasswordCallable implements Callable<String> {
    private String param;

    public GetPasswordCallable(String param) {
        this.param = param;
    }

    @Override
    public String call() throws Exception {
        String query = "SELECT password FROM user WHERE username = ? or email = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, param);
            preparedStatement.setString(2, param);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("password");
                }
            }
            finally {
                DataBaseManager.closeConnection();
            }
        }
        return "";
    }
}

//ok
class GetUsernameCallable implements Callable<String>  {
    private String email;

    public GetUsernameCallable(String email) {
        this.email = email;
    }

    @Override
    public String call() throws Exception {
        String query = "SELECT username FROM user WHERE email = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("username");
                }
            } finally {
                DataBaseManager.closeConnection();
            }
            return "";
        }
    }
}
class GetEmailCallable implements Callable<String>  {
    private String username;

    public GetEmailCallable(String username) {
        this.username = username;
    }

    @Override
    public String call() throws Exception {
        String query = "SELECT email FROM user WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("email");
                }
            } finally {
                DataBaseManager.closeConnection();
            }
            return "";
        }
    }
}
class GetInfoCallable implements Callable<Vector<String>>  {
    private String value;

    public GetInfoCallable(String value) {
        this.value = value;
    }

    @Override
    public Vector<String> call() throws Exception {
        Vector<String> result = new Vector<>();
        String query = "SELECT language FROM user WHERE username = ? or email = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, value);
            preparedStatement.setString(2, value);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    result.add(resultSet.getString("language"));

                }
            }
            finally {
                DataBaseManager.closeConnection();
            }
            return result;
        }
    }
}



class UpdateOnUserCallable implements Callable<Boolean>  {
    private String user, language;

    public UpdateOnUserCallable(String user, String language) {
        this.user = user;
        this.language= language;

    }

    @Override
    public Boolean call() throws Exception {
        String query = "UPDATE user SET language = ? WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, language);
            preparedStatement.setString(2, user);
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement.executeUpdate() + " rows updated");
            return true;
        } finally {
            DataBaseManager.closeConnection();
        }

    }
}


class FindUserCallable implements Callable<Boolean>  {
    private String user, email;

    public FindUserCallable(String user, String email) {
        this.user = user;
        this.email = email;
    }

    @Override
    public Boolean call() throws Exception {
        String query = "SELECT COUNT(*) FROM user WHERE username = ? and email = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count == 1;
                }
            }
            finally {
                DataBaseManager.closeConnection();
            }
        }
        return false;
    }
}

class UpdateUserPasswordCallable implements Callable<Boolean>  {
    private String user, password;

    public UpdateUserPasswordCallable(String user, String password) {
        this.user = user;
        this.password = password;
    }

    @Override
    public Boolean call() throws Exception {
        String query = "UPDATE user SET password = ? WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, user);
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement.executeUpdate() + " rows updated");
            return true;
        } finally {
            DataBaseManager.closeConnection();
        }

    }
}