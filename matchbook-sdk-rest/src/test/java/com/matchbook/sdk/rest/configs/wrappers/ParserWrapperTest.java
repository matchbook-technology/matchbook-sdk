package com.matchbook.sdk.rest.configs.wrappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ParserWrapperTest {

    @Mock
    private JsonFactory jsonFactory;

    @Mock
    private JsonParser jsonParser;

    private ParserWrapper unit;

    @BeforeEach
    void setUp() throws IOException {
        when(jsonFactory.createParser(any(InputStream.class))).thenReturn(jsonParser);
        InputStream inputStream = mock(InputStream.class);
        unit = new ParserWrapper(jsonFactory, inputStream);
    }

    @Test
    void instantiationFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonFactory).createParser(any(InputStream.class));
        InputStream inputStream = mock(InputStream.class);

        assertThatExceptionOfType(MatchbookSDKParsingException.class)
                .isThrownBy(() -> new ParserWrapper(jsonFactory, inputStream));
    }

    @Test
    void hasCurrentTokenTest() {
        when(jsonParser.hasCurrentToken()).thenReturn(true);
        boolean result = unit.hasCurrentToken();
        assertThat(result).isTrue();
    }

    @Test
    void isEndOfObjectTest() {
        when(jsonParser.hasToken(JsonToken.END_OBJECT)).thenReturn(true);
        boolean result = unit.isEndOfObject();
        assertThat(result).isTrue();
    }

    @Test
    void isEndOfArrayTest() {
        when(jsonParser.hasToken(JsonToken.END_ARRAY)).thenReturn(true);
        boolean result = unit.isEndOfArray();
        assertThat(result).isTrue();
    }

    @Test
    void moveToNextTokenTest() throws IOException {
        unit.moveToNextToken();
        verify(jsonParser).nextToken();
    }

    @Test
    void moveToNextTokenFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonParser).nextToken();

        assertThatExceptionOfType(MatchbookSDKParsingException.class)
                .isThrownBy(() -> unit.moveToNextToken());
    }

    @Test
    void moveToNextValueTest() throws IOException {
        unit.moveToNextValue();
        verify(jsonParser).nextValue();
    }

    @Test
    void moveToNextValueFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonParser).nextValue();

        assertThatExceptionOfType(MatchbookSDKParsingException.class)
                .isThrownBy(() -> unit.moveToNextValue());
    }

    @Test
    void skipChildrenTest() throws IOException {
        unit.skipChildren();
        verify(jsonParser).skipChildren();
    }

    @Test
    void skipChildrenFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonParser).skipChildren();

        assertThatExceptionOfType(MatchbookSDKParsingException.class)
                .isThrownBy(() -> unit.skipChildren());
    }

    @Test
    void getFieldNameTest() throws IOException {
        unit.getFieldName();
        verify(jsonParser).getCurrentName();
    }

    @Test
    void getFieldNameFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonParser).getCurrentName();

        assertThatExceptionOfType(MatchbookSDKParsingException.class)
                .isThrownBy(() -> unit.getFieldName());
    }

    @Test
    void getBooleanTest() throws IOException {
        when(jsonParser.getValueAsBoolean()).thenReturn(true);
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        Boolean result = unit.getBoolean();
        assertThat(result).isNotNull();
        assertThat(result).isTrue();
    }

    @Test
    void getBooleanNullTest() {
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(true);
        Boolean result = unit.getBoolean();
        assertThat(result).isNull();
    }

    @Test
    void getBooleanFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonParser).getValueAsBoolean();
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        assertThatExceptionOfType(MatchbookSDKParsingException.class)
                .isThrownBy(() -> unit.getBoolean());
    }

    @Test
    void getStringTest() throws IOException {
        String expectedResult = "a string";
        when(jsonParser.getValueAsString()).thenReturn(expectedResult);
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        String result = unit.getString();
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void getStringNullTest() {
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(true);
        String result = unit.getString();
        assertThat(result).isNull();
    }

    @Test
    void getStringFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonParser).getValueAsString();
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        assertThatExceptionOfType(MatchbookSDKParsingException.class)
                .isThrownBy(() -> unit.getString());
    }

    @Test
    void getIntegerTest() throws IOException {
        Integer expectedResult = 42;
        when(jsonParser.getValueAsInt()).thenReturn(expectedResult);
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        Integer result = unit.getInteger();
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void getIntegerNullTest() {
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(true);
        Integer result = unit.getInteger();
        assertThat(result).isNull();
    }

    @Test
    void getIntegerFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonParser).getValueAsInt();
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        assertThatExceptionOfType(MatchbookSDKParsingException.class)
                .isThrownBy(() -> unit.getInteger());
    }

    @Test
    void getLongTest() throws IOException {
        Long expectedResult = 42L;
        when(jsonParser.getValueAsLong()).thenReturn(expectedResult);
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        Long result = unit.getLong();
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void getLongNullTest() {
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(true);
        Long result = unit.getLong();
        assertThat(result).isNull();
    }

    @Test
    void getLongFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonParser).getValueAsLong();
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        assertThatExceptionOfType(MatchbookSDKParsingException.class)
                .isThrownBy(() -> unit.getLong());
    }

    @Test
    void getDoubleTest() throws IOException {
        Double expectedResult = 42.0;
        when(jsonParser.getValueAsDouble()).thenReturn(expectedResult);
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        Double result = unit.getDouble();
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void getDoubleNullTest() {
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(true);
        Double result = unit.getDouble();
        assertThat(result).isNull();
    }

    @Test
    void getDoubleFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonParser).getValueAsDouble();
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        assertThatExceptionOfType(MatchbookSDKParsingException.class)
                .isThrownBy(() -> unit.getDouble());
    }

    @Test
    void getDecimalTest() throws IOException {
        BigDecimal expectedResult = new BigDecimal("42");
        when(jsonParser.getDecimalValue()).thenReturn(expectedResult);
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        BigDecimal result = unit.getDecimal();
        assertThat(result).isNotNull();
        assertThat(result).isEqualByComparingTo(expectedResult);
    }

    @Test
    void getDecimalNullTest() {
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(true);
        BigDecimal result = unit.getDecimal();
        assertThat(result).isNull();
    }

    @Test
    void getDecimalFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonParser).getDecimalValue();
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        assertThatExceptionOfType(MatchbookSDKParsingException.class)
                .isThrownBy(() -> unit.getDecimal());
    }

    @Test
    void getInstantTest() throws IOException {
        String instantAsString = "2017-02-14T11:08:00.000Z";
        Instant expectedResult = Instant.parse(instantAsString);
        when(jsonParser.getValueAsString()).thenReturn(instantAsString);
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        Instant result = unit.getInstant();
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void getInstantNullTest() {
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(true);
        Instant result = unit.getInstant();
        assertThat(result).isNull();
    }

    @Test
    void getInstantIncorrectFormatTest() throws IOException {
        String badInstanceString = "not a well-formed timestamp";
        when(jsonParser.getValueAsString()).thenReturn(badInstanceString);
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        assertThatExceptionOfType(MatchbookSDKParsingException.class)
                .isThrownBy(() -> unit.getInstant());
    }

    @Test
    void getInstantFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonParser).getValueAsString();
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        assertThatExceptionOfType(MatchbookSDKParsingException.class)
                .isThrownBy(() -> unit.getInstant());
    }

    @Test
    void getEnumTest() throws IOException {
        TestEnum expectedResult = TestEnum.VALUE;
        when(jsonParser.getValueAsString()).thenReturn(expectedResult.name());
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        TestEnum result = unit.getEnum(TestEnum.class);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void getEnumUnknownTest() throws IOException {
        when(jsonParser.getValueAsString()).thenReturn("something not known");
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        TestEnum result = unit.getEnum(TestEnum.class);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(TestEnum.UNKNOWN);
    }

    @Test
    void getEnumNullTest() {
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(true);
        TestEnum result = unit.getEnum(TestEnum.class);
        assertThat(result).isNull();
    }

    @Test
    void getEnumFailedTest() throws IOException {
        doThrow(IOException.class).when(jsonParser).getValueAsString();
        when(jsonParser.hasToken(JsonToken.VALUE_NULL)).thenReturn(false);

        assertThatExceptionOfType(MatchbookSDKParsingException.class)
                .isThrownBy(() -> unit.getEnum(TestEnum.class));
    }

    @Test
    void closeTest() throws IOException {
        unit.close();
        verify(jsonParser).close();
    }

    private enum TestEnum {
        VALUE, UNKNOWN
    }

}