package info.unical.Model;

public class User
{
    private static User instance;
    private String nomeUtente;
    private String email;
    private String language="inglese";

    private boolean access = false;


    private User() {}

    public String getNomeUtente() {return nomeUtente;}


    public void setNomeUtente(String nomeUtente) {this.nomeUtente = nomeUtente;}
    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public boolean getAccess() {
        if (nomeUtente != null && email != null) return true;
        else return false;
    }

    public void setAccess(boolean value) {this.access = value;}

    public static User getInstance() {
        if (instance == null)
            instance = new User();
        return instance;
    }

    public void logOut ()
    {
        this.email = null;
        this.nomeUtente = null;
        this.access= false;
        this.language =null;
    }

}

