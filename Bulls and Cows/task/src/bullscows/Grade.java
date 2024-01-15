package bullscows;

public class Grade {
    private int bulls;
    private int cows;
    private final SecretCode secretCode;

    public Grade(SecretCode secretCode) {
        this.secretCode = secretCode;
    }

    public int getBulls() {
        return bulls;
    }

    public int getCows() {
        return cows;
    }

    public void evaluateInput(String input) {
        char[] charsInInput = input.toCharArray();
        char[] charsInSecret = secretCode.getCode().toCharArray();
        int countBulls = 0;
        int countCows = 0;
        for (int i = 0; i < charsInInput.length; i++) {
            if(charsInInput[i] == charsInSecret[i]) {
                countBulls++;
            } else {
                for(char charInSecret : charsInSecret) {
                    if(charInSecret == charsInInput[i]) {
                        countCows++;
                    }
                }
            }
        }
        bulls = countBulls;
        cows = countCows;
    }

    public boolean isExactMatch(String input) {
        return bulls == input.length() && cows == 0;
    }
}
