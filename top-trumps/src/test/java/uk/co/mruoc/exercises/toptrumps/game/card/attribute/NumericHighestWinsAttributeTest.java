package uk.co.mruoc.exercises.toptrumps.game.card.attribute;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.exercises.toptrumps.game.PlayedCard;
import uk.co.mruoc.exercises.toptrumps.game.Player;
import uk.co.mruoc.exercises.toptrumps.game.PlayerMother;
import uk.co.mruoc.exercises.toptrumps.game.card.AttributeMother;
import uk.co.mruoc.exercises.toptrumps.game.card.CardMother;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class NumericHighestWinsAttributeTest {

    private static final Player PLAYER_1 = PlayerMother.withName("Player 1");
    private static final Player PLAYER_2 = PlayerMother.withName("Player 2");

    private final Attribute dribbling = AttributeMother.dribbling(50);

    @Test
    void shouldReturnPlayerWithCardWithHighestMatchingAttributeAsWinner() {
        PlayedCard card1 = PlayedCard.builder()
                .player(PLAYER_1)
                .card(CardMother.withAttribute(AttributeMother.dribbling(29)))
                .build();
        PlayedCard card2 = PlayedCard.builder()
                .player(PLAYER_2)
                .card(CardMother.withAttribute(AttributeMother.dribbling(30)))
                .build();
        Collection<PlayedCard> cards = List.of(card1, card2);

        Optional<Player> winner = dribbling.calculateWinner(cards);

        assertThat(winner).contains(card2.getPlayer());
    }

    @Test
    void shouldReturnEmptyWinnerIfMoreThanOneCardHasHighestMatchingAttributeValue() {
        PlayedCard card1 = PlayedCard.builder()
                .player(PLAYER_1)
                .card(CardMother.withAttribute(AttributeMother.dribbling(48)))
                .build();
        PlayedCard card2 = PlayedCard.builder()
                .player(PLAYER_2)
                .card(CardMother.withAttribute(AttributeMother.dribbling(49)))
                .build();
        PlayedCard card3 = PlayedCard.builder()
                .player(PlayerMother.withName("Player 3"))
                .card(CardMother.withAttribute(AttributeMother.dribbling(49)))
                .build();
        Collection<PlayedCard> cards = List.of(card1, card2, card3);

        Optional<Player> winner = dribbling.calculateWinner(cards);

        assertThat(winner).isEmpty();
    }

    @Test
    void shouldReturnEmptyWinnerIfCardsEmpty() {
        Collection<PlayedCard> cards = Collections.emptyList();

        Optional<Player> winner = dribbling.calculateWinner(cards);

        assertThat(winner).isEmpty();
    }

    @Test
    void shouldThrowExceptionIfCardDoesNotMatchAttribute() {
        PlayedCard card1 = PlayedCard.builder()
                .player(PLAYER_1)
                .card(CardMother.withAttribute(AttributeMother.shooting(48)))
                .build();
        Collection<PlayedCard> cards = List.of(card1);

        Throwable error = catchThrowable(() -> dribbling.calculateWinner(cards));

        assertThat(error)
                .isInstanceOf(AttributeNotFoundException.class)
                .hasMessage(dribbling.getName());
    }

    @Test
    void shouldThrowExceptionIfAttemptToGetValueAsWrongType() {
        Attribute attribute = AttributeMother.shooting(20);

        Throwable error = catchThrowable(() -> attribute.getValueAs(String.class));

        assertThat(error)
                .isInstanceOf(InvalidAttributeTypeException.class)
                .hasMessage("invalid type class java.lang.String for attribute shooting with value 20.0");
    }
}
