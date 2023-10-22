package edu.miu.waa.waaauctionsystem.models;

import lombok.Getter;

@Getter
public enum Role {
    SELLER ("SELLER"), CUSTOMER("CUSTOMER");
    private final String value;
    Role(String value) {
        this.value=value;
    }
}
