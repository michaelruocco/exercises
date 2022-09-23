
package uk.co.mruoc.exercises.palindrome.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Collection;

public class ApiPalindromeResponseDeserializer extends StdDeserializer<ApiPalindromeResponse> {

    private static final TypeReference<Collection<String>> STRING_COLLECTION = new TypeReference<>() {
        // intentionally blank
    };

    protected ApiPalindromeResponseDeserializer() {
        super(ApiPalindromeResponse.class);
    }

    @Override
    public ApiPalindromeResponse deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        return ApiPalindromeResponse.builder()
                .input(node.get("input").asText())
                .minLength(node.get("minLength").asInt())
                .palindromes(node.get("palindromes").traverse(parser.getCodec()).readValueAs(STRING_COLLECTION))
                .build();
    }
}
