package ru.netology.i18nTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

public class LocalizationServiceImplTest {
    @ParameterizedTest
    @MethodSource("addTestParameterized")
    public void testLocale(Country country, String expected) {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(country);
        Assertions.assertEquals(result, expected);

    }

    public static Stream<Arguments> addTestParameterized() {
        return Stream.of(
                Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.BRAZIL, "Welcome"),
                Arguments.of(Country.GERMANY, "Welcome")

        );
    }
}
