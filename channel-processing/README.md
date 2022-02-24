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