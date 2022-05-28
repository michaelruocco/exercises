package uk.co.mruoc.exercises.naughtsandcrosses.board;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class Board {

    private final int size;
    private final Map<String, Location> locations;

    public Board() {
        this(3);
    }

    public Board(int size) {
        this(size, buildLocations(size));
    }

    public Location selectLocation(SelectLocationStrategy strategy) {
        return strategy.selectLocation(this);
    }

    public Location getLocation(int x, int y) {
        return getLocation(toKey(x, y));
    }

    public Location getLocation(String key) {
        return locations.get(key);
    }

    public Location getRandomFreeLocation() {
        List<Location> randomLocations = getFreeLocations().collect(Collectors.toList());
        Collections.shuffle(randomLocations);
        return randomLocations.stream()
                .findFirst()
                .orElseThrow(NoLocationsRemainingException::new);
    }

    public Location getNextFreeLocation() {
        return getFreeLocations()
                .findFirst()
                .orElseThrow(NoLocationsRemainingException::new);
    }

    public boolean isFull() {
        return getFreeLocations().findAny().isEmpty();
    }

    public String getState() {
        Collection<String> rows = new ArrayList<>();
        for (int y = 0; y < size; y++) {
            Collection<String> tokens = new ArrayList<>();
            for (int x = 0; x < size; x++) {
                tokens.add(getLocation(x, y).getToken());
            }
            rows.add(String.join(" ", tokens));
        }
        return rows.stream().collect(Collectors.joining(System.lineSeparator()));
    }

    public boolean hasWinner(String token) {
        if (hasVerticalWinner(token)) {
            return true;
        }
        if (hasHorizontalWinner(token)) {
            return true;
        }
        if (hasForwardSlashWinner(token)) {
            return true;
        }
        return hasBackSlashWinner(token);
    }

    private Stream<Location> getFreeLocations() {
        return locations.values()
                .stream()
                .filter(Location::isFree);
    }

    private boolean hasVerticalWinner(String token) {
        for (int x = 0; x < size; x++) {
            if (winsColumn(x, token)) {
                return true;
            }
        }
        return false;
    }

    private boolean winsColumn(int x, String token) {
        for (int y = 0; y < size; y++) {
            Location location = getLocation(x, y);
            if (!location.hasToken(token)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasHorizontalWinner(String token) {
        for (int y = 0; y < size; y++) {
            if (winsRow(y, token)) {
                return true;
            }
        }
        return false;
    }

    private boolean winsRow(int y, String token) {
        for (int x = 0; x < size; x++) {
            Location location = getLocation(x, y);
            if (!location.hasToken(token)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasForwardSlashWinner(String token) {
        int y = 0;
        int x = size - 1;
        do {
            Location location = getLocation(x, y);
            if (!location.hasToken(token)) {
                return false;
            }
            y++; x--;
        } while (y <= size && x >= 0);
        return true;
    }

    private boolean hasBackSlashWinner(String token) {
        int y = 0;
        int x = 0;
        do {
            Location location = getLocation(x, y);
            if (!location.hasToken(token)) {
                return false;
            }
            y++; x++;
        } while (y < size && x < size);
        return true;
    }

    private static Map<String, Location> buildLocations(int size) {
        Map<String, Location> locations = new LinkedHashMap<>();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                Location location = new Location(toKey(x, y));
                locations.put(location.getKey(), location);
            }
        }
        return locations;
    }

    private static String toKey(int x, int y) {
        return String.format("%s-%s", x, y);
    }
}
