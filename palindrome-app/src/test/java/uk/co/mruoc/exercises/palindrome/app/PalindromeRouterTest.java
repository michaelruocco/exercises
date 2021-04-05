package uk.co.mruoc.exercises.palindrome.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.co.mruoc.exercises.palindrome.client.PalindromeClient;
import uk.co.mruoc.exercises.palindrome.domain.PalindromeResult;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PalindromeRouterTest {

    @LocalServerPort
    private int port;

    @Test
    void shouldReturnInputInResponse() {
        String input = "my-input";
        PalindromeClient client = toClient(port);

        Optional<PalindromeResult> result = client.isPalindrome(input).blockOptional();

        assertThat(result).map(PalindromeResult::getInput).contains(input);
    }

    @Test
    void shouldReturnReversedInputInResponse() {
        String input = "abcdef";
        PalindromeClient client = toClient(port);

        Optional<PalindromeResult> result = client.isPalindrome(input).blockOptional();

        assertThat(result).map(PalindromeResult::getReversed).contains("fedcba");
    }

    @Test
    void shouldReturnFalseIfInputIsNotPalindrome() {
        String input = "non-palindrome-input";
        PalindromeClient client = toClient(port);

        Optional<PalindromeResult> result = client.isPalindrome(input).blockOptional();

        assertThat(result).map(PalindromeResult::isPalindrome).contains(false);
    }

    @Test
    void shouldReturnTrueIfInputIsPalindrome() {
        String input = "racecar";
        PalindromeClient client = toClient(port);

        Optional<PalindromeResult> result = client.isPalindrome(input).blockOptional();

        assertThat(result).map(PalindromeResult::isPalindrome).contains(true);
    }

    private static PalindromeClient toClient(int port) {
        return new PalindromeClient(String.format("http://localhost:%d", port));
    }

}
