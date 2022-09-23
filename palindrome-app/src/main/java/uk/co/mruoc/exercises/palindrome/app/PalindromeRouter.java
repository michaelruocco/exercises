package uk.co.mruoc.exercises.palindrome.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import uk.co.mruoc.exercises.palindrome.domain.PalindromeFinder;

@Configuration
public class PalindromeRouter {

    @Bean
    public RouterFunction<ServerResponse> route(PalindromeHandler handler) {
        RequestPredicate predicate = RequestPredicates.POST("/palindromes");
        return RouterFunctions.route(predicate, handler::findPalindromes);
    }

    @Bean
    public PalindromeHandler handler(PalindromeFinder calculator) {
        return new PalindromeHandler(calculator);
    }

    @Bean
    public PalindromeFinder calculator() {
        return new PalindromeFinder();
    }
}
