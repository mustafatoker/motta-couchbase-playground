package com.mustafatoker.motta.auth.converter;

import com.mustafatoker.motta.auth.domain.Password;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@ReadingConverter
@Component
public class StringToPasswordConverter implements Converter<String, Password> {
    @Override
    public Password convert(@NonNull String source) {
        return Password.ofEncoded(source);
    }

}