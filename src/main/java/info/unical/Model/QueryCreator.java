package info.unical.Model;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Callable;

    public class QueryCreator
    {
        public static Callable<Boolean> createInsertUser(String username, String email, String password, String lingua)
        {
            InsertUserCallable callable = new InsertUserCallable(username, email, password, lingua);
            return callable;
        }


        public static Callable<Boolean> createUsernameNotExists (String username)
        {
            UsernameNotExistsCallable callable = new UsernameNotExistsCallable(username);
            return callable;
        }

        public static  Callable<Boolean> createEmailNotExists (String email)
        {
            EmailNotExistsCallable callable = new EmailNotExistsCallable(email);
            return callable;
        }

        public static  Callable<String> createGetPassword (String param)
        {
            GetPasswordCallable callable = new GetPasswordCallable(param);
            return callable;
        }

        public static  Callable<String> createGetUsername (String email)
        {
            GetUsernameCallable callable = new GetUsernameCallable(email);
            return callable;
        }

        public static  Callable<String> createGetEmail (String username)
        {
            GetEmailCallable callable = new GetEmailCallable(username);
            return callable;
        }

        public static  Callable<Boolean> createUpdateOnUser(String username, String nuovaLingua)
        {
            UpdateOnUserCallable callable = new UpdateOnUserCallable(username,nuovaLingua);
            return callable;
        }


        public static Callable<Vector<String>> returnUserInfoCallable(String value)
        {
            GetInfoCallable callable = new GetInfoCallable(value);
            return callable;
        }

        public static Callable<Integer> createRetrieveTestsEnglishCallable()
        {
            RetrieveTestsEnglishCallable callable = new RetrieveTestsEnglishCallable();
            return callable;
        }

        public static Callable<Integer> createRetrieveTestsFrenchCallable()
        {
            RetrieveTestsFrenchCallable callable = new RetrieveTestsFrenchCallable();
            return callable;
        }

        public static Callable<Integer> createRetrieveTestsSpanishCallable()
        {
            RetrieveTestsSpanishCallable callable = new RetrieveTestsSpanishCallable();
            return callable;
        }

        public static Callable<Integer> createRetrieveTestsPortugueseCallable()
        {
            RetrieveTestsPortugueseCallable callable = new RetrieveTestsPortugueseCallable();
            return callable;
        }

    }


