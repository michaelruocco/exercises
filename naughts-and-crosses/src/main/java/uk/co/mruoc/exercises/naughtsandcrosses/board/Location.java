package uk.co.mruoc.exercises.naughtsandcrosses.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
@Data
public class Location {

    private static final String FREE_TOKEN = "-";

    private final String key;
    private String token;

    public Location(String key) {
        this(key, FREE_TOKEN);
    }

    public boolean isFree() {
        return hasToken(FREE_TOKEN);
    }

    public boolean hasToken(String tokenToCheck) {
        return token.equals(tokenToCheck);
    }
}
