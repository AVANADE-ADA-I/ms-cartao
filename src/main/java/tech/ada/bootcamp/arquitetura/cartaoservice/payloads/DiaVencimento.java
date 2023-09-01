package tech.ada.bootcamp.arquitetura.cartaoservice.payloads;

import java.util.HashMap;
import java.util.Map;

public enum DiaVencimento {
    CINCO(5),
    DEZ(10),
    QUINZE(15),
    VINTE(20),
    VINTE_CINCO(25);
    final private Integer dia;
    DiaVencimento(Integer dia) {
        this.dia = dia;
    }

    public Integer getDia() {return dia;}
}
