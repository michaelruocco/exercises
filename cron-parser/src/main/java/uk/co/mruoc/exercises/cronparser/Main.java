package uk.co.mruoc.exercises.cronparser;

import org.apache.commons.lang3.StringUtils;

public class Main {

    public static void main(String[] args) {
        CronExpressionParser parser = new CronExpressionParser();
        CronResult result = parser.parse(StringUtils.join(args, " "));
        System.out.println(CronResultFormatter.format(result));
    }

}
