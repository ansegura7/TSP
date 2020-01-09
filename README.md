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
Below, the results for 10 different TSP files using the SOM algorithm implementation.

| TSP File | # Points | Best Tout | Curr Tour | MAPE (%) | Elapsed Time (ms) |
| -- | -- | -- | -- | -- | -- |
| XQF131  | 131  |  564.00 |  603.0747 | 6.9281  |    91 |
| XQG237  | 237  | 1019.00 | 1098.7306 | 7.8244  |   281 |
| PMA343  | 343  | 1368.00 | 1466.9586 | 7.2338  |   500 |
| XQL662  | 662  | 2513.00 | 2874.2065 | 14.3735 |  1928 |
| RBX711  | 711  | 3115.00 | 3466.4948 | 11.2839 |  2055 |
| PBD984  | 984  | 2797.00 | 3125.5329 | 11.7459 |  4280 |
| DKA1376 | 1376 | 4666.00 | 5310.5833 | 13.8145 |  8450 |
| DJA1436 | 1436 | 5257.00 | 5907.1585 | 12.3675 |  9232 |
| DJC1785 | 1785 | 6115.00 | 6908.7349 | 12.9801 | 14493 |
| DCB2086 | 2086 | 6600.00 | 7516.3060 | 13.8834 | 21505 |

![som-xqf131 solution](https://raw.githubusercontent.com/ansegura7/TSP/master/images/results1.PNG)

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
