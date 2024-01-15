package bullscows;

import java.util.Random;
import java.util.StringJoiner;

public class SecretCode {
    private final int lengthOfSecretCode;

    private final int numberOfPossibleSymbols;

    private final String code;

    private final char[] possibleSymbolsForSecret;

    public SecretCode(int lengthOfSecretCode, int numberOfPossibleSymbols) {
        possibleSymbolsForSecret = "0123456789abcdefghijklmnopqrstuvwxyz".substring(0, numberOfPossibleSymbols).toCharArray();
        this.lengthOfSecretCode = lengthOfSecretCode;
        this.numberOfPossibleSymbols = numberOfPossibleSymbols;
        code = generateSecretCodeWithUniqueDigits(lengthOfSecretCode, numberOfPossibleSymbols);
    }

    public int getLengthOfSecretCode() {
        return lengthOfSecretCode;
    }

    public int getNumberOfPossibleSymbols() {
        return numberOfPossibleSymbols;
    }

    public String getCode() {
        return code;
    }

    public String getMaskedCode() {
        StringJoiner sj = new StringJoiner("");
        for(int i = 0; i < code.length(); i++) {
            sj.add("*");
        }
        return sj.toString();
    }


    public String getPossibleCharsRangeString() {
        if(numberOfPossibleSymbols <= 10) {
            return String.format("0-%d)", numberOfPossibleSymbols - 1);
        }
        return String.format("(0-9, a-%c)", possibleSymbolsForSecret[possibleSymbolsForSecret.length - 1]);

    }

    public String generateSecretCodeWithUniqueDigits(int lengthOfSecretCode, int numberOfPossibleSymbols) {
        if(lengthOfSecretCode == - 1 || numberOfPossibleSymbols == -1) {
            return null;
        }
        Random random = new Random();
        StringJoiner sj = null;
        do {
            sj = new StringJoiner("");
            for(int i = 0 ; i < lengthOfSecretCode; i++) {
                int randomIndex = random.nextInt(numberOfPossibleSymbols);
                sj.add(String.valueOf(possibleSymbolsForSecret[randomIndex]));
            }

        } while (!hasUniqueSymbols(sj.toString()));

        System.out.printf("The random secret number is %s.\n", sj);
        return sj.toString();
    }

    private boolean hasUniqueSymbols(String code) {
        char[] digitArray = code.toCharArray();
        for(int i = 0; i < digitArray.length; i++) {
            for(int j = i + 1; j < digitArray.length; j++) {
                if(digitArray[i] == digitArray[j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
