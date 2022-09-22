package uk.co.mruoc.exercises.palindrome.app;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import uk.co.mruoc.exercises.palindrome.domain.PalindromeResult;
import uk.co.mruoc.exercises.palindrome.domain.PalindromeFinder;

@Slf4j
@RequiredArgsConstructor
public class PalindromeHandler {

    private final PalindromeFinder calculator;

    @NonNull
    public Mono<ServerResponse> isPalindrome(ServerRequest request) {
        return isPalindrome(request.pathVariable("input"));
    }

    private Mono<ServerResponse> isPalindrome(String input) {
        log.info("received request with input value {}", input);
        PalindromeResult result = PalindromeResult.builder()
                .input(input)
                .palindromes(calculator.findPalindromes(input))
                .build();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(result));
    }

}
