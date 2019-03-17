package core;

import beans.YandexSpellerAnswer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import core.constants.YandexSpellerConstants;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static core.YandexSpellerCheckTextAPI.YANDEX_SPELLER_API_URI;
import static org.hamcrest.Matchers.lessThan;

public class ServiceObject {

    //builder pattern
    private ServiceObject() {

    }
    public static final String YANDEX_SPELLER_API_URL =
            "https://speller.yandex.net/services/spellservice?op=checkTexts";

    private HashMap<String, String> params = new HashMap<>();

    public static class ApiBuilder {
        ServiceObject spellerApi;

        private ApiBuilder (ServiceObject gcApi) {
            spellerApi = gcApi;
        }

        public ApiBuilder text (String text) {
            spellerApi.params.put(YandexSpellerConstants.PARAM_TEXT, text);
            return this;
        }

        public ApiBuilder options (String options) {
            spellerApi.params.put(YandexSpellerConstants.PARAM_OPTIONS, options);
            return this;
        }

        public ApiBuilder language(YandexSpellerConstants.Language language) {
            spellerApi.params.put(YandexSpellerConstants.PARAM_LANG, language.languageCode);
            return this;
        }

        public Response callApi () {
            return RestAssured.with()
                    .queryParam(String.valueOf(spellerApi.params))
                    .log().all()
                    .get(YANDEX_SPELLER_API_URL).prettyPeek();
        }
    }
    public static ApiBuilder with() {
        ServiceObject api = new ServiceObject();
        return new ApiBuilder(api);
    }

    public static List<YandexSpellerAnswer> getYandexSpellerAnswers(Response response){
        return new Gson().fromJson( response.asString()
                .trim(), new TypeToken<List<YandexSpellerAnswer>>() {}
                .getType());
    }

    //set base request and response specifications for usage in tests
    public static ResponseSpecification successResponse(){
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectHeader("Connection", "keep-alive")
                .expectResponseTime(lessThan(20000L))
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

    public static RequestSpecification baseRequestConfiguration(){
        return new RequestSpecBuilder()
                .setAccept(ContentType.XML)
                .setRelaxedHTTPSValidation()
                .addHeader("custom header2", "header2.value")
                .addQueryParam("requestID", new Random().nextLong())
                .setBaseUri(YANDEX_SPELLER_API_URI)
                .build();
    }


}
