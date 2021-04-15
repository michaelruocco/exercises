# Cron Parser

Parses Cron Expressions of the following format:

*   (Minute) (hour) (day of month) (month) (day of week) (command)
*   `*` means all possible time units
*   `-` a range of time units
*   `,` a comma separated list of individual time units
*   `/` increments of a time unit, the left value is the starting value and the right value is the max value

For example given the input argument:

`*/15 0 1,15 * 1-5 /usr/bin/find`

The output should be:

```
minute 0 15 30 45
hour 0
day of month 1 15
month 1 2 3 4 5 6 7 8 9 10 11 12
day of week 1 2 3 4 5
command /usr/bin/find
```