package core;

import beans.YandexSpellerAnswer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.List;

import static core.constants.actionsSOAP.*;
import static core.constants.yandexSpellerConstants.SPELLER_JSON;
import static core.constants.yandexSpellerConstants.YANDEX_SPELLER;
import static org.hamcrest.Matchers.lessThan;

public class yandexSpellerAPI {
    public HashMap<String, Object> params = new HashMap<>();

    // Specs
    public static ResponseSpecification successResponse() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectHeader("Connection", "keep-alive")
                .expectResponseTime(lessThan(20000L))
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

    public static RequestSpecification baseRequestConfiguration() {
        return new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setRelaxedHTTPSValidation()
                .setBaseUri(YANDEX_SPELLER + SPELLER_JSON + CHECK_TEXTS.getMethod())
                .build();
    }

    // answers
    public static List<List<YandexSpellerAnswer>> getYandexSpellerAnswers(Response response) {
        return new Gson().fromJson(response.asString().trim(), new TypeToken<List<List<YandexSpellerAnswer>>>() {
        }.getType());
    }

    public static builderApi with() {
        yandexSpellerAPI api = new yandexSpellerAPI();
        return new builderApi(api);
    }

}
