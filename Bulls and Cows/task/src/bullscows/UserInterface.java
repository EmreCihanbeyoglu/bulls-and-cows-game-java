package bullscows;

import java.util.Scanner;
import java.util.StringJoiner;

public class UserInterface {

    private final Scanner scanner;
    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public int getLengthOfSecretCodeFromUser() {
        System.out.println("Input the length of the secret code:");
        int codeLength = -1;
        try {
            String input = scanner.nextLine();
            if(!isNumeric(input)) {
                throw new NumberFormatException(String.format("Error: \"%s\" isn't a valid number.", input));
            }
            codeLength = Integer.parseInt(input);
            if(codeLength <= 0 ) {
                throw new IllegalArgumentException("Error: minimum length of code is 1.");
            }
            if(codeLength > 36) {
                throw new IllegalArgumentException("Error: maximum length of code is 36 (0-9, a-z).");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            codeLength = -1;
        }
        return codeLength;
    }

    public int getNumberOfPossibleSymbols(int codeLength) {
        System.out.println("Input the number of possible symbols in the code:");
        int numberOfPossibleSymbols = -1;
        try {
            String input = scanner.nextLine();
            if(!isNumeric(input)) {
                throw new NumberFormatException(String.format("Error: \"%s\" isn't a valid number.", input));
            }
            numberOfPossibleSymbols = Integer.parseInt(input);
            if(numberOfPossibleSymbols <= 0 ) {
                throw new IllegalArgumentException("Error: minimum number of possible symbols in the code is 1");
            }
            if(numberOfPossibleSymbols > 36) {
                throw new IllegalArgumentException("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            }
            if(numberOfPossibleSymbols < codeLength) {
                throw new IllegalArgumentException(String.format("Error: it's not possible to generate a code with a length of %d with %d unique symbols.", codeLength, numberOfPossibleSymbols));
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            numberOfPossibleSymbols = -1;
        }
        return numberOfPossibleSymbols;
    }

    public boolean isNumeric(String input) {
        if(input == null) {
            return false;
        }
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public boolean isNumberOfSymbolsLargerThanSecretCodeLength(int lengthOfSecret, int numberOfSymbols) {
        boolean flag = lengthOfSecret <= numberOfSymbols;
        if(!flag) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.", lengthOfSecret, numberOfSymbols);
        }

        return flag;
    }


    public void printCongratulationsMessage() {
        System.out.println("Congratulations! You guessed the secret code.");
    }

    public void printGameStartMessage(SecretCode secretCode) {
        String startMessage = """
                The secret is prepared: %s %s.
                Okay, let's start a game!
                """;
        String entireMessage = String.format(startMessage, secretCode.getMaskedCode(), secretCode.getPossibleCharsRangeString());
        System.out.println(entireMessage);
    }

    public String getTurnInputForGrading(int turn) {
        String input = null;
        System.out.printf("Turn %d:\n", turn);
        try {
            input = scanner.nextLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return input;
    }

    public void printLastGrade(Grade grade) {
        int bulls = grade.getBulls();
        int cows = grade.getCows();
        String bullString = "";
        String cowString = "";

        if(bulls == 1 ) {
            bullString = bulls + " bull";
        }else if(bulls > 1) {
            bullString = bulls + " bulls";
        }
        if(cows == 1 ) {
            cowString = cows + " cow";
        } else if(cows > 1) {
            cowString = cows + " cows";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Grade: ");
        if(cows == 0 && bulls == 0) {
            sb.append("None");
        } else {
            sb
                    .append(bullString.isEmpty() ? "" : bullString)
                    .append(!bullString.isEmpty() && !cowString.isEmpty() ? " and " : "")
                    .append(cowString.isEmpty() ? "" : cowString);
        }
        System.out.println(sb);
    }

    public void closeUserInterface() {
        scanner.close();
    }

}
