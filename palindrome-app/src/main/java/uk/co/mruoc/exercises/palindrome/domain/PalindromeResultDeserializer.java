package uk.co.mruoc.exercises.palindrome.domain;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Collection;

public class PalindromeResultDeserializer extends StdDeserializer<PalindromeResult> {

    private static final TypeReference<Collection<String>> STRING_COLLECTION = new TypeReference<>() {
        // intentionally blank
    };

    protected PalindromeResultDeserializer() {
        super(PalindromeResult.class);
    }

    @Override
    public PalindromeResult deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        return PalindromeResult.builder()
                .input(node.get("input").asText())
                .palindromes(node.get("palindromes").traverse(parser.getCodec()).readValueAs(STRING_COLLECTION))
                .build();
    }
}
