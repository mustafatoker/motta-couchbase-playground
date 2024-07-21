package com.mustafatoker.motta.auth.converter;

import com.mustafatoker.motta.auth.domain.Password;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@WritingConverter
@Component
public class PasswordToStringConverter implements Converter<Password, String> {
    @Override
    public String convert(Password password) {
        return password.getValue();
    }
}
