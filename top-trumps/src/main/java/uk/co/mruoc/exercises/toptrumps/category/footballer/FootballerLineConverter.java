package uk.co.mruoc.exercises.toptrumps.category.footballer;

import com.neovisionaries.i18n.CountryCode;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.exercises.toptrumps.category.LineConverter;
import uk.co.mruoc.exercises.toptrumps.game.card.Card;
import uk.co.mruoc.exercises.toptrumps.game.card.Field;
import uk.co.mruoc.exercises.toptrumps.game.card.attribute.Attribute;
import uk.co.mruoc.exercises.toptrumps.game.card.attribute.NumericHighestWinsAttribute;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class FootballerLineConverter implements LineConverter {

    private final DateTimeFormatter dateFormatter;

    public FootballerLineConverter() {
        this(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public Card toCard(String line) {
        String[] fields = line.split(",");
        return Card.builder()
                .id(Long.parseLong(fields[0]))
                .fields(toFields(fields))
                .attributes(toAttributes(fields))
                .build();
    }

    private Collection<Field> toFields(String[] fields) {
        return List.of(
                new Field("name", fields[1]),
                new Field("dateOfBirth", parseDate(fields[2]).toString()),
                new Field("nationality", parseNationality(fields[3]).toString())
        );
    }

    private Collection<Attribute> toAttributes(String[] fields) {
        return List.of(
                new NumericHighestWinsAttribute("pace", Double.parseDouble(fields[4])),
                new NumericHighestWinsAttribute("shooting", Double.parseDouble(fields[5])),
                new NumericHighestWinsAttribute("passing", Double.parseDouble(fields[6])),
                new NumericHighestWinsAttribute("tackling", Double.parseDouble(fields[7])),
                new NumericHighestWinsAttribute("stamina", Double.parseDouble(fields[8])),
                new NumericHighestWinsAttribute("dribbling", Double.parseDouble(fields[9]))
        );
    }

    private LocalDate parseDate(String value) {
        return LocalDate.parse(value, dateFormatter);
    }

    private CountryCode parseNationality(String value) {
        return CountryCode.getByAlpha3Code(value);
    }
}
