package com.viictrp.api.finance.server.api.domain.enums;

public final class EnumResolver {

    private EnumResolver() {

    }

    public static <E extends Enum<E>> Enum<E> customValueOf(Class<E> clazz, String name) {
        for(E enun : clazz.getEnumConstants()) {
            if (enun.name().equalsIgnoreCase(name)) {
                return enun;
            }
        }
        throw new IllegalArgumentException("Enum" + name+ " não encontrado");
    }
}
