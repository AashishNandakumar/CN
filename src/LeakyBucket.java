import java.util.Scanner;

public class LeakyBucket {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter no of time intervals:");
        int n = in.nextInt();

        int[] intervals = new int[n];
        System.out.println("Enter the time intervals");
        for (int i=0;i<n;i++)
            intervals[i] = in.nextInt();

        System.out.println("Enter output rate and bucket size:");
        int outputRate = in.nextInt();  // constant output
        int bucketSize = in.nextInt();

        int lastConformingTime = intervals[0];  // first interval the packets start to arrive
        int currentBucketSize = 0;  // initial bucket size, tells the current amount of data in the bucket

        for (int interval : intervals){
            currentBucketSize = Math.max(0, currentBucketSize - (interval - lastConformingTime));

            if (currentBucketSize + outputRate > bucketSize)
                System.out.println("non conforming packet at interval "+interval);
            else{
                currentBucketSize += outputRate;
                System.out.println("Conforming packet at interval "+interval);
            }
            lastConformingTime = interval;

        }
    }
}
