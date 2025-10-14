import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class Questions {

    private final String[] Questions = new String[93];
    private final String[][] Answers = new String[93][4];
    private final char[] CorrectAnswer = new char[93];
    private final String[] QType = new String[93];

    public Questions() {
        try {

            FileReader file = new FileReader("Questions.txt");
            BufferedReader reader = new BufferedReader(file);
            Scanner scanner = new Scanner(reader);

            String line;
            String lineFixed;

            int qcounter = 0;
            int acounter = 0;
            int f=0;
            int f2=0;

            do {
                do {
                    line = scanner.nextLine();
                    if (line.contains("?")) { //Stores the Question
                        Questions[qcounter] = line;
                    } else if (line.contains(")")) { //Stores the Answers
                        lineFixed = line;
                        lineFixed = lineFixed.replace("1) ", "");
                        lineFixed = lineFixed.replace("2) ", "");
                        lineFixed = lineFixed.replace("3) ", "");
                        lineFixed = lineFixed.replace("4) ", "");
                        Answers[qcounter][acounter] = lineFixed;
                        acounter++;
                    } else if (f==0){ //Stores the CorrectAnswer
                        CorrectAnswer[qcounter] = line.charAt(0);
                        f=1;
                    } else { //Stores the Question Type
                        QType[qcounter] = line;
                        f2=1;
                    }
                } while (f2==0);

                f = 0;
                f2=0;
                acounter = 0;
                qcounter++;

            } while (scanner.hasNext());

            file.close();
            reader.close();
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[][] getAnswers() {
        return Answers;
    }

    public String[] getQuestions() {
        return Questions;
    }

    public char[] getCorrectAnswer() {
        return CorrectAnswer;
    }

    public String[] getQType() {
        return QType;
    }

}