package uk.co.mruoc.exercises.palindrome.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import uk.co.mruoc.exercises.palindrome.domain.PalindromeResult;

@RequiredArgsConstructor
public class PalindromeClient {

    private final WebClient client;

    public PalindromeClient(String baseUri) {
        this(WebClient.create(baseUri));
    }

    public Mono<PalindromeResult> isPalindrome(String input) {
        return client.get().uri(toGetIsPalindromeUri(input))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PalindromeResult.class);
    }

    private static String toGetIsPalindromeUri(String input) {
        return String.format("/palindromes/%s", input);
    }

}
