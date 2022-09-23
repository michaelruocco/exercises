package uk.co.mruoc.exercises.palindrome.client;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import uk.co.mruoc.exercises.palindrome.api.ApiPalindromeRequest;
import uk.co.mruoc.exercises.palindrome.api.ApiPalindromeResponse;

public class PalindromeClient {

    private final WebClient client;

    public PalindromeClient(String baseUri) {
        this(WebClient.create(baseUri));
    }

    public PalindromeClient(WebClient client) {
        this.client = client;
    }

    public Mono<ApiPalindromeResponse> findPalindromes(ApiPalindromeRequest request) {
        return client.post().uri("/palindromes")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ApiPalindromeResponse.class);
    }
}
