# Channel Processing

This is another interview question I have come across in the past, the problem was
stated as follows:

## Problem Statement

A channel processing system to augment measured data and to calculate metrics is required.
The system will have a mechanism for reading and writing channels and metrics, and a mechanism for reading parameters.
The functions that will process the channel data have the form outputs = function(parameters, inputs).

The functions for this example are given below:

### Function 1

Y=mX+c

### Function 2

B=A+Y
b=mean(B)

### Function 3

A=1/X

### Function 4

C=X+b

### Other information

Channels are arrays of data and are denoted with a capital letter, e.g. Y.
Metrics and parameters are scalars and are denoted with a lowercase letter, e.g. m.
Note that b is the only metric in the above example. X, m and c are known.

Can you design and implement a flexible and generic system for processing channel data
using the above functions? Feel free to use a language of your choice.

From the channels.txt and parameters.txt files provided, what is the value of the metric b?

## Building and testing the application

## Running the application

If you want to specify different input files you can provide the paths to those files
as command line arguments by using the following command template

```bash
./gradlew run {path-to-channel-file} {path-to-parameters-file} {path-to-functions-file} {output-targets}
```

The output-targets argument can be a single channel, metric or parameter, (e.g. b) or a comma separated list
of multiple ids (e.g. Y,b)

e.g.

```bash
./gradlew run --args="-c ./src/main/resources/channels.txt \
    -p ./src/main/resources/parameters.txt \
    -f ./src/main/resources/functions-metric-b.csv \
    -o b"
```