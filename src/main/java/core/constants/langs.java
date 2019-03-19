package core.constants;

public enum langs {
    EN("en"),
    RU("ru"),
    UK("uk"),
    IT("it");

    private final String lang;

    langs(String lang) {
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }
}
