package core;

import beans.YandexSpellerAnswer;
import core.constants.errorCodes;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertFalse;

public class checkResponse {
    public static void checkAnswer(String[] text s, List[] expectedSuggestions, List<List<YandexSpellerAnswer>> answers, errorCodes error) {
        assertThat(answers.size(), equalTo(texts.length));
        for (int i = 0; i < texts.length; i++) {
            if (!answers.get(i).isEmpty()) {
                assertThat("Proposed suggestion is not expected:", answers.get(i).get(0).s, equalTo(expectedSuggestions[i]));
                assertThat("Error is incorect", answers.get(i).get(0).code, equalTo(error.errorCode));
            } else {
                assertFalse(answers.get(i).isEmpty(), "Received response is empty:");
            }
        }
    }

    public static void checkErrorAttribute(String[] texts, List[] expectedSuggestions, List<List<YandexSpellerAnswer>> answers, errorCodes error) {
        assertThat(answers.size(), equalTo(texts.length));

        for (int i = 0; i < texts.length; i++) {
            if (!answers.get(i).isEmpty()) {
                assertThat("Proposed suggestion is not expected:", answers.get(i).get(0).s, equalTo(expectedSuggestions[i]));
                assertThat("Error is incorect", answers.get(i).get(0).code, equalTo(error.errorCode));
                assertThat(answers.get(i).get(0).pos, equalTo(texts[i].indexOf(answers.get(i).get(0).word)));
                assertThat(answers.get(i).get(0).row, equalTo(0));
                assertThat(answers.get(i).get(0).col, equalTo(0));
                assertThat(answers.get(i).get(0).len, equalTo(answers.get(i).get(0).word.length()));
            } else {
                assertFalse(answers.get(i).isEmpty(), "Received response is empty:");
            }
        }
    }
}
