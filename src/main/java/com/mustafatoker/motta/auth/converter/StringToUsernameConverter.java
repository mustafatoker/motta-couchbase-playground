package com.mustafatoker.motta.auth.converter;

import com.mustafatoker.motta.auth.domain.Username;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@ReadingConverter
@Component
public class StringToUsernameConverter implements Converter<String, Username> {
    @Override
    public Username convert(@NonNull String source) {
        return Username.of(source);
    }
}
