package uk.co.mruoc.exercises.palindrome.domain;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class PalindromeResultDeserializer extends StdDeserializer<PalindromeResult> {

    protected PalindromeResultDeserializer() {
        super(PalindromeResult.class);
    }

    @Override
    public PalindromeResult deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        return PalindromeResult.builder()
                .input(node.get("input").asText())
                .reversed(node.get("reversed").asText())
                .palindrome(node.get("palindrome").asBoolean())
                .build();
    }

}
