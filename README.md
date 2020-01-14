# TSP
Project in Java to solve the Travelling Salesman Problem (TSP). An approximation solution with a self-organizing maps (SOM) is proposed.

![som-xqf131-solution](https://raw.githubusercontent.com/ansegura7/TSP/master/images/som-xqf131.gif)

TSP Solver GUI
![tsp-solver-gui](https://raw.githubusercontent.com/ansegura7/TSP/master/images/gui.PNG)

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

| TSP File | # Points | Optimal Tour | Curr Tour | MAPE (%) | Elapsed Time (ms) |
| -- | -- | -- | -- | -- | -- |
| XQF131  |  131 |  564 |   598.915 |  6.1906 |   245 | 
| XQG237  |  237 | 1019 | 1108.2272 |  8.7563 |   714 | 
| PMA343  |  343 | 1368 | 1464.7101 |  7.0695 |  1571 | 
| XQL662  |  662 | 2513 | 2804.3936 | 11.5954 |  4941 | 
| RBX711  |  711 | 3115 | 3428.1617 | 10.0533 |  5806 | 
| PBD984  |  984 | 2797 | 3102.7567 | 10.9316 | 10225 | 
| DKA1376 | 1376 | 4666 | 5242.9936 | 12.3659 | 19565 | 
| DJA1436 | 1436 | 5257 | 5855.2285 | 11.3797 | 21390 | 
| DJC1785 | 1785 | 6115 | 6864.0603 | 12.2496 | 32591 | 
| DCB2086 | 2086 | 6600 | 7452.5379 | 12.9172 | 44726 | 

With a weighted average MAPE of 11.5946 %. Below, the solution accuracy in one chart:

![solution accuracy](https://raw.githubusercontent.com/ansegura7/TSP/master/images/results1.PNG)

And the time complexity of the solution is:

![time complexity](https://raw.githubusercontent.com/ansegura7/TSP/master/images/results2.PNG)

Experimentally, it was calculated that the computational complexity of the solution is quadratic, which is better than the factorial time (natural behavior of the problem).

## Program Execution Rules
The repository has an executable file in the **jar** folder. The .JAR program must be run with Java 8 or higher.

The program can be run in 2 modes: GUI or TESTS. Below, some examples of how to call the program in each mode.

**GUI mode example:** java -jar TSP_Solver-vX param-mode
``` console
    java -jar TSP_Solver-v0.6.jar
    java -jar TSP_Solver-v0.6.jar GUI
```

**Tests mode example:** java -jar TSP_Solver-vX param-mode param-directory param-algorithm param-number-tests
``` console
    java -jar TSP_Solver-v0.6.jar TESTS
    java -jar TSP_Solver-v0.6.jar TESTS Project/TSP/ SOM
    java -jar TSP_Solver-v0.6.jar TESTS Project/TSP/ SOM
    java -jar TSP_Solver-v0.6.jar TESTS Project/TSP/ SOM 5
```

The project was developed in the Eclipse IDE, and is located in the **code** folder.

## Contributing and Feedback
Any kind of feedback/criticism would be greatly appreciated (algorithm design, documentation, improvement ideas, spelling mistakes, etc...).

## Authors
- Created by Andrés Segura Tinoco
- Created on Jan 02, 2020

## License
This project is licensed under the terms of the MIT license.
