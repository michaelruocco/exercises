package uk.co.mruoc.exercises.palindrome.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.co.mruoc.exercises.palindrome.api.ApiPalindromeRequest;
import uk.co.mruoc.exercises.palindrome.api.ApiPalindromeRequest.ApiPalindromeRequestBuilder;
import uk.co.mruoc.exercises.palindrome.api.ApiPalindromeResponse;
import uk.co.mruoc.exercises.palindrome.client.PalindromeClient;


import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.exercises.palindrome.domain.filter.Filter.REMOVE_DUPLICATES;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PalindromeRouterTest {

    private final ApiPalindromeRequestBuilder requestBuilder = ApiPalindromeRequest.builder()
            .minLength(3)
            .filter(REMOVE_DUPLICATES);

    @LocalServerPort
    private int port;

    @Test
    void shouldReturnEmptyPalindromeIfInputDoesNotContainPalindrome() {
        PalindromeClient client = toClient(port);
        ApiPalindromeRequest request = requestBuilder.input("my input").build();

        ApiPalindromeResponse response = requireNonNull(client.findPalindromes(request).block());

        assertThat(response.getPalindromes()).isEmpty();
    }

    @Test
    void shouldReturnPalindromeIfInputIsPalindrome() {
        PalindromeClient client = toClient(port);
        ApiPalindromeRequest request = requestBuilder.input("racecar").build();

        ApiPalindromeResponse response = requireNonNull(client.findPalindromes(request).block());

        assertThat(response.getPalindromes()).contains(request.getInput());
    }

    @Test
    void shouldReturnPalindromeIfInputContainsPalindrome() {
        PalindromeClient client = toClient(port);
        ApiPalindromeRequest request = requestBuilder.input("was it a palindrome, or was it a rat i saw in here").build();

        ApiPalindromeResponse response = requireNonNull(client.findPalindromes(request).block());

        assertThat(response.getPalindromes()).contains("wasitaratisaw");
    }

    private static PalindromeClient toClient(int port) {
        return new PalindromeClient(String.format("http://localhost:%d", port));
    }

}
