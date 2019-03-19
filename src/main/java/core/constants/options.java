package core.constants;

public enum options {
    IGNORE_DIGITS("2"),
    IGNORE_URLS("4"),
    FIND_REPEAT_WORDS("8"),
    IGNORE_CAPITALIZATION("512");

    public String code;

    options(String code) {
        this.code = code;
    }

    public static String sumOfOptions(options... options) {
        int sum = 0;
        for (options option : options) {
            sum += Integer.parseInt(option.code);
        }
        return String.valueOf(sum);
    }
}
