package uk.co.mruoc.exercises.palindrome.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonDeserialize(using = PalindromeResultDeserializer.class)
public class PalindromeResult {

    private final String input;
    private final String reversed;
    private final boolean palindrome;

}
