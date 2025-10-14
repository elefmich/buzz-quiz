import java.util.Random;

public class RoundType {

    /**
     * This method randomly picks one of the two types of rounds (Correct Answer and Betting).
     * @return the type of round.
     */
    public String TypeOfRounds(){
        String[] roundType = new String[5];
        roundType[0] = "Correct Answer";
        roundType[1] = "Betting";
        roundType[2] = "Stop The Timer";
        roundType[3] = "Thermometer";
        roundType[4] = "Quick Answer";
        Random rand = new Random();
        return roundType[rand.nextInt(5)];
    }
}