package com.matchbook.sdk.rest.configs.wrappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.Instant;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParserWrapperTest {

    @Mock
    private JsonFactory jsonFactory;

    @Mock
    private JsonParser jsonParser;

    private ParserWrapper unit;

    @Before
    public void setUp() throws IOException {
        when(jsonFactory.createParser(any(InputStream.class))).thenReturn(jsonParser);
        InputStream inputStream = mock(InputStream.class);
        unit = new ParserWrapper(jsonFactory, inputStream);
    }

    @Test(expected = MatchbookSDKParsingException.class)
    public void instantiationFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonFactory).createParser(any(InputStream.class));
        InputStream inputStream = mock(InputStream.class);
        new ParserWrapper(jsonFactory, inputStream);
        failBecauseExceptionWasNotThrown(MatchbookSDKParsingException.class);
    }

    @Test
    public void hasCurrentTokenTest() {
        when(jsonParser.hasCurrentToken()).thenReturn(true);
        boolean result = unit.hasCurrentToken();
        assertThat(result).isTrue();
    }

    @Test
    public void isEndOfObjectTest() {
        when(jsonParser.hasToken(JsonToken.END_OBJECT)).thenReturn(true);
        boolean result = unit.isEndOfObject();
        assertThat(result).isTrue();
    }

    @Test
    public void isEndOfArrayTest() {
        when(jsonParser.hasToken(JsonToken.END_ARRAY)).thenReturn(true);
        boolean result = unit.isEndOfArray();
        assertThat(result).isTrue();
    }

    @Test
    public void moveToNextTokenTest() throws IOException {
        unit.moveToNextToken();
        verify(jsonParser).nextToken();
    }

    @Test(expected = MatchbookSDKParsingException.class)
    public void moveToNextTokenFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonParser).nextToken();
        unit.moveToNextToken();
        failBecauseExceptionWasNotThrown(MatchbookSDKParsingException.class);
    }

    @Test
    public void moveToNextValueTest() throws IOException {
        unit.moveToNextValue();
        verify(jsonParser).nextValue();
    }

    @Test(expected = MatchbookSDKParsingException.class)
    public void moveToNextValueFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonParser).nextValue();
        unit.moveToNextValue();
        failBecauseExceptionWasNotThrown(MatchbookSDKParsingException.class);
    }

    @Test
    public void skipChildrenTest() throws IOException {
        unit.skipChildren();
        verify(jsonParser).skipChildren();
    }

    @Test(expected = MatchbookSDKParsingException.class)
    public void skipChildrenFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonParser).skipChildren();
        unit.skipChildren();
        failBecauseExceptionWasNotThrown(MatchbookSDKParsingException.class);
    }

    @Test
    public void getFieldNameTest() throws IOException {
        unit.getFieldName();
        verify(jsonParser).getCurrentName();
    }

    @Test(expected = MatchbookSDKParsingException.class)
    public void getFieldNameFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonParser).getCurrentName();
        unit.getFieldName();
        failBecauseExceptionWasNotThrown(MatchbookSDKParsingException.class);
    }

    @Test
    public void getBooleanTest() throws IOException {
        when(jsonParser.getValueAsBoolean()).thenReturn(true);
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        Boolean result = unit.getBoolean();
        assertThat(result).isNotNull();
        assertThat(result).isTrue();
    }

    @Test
    public void getBooleanNullTest() {
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(true);
        Boolean result = unit.getBoolean();
        assertThat(result).isNull();
    }

    @Test(expected = MatchbookSDKParsingException.class)
    public void getBooleanFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonParser).getValueAsBoolean();
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);
        unit.getBoolean();
        failBecauseExceptionWasNotThrown(MatchbookSDKParsingException.class);
    }

    @Test
    public void getStringTest() throws IOException {
        String expectedResult = "a string";
        when(jsonParser.getValueAsString()).thenReturn(expectedResult);
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        String result = unit.getString();
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void getStringNullTest() {
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(true);
        String result = unit.getString();
        assertThat(result).isNull();
    }

    @Test(expected = MatchbookSDKParsingException.class)
    public void getStringFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonParser).getValueAsString();
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);
        unit.getString();
        failBecauseExceptionWasNotThrown(MatchbookSDKParsingException.class);
    }

    @Test
    public void getIntegerTest() throws IOException {
        Integer expectedResult = 42;
        when(jsonParser.getValueAsInt()).thenReturn(expectedResult);
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        Integer result = unit.getInteger();
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void getIntegerNullTest() {
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(true);
        Integer result = unit.getInteger();
        assertThat(result).isNull();
    }

    @Test(expected = MatchbookSDKParsingException.class)
    public void getIntegerFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonParser).getValueAsInt();
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);
        unit.getInteger();
        failBecauseExceptionWasNotThrown(MatchbookSDKParsingException.class);
    }

    @Test
    public void getLongTest() throws IOException {
        Long expectedResult = 42L;
        when(jsonParser.getValueAsLong()).thenReturn(expectedResult);
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        Long result = unit.getLong();
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void getLongNullTest() {
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(true);
        Long result = unit.getLong();
        assertThat(result).isNull();
    }

    @Test(expected = MatchbookSDKParsingException.class)
    public void getLongFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonParser).getValueAsLong();
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);
        unit.getLong();
        failBecauseExceptionWasNotThrown(MatchbookSDKParsingException.class);
    }

    @Test
    public void getDoubleTest() throws IOException {
        Double expectedResult = 42.0;
        when(jsonParser.getValueAsDouble()).thenReturn(expectedResult);
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        Double result = unit.getDouble();
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void getDoubleNullTest() {
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(true);
        Double result = unit.getDouble();
        assertThat(result).isNull();
    }

    @Test(expected = MatchbookSDKParsingException.class)
    public void getDoubleFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonParser).getValueAsDouble();
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);
        unit.getDouble();
        failBecauseExceptionWasNotThrown(MatchbookSDKParsingException.class);
    }

    @Test
    public void getDecimalTest() throws IOException {
        BigDecimal expectedResult = new BigDecimal("42");
        when(jsonParser.getDecimalValue()).thenReturn(expectedResult);
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        BigDecimal result = unit.getDecimal();
        assertThat(result).isNotNull();
        assertThat(result).isEqualByComparingTo(expectedResult);
    }

    @Test
    public void getDecimalNullTest() {
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(true);
        BigDecimal result = unit.getDecimal();
        assertThat(result).isNull();
    }

    @Test(expected = MatchbookSDKParsingException.class)
    public void getDecimalFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonParser).getDecimalValue();
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);
        unit.getDecimal();
        failBecauseExceptionWasNotThrown(MatchbookSDKParsingException.class);
    }

    @Test
    public void getInstantTest() throws IOException {
        String instantAsString = "2017-02-14T11:08:00.000Z";
        Instant expectedResult = Instant.parse(instantAsString);
        when(jsonParser.getValueAsString()).thenReturn(instantAsString);
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        Instant result = unit.getInstant();
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void getInstantNullTest() {
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(true);
        Instant result = unit.getInstant();
        assertThat(result).isNull();
    }

    @Test(expected = MatchbookSDKParsingException.class)
    public void getInstantIncorrectFormatTest() throws IOException {
        String badInstanceString = "not a well-formed timestamp";
        when(jsonParser.getValueAsString()).thenReturn(badInstanceString);
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);
        unit.getInstant();
        failBecauseExceptionWasNotThrown(MatchbookSDKParsingException.class);
    }

    @Test(expected = MatchbookSDKParsingException.class)
    public void getInstantFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonParser).getValueAsString();
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);
        unit.getInstant();
        failBecauseExceptionWasNotThrown(MatchbookSDKParsingException.class);
    }

    @Test
    public void getEnumTest() throws IOException {
        TestEnum expectedResult = TestEnum.VALUE;
        when(jsonParser.getValueAsString()).thenReturn(expectedResult.name());
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        TestEnum result = unit.getEnum(TestEnum.class);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void getEnumUnknownTest() throws IOException {
        when(jsonParser.getValueAsString()).thenReturn("something not known");
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        TestEnum result = unit.getEnum(TestEnum.class);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(TestEnum.UNKNOWN);
    }

    @Test
    public void getEnumNullTest() {
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(true);
        TestEnum result = unit.getEnum(TestEnum.class);
        assertThat(result).isNull();
    }

    @Test(expected = MatchbookSDKParsingException.class)
    public void getEnumFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonParser).getValueAsString();
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);
        unit.getEnum(TestEnum.class);
        failBecauseExceptionWasNotThrown(MatchbookSDKParsingException.class);
    }

    @Test
    public void closeTest() throws IOException {
        unit.close();
        verify(jsonParser).close();
    }

    private enum TestEnum {
        VALUE, UNKNOWN
    }

}