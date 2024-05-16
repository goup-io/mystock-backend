package com.goup.utils;

import com.goup.entities.estoque.ETP;

public class Utils {
    public static void ordenarNome(ListaGenerica<ETP> array, int left, int right) {
        if (right <= left) return;
        int mid = (left + right) / 2;
        ordenarNome(array, left, mid);
        ordenarNome(array, mid + 1, right);
        merge(array, left, mid, right);
    }
    static void merge(ListaGenerica<ETP> array, int left, int mid, int right) {
        int lengthLeft = mid - left + 1;
        int lengthRight = right - mid;

        ListaGenerica<ETP> leftArray = new ListaGenerica<>(lengthLeft);
        ListaGenerica<ETP> rightArray = new ListaGenerica<>(lengthRight);

        for (int i = 0; i < lengthLeft; i++) {
            leftArray.adiciona(array.getElemento(left + i));
        }
        for (int i = 0; i < lengthRight; i++) {
            rightArray.adiciona(array.getElemento(mid + 1 + i));
        }

        int leftIndex = 0;
        int rightIndex = 0;

        for (int i = left; i < right + 1; i++) {
            if (leftIndex < lengthLeft && rightIndex < lengthRight) {
                if (leftArray.getElemento(leftIndex).getProduto().getNome().compareTo(rightArray.getElemento(rightIndex).getProduto().getNome()) < 0) {
                    array.adiciona(i, leftArray.getElemento(leftIndex));
                    leftIndex++;
                } else {
                    array.adiciona(i, rightArray.getElemento(rightIndex));
                    rightIndex++;
                }
            } else if (leftIndex < lengthLeft) {
                array.adiciona(i, leftArray.getElemento(leftIndex));
                leftIndex++;
            } else if (rightIndex < lengthRight) {
                array.adiciona(i, rightArray.getElemento(rightIndex));
                rightIndex++;
            }
        }
    }
}

