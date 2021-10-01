public class test {
    public static int findFirst(int x, int[] array) {
        int mid, left = 0, right = array.length;

        while (left <= right) {
            mid = (int) ((left + right)/2);

            if (array[mid] < x) {
                left = mid + 1;
            }

            else if (array[mid] > x) {
                right = mid - 1;
            }

            else {
                return mid;
            }
        }
        return -1;
    }
    

    public static boolean testCorrectOutput(int[] array) {
        return (4 == findFirst(3, array));
    }
    public static void main(String[] args) {
        int[] array = {1, 15};
        System.out.println(testCorrectOutput(array));
        System.out.println(findFirst(4, array));
    }
}