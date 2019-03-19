package core;

import core.constants.langs;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static core.constants.actionsSOAP.CHECK_TEXTS;
import static core.constants.yandexSpellerConstants.*;

public class builderApi {

    yandexSpellerAPI spellerApi;

    builderApi(yandexSpellerAPI gcApi) {
        spellerApi = gcApi;
    }

    public builderApi texts(String... texts) {
        List<String> textsList = Arrays.asList(texts);
        spellerApi.params.put(PARAM_TEXTS, textsList);
        return this;
    }

    public builderApi options(int... options) {
        spellerApi.params.put(PARAM_OPTIONS, Integer.toString(IntStream.of(options).sum()));
        return this;
    }

    public builderApi language(langs... languages) {
        List<String> languagesList = new ArrayList<>();
        for (langs language : languages) {
            languagesList.add(language.getLang());
        }
        String newLanguageList = String.join(", ", languagesList);
        spellerApi.params.put(PARAM_LANGUAGES, newLanguageList);
        return this;
    }

    public Response getCheckTexts() {
        return RestAssured.with()
                .queryParams(spellerApi.params)
                .log().all()
                .get(YANDEX_SPELLER + SPELLER_JSON + CHECK_TEXTS.getMethod()).prettyPeek();
    }

    public Response getCheckText() {
        return RestAssured.with()
                .queryParams(spellerApi.params)
                .log().all()
                .get(YANDEX_SPELLER + SPELLER_JSON + CHECK_TEXTS.getMethod()).prettyPeek();
    }
}
