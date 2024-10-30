// Alex Welk //

import java.util.Scanner;
public class cmsc401 {
    public static void main(String [] args) {
        int counter = 1;
        int MEcount = 0;
        Scanner input = new Scanner((System.in));
        int number = input.nextInt();
        int [] numbers = new int[number];
        int ME = numbers[0];

        for(int i = 0; i < number; i++) {
            numbers[i] = input.nextInt();
        }
        for(int j = 0; j < number; j++) {
            if(numbers[j] == ME) {
                counter++;
            }
            else counter--;
            if(counter == 0) {
                ME = numbers[j];
                counter = 1;
            }
        }
        for(int k = 0; k < number; k++) {
            if(numbers[k] == ME)
                MEcount++;
        }
        if(MEcount > (number / 2))
            System.out.println(ME);
        else
            System.out.println(-1);
    }
}
