package uk.co.mruoc.exercises.naughtsandcrosses;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Board {

    private final int size;
    private final Collection<Location> locations;

    public Board() {
        this(3);
    }

    public Board(int size) {
        this(size, buildRandomLocations(size));
    }

    public void placeToken(String token) {
        Location freeLocation = getNextFreeLocation()
                .orElseThrow(NoLocationsRemainingException::new);
        freeLocation.setToken(token);
    }

    public boolean hasLocationsRemaining() {
        return getNextFreeLocation().isPresent();
    }

    private Optional<Location> getNextFreeLocation() {
        return locations.stream()
                .filter(Location::isFree)
                .findFirst();
    }

    public String getState() {
        return String.join(System.lineSeparator(), buildRows());
    }

    public Collection<String> buildRows() {
        List<String> tokens = locations.stream()
                .sorted()
                .map(Location::getToken)
                .collect(Collectors.toList());
        return toRows(tokens);
    }

    private Collection<String> toRows(List<String> tokens) {
        List<String> rows = new ArrayList<>();
        while (!tokens.isEmpty()) {
            List<String> row = tokens.subList(0, size);
            rows.add(String.join(" ", row));
            row.clear();
        }
        return rows;
    }

    private static Collection<Location> buildRandomLocations(int size) {
        List<Location> locations = buildLocations(size);
        Collections.shuffle(locations);
        return locations;
    }

    private static List<Location> buildLocations(int size) {
        List<Location> locations = new ArrayList<>();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                locations.add(new Location(toKey(x, y)));
            }
        }
        return locations;
    }

    private static String toKey(int x, int y) {
        return String.format("%s-%s", x, y);
    }

}
