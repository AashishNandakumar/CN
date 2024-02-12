import java.util.Scanner;

public class CRC {

    static int[] divide(int[] dividend, int[] divisor){
        int[] rem = new int[dividend.length];

        System.arraycopy(dividend, 0, rem, 0, dividend.length);
        int cur = 0;

        while(true){
            for(int i=0;i<divisor.length;i++)
                rem[cur + i] = (rem[cur + i] ^ divisor[i]);

            while(rem[cur] == 0 && cur != rem.length - 1)
                cur++;

            if ((rem.length - cur) < divisor.length)
                break;
        }
        return rem;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] data;
        int[] divisor;
        int[] rem;
        int[] crc;
        int data_bits, divisor_bits, total_length;

        System.out.println("Enter number of data bits:");
        data_bits = in.nextInt();
        data = new int[data_bits];

        System.out.println("Enter data bits:");
        for(int i=0;i<data_bits;i++)
            data[i] = in.nextInt();

        System.out.println("Enter number of divisor bits:");
        divisor_bits = in.nextInt();
        divisor = new int[divisor_bits];

        System.out.println("Enter divisor bits:");
        for(int i=0;i<divisor_bits;i++)
            divisor[i] = in.nextInt();

        total_length = data_bits + (divisor_bits - 1);

        int[] dividend = new int[total_length];
        rem = new int[total_length];
        crc = new int[total_length];

//        CRC generation
        System.arraycopy(data, 0, dividend, 0, data.length);

        System.out.println("Dividend (after appending 0's) are:");
        for (int i:dividend)
            System.out.print(i);
        System.out.println();

        rem = divide(dividend, divisor);

        for(int i=0;i<dividend.length;i++)
            crc[i] = (dividend[i] ^ rem[i]);

        System.out.println("CRC code:");
        for(int i:crc)
            System.out.print(i);
        System.out.println();

//        Error detection
        System.out.println("Enter CRC code of "+ total_length + " bits");
        for(int i=0;i<crc.length;i++)
            crc[i] = in.nextInt();

        rem = divide(crc, divisor);

        for(int i=0;i<rem.length;i++){
            if (rem[i] != 0){
                System.out.println("Error");
                break;
            }
            if(i == rem.length - 1)
                System.out.println("No Error");
        }
        System.out.println("Thank you");
    }
}
