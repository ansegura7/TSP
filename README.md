# TSP
Project in Java to solve the Travelling Salesman Problem (TSP). The first solution approach is done with a self-organized map (SOM).

![som-xqf131 solution](https://raw.githubusercontent.com/ansegura7/TSP/master/images/som-xqf131.gif)

## Algorithms
- Self-organizing maps

## Data
TSP files from <a href="http://elib.zib.de/pub/mp-testdata/tsp/tsplib/tsp/index.html" target="_blank">TSPLIB</a> in the following format:

```
NAME : a280
COMMENT : drilling problem (Ludwig)
TYPE : TSP
DIMENSION: 280
EDGE_WEIGHT_TYPE : EUC_2D
NODE_COORD_SECTION
  1 288 149
  2 288 129
  3 270 133
  4 256 141
  5 256 157
  6 246 157
  7 236 169
  .
  .
  .
  EOF
```

# Results
Next, the results of 10 different TSP files using the SOM algorithm implementation. The results are calculated after executing and averaging the 10 cases 5 times.

| TSP File | # Points | Best Tout | Curr Tour | MAPE (%) | Elapsed Time (ms) |
| -- | -- | -- | -- | -- | -- |
| XQF131  | 131  |  564.00 | 607.8345  |  7.7721 |    73 |
| XQG237  | 237  | 1019.00 | 1100.4472 |  7.9929 |   215 |
| PMA343  | 343  | 1368.00 | 1448.5932 |  5.8913 |   442 |
| XQL662  | 662  | 2513.00 | 2870.8841 | 14.2413 |  1736 |
| RBX711  | 711  | 3115.00 | 3461.0800 | 11.1101 |  2044 |
| PBD984  | 984  | 2797.00 | 3129.5691 | 11.8902 |  4204 |
| DKA1376 | 1376 | 4666.00 | 5305.0492 | 13.6959 |  9393 |
| DJA1436 | 1436 | 5257.00 | 5878.6449 | 11.8251 |  9276 |
| DJC1785 | 1785 | 6115.00 | 6930.8129 | 13.3412 | 14230 |
| DCB2086 | 2086 | 6600.00 | 7526.3571 | 14.0357 | 21605 |

With a weighted average MAPE of: 12.6017 %. Below, the solution accuracy in one chart:

![solution accuracy](https://raw.githubusercontent.com/ansegura7/TSP/master/images/results1.PNG)

The time complexity of this solution is:

![time complexity](https://raw.githubusercontent.com/ansegura7/TSP/master/images/results2.PNG)

## Program Execution Rules
The project has an executable in the 'jar' folder.

Execution example:
``` console
    java -jar TSP_Solver-v0.9.jar
```
The .JAR program must be run with Java 7 or higher.

## Contributing and Feedback
Any kind of feedback/criticism would be greatly appreciated (algorithm design, documentation, improvement ideas, spelling mistakes, etc...).

## Authors
- Created by Andrés Segura Tinoco
- Created on Jan 02, 2020

## License
This project is licensed under the terms of the MIT license.
