package bullscows;

public class Main {
    private static UserInterface userInterface;
    private static Grade grade;

    public static void main(String[] args) {
        if(start()) {
            playGame();
        }
        end();
    }

    public static boolean start() {
        userInterface = new UserInterface();
        int lengthOfSecretCode = userInterface.getLengthOfSecretCodeFromUser();
        if (lengthOfSecretCode == -1) {
            return false;
        }
        int numberOfPossibleSymbols = userInterface.getNumberOfPossibleSymbols(lengthOfSecretCode);
        if(numberOfPossibleSymbols == -1) {
            return false;
        }
        SecretCode secretCode = new SecretCode(lengthOfSecretCode, numberOfPossibleSymbols);
        userInterface.printGameStartMessage(secretCode);
        grade = new Grade(secretCode);
        return true;
    }



    public static void playGame() {
        int turn = 1;
        while(true) {
            String input = userInterface.getTurnInputForGrading(turn);
            grade.evaluateInput(input);
            userInterface.printLastGrade(grade);
            if(grade.isExactMatch(input)) {
                userInterface.printCongratulationsMessage();
                break;
            }
            turn++;
        }
    }

    public static void end() {
        userInterface.closeUserInterface();
    }

}
