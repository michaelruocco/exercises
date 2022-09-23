
package uk.co.mruoc.exercises.palindrome.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class ApiPalindromeRequestDeserializer extends StdDeserializer<ApiPalindromeRequest> {

    protected ApiPalindromeRequestDeserializer() {
        super(ApiPalindromeRequest.class);
    }

    @Override
    public ApiPalindromeRequest deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        return ApiPalindromeRequest.builder()
                .input(node.get("input").asText())
                .minLength(node.get("minLength").asInt())
                .build();
    }
}
