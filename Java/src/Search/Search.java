package Search;

public class Search {
    public int binarySearch(int[] arr, int target) {
        int i = 0, j = arr.length - 1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (arr[mid] < target) i = mid + 1;
            else if (arr[mid] > target) j = mid - 1;
            else return mid;
        }
        return -1;
    }

    public int binarySearchLCRO(int[] arr, int target) {
        int i = 0, j = arr.length;
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (arr[mid] < target) i = mid + 1;
            else if (arr[mid] > target) j = mid;
            else return mid;
        }
        return -1;
    }

    public int binarySearchInsertion(int[] arr, int target) {
        int i = 0, j = arr.length - 1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (arr[mid] < target) i = mid + 1;
            else if (arr[mid] > target) j = mid - 1;
            else j = mid - 1;
        }
        return i;
    }
}
