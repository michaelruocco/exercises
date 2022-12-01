package uk.co.mruoc.exercises.toptrumps.category.footballer;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.exercises.toptrumps.game.card.Card;
import uk.co.mruoc.exercises.toptrumps.game.card.Deck;
import uk.co.mruoc.exercises.toptrumps.game.card.DeckLoader;
import uk.co.mruoc.file.line.ClasspathFileLineLoader;
import uk.co.mruoc.file.line.FileLineLoader;

import java.util.Collection;

@RequiredArgsConstructor
public class FootballerDeckLoader implements DeckLoader {

    private final FileLineLoader loader;
    private final FootballerLineConverter lineConverter;

    public FootballerDeckLoader() {
        this(new ClasspathFileLineLoader(), new FootballerLineConverter());
    }

    public Deck load() {
        return new Deck(load("footballers.csv"));
    }

    public Collection<Card> load(String path) {
        return loader.loadLines(path)
                .stream()
                .skip(1)
                .map(lineConverter::toCard)
                .toList();
    }
}
