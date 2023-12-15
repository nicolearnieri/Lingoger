package info.unical.Controller;

public class QuestionsMediator
{
    private static boolean questionAnswered;
    private static int answerNumber; //numero della risposta data, 1, 2 o 3 perchè ho tre possibili alternative per ogni domanda

    public QuestionsMediator()
    {
        questionAnswered = false;
        answerNumber = 0;
    }

    public static boolean isQuestionAnswered()
    {
        return questionAnswered;
    }

    public static int getAnswerNumber()
    {
        return answerNumber;
    }

    public static void resetQuestionAnswered()
    {
       questionAnswered = false;
       answerNumber = 0;
    }

    public static void answerGiven (int number)
    {
        QuestionsMediator.questionAnswered = true;
        QuestionsMediator.answerNumber = number;
    }

    public static void setAnswerNumber(int number)
    {
        QuestionsMediator.answerNumber = number;
    }

    public static boolean controlling(int number)
    {
        if (isQuestionAnswered())
        {
            if (getAnswerNumber() == number)
            {
                resetQuestionAnswered(); //ho deselezionato la risposta
                return true; //la risposta era già stata data
            }
        }
        //la risposta non era stata data, oppure era stata data ma era diversa da quella che ho appena cliccato
        answerGiven(number);
        return false;
    }
}
