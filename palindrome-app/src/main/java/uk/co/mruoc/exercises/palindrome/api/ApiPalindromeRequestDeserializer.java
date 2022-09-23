
package uk.co.mruoc.exercises.palindrome.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import uk.co.mruoc.exercises.palindrome.domain.filter.Filter;

import java.io.IOException;
import java.util.Optional;

import static uk.co.mruoc.exercises.palindrome.domain.filter.Filter.REMOVE_DUPLICATES;

public class ApiPalindromeRequestDeserializer extends StdDeserializer<ApiPalindromeRequest> {

    private static final int DEFAULT_MIN_LENGTH = 3;
    private static final Filter DEFAULT_FILTER = REMOVE_DUPLICATES;

    protected ApiPalindromeRequestDeserializer() {
        super(ApiPalindromeRequest.class);
    }

    @Override
    public ApiPalindromeRequest deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        return ApiPalindromeRequest.builder()
                .input(node.get("input").asText())
                .minLength(extractMinLength(node))
                .filter(extractFilter(node))
                .build();
    }

    private static int extractMinLength(JsonNode node) {
        return Optional.ofNullable(node.get("minLength"))
                .map(JsonNode::asInt)
                .orElse(DEFAULT_MIN_LENGTH);
    }

    private static Filter extractFilter(JsonNode node) {
        return Optional.ofNullable(node.get("filter"))
                .map(filterNode -> Filter.valueOf(filterNode.asText()))
                .orElse(DEFAULT_FILTER);
    }

}
