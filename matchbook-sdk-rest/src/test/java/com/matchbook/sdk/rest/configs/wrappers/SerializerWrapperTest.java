package com.matchbook.sdk.rest.configs.wrappers;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.matchbook.sdk.rest.configs.Parser;
import org.junit.Before;
import org.junit.Test;

public class SerializerWrapperTest {

    private SerializerWrapper unit;

    @Before
    public void setUp() {
        unit = new SerializerWrapper();
    }

    @Test
    public void newParserTest() {
        InputStream inputStream = new ByteArrayInputStream(new byte[]{ (byte) 0b101010 });
        Parser parser = unit.newParser(inputStream);
        assertThat(parser).isNotNull();
        assertThat(parser).isInstanceOf(ParserWrapper.class);
    }

    @Test
    public void writeObjectAsStringTest() throws IOException {
        TestClass testObject1 = new TestClass();
        testObject1.fieldInt = 42;
        testObject1.fieldString = "value";
        String serialisedString1 = unit.writeObjectAsString(testObject1);
        assertThat(serialisedString1).isEqualToIgnoringWhitespace("{\"field-int\":42,\"field-string\":\"value\"}");

        TestClass testObject2 = new TestClass();
        testObject2.fieldInt = 73;
        testObject2.fieldString = null;
        String serialisedString2 = unit.writeObjectAsString(testObject2);
        assertThat(serialisedString2).isEqualToIgnoringWhitespace("{\"field-int\":73,\"field-string\":null}");
    }

    private static class TestClass {

        public int fieldInt;
        public String fieldString;

    }

}
