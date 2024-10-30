/* Alex Welk - CMSC401 - Programming Assignment #4 */
/* I know the variable names are messy but I don't have time to fix it */
import java.util.*;
public class cmsc401 {

    //function to calculate optimal cost for slices
    public static int optimalSlice(int perimeter, int[] cost, int slice) {
        int [][] x = new int[slice + 1][slice + 1];
        ArrayList<Integer> y = new ArrayList<>();

        for(int i: cost) {
            y.add(i);
        }

        for(int[] z: x) {
            Arrays.fill(z, -1);
        }
        y.add(perimeter);
        y.add(0, 0);
        Collections.sort(y);

        return helperFunc(1, slice, y, x);
    }

    //helper function for optimalSlice
    private static int helperFunc(int u, int v, ArrayList<Integer> y, int[][] x) {
        int max = Integer.MAX_VALUE;
        if(v < u)
            return 0;
        if(x[u][v] != -1)
            return x[u][v];
        for(int p = u; p <= v; p++) {
            int doug = y.get(v + 1) - y.get(u - 1) + (helperFunc(u, p - 1, y, x) + helperFunc(p + 1, v, y, x));
            max = Math.min(max, doug);
        }
        return x[u][v] = max;
    }

    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);
        int perimeter = input.nextInt();
        int numSlice = input.nextInt();
        int[] coords = new int[numSlice];

        for(int i = 0; i < numSlice; i++)
            coords[i] = input.nextInt();

        int cost = optimalSlice(perimeter, coords, numSlice);
        System.out.println(cost);
    }
}
