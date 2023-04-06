package com.anjun.eskokado.apideliveryesk.domain.enums;
public enum StateDelivery {
    PENDING(1, "Pending"),
    DELIVERED(2, "Delivered"),
    CANCELED(3, "Canceled");

    private int code;
    private String description;

    private StateDelivery(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static StateDelivery toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for(StateDelivery x : StateDelivery.values()) {
            if (code.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id Invalid: " + code);
    }
}
