package info.unical.Model;

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

        public static Callable<Vector<Object>> createRetrieveQuestionsEnglishCallable(int i)
        {
            GetTestQuestionsEngCallable callable = new GetTestQuestionsEngCallable(i);
            return callable;
        }

        public static Callable<Vector<Object>> createRetrieveQuestionsFrenchCallable(int i)
        {
            GetTestQuestionsFrenchCallable callable = new GetTestQuestionsFrenchCallable(i);
            return callable;
        }

        public static Callable<Vector<Object>> createRetrieveQuestionsSpanishCallable(int i)
        {
            GetTestQuestionsSpanishCallable callable = new GetTestQuestionsSpanishCallable(i);
            return callable;
        }

        public static Callable<Vector<Object>> createRetrieveQuestionsPortugueseCallable(int i)
        {
            GetTestQuestionsPortugueseCallable callable = new GetTestQuestionsPortugueseCallable(i);
            return callable;
        }

        public static Callable<Vector<String>> createRetrieveAnswersEnglishCallable(int i)
        {
            GetAnswersTestEngCallable callable = new GetAnswersTestEngCallable(i);
            return callable;
        }

        public static Callable<Vector<String>> createRetrieveAnswersFrenchCallable(int i)
        {
            GetAnswersTestFrenchCallable callable = new GetAnswersTestFrenchCallable(i);
            return callable;
        }

        public static Callable<Vector<String>> createRetrieveAnswersSpanishCallable(int i)
        {
            GetAnswersTestSpanishCallable callable = new GetAnswersTestSpanishCallable(i);
            return callable;
        }

        public static Callable<Vector<String>> createRetrieveAnswersPortugueseCallable(int i)
        {
            GetAnswersTestPortCallable callable = new GetAnswersTestPortCallable(i);
            return callable;
        }

        public static Callable<Integer> createRetrieveEnglishLessonsCallable()
        {
            RetrieveEnglishLessonsCallable callable = new RetrieveEnglishLessonsCallable();
            return callable;
        }

        public static Callable<Integer> createRetrieveFrenchLessonsCallable()
        {
            RetrieveFrenchLessonsCallable callable = new RetrieveFrenchLessonsCallable();
            return callable;
        }

        public static Callable<Integer> createRetrievePortugueseLessonsCallable()
        {
            RetrievePortugueseLessonsCallable callable = new RetrievePortugueseLessonsCallable();
            return callable;
        }

        public static Callable<Integer> createRetrieveSpanishLessonsCallable()
        {
            RetrieveSpanishLessonsCallable callable = new RetrieveSpanishLessonsCallable();
            return callable;
        }

        public static Callable<Vector<String>> createRetrieveEnglishLessonInfoCallable(int cod)
        {
            GetEnglishLessonInfo callable = new GetEnglishLessonInfo(cod);
            return callable;
        }

        public static Callable<Vector<String>> createRetrieveFrenchLessonInfoCallable(int cod)
        {
            GetFrenchLessonInfo callable = new GetFrenchLessonInfo(cod);
            return callable;
        }

        public static Callable<Vector<String>> createRetrieveSpanishLessonInfoCallable(int cod)
        {
            GetSpanishLessonInfo callable = new GetSpanishLessonInfo(cod);
            return callable;
        }

        public static Callable<Vector<String>> createRetrievePortugueseLessonInfoCallable(int cod)
        {
            GetPortugueseLessonInfo callable = new GetPortugueseLessonInfo(cod);
            return callable;
        }

        public static Callable<Vector<String>> createRetrieveEnglishDescriptionCallable() {
            RetrieveEnglishDescriptionCallable callable = new RetrieveEnglishDescriptionCallable();
            return callable;
        }

        public static Callable<Vector<String>> createRetrieveFrenchDescriptionCallable() {
            RetrieveFrenchDescriptionCallable callable = new RetrieveFrenchDescriptionCallable();
            return callable;
        }

        public static Callable<Vector<String>> createRetrieveSpanishDescriptionCallable() {
            RetrieveSpanishDescriptionCallable callable = new RetrieveSpanishDescriptionCallable();
            return callable;
        }

        public static Callable<Vector<String>> createRetrievePortugueseDescriptionCallable()
        {
            RetrievePortugueseDescriptionCallable callable = new RetrievePortugueseDescriptionCallable();
            return callable;
        }

        public static Callable<Vector<String>> createRetrieveTestInfosEnglishCallable()
        {
            GetTestInfosEnglishCallable callable = new GetTestInfosEnglishCallable();
            return callable;
        }

        public static Callable<Vector<String>> createRetrieveTestInfosFrenchCallable()
        {
            GetTestInfosFrenchCallable callable = new GetTestInfosFrenchCallable();
            return callable;
        }

        public static Callable<Vector<String>> createRetrieveTestInfosSpanishCallable()
        {
            GetTestInfosSpanishCallable callable = new GetTestInfosSpanishCallable();
            return callable;
        }

        public static Callable<Vector<String>> createRetrieveTestInfosPortugueseCallable()
        {
            GetTestInfosPortugueseCallable callable = new GetTestInfosPortugueseCallable();
            return callable;
        }

        public static Callable<Boolean> createAddTestDoneCallable(int codice, String username, String lingua )
        {
            AddTestDoneCallable callable = new AddTestDoneCallable(codice, lingua, username);
            return callable;
        }

        public static Callable<Boolean> createAddLessonDoneCallable(int codice, String username, String lingua )
        {
            AddLessonDoneCallable callable = new AddLessonDoneCallable(codice, lingua, username);
            return callable;
        }

        public static Callable<Boolean> createGetIfLessonHasBeenDoneCallable(int lesson, String language, String user)
        {
        	GetIfLessonHasBeenDoneCallable callable = new GetIfLessonHasBeenDoneCallable(lesson, language, user);
        	return callable;
        }

        public static Callable<Boolean> createGetIfTestHasBeenDoneCallable(int test, String language, String user)
        {
        	GetIfTestHasBeenDoneCallable callable = new GetIfTestHasBeenDoneCallable(test, language, user);
        	return callable;
        }

    }


