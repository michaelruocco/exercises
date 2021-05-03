# Cron Parser

Project requires Java 15 or greater

Parses Cron Expressions of the following format:

*   (Minute) (hour) (day of month) (month) (day of week) (command)
*   `*` means all possible time units
*   `-` a range of time units
*   `,` a comma separated list of individual time units
*   `/` intervals time units, the left value is the starting value and the right value is the max value

For example given the input argument:

`*/15 0 1,15 * 1-5 /usr/bin/find`

The output should be:

```bash
minute 0 15 30 45
hour 0
day of month 1 15
month 1 2 3 4 5 6 7 8 9 10 11 12
day of week 1 2 3 4 5
command /usr/bin/find
```

## Running from gradle

`./gradlew run --args="*/15 0 1,15 * 1-5 /usr/bin/find"`

or

`./gradlew run --args="-arguments */15 0 1,15 * 1-5 /usr/bin/find"`

## Running built jar

The build will build two jars:

1.  A plain jar that is built for use a library at build/libs/cron-parser.jar
2.  A shadow jar that is built for running as a standalone application build/libs/cron-parser-all.jar

To run the shadow jar if you are planning to pass any arguments with `*` notation you may
need to configure your terminal to disable globbing. I found that using zsh shell on my mac this was
and issue. If I try to run the following command:

`java -jar cron-parser/build/libs/cron-parser-all.jar */15 0 1,15 * 1-5 /usr/bin/find`

or

`java -jar cron-parser/build/libs/cron-parser-all.jar -arguments */15 0 1,15 * 1-5 /usr/bin/find`

Then the following output is returned:

`usage: please provide a valid cron expression, invalid cron expression provided */15 0 1,15 LICENSE README.md area-of-circle binary-gap build build.gradle cake-factory cron-parser first-unique-character gradle gradlew gradlew.bat instruction-processing lombok.config matrix-transpose palindrome-app reactive-batch-processor settings.gradle smallest-missing-positive-integer 1-5 /usr/bin/find`

As you can see the * command has been expanded to include all the files and folders
from the current directory where the command is being run from. This can be fixed by
running the following command to disable globbing:

`set -o noglob`

Once globbing has been disabled, when the jar command list above is run again the following output will
be displayed:

```shell
minute        0 15 30 45
hour          0
day of month  1 15
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   1 2 3 4 5
command       /usr/bin/find
```
