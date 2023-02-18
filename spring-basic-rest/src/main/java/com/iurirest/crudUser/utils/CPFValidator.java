package com.iurirest.crudUser.utils;

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
        int primeiroDigitoVerificador = 0;
        int segundoDigitoVerificador = 0;

        //Multiplica os 9 primeiros digitos do CPF pela coluna
        for (int i = 1;i<=9;i++) {
            primeiroDigitoVerificador += Integer.parseInt(cpf.substring(i-1, i)) * i;
        }

        //Divide por 11, se der 10 o digito verificador é 0, se for menor é ele mesmo
        if (primeiroDigitoVerificador % 11 > 9) {
            primeiroDigitoVerificador = 0;
        }
        else {
            primeiroDigitoVerificador = primeiroDigitoVerificador % 11;
        }

        //Multiplica os 9 primeiros digitos do CPF pela coluna-1
        for (int i = 0;i<9;i++) {
            segundoDigitoVerificador += Integer.parseInt(cpf.substring(i, i+1)) * i;
        }
        //Soma + o primeiro digito verificador multiplicado por 9
        segundoDigitoVerificador += primeiroDigitoVerificador * 9;

        //Divide por 11, se der 10 o digito verificador é 0, se for menor é ele mesmo
        if (segundoDigitoVerificador % 11 > 9) {
            segundoDigitoVerificador = 0;
        }
        else {
            segundoDigitoVerificador = segundoDigitoVerificador % 11;
        }

        //Se o primeiro e o segundo digito forem iguais aos 2 ultimos numeros do CPF, o CPF esta correto e retorna true
        if ((primeiroDigitoVerificador == Integer.parseInt(cpf.substring(9,10))) && (segundoDigitoVerificador == Integer.parseInt(cpf.substring(10,11)))){
            return true;
        }
        else {
            return false;
        }
    }
}
