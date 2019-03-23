package core.constants;

public enum TestText {
    MOTHER("mother", "motthher"),
    BROTHER("brother", "bbrother"),
    UK_WORD("питання", "питаня"),
    RU_WORD("сметана", "смитана"),
    RU_WRONG_CAPITAL("Петропавловск-Камчатский", "петропавловск-камчатский"),
    EN_WITH_DIGITS("father", "666father")
    ;

    private String corrVer;
    private String wrongVer;

    public String corrVer(){return corrVer;}
    public String wrongVer(){return wrongVer;}

    private TestText(String corrVer, String wrongVer){
        this.corrVer = corrVer;
        this.wrongVer = wrongVer;
    }
}