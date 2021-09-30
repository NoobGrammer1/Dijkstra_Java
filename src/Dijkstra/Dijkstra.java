package Dijkstra;

import java.util.*;

public class Dijkstra {
    //Processed to keep a reference of those nodes already processed.
    private List<Integer> processed = new ArrayList<Integer>();
    // This graph has the form of {(srcNode, {(destNode, cost)...}),...}
    private Map<Integer, Map<Integer, Integer>> graph = new HashMap<Integer, Map<Integer, Integer>>();
    //As we had to do this for 6 nodes, we used this maps of neighbors.
    //But if you consider for n nodes, you could initialize the map in
    //the moment you insert the information.
    private Map<Integer, Integer> neigborof1;
    private Map<Integer, Integer> neigborof2;
    private Map<Integer, Integer> neigborof3;
    private Map<Integer, Integer> neigborof4;
    private Map<Integer, Integer> neigborof5;
    private Map<Integer, Integer> neigborof6;
    //This map stores the costs of each node, it get updated as the algorithm progresses. 
    private Map<Integer, Integer> costs = new HashMap<Integer, Integer>();
    //This map stores the parents,it's useful for finding the path at the end.
    private Map<Integer, Integer> parents = new HashMap<Integer, Integer>();
    //As the nodes are integers, we can save them in the path.
    private int[] path;

    public List<Integer> getProcesed(){
        return processed;
    }
    public Map<Integer, Map<Integer, Integer>> getGraph(){
        return graph;
    }
    public Map<Integer, Integer> getNeigbor1(){
        return neigborof1;
    }
    public Map<Integer, Integer> getNeigbor2(){
        return neigborof2;
    }
    public Map<Integer, Integer> getNeigbor3(){
        return neigborof3;
    }
    public Map<Integer, Integer> getNeigbor4(){
        return neigborof4;
    }
    public Map<Integer, Integer> getNeigbor5(){
        return neigborof5;
    }
    public Map<Integer, Integer> getNeigbor6(){
        return neigborof6;
    }
    public Map<Integer, Integer> getCosts() {
        return costs;
    }
    public Map<Integer, Integer> getParents() {
        return parents;
    }
    public int[] getPath(){
        return path;
    }
//This constructor swithces the amount of nodes, as told, we had to work between 4 and 6
    public Dijkstra(int numberOfNodes) {
        switch (numberOfNodes) {
            case 4:
                neigborof1 = new HashMap<Integer, Integer>();
                neigborof2 = new HashMap<Integer, Integer>();
                neigborof3 = new HashMap<Integer, Integer>();
                neigborof4 = new HashMap<Integer, Integer>();
                break;
            case 5:
                neigborof1 = new HashMap<Integer, Integer>();
                neigborof2 = new HashMap<Integer, Integer>();
                neigborof3 = new HashMap<Integer, Integer>();
                neigborof4 = new HashMap<Integer, Integer>();
                neigborof5 = new HashMap<Integer, Integer>();
                break;
            case 6:
                neigborof1 = new HashMap<Integer, Integer>();
                neigborof2 = new HashMap<Integer, Integer>();
                neigborof3 = new HashMap<Integer, Integer>();
                neigborof4 = new HashMap<Integer, Integer>();
                neigborof5 = new HashMap<Integer, Integer>();
                neigborof6 = new HashMap<Integer, Integer>();
                break;
        }
    }

    public int findLowestCostNode(Map<Integer, Integer> costs) {
        int lowestCost = 9999;
        int lowestCostNode = 0;
        for (int node : costs.keySet()) {
            int cost = costs.get(node);
            System.out.println("node = " + node + ", cost = " + cost);
            if (cost < lowestCost && notInProccessed(node) && cost > 0) {
                lowestCost = cost;
                lowestCostNode = node;
            }
        }
        return lowestCostNode;
    }

    public  boolean notInProccessed(int node) {
        for (int key : processed) {
            if (key == node) {
                System.out.println("key = " + key + " node = " + node);
                return false;
            }
        }
        return true;
    }

    //Having in mind that we can find the path starting from the end of the path and going through it using it's parents.
    public void findPath(Map<Integer, Integer> parents, int size, int start, int end) {
     
        List<Integer> path = new ArrayList<Integer>();
        path.add(end);
        int father = parents.get(end);
        path.add(father);
        while (father != start) {
            father = parents.get(father);
            path.add(father);
        }
        System.out.println("path.size() = " + path.size());
        int[] pathAsArray = new int[path.size()];
        for (int i = 0; i < pathAsArray.length; i++) {
            pathAsArray[i] = path.get(path.size() - i - 1);
        }
        for (int i = 0; i < pathAsArray.length; i++) {
            System.out.println("pathAsArray [" + i + "]" + pathAsArray[i]);
        }

        this.path = pathAsArray;
        for (int i = 0; i < this.path.length; i++) {
            System.out.println("path [" + i + "]" + this.path[i]);
        }

    }

