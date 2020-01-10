# TSP Solver

## Authors
- Created by Andrés Segura Tinoco
- Created on Jan 02, 2020

## Program Description
The program has 12 classes. Below, each of them are described:

File class: **Main.java**
- Description: Program Main Class of TSP Solver solution GUI.
- Package: tsp.main
- Main methods:
    - **main**: Input function of the main class.

File class: **TestEngine.java**
- Description: Program Main Class of TSP Solver solution for test and calibration purpose.
- Package: tsp.test
- Main methods:
    - **main**: Input function of the main class.
    - **runBatchTest**: Function that runs the batch process to calculate the TSP for each case.
    - **readFileList**: Read the TSP file list.
    - **readFileList**: Save the TSP cases results into CSV file.

File class: **TspCase.java**
- Description: Class that stores TSP case information.
- Package: tsp.test
- Main methods:
    - **TspCase**: TspCase class constructor.
    - **getTourMAPE**: Return tour MAPE (Mean Absolute Percentage Error).

File class: **TspAlgorithm.java**
- Description: Abstract class for TSP algorithm classes.
- Package: tsp.algorithm
- Main methods:
    - **run**: Super run method that solves the algorithm.
    - **init**: Abstract methods declaration.
    - **solve**: Abstract methods declaration.
    - **getTourLength**: Abstract methods declaration.
    - **getElapsedTime**: Abstract methods declaration.

File class: **SOMAlgorithm.java**
- Description: Self organizing map (SOM) class. Inherited from TspAlgorithm class.
- Package: tsp.algorithm
- Main methods:
    - **SOMAlgorithm**: SOM class constructor (overload).
    - **init**: Function based on the point cloud, sets the initial SOM.
    - **solve**: This is the method that solves the TSP problem using SOM algorithm.
    - **getTourLength**: Method that calculates the length of the tour.
    - **getElapsedTime**: Returns the time it took for the algorithm to resolve the TSP.
    - **setInitialPoints**: Method that looks for the initial position of the SOM.
    - **paint**: Method in charge of drawing. Use a separate thread to make the drawings.
    - **updateSOM**: Method that update the SOM.
    - **addNodes**: Method of the network nodes insertion.
    - **finish**: This method ends the TSP. Check which stimuli need a new node.
    - **removeIsolatedNodes**: Method that eliminate isolated nodes.
    - **searchCache**: This method searches the cache for a stimulus.
    - **closeupPhase**: This method brings the winning node and its neighbors closer to the stimulus.
    - **getEuclideanDistance**: Returns the Euclidean distance between 2 points in the plane (overload).

File class: **Environment.java**
- Description: GUI class of the solution.
- Package: tsp.gui
- Main methods:
    - **Environment**: Creates new Environment GUI.
    - **initComponents**: Method to Initialize the graphic components.
    - **jButton1ActionPerformed**: Event - Load TSP data file.
    - **jButton2ActionPerformed**: Event - Method to solve the TSP.
    - **jButton3ActionPerformed**: Event - Stop algorithm execution.
    - **displayResults**: Show the final results of the minimum tour.

File class: **EnvCanvas.java**
- Description: Environment Canvas class.
- Package: tsp.gui
- Main methods:
    - **EnvCanvas**: Creates a new instance of EnvCanvas.
    - **paint**: Definition of paint abstract method.
    - **paintGraph**: Function that repaints (update) the graph.
    - **updateCanvas**: Function that updates the canvas objects: points and graph.

File class: **PaintProcess.java**
- Description: Threading class to painting/repainting the Environment Canvas.
- Package: tsp.gui
- Main methods:
    - **EnvCanvas**: Creates a new instance of PaintProcess.
    - **run**: Definition of abstract method to executing the thread.

File class: **FileManager.java**
- Description: Class that manages TSP files.
- Package: tsp.util
- Main methods:
    - **FileManager**: Creates a new instance of FileManager.
    - **scaleData**: Method that transforms double points to positive data on the scale of [0, 1].
    - **loadFile**: Method that reads the input data from a TSPLIB file (overload).
    - **getFileName**: Returns the TSP file name.
    - **getPoints**: Returns the array points.
    - **getFactorValue**: Returns the scaling factor of the data.

File class: **DoublePoint.java**
- Description: Class Double Point.
- Package: tsp.util
- Main methods:
    - **DoublePoint**: Creates a new instance of DoublePoint (overload).

File class: **Node.java**
- Description: Class Node of the DoubleLinkedList class.
- Package: tsp.util
- Main methods:
    - **Node**: Creates a new instance of Node (overload).

File class: **DoubleLinkedList.java**
- Description: Double Linked List class.
- Package: tsp.util
- Main methods:
    - **DoubleLinkedList**: Class constructor.
    - **isEmpty**: Returns if the list is empty or not.
    - **size**: Returns the number of nodes in the list.
    - **getLast**: Returns the last selected node.
    - **get**: Search for a node from its key name.
    - **rightInsert**: Insert a node to the right of node p .
    - **leftInsert**: Insert a node to the left of node p.
    - **remove**: Delete a node from the list.
