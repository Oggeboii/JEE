package com.example.presentation;

import com.example.business.CarService;



import com.example.exceptions.mappers.NotFoundMapper;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.ExceptionMapper;
import com.example.exceptions.NotFound;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.spi.Dispatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class CarResourceTest {

    @Mock
    CarService carService;

    Dispatcher dispatcher;

    @BeforeEach
    public void setUp() {
        dispatcher = MockDispatcherFactory.createDispatcher();
        CarResource carResource = new CarResource(carService);
        dispatcher.getRegistry().addSingletonResource(carResource);
        ExceptionMapper<NotFound> mapper = new NotFoundMapper();
        dispatcher.getProviderFactory().registerProviderInstance(mapper);

    }

    @Test
    @DisplayName("Test name")
    void returnsEmptyListWhenNoCarsHaveBeenAdded() throws URISyntaxException, UnsupportedEncodingException {
        Mockito.when(carService.getCars()).thenReturn(List.of());
        MockHttpRequest request = MockHttpRequest.get("/cars");
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        assertEquals(200, response.getStatus());
        assertEquals("""
                {"cars":[]}""", response.getContentAsString());
    }

}
