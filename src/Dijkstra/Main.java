package Dijkstra;

import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Scanner edges = new Scanner(System.in);
        Scanner startNode = new Scanner(System.in);
        Scanner endNode = new Scanner(System.in);
        int nodesNumber;
        String ed;

        /**
         * Here we receive the amount of nodes in the graph.
         */
        System.out.println("How many nodes do you want in your graph? [4-6]");
        nodesNumber = input.nextInt();
        while (nodesNumber > 6 || nodesNumber < 4) {
            System.out.println("We can not have that amount of nodes, please try again");
            nodesNumber = input.nextInt();
        }

        // Initalize the Dijkstra object.
        Dijkstra dijkstra = new Dijkstra(nodesNumber);
        for (int i = 1; i < nodesNumber + 1; i++) {
            // We initialize the dicts of costs and parents in order to keep some control
            // over this.
            dijkstra.getCosts().put(i, 9999);
            dijkstra.getParents().put(i, 9999);
        }

        /**
         * Here we take the edges for each node. We can consider that for a node 1 that
         * has 2,3,5 as neighbors with a cost 5, 6, 1 respectively. We may introduce it
         * like this: 2-5,3-6,5-1.
         */
        for (int i = 1; i <= nodesNumber; i++) {
            System.out
                    .println("Insert edges and their values for " + i + " as this => 2-5,3-6,5-1 || 4-4 || 4,2 || 4,1");
            ed = edges.next();

            dijkstra.insertEdgesFromKeyBoard(i, ed);
        }
        // inicializar el grafo con el numero de nodos.
        dijkstra.initializeGraph(nodesNumber);

        //Ending and starting point of the path.
        System.out.println("Insert the starting node:");
        int start = startNode.nextInt();

        System.out.println("Insert the ending node:");
        int end = endNode.nextInt();
        // insertar costes y parientes los valores dependiendo del nodo inicial.
        switch (start) {
            case 1:
                for (int key : dijkstra.getNeigbor1().keySet()) {
                    dijkstra.getCosts().replace(key, dijkstra.getNeigbor1().get(key));
                    dijkstra.getParents().replace(key, start);
                }
                break;
            case 2:
                for (int key : dijkstra.getNeigbor2().keySet()) {
                    dijkstra.getCosts().replace(key, dijkstra.getNeigbor2().get(key));
                    dijkstra.getParents().replace(key, start);
                }
                break;
            case 3:
                for (int key : dijkstra.getNeigbor3().keySet()) {
                    dijkstra.getCosts().replace(key, dijkstra.getNeigbor3().get(key));
                    dijkstra.getParents().replace(key, start);
                }
                break;
            case 4:
                for (int key : dijkstra.getNeigbor4().keySet()) {
                    dijkstra.getCosts().replace(key, dijkstra.getNeigbor4().get(key));
                    dijkstra.getParents().replace(key, start);
                }
                break;
            case 5:
                for (int key : dijkstra.getNeigbor5().keySet()) {
                    dijkstra.getCosts().replace(key, dijkstra.getNeigbor5().get(key));
                    dijkstra.getParents().replace(key, start);
                }
                break;
            case 6:
                for (int key : dijkstra.getNeigbor6().keySet()) {
                    dijkstra.getCosts().replace(key, dijkstra.getNeigbor6().get(key));
                    dijkstra.getParents().replace(key, start);
                }
                break;
        }

        System.out.println("costs = " + dijkstra.getCosts().toString());
        System.out.println("parents = " + dijkstra.getParents().toString());

        edges.close();
        input.close();
        startNode.close();
        endNode.close();

        
        int node = dijkstra.findLowestCostNode(dijkstra.getCosts());
        int finalCost = 0;
        System.out.println("node findedLowest cost = " + node);
        // Repeat until node is zero, it would mean there is no more to search.
        while (node != 0) {
            System.out.println("----------------------Proccessing--------------------------");
            int cost = dijkstra.getCosts().get(node);
            finalCost = cost;
            
            System.out.println("Llegar a " + node + " costs " + cost);
            if (node == end) {
                break;
            }
            Map<Integer, Integer> neighbors = dijkstra.getGraph().get(node);
            System.out.println("neighbors = " + neighbors);
            for (int ne : neighbors.keySet()) {
                int newCost = cost + neighbors.get(ne);
                if (dijkstra.getCosts().get(ne) > newCost) {
                    System.out.println("El costo de " + ne + " es más barato desde " + node + " con " + newCost);
                    dijkstra.getCosts().replace(ne, newCost);
                    dijkstra.getParents().replace(ne, node);

                }
            }
            dijkstra.getProcesed().add(node);

            System.out.println("proccessed = " + node);
            System.out.println("");

            node = dijkstra.findLowestCostNode(dijkstra.getCosts());
        }
        for (int son : dijkstra.getParents().keySet()) {
            System.out.println(son + " is son of " + dijkstra.getParents().get(son));
        }
        try {
            dijkstra.findPath(dijkstra.getParents(), dijkstra.getGraph().size(), start, end);
            int[] finalPath = dijkstra.getPath();
            for (int i = 0; i < finalPath.length; i++) {
                System.out.print(finalPath[i] + " ");
            }
            System.out.println();
            System.out.println("final cost is " + finalCost);

            dijkstra.graphToAdjacencyMatrix();
        } catch (Exception e) {
            
            System.out.println("It looks like there is no such a path");

        }

    }

}
