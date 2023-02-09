package com.iurirest.crud.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CPFValidator implements ConstraintValidator<CPF, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty() || !isCPF(value)) {
            return false;
        }
        return true;
    }

    private static boolean isCPF(String cpf) {
        // Implemente a validação de CPF aqui
        // Retornar verdadeiro se o CPF for válido, falso caso contrário
        return false;
    }
}
