package uk.co.mruoc.exercises.palindrome.app;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import uk.co.mruoc.exercises.palindrome.domain.PalindromeResult;
import uk.co.mruoc.exercises.palindrome.domain.PalindromeCalculator;

@Slf4j
@RequiredArgsConstructor
public class PalindromeHandler {

    private final PalindromeCalculator calculator;

    public Mono<ServerResponse> isPalindrome(ServerRequest request) {
        return isPalindrome(request.pathVariable("input"));
    }

    private Mono<ServerResponse> isPalindrome(String input) {
        log.info("received request with input value {}", input);
        PalindromeResult result = calculator.isPalindrome(input);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(result));
    }

}
