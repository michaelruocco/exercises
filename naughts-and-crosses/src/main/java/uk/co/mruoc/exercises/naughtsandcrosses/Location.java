package uk.co.mruoc.exercises.naughtsandcrosses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Location implements Comparable<Location> {

    private static final String FREE_TOKEN = "-";

    private final String key;

    @Getter
    @Setter
    private String token;

    public Location(String key) {
        this(key, FREE_TOKEN);
    }

    public boolean isFree() {
        return token.equals(FREE_TOKEN);
    }

    @Override
    public int compareTo(Location location) {
        return key.compareTo(location.key);
    }

}
