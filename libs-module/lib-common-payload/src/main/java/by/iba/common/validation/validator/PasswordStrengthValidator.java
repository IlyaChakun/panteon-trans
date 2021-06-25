package by.iba.common.validation.validator;

import by.iba.common.validation.annotation.PasswordStrengthValidation;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordStrengthValidation, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Properties props = new Properties();

        InputStream inputStream = getClass()

                .getClassLoader().getResourceAsStream("passay.properties");

        try {
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        MessageResolver resolver = new PropertiesMessageResolver(props);


        PasswordValidator validator = new PasswordValidator(resolver, Arrays.asList(

                new LengthRule(8, 16),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),

                new CharacterRule(EnglishCharacterData.LowerCase, 1),


                new CharacterRule(EnglishCharacterData.Digit, 1),

                new CharacterRule(EnglishCharacterData.Special, 1),

                new WhitespaceRule(),

                new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false),

                new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false)

        ));

        RuleResult result = validator.validate(new PasswordData(value.toString()));


        if (result.isValid()) {

            return true;

        }

        List<String> messages = validator.getMessages(result);
        String messageTemplate = String.join(",", messages);
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
