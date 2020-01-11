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
| XQF131  | 131  |  564.00 | 608.8120  | 7.9454  |   146 |
| XQG237  | 237  | 1019.00 | 1092.1591 | 7.1795  |   387 |
| PMA343  | 343  | 1368.00 | 1451.8500 | 6.1294  |   726 |
| XQL662  | 662  | 2513.00 | 2862.8253 | 13.9206 |  2411 |
| RBX711  | 711  | 3115.00 | 3444.4560 | 10.5764 |  2788 |
| PBD984  | 984  | 2797.00 | 3126.9053 | 11.7950 |  5264 |
| DKA1376 | 1376 | 4666.00 | 5267.2621 | 12.8860 |  9926 |
| DJA1436 | 1436 | 5257.00 | 5846.3651 | 11.2111 | 10834 |
| DJC1785 | 1785 | 6115.00 | 6895.1806 | 12.7585 | 17214 |
| DCB2086 | 2086 | 6600.00 | 7444.1573 | 12.7903 | 24017 |

With a weighted average MAPE of 11.9445 %. Below, the solution accuracy in one chart:

![solution accuracy](https://raw.githubusercontent.com/ansegura7/TSP/master/images/results1.PNG)

And the time complexity of the solution is:

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
