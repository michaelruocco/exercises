package uk.co.mruoc.exercises.cronparser;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public class ArgumentParser {

    public Optional<String> toExpression(String[] args) {
        String expression = StringUtils.join(args, " ").trim();
        if (StringUtils.isEmpty(expression)) {
            return Optional.empty();
        }
        return Optional.of(expression);
    }

}
