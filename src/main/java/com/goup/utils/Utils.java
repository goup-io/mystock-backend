package com.goup.utils;

public class Utils {
    /*
    // mergesort
    public static void mergeSort(T[] array, int left, int right) {
        if (right <= left) return;
        int mid = (left + right) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    static void merge(T[] array, int left, int mid, int right) {
        int lengthLeft = mid - left + 1;
        int lengthRight = right - mid;

        T[] leftArray = (T[]) new Object[lengthLeft];
        T[] rightArray = (T[]) new Object[lengthRight];

        System.arraycopy(array, left, leftArray, 0, lengthLeft);
        System.arraycopy(array, mid + 1, rightArray, 0, lengthRight);

        int leftIndex = 0;
        int rightIndex = 0;

        for (int i = left; i < right + 1; i++) {
            if (leftIndex < lengthLeft && rightIndex < lengthRight) {
                if (leftArray[leftIndex].getNome().compareTo(rightArray[rightIndex].getNome()) < 0) {
                    array[i] = leftArray[leftIndex];
                    leftIndex++;
                } else {
                    array[i] = rightArray[rightIndex];
                        rightIndex++;
                    }
                } else if (leftIndex < lengthLeft) {
                    array[i] = leftArray[leftIndex];
                    leftIndex++;
                } else if (rightIndex < lengthRight) {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            }

    }

    */
}

