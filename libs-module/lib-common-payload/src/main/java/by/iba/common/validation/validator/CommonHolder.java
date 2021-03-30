package by.iba.common.validation.validator;

final class CommonHolder {

    static final String PHONE_PATTERN = "^(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\\s*)?$";

    static final String UNP_PATTERN = "^[\\dA-Z]{9}$";

    private CommonHolder() {

    }
}
