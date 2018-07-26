package com.myapp.business.core.constants;

public class ApiCode {
    public static final String UNKNOWN = "-1";

    public enum ExampleEnum {
        SUCCESS(""),
        UNKNOWN(ApiCode.UNKNOWN);

        private final String mValue;

        ExampleEnum(String value) {
            mValue = value;
        }

        public static ExampleEnum getFromValue(String value) {
            for (ExampleEnum c : ExampleEnum.values()) {
                if (c.getValue().equals(value))
                    return c;
            }
            return UNKNOWN;
        }

        public String getValue() {
            return mValue;
        }

    }
}
