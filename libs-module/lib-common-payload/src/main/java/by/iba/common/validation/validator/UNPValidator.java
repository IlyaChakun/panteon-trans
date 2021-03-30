package by.iba.common.validation.validator;

import by.iba.common.validation.annotation.ValidUNP;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.iba.common.validation.validator.CommonHolder.UNP_PATTERN;

public class UNPValidator implements ConstraintValidator<ValidUNP, String> {

    private static final Pattern PATTERN = Pattern.compile(UNP_PATTERN);

    @Override
    public boolean isValid(final String unp, final ConstraintValidatorContext context) {
        if (Objects.nonNull(unp)) {
            return validateUnp(unp);
        } else {
            return false;
        }
    }

    private boolean validateUnp(final String unp) {
        final Matcher matcher = PATTERN.matcher(unp);
        return matcher.matches();
    }

}
