package com.totvs.app.common.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TelefoneValidatorCommon.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TelefoneValidator {

    String message() default "{telefone.mal.formatado}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
