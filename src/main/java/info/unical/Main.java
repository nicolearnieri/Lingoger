package info.unical;

public class Main {
    public static void main(String[] args) {
        try {
            MainApplication.main(args);
            // codice che potrebbe lanciare un'eccezione
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();

        }
    }
}