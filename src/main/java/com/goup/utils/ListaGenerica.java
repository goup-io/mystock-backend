package com.goup.utils;

public class ListaGenerica<T> {
        private T[] vetor;
        private int nroElem;

        public ListaGenerica(int tamanho) {
            this.vetor = (T[]) new Object[tamanho];
            this.nroElem = 0;
        }

        public void adiciona(T elemento) {
            if (nroElem < vetor.length){
                vetor[nroElem++] = elemento;
            } else {
                throw new IllegalStateException("Lista cheia");
            }
        }
        public void adiciona(int indice,T elemento) {
            if (indice >= 0 && indice < vetor.length) {
                vetor[indice] = elemento;
            } else {
                throw new IllegalStateException("Lista cheia");
            }
        }

        public int busca(T elementoBuscado) {
            for (int i = 0; i < nroElem; i++) {
                if (vetor[i].equals(elementoBuscado)){
                    return i;
                }
            }
            return -1;
        }

        public boolean removePeloIndice(int indice) {
            if (indice >= 0 && indice < getTamanho()){
                for (int i = indice; i < nroElem - 1; i++) {
                    vetor[i] = vetor[i+1];
                }
                nroElem--;
                return true;
            }
            return false;
        }

        public boolean removeElemento(T elementoARemover) {
            int indice = busca(elementoARemover);
            if (indice >= 0){
                return removePeloIndice(indice);
            }
            return false;
        }

        public int getTamanho() {
            return vetor.length - 1;
        }

        public T getElemento(int indice) {
            if (indice < vetor.length) return vetor[indice];
            return null;
        }

        public void limpa() {
            for (T t : vetor) {
                t = null;
            }
        }

        public void exibe() {
            System.out.printf("Exibindo os valores da lista\n");
            for (T t : vetor) {
                System.out.printf("{ %S }, ", t);
            }

        }


}
