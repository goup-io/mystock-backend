package com.goup.utils;

import org.springframework.stereotype.Component;
public class PilhaObj<T> {

    // Atributos
    private T[] pilha;
    private int topo;

    // Construtor
    public PilhaObj(int capacidade) {
        this.topo = -1;
        this.pilha = (T[]) new Object[capacidade];
    }

    // isEmpty
    public boolean isEmpty() {
        return topo == -1;
    }

    // isFull
    public boolean isFull() {
        return topo == pilha.length - 1;
    }

    // push
    public void push(T elemento) {
        if (!isFull()) {
            pilha[++topo] = elemento;
        } else {
            throw new IllegalStateException("Pilha cheia!");
        }
    }

    // pop
    public T pop() {
        if (!isEmpty()) {
            return pilha[topo--];
        } else {
            throw new IllegalStateException("Pilha vazia!");
        }
    }

    // peek
    public T peek() {
        if (!isEmpty()) {
            return pilha[topo];
        } else {
            throw new IllegalStateException("Pilha vazia!");
        }
    }

    // exibe
    public void exibe() {
        if (isEmpty()) {
            System.out.println("Pilha está vazia!");
        } else {
            System.out.println("-------- Exibindo os itens da pilha --------");
            for (int i = topo; i >= 0; i--) {
                System.out.println(pilha[i]);
            }
        }
    }

    // Getters & Setters
    public int getTopo() {
        return topo;
    }
}
