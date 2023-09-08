package tech.ada.bootcamp.arquitetura.cartaoservice.payloads;

public enum TipoCartao {
    PRATA(0), OURO(1), PLATINA(2);

    final private int valor;
    TipoCartao(int valor) {
        this.valor = valor;
    }

    public int getValor() {return valor;}
}
