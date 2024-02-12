import java.util.Scanner;

public class BellmanFord {
    static int n, dest;
    static double[] prevDistanceVector, distanceVector;
    static double[][] adjacencyMatrix;

    static void bellmanFordAlgorithm(){
        for (int i=0;i<n-1;i++){
            prevDistanceVector = distanceVector.clone();
            for(int j=0;j<n;j++){
                for(int k=0;k<n;k++){
                    if (adjacencyMatrix[j][k] != Double.POSITIVE_INFINITY && prevDistanceVector[k] + adjacencyMatrix[j][k] < distanceVector[j])
                        distanceVector[j] = prevDistanceVector[k] + adjacencyMatrix[j][k];
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter number of nodes");
        n = in.nextInt();
        adjacencyMatrix = new double[n][n];

        System.out.println("Enter adjacency matrix (use 9999 for INFINITY):");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                adjacencyMatrix[i][j] = in.nextDouble();
                if(adjacencyMatrix[i][j] == 9999){
                    adjacencyMatrix[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }

        System.out.println("Enter destination matrix");
        dest = in.nextInt();
        distanceVector = new double[n];
        for(int i=0;i<n;i++)
            distanceVector[i] = Double.POSITIVE_INFINITY;

        distanceVector[dest - 1] = 0;

        bellmanFordAlgorithm();

        System.out.println("Distance Vector:");
        for(int i=0;i<n;i++){
            if(i==dest-1)
                continue;
            System.out.println("Distance from "+(i + 1)+ " to "+dest+" is: "+distanceVector[i]);
        }
    }
}
