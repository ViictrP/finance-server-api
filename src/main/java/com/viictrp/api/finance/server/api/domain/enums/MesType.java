package com.viictrp.api.finance.server.api.domain.enums;

import java.util.HashMap;
import java.util.Map;

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

    public static MesType customValueOfWithName(String name) {
        String ptBrName = resolveName(name);
        return (MesType) EnumResolver.customValueOf(MesType.class, ptBrName);
    }

    public static MesType nextMonth(MesType mesType) {
        switch (mesType) {
            case JANEIRO:
                return FEVEREIRO;
            case FEVEREIRO:
                return MARCO;
            case MARCO:
                return ABRIL;
            case ABRIL:
                return MAIO;
            case MAIO:
                return JUNHO;
            case JUNHO:
                return JULHO;
            case JULHO:
                return AGOSTO;
            case AGOSTO:
                return SETEMBRO;
            case SETEMBRO:
                return OUTUBRO;
            case OUTUBRO:
                return NOVEMBRO;
            case NOVEMBRO:
                return DEZEMBRO;
            default:
                return JANEIRO;
        }
    }

    private static String resolveName(String month) {
        Map<String, String> in18EnglishMonths = new HashMap<>();
        in18EnglishMonths.put("Janeiro", JANEIRO.name());
        in18EnglishMonths.put("Fevereiro", FEVEREIRO.name());
        in18EnglishMonths.put("Março", MARCO.name());
        in18EnglishMonths.put("Abril", ABRIL.name());
        in18EnglishMonths.put("Maio", MAIO.name());
        in18EnglishMonths.put("Junho", JUNHO.name());
        in18EnglishMonths.put("Julho", JULHO.name());
        in18EnglishMonths.put("Agosto", AGOSTO.name());
        in18EnglishMonths.put("Setembro", SETEMBRO.name());
        in18EnglishMonths.put("Outubro", OUTUBRO.name());
        in18EnglishMonths.put("Novembro", NOVEMBRO.name());
        in18EnglishMonths.put("Dezembro", DEZEMBRO.name());
        return in18EnglishMonths.get(month);
    }
}
