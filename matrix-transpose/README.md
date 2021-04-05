# Matrix Transpose

This is an interview question that I have come across before. The
idea is to write some code that accepts a matrix as a 2D array as an input
and performs an in place (i.e. without creating a copy) transpose on that
matrix.

## 2D Example

An example with a 2D matrix input would be:
```matrix
 1 | 2
---|---
 3 | 4
```

Should be transposed into:
```matrix
 1 | 3
---|---
 2 | 4
```

## 3D Example

An example with a 3D matrix input would be:
```matrix
 1 | 2 | 3
---|---|---
 4 | 5 | 6
---|---|---
 7 | 8 | 9 
```

Should be transposed into:
```matrix
 1 | 4 | 7
---|---|---
 2 | 5 | 8
---|---|---
 3 | 6 | 9 
```

In both cases the top left to bottom right diagonal remains unchanged
whilst the other opposite values are swapped with each other.
