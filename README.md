This repository contains a Java implementation of an airline scheduling algorithm based on the Ford-Fulkerson method for computing the maximum flow in a flow network. The algorithm is used to optimize flight scheduling between airports by determining the maximum number of flights that can be scheduled between a source airport and a destination airport given certain capacities.
Features
(i) Directed Edge Representation: Each edge in the network is represented with a source, destination, capacity, and flow.
(ii) BFS for Path Finding: Uses Breadth-First Search (BFS) to find augmenting paths in the flow network.
(iii) Ford-Fulkerson Algorithm: Implements the Ford-Fulkerson method to compute the maximum flow from a source to a destination in the network.
(iv) Flow Network Representation: Represents the network using adjacency lists.
