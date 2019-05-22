package com.viictrp.api.finance.server.api.domain.enums;

public enum MesType {

    JANEIRO,
    FEVEREIRO,
    MARCO,
    ABRIL,
    MAIO,
    JUNHO,
    JULHO,
    AGOSTO,
    SETEMBRO,
    OUTUBRO,
    NOVEMBRO,
    DEZEMBRO;

    public static MesType customValueOf(String name) {
       return (MesType) EnumResolver.customValueOf(MesType.class, name);
    }
}
