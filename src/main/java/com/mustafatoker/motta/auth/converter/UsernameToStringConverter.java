package com.mustafatoker.motta.auth.converter;

import com.mustafatoker.motta.auth.domain.Username;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@WritingConverter
@Component
public class UsernameToStringConverter implements Converter<Username, String> {
    @Override
    public String convert(Username username) {
        return username.getValue();
    }
}
