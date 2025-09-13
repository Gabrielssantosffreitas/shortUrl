package com.gabriel.EncurtadorDelinkSimples.enums;

import java.util.Arrays;
import java.util.Optional;

public enum EstateAtivoDesetivado {
    ATIVO(0),
    DESATIVO(1);
    private  int ativoNumber;

    EstateAtivoDesetivado(int ativoNumber) {
        this.ativoNumber = ativoNumber;
    }

    public int getAtivoNumber() {
        return ativoNumber;
    }
    /*
        usando o Api Strem do java para facilitar a leitura
     */
    public  static Optional<EstateAtivoDesetivado> getEstadoByNumber(int number){
        EstateAtivoDesetivado[] estadoAtivoDesetivados = EstateAtivoDesetivado.values();

        return Arrays.stream(estadoAtivoDesetivados)
                .filter(e -> e.getAtivoNumber() == number)
                .findFirst();
    }
}
