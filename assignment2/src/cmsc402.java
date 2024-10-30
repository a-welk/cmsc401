import java.util.Scanner;
import java.lang.Math;

public class cmsc402 {
    int partition(int arr[], int left, int right)
    {
        int i = left, j = right;
        int tmp;
        int pivot = arr[(left + right) / 2];

        while (i <= j) {
            while (arr[i] < pivot)
                i++;
            while (arr[j] > pivot)
                j--;
            if (i <= j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }

        return i;

    }
    void quickSort(int arr[], int left, int right) {
        int index = partition(arr, left, right);
        if (left < index - 1)
            quickSort(arr, left, index - 1);
        if (index < right)
            quickSort(arr, index, right);
    }

    public void main(String[] args) {
        int input;
        int index;
        int y_value = 0;

        Scanner in = new Scanner(System.in);

        System.out.println("Number of Gardens? ");
        int num = in.nextInt();
        int[] y_coord = new int[num];
        int[] garden_size = new int[num];
        for(int i = 0; i < num; i++) {
            y_coord[i] = in.nextInt();
            garden_size[i] = in.nextInt();
        }
        partition(y_coord, 0, num-1);
        quickSort(y_coord, 0, num-1);

        if(num % 2 == 0) {
            index = num / 2;
            y_value = (y_coord[index] + y_coord[index+1]) / 2;
        }
        else if(num % 2 != 0) {
            index = num / 2;
            y_value = y_coord[index+1];
        }

        System.out.println(y_value);
    }

}
