package uk.co.mruoc.exercises.channelprocessing;

import com.beust.jcommander.Parameter;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class AppArgs {

    @Parameter(names = {"-c", "-channels-path"}, description = "Path to channels file to use", required = true)
    private String channelsPath;

    @Parameter(names = {"-p", "-parameters-path"}, description = "Path to parameters file to use", required = true)
    private String parametersPath;

    @Parameter(names = {"-f", "-functions-path"}, description = "Path to functions file to use", required = true)
    private String functionsPath;

    @Parameter(names = {"-o", "-output-targets"}, description = "Target values to print once functions have completed", required = true)
    private String outputTargets;

    public Collection<Character> getOutputTargetsCollection() {
        return Arrays.stream(outputTargets.split(","))
                .map(StringUtils::deleteWhitespace)
                .map(target -> target.charAt(0))
                .collect(Collectors.toList());
    }
}
