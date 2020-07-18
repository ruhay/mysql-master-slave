package com.chuanchiai.enums;

import lombok.Getter;

/**
 * Created by chuanchiai on 2020/7/18
 */
@Getter
public enum DataSourceEnum {
    MASTER("master"),SLAVE("slave");
    private String datasourceName;
    DataSourceEnum(String datasourceName) {
        this.datasourceName = datasourceName;
    }
}
