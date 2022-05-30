package uk.co.mruoc.exercises.naughtsandcrosses.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class Board {

    @Getter
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

    public String getToken(int x, int y) {
        return getLocation(x, y).getToken();
    }

    public Location getLocation(int x, int y) {
        return getLocation(toKey(x, y));
    }

    public Location getLocation(String key) {
        return Optional.ofNullable(locations.get(key))
                .orElseThrow(() -> new LocationNotFoundException(key));
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
        for (int x = 1; x < size + 1; x++) {
            if (winsColumn(x, token)) {
                return true;
            }
        }
        return false;
    }

    private boolean winsColumn(int x, String token) {
        for (int y = 1; y < size + 1; y++) {
            Location location = getLocation(x, y);
            if (!location.hasToken(token)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasHorizontalWinner(String token) {
        for (int y = 1; y < size + 1; y++) {
            if (winsRow(y, token)) {
                return true;
            }
        }
        return false;
    }

    private boolean winsRow(int y, String token) {
        for (int x = 1; x < size + 1; x++) {
            Location location = getLocation(x, y);
            if (!location.hasToken(token)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasForwardSlashWinner(String token) {
        int y = 1;
        int x = size;
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
        int y = 1;
        int x = 1;
        do {
            Location location = getLocation(x, y);
            if (!location.hasToken(token)) {
                return false;
            }
            y++; x++;
        } while (y < size + 1 && x < size + 1);
        return true;
    }

    private static Map<String, Location> buildLocations(int size) {
        Map<String, Location> locations = new LinkedHashMap<>();
        for (int y = 1; y < size + 1; y++) {
            for (int x = 1; x < size + 1; x++) {
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
