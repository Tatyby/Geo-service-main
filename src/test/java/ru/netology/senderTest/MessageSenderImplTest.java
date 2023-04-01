package ru.netology.senderTest;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {
    LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
    GeoServiceImpl geoServiceIml = Mockito.mock(GeoServiceImpl.class);
    Map<String, String> headers = new HashMap<>();

    @Test
    public void testSendRus() {
        String expected = "Добро пожаловать";
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        Mockito.when(geoServiceIml.byIp("172.123.12.19")).thenReturn(new Location("Moskoy", Country.RUSSIA, "Lenina", 15));
        MessageSenderImpl messageSenderImpl = new MessageSenderImpl(geoServiceIml, localizationService);

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");

        String result = messageSenderImpl.send(headers);
        Assertions.assertEquals(result, expected);

    }

    @Test
    public void testSendEng() {
        String expected = "Welcome";
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        Mockito.when(geoServiceIml.byIp("96.44.183.149")).thenReturn(new Location("New York", Country.USA, "Lenina", 10));
        MessageSenderImpl messageSenderImpl = new MessageSenderImpl(geoServiceIml, localizationService);

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");

        String result = messageSenderImpl.send(headers);
        Assertions.assertEquals(result, expected);


    }

}
