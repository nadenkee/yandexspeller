package core.constants;

public enum actionsSOAP {
    CHECK_TEXT("checkText"),
    CHECK_TEXTS("checkTexts");
    private String method;

    public String getMethod() {
        return method;
    }


    actionsSOAP(String action) {
        this.method = action;

    }
}

