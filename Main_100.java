import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.util.Scanner;


class Main {
    public static void main(String[] args) {
        
        int num1;
        int num2;
        int acm100Result = 0;
        
        Scanner scanner = new Scanner (new BufferedInputStream(System.in));
        while(scanner.hasNext()) {
            num1 = scanner.nextInt();
            num2 = scanner.nextInt();
            if (num1 > num2) {
                acm100Result = calculateMaxCycleLength(num2, num1);
            } else {
                acm100Result = calculateMaxCycleLength(num1, num2);
            }
            System.out.println(num1 + " " + num2 + " " + acm100Result);
        }
    }

    private static int calculateMaxCycleLength(int start, int end) {
        int maximumCycleLength = 0;
        int cycleLength = 0;
        int i;
        for (i = start; i <= end; i++) {
            cycleLength = calculateCycleLength(i);
            if (cycleLength > maximumCycleLength) {
                maximumCycleLength = cycleLength;
            } else {
                //nothing else.
            }
        }
        return maximumCycleLength;
    }
    
    private static int calculateCycleLength(int num) {
        int calculateNum = num;
        int cycleLength = 1;
    
        while (calculateNum != 1) {
            if(calculateNum == 1) {
                break;
            } else {
            
                if(isOddNumber(calculateNum)) {
                    calculateNum = 3 * calculateNum + 1;
                } else {
                    calculateNum = calculateNum / 2;
                }
                    cycleLength++;
            }
        }
        return cycleLength;
    }
    
    
    private static boolean isOddNumber(int num) {
        if (num % 2 == 0) {
            return false;
        } else {
            return true;
        }
    }
}
