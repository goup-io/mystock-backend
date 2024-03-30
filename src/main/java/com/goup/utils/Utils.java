package com.goup.utils;

import com.goup.entities.usuarios.Usuario;
import com.goup.entities.usuarios.Vendedor;

public class Utils {

    // Pesquisa binária pelo nome do produto
    public int pesquisaBinaria(Usuario[] vetor, String x) {
        int indinf = 0;
        int indsup = vetor.length - 1;

        while (indinf <= indsup) {
            int meio = (indinf + indsup) / 2;
            if (vetor[meio].getNome().compareTo(x) == 0) {
                return meio;
            } else if (vetor[meio].getNome().compareTo(x) < 0) {
                indsup = meio + 1;
            } else {
                indinf = meio - 1;
            }
        }
        return -1;
    }

    // Selection enhanced sort baseado no valorCusto
    public void sortComissaoVendedor(Vendedor[] vetor) {
        for (int i = 0; i < vetor.length - 1; i++) {
            int indMenor = i;
            Vendedor aux;
            for (int j = i + 1; j < vetor.length; j++) {
                if (vetor[j].getComissao() < vetor[indMenor].getComissao()) {
                    indMenor = j;
                }
            }

            aux = vetor[i];
            vetor[i] = vetor[indMenor];
            vetor[indMenor] = aux;
        }
    }

    // Selection enhanced sort baseado no nome
    public void sortVendedorNome(Vendedor[] usuarios){
        for (int i = 0; i < usuarios.length - 1; i++) {
            int indMenor = i;
            Vendedor aux;
            for (int j = i + 1; j < usuarios.length; j++) {
                if (usuarios[j].getNome().compareTo(usuarios[indMenor].getNome()) < 0) {
                    indMenor = j;
                }
            }

            aux = usuarios[i];
            usuarios[i] = usuarios[indMenor];
            usuarios[indMenor] = aux;
        }
    }

}

