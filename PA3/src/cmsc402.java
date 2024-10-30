/* Alex Welk - CMSC401 Programming Project 3 - 11/13/22 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class cmsc402 {
    static class graph {
        int matrix[][];
        final int infinity = Integer.MAX_VALUE/2;
        int numVertices;
        int count;
        boolean bArray[]; //boolean array to check for nodes
        Vertex vArray[]; // vertex array

        static class Vertex{
            int index;
            int previous;
            int distance;

            //function to create a single vertex to add
            public Vertex(int index, int distance, int previous) {
                setIndex(index);
                setDistance(distance);
                setPrev(previous);
            }

            public void setIndex(int index) {
                this.index = index;
            }
            public int getIndex() {
                return index;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public int getDistance() {
                return distance;
            }

            public void setPrev(int previous) {
                this.previous = previous;
            }

            public int getPrev() {
                return previous;
            }
        }

        public void addEdge(int origin, int destination, int weight) {
            matrix[origin][destination] = weight;
            matrix[destination][origin] = weight;
        }

        public graph(int numVertices) {
            matrix = new int [numVertices][numVertices];
            this.numVertices = numVertices;
        }

        public void addHotel(int cost[][]) {
            for (int i = 0; i < numVertices; i++) {
                for (int k = 0; k < numVertices; k++) {
                    if (i != k && matrix[i][k] == 0) {
                        matrix[i][k] = infinity;
                    }
                }
            }

            for(int i = 0; i < numVertices-2; i++) {
                for(int k = 0; k < numVertices; k++) {
                    if(k != cost[i][0] - 1 && matrix[k][cost[i][0] - 1] != infinity) {
                        matrix[k][cost[i][0] - 1] += cost[i][1];
                    }
                }
            }
        }

        //initializes the array of vertices with numVertices and sets the source vertex to 0
        void Initialize() {
            vArray = new Vertex[numVertices];
            for(int i = 0; i < numVertices; i ++) {
                vArray[i] = new Vertex(i, infinity, -1);
            }

            vArray[0].setDistance(0);
        }

        void Relax(int x, int y) {
            if(vArray[y].getDistance() > (vArray[x].getDistance() + matrix[x][y])) {
                vArray[y].setPrev(vArray[x].getIndex());
                vArray[y].setDistance(vArray[x].getDistance() + matrix[x][y]);
            }
        }

        //Dijkstra function
        public void Dijkstra() {
            Initialize();
            bArray = new boolean[numVertices];
            Arrays.fill(bArray, Boolean.TRUE);
            count = numVertices;

            while(count > 0) {
                int min = getMin();
                for(int i = 0; i < numVertices; i++) {
                    if(matrix[min][i] != infinity) {
                        Relax(min, i);
                    }
                }
            }
        }

        public void secondPath() {
            int prev = -1;
            int pres = -1;
            int pred = -1;

            for(int i = 0; i < bArray.length - 1; i++) {
                int s = vArray[i].getIndex();
                int d = vArray[i+1].getIndex();
                if(prev != -1) {
                    matrix[pres][pred] = prev;
                    matrix[pred][pres] = prev;
                }
                prev = matrix[s][d];
                pres = s;
                pred = d;

                matrix[s][d] = 0;
                matrix[d][s] = 0;
                Dijkstra();
            }
        }

        //function to get min distance between vertices
        public int getMin() {
            int minDistance = infinity;
            int minB = -1;

            for(int i = 0; i < bArray.length; i++) {
                if(bArray[i] == true && vArray[i].getDistance() <= minDistance) {
                    minDistance = vArray[i].getDistance();
                    minB = vArray[i].getIndex();
                }
            }
            bArray[minB] = false;
            count--;
            return minB;

        }

        //function for printing output
        public void output() {
            int totalCost = 0;
            ArrayList<Integer> route = new ArrayList<Integer>();
            int destination = 1;
            route.add(destination);

            while(vArray[destination].getPrev() != -1) {
                route.add(vArray[destination].getPrev());
                destination = vArray[destination].getPrev();
            }

            for(int i = 0; i < route.size() - 1; i++) {
                totalCost += matrix[route.get(i)][route.get(i+1)];
            }
            System.out.println(totalCost);
        }
    }
    //main method
    public static void main(String[] args) {
        int numCities;
        int numHighways;
        int hotels[][];
        Scanner input = new Scanner(System.in);
        numCities = input.nextInt();
        numHighways = input.nextInt();
        graph graph = new graph(numCities);
        hotels = new int[numCities-2][2];

        for(int i = 0; i < numCities - 2; i++) {
            hotels[i][0] = input.nextInt();
            hotels[i][1] = input.nextInt();
        }

        for(int i = 0; i < numHighways; i++) {
            int city1 = input.nextInt();
            int city2 = input.nextInt();
            int gas = input.nextInt();

            graph.addEdge(city1-1, city2-1, gas);
        }

        graph.addHotel(hotels);
        graph.Dijkstra();
        graph.secondPath();
        graph.output();

    }
}
