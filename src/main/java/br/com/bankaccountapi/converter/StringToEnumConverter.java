package br.com.bankaccountapi.converter;

import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter<T extends Enum> implements Converter<String, T> {

    Class<T> classe;

    @Override
    public T convert(String source) {
        return (T) Enum.valueOf(classe, source.toUpperCase());
    }
}