package uk.co.mruoc.exercises.palindrome.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.exercises.palindrome.domain.PalindromeRequest;

@Builder
@RequiredArgsConstructor
@Data
@JsonDeserialize(using = ApiPalindromeRequestDeserializer.class)
public class ApiPalindromeRequest implements PalindromeRequest {

    private static final int DEFAULT_MIN_LENGTH = 3;

    private final String input;
    private final int minLength;

    public ApiPalindromeRequest(String input) {
        this(input, DEFAULT_MIN_LENGTH);
    }
}
