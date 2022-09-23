package uk.co.mruoc.exercises.palindrome.app;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import uk.co.mruoc.exercises.palindrome.api.ApiPalindromeRequest;
import uk.co.mruoc.exercises.palindrome.api.ApiPalindromeResponse;
import uk.co.mruoc.exercises.palindrome.domain.PalindromeFinder;

@Slf4j
@RequiredArgsConstructor
public class PalindromeHandler {

    private final PalindromeFinder calculator;

    @NonNull
    public Mono<ServerResponse> findPalindromes(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(ApiPalindromeRequest.class)
                .flatMap(this::findPalindromes);
    }

    private Mono<ServerResponse> findPalindromes(ApiPalindromeRequest request) {
        log.info("received request palindrome request {}", request);
        ApiPalindromeResponse response = ApiPalindromeResponse.builder()
                .input(request.getInput())
                .filter(request.getFilter())
                .minLength(request.getMinLength())
                .palindromes(calculator.findPalindromes(request))
                .build();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(response));
    }


}
