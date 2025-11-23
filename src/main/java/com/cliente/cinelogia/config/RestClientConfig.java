package com.cliente.cinelogia.config;
import org.springframework.beans.factory.annotation.Value; 
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.web.client.RestClient; 
import org.springframework.web.client.support.RestClientAdapter; 
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.cliente.cinelogia.client.ActorClient;
import com.cliente.cinelogia.client.PeliculaClient;
import com.cliente.cinelogia.client.RolClient;
import com.cliente.cinelogia.client.UsuarioClient;

@Configuration
public class RestClientConfig {
    @Value("${cinelogia.service.url}") 
    private String cursosServiceUrl; 
    
    @Bean 
    public RestClient restClient() 
    { 
        return RestClient.builder() 
        .baseUrl(cursosServiceUrl) 
        .build(); 
    }
    @Bean 
    public PeliculaClient peliculaClient(RestClient restClient) 
    { 
        RestClientAdapter adapter = RestClientAdapter.create(restClient); 
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build(); 
        return factory.createClient(PeliculaClient.class); 
    }

    @Bean 
    public RolClient rolClient(RestClient restClient) 
    { 
        RestClientAdapter adapter = RestClientAdapter.create(restClient); 
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build(); 
        return factory.createClient(RolClient.class); 
    }

    @Bean 
    public UsuarioClient usuarioClient(RestClient restClient) 
    { 
        RestClientAdapter adapter = RestClientAdapter.create(restClient); 
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build(); 
        return factory.createClient(UsuarioClient.class); 
    }

    @Bean 
    public ActorClient actorClient(RestClient restClient) 
    { 
        RestClientAdapter adapter = RestClientAdapter.create(restClient); 
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build(); 
        return factory.createClient(ActorClient.class); 
    }

}
