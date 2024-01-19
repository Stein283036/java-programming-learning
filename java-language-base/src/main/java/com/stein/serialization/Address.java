package com.stein.serialization;

import java.io.Serializable;

/**
 * @author stein
 * @date 2024/1/9
 */
class Address implements Serializable {
    public String province;
    public String detail;

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
