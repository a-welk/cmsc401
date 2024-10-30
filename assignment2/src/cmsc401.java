import java.util.Scanner;

public class cmsc401 {

    public static void main(String[] args) {
        int input;
        int index = 0;
        int y_value = 0;
        int price = 0;

        Scanner in = new Scanner(System.in);

        System.out.println("Number of Gardens? ");
        int num = in.nextInt();
        int[] y_coord = new int[num];
        int[] y_temp = new int[num];
        int[] garden_size = new int[num];
        for (int i = 0; i < num; i++) {
            y_coord[i] = in.nextInt();
            garden_size[i] = in.nextInt();
        }
        y_temp = y_coord;

        System.out.println(kthElement(y_coord, 0, num - 1, num / 2));
    }

    static int partition(int arr[], int left, int right) {
        int pivot = arr[right];
        int x = left - 1;
        int temp;

        for (int i = left; i < right; i++) {
            if (arr[i] < pivot) {
                x++;
                temp = arr[x];
                arr[x] = arr[i];
                arr[i] = temp;
            }
        }
        temp = arr[x + 1];
        arr[x + 1] = arr[right];
        arr[right] = temp;

        return x + 1;
    }

    static int kthElement(int arr[], int left, int right, int k) {
        int partition_value = partition(arr, left, right);

        if (partition_value == k) {
            return arr[partition_value];
        } else if (partition_value < k) {
            return kthElement(arr, partition_value + 1, right, k);
        } else {
            return kthElement(arr, left, partition_value - 1, k);
        }

    }
}
