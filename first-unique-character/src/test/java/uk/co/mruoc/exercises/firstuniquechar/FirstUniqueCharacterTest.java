package uk.co.mruoc.exercises.firstuniquechar;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class FirstUniqueCharacterTest {

    private final FirstUniqueCharacter firstUniqueCharacter = new FirstUniqueCharacter();

    @Test
    void shouldFindFirstUniqueCharacterInString() {
        String input = "AABBCDE";

        Optional<Character> firstUnique = firstUniqueCharacter.find(input);

        assertThat(firstUnique).contains('C');
    }

    @Test
    void shouldReturnEmptyIfStringDoesNotHaveAnyUniqueCharacters() {
        String input = "AABB";

        Optional<Character> firstUnique = firstUniqueCharacter.find(input);

        assertThat(firstUnique).isEmpty();
    }

    @Test
    void shouldThrowExceptionIfNullInputStringPassed() {
        String input = null;

        Throwable error = catchThrowable(() -> firstUniqueCharacter.find(input));

        assertThat(error)
                .isInstanceOf(NoUniqueCharactersException.class)
                .hasMessage("cannot pass null string");
    }

    @Test
    void shouldForceFindFirstUniqueCharacterInString() {
        String input = "ABABCCDDE";

        char firstUnique = firstUniqueCharacter.forceFind(input);

        assertThat(firstUnique).isEqualTo('E');
    }

    @Test
    void shouldThrowExceptionIfForceFindAndStringDoesNotHaveAnyUniqueCharacters() {
        String input = "AA";

        Throwable error = catchThrowable(() -> firstUniqueCharacter.forceFind(input));

        assertThat(error)
                .isInstanceOf(NoUniqueCharactersException.class)
                .hasMessage(input);
    }

    @Test
    void shouldHandleInLongString() {
        char expected = '@';
        String input = StringUtils.repeat("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 2500000) + expected;

        Optional<Character> firstUnique = firstUniqueCharacter.find(input);

        assertThat(firstUnique).contains(expected);
    }

}
