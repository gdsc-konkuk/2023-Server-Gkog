package com.gkog.prototype.domain.user.enums;

import com.gkog.prototype.global.enums.EnumMapperType;

import java.util.Arrays;

public enum ProviderType implements EnumMapperType {

    APPLE("apple"),
    GOOGLE("google");

    private String description;

    ProviderType(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return name();
    }

    @Override
    public String getDescription() {
        return description;
    }

    public static ProviderType findByCode(String code) {
        return Arrays.stream(ProviderType.values())
                .filter(value -> code.equals(value.getName()))
                .findAny()
                .orElse(null);
    }

}
