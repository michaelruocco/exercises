package uk.co.mruoc.exercises.palindrome.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.co.mruoc.exercises.palindrome.client.PalindromeClient;
import uk.co.mruoc.exercises.palindrome.domain.PalindromeResult;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PalindromeRouterTest {

    @LocalServerPort
    private int port;

    @Test
    void shouldReturnEmptyPalindromeIfInputDoesNotContainPalindrome() {
        String input = "my input";
        PalindromeClient client = toClient(port);

        PalindromeResult result = Objects.requireNonNull(client.findPalindromes(input).block());

        assertThat(result.getPalindromes()).isEmpty();
    }

    @Test
    void shouldReturnPalindromeIfInputIsPalindrome() {
        String input = "racecar";
        PalindromeClient client = toClient(port);

        PalindromeResult result = Objects.requireNonNull(client.findPalindromes(input).block());

        assertThat(result.getPalindromes()).contains(input);
    }

    @Test
    void shouldReturnPalindromeIfInputContainsPalindrome() {
        String input = "was it a palindrome, or was it a rat i saw in here";
        PalindromeClient client = toClient(port);

        PalindromeResult result = Objects.requireNonNull(client.findPalindromes(input).block());

        assertThat(result.getPalindromes()).contains("wasitaratisaw");
    }

    private static PalindromeClient toClient(int port) {
        return new PalindromeClient(String.format("http://localhost:%d", port));
    }

}