    public void insertEdgesFromKeyBoard(int node, String edges) {
        String[] separateWithComa = edges.split(",");//2-3,4-3
        for (int i = 0; i < separateWithComa.length; i++) {
            System.out.println("separatedWithComa = " + separateWithComa[i]);
        }
        for (int i = 0; i < separateWithComa.length; i++) {

            String[] splitAgain = separateWithComa[i].split("-");
            System.out.println("splitAgain[0] = " + splitAgain[0]);
            System.out.println("splitAgain[1] = " + splitAgain[1]);
            int nodeDest = Integer.parseInt(splitAgain[0]);
            int costOfThis = Integer.parseInt(splitAgain[1]);
            if (node != nodeDest) {
                switch (node) {
                    case 1:
                        neigborof1.put(nodeDest, costOfThis);
                        break;
                    case 2:
                        neigborof2.put(nodeDest, costOfThis);
                        break;
                    case 3:
                        neigborof3.put(nodeDest, costOfThis);
                        break;
                    case 4:
                        neigborof4.put(nodeDest, costOfThis);
                        break;
                    case 5:
                        neigborof5.put(nodeDest, costOfThis);
                        break;
                    case 6:
                        neigborof6.put(nodeDest, costOfThis);
                        break;

                }
            } else {
                switch (node) {
                    case 1:
                        neigborof1.put(nodeDest, 0);
                        break;
                    case 2:
                        neigborof2.put(nodeDest, 0);
                        break;
                    case 3:
                        neigborof3.put(nodeDest, 0);
                        break;
                    case 4:
                        neigborof4.put(nodeDest, 0);
                        break;
                    case 5:
                        neigborof5.put(nodeDest, 0);
                        break;
                    case 6:
                        neigborof6.put(nodeDest, 0);
                        break;
                }
            }

            // printNeighbors(node);

        }
    }

    public void initializeGraph(int size) {
        switch (size) {
            case 1:
                graph.put(1, neigborof1);
                break;
            case 2:
                graph.put(1, neigborof1);
                graph.put(2, neigborof2);

                break;
            case 3:
                graph.put(1, neigborof1);
                graph.put(2, neigborof2);
                graph.put(3, neigborof3);

                break;
            case 4:
                graph.put(1, neigborof1);
                graph.put(2, neigborof2);
                graph.put(3, neigborof3);
                graph.put(4, neigborof4);
                break;
            case 5:
                graph.put(1, neigborof1);
                graph.put(2, neigborof2);
                graph.put(3, neigborof3);
                graph.put(4, neigborof4);
                graph.put(5, neigborof5);
                break;
            case 6:
                graph.put(1, neigborof1);
                graph.put(2, neigborof2);
                graph.put(3, neigborof3);
                graph.put(4, neigborof4);
                graph.put(5, neigborof5);
                graph.put(6, neigborof6);
                break;
        }
        System.out.println("graph = " + graph.toString());
    }

    public void printNeighbors(int node) {

        switch (node) {
            case 1:
                for (int ne : neigborof1.keySet()) {
                    System.out.println("ne =" + ne + ", neighbors = " + neigborof1.get(ne));
                }

                break;
            case 2:
                for (int ne : neigborof2.keySet()) {
                    System.out.println("ne =" + ne + ", neighbors = " + neigborof2.get(ne));
                }
                break;
            case 3:
                for (int ne : neigborof3.keySet()) {
                    System.out.println("ne =" + ne + ", neighbors = " + neigborof3.get(ne));
                }
                break;
            case 4:
                for (int ne : neigborof4.keySet()) {
                    System.out.println("ne =" + ne + ", neighbors = " + neigborof4.get(ne));
                }
                break;
            case 5:
                for (int ne : neigborof5.keySet()) {
                    System.out.println("ne =" + ne + ", neighbors = " + neigborof5.get(ne));
                }
                break;
            case 6:
                for (int ne : neigborof6.keySet()) {
                    System.out.println("ne =" + ne + ", neighbors = " + neigborof6.get(ne));
                }
                break;
        }

    }
    // You may want to get the adjacency matrix, it could be useful.
    public String graphToAdjacencyMatrix(){
        String returnable = "";
        int [][] adjacencyMatrix = new int[this.graph.size()][this.graph.size()];
        int flag = 1;
        String header = "X | ";
        String line = "____";
        while (flag <= this.graph.size()) {
            header += (String.valueOf(flag) + " ");
            line += ("__");
            flag++;
        }
        System.out.println(header);
        System.out.println(line);
        returnable += header +"\n"+ line +"\n";
        for (int node : this.graph.keySet()) {
            for (int neighbor : this.graph.get(node).keySet()) {
                adjacencyMatrix[neighbor - 1][node - 1] = this.graph.get(node).get(neighbor);
            }
        }
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            System.out.print(String.valueOf(i+1) + " | ");
            returnable += String.valueOf(i+1) + " | ";
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                System.out.print( adjacencyMatrix[i][j] +" ");
                returnable += adjacencyMatrix[i][j] + " ";
            }
            System.out.println(" ");
            returnable += "\n";
        }
return returnable;
    }
}
