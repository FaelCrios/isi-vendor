package com.isi.isivendor.entities.enums;

public enum PedidoStatus {
    AGUARDANDO_PAGAMENTO(1),
    PAGO(2),
    A_CAMINHO(3),
    ENTREGUE(4),
    CANCELADO(5);

    private int code;

    private PedidoStatus(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static PedidoStatus valor(int code){
        for( PedidoStatus value : PedidoStatus.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("O código informado é inválido");
    }

}
