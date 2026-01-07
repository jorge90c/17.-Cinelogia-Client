package com.cliente.cinelogia.config;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value; 
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.http.MediaType; 
import org.springframework.http.converter.StringHttpMessageConverter; 
import org.springframework.web.client.RestClient; 
import org.springframework.web.client.support.RestClientAdapter; 
import org.springframework.web.service.invoker.HttpServiceProxyFactory; 
import java.nio.charset.StandardCharsets;

import com.cliente.cinelogia.client.ActorClient;
import com.cliente.cinelogia.client.PeliculaClient;
import com.cliente.cinelogia.client.ReviewClient;
import com.cliente.cinelogia.client.RolClient;
import com.cliente.cinelogia.client.UsuarioClient;

@Configuration
public class RestClientConfig {
    @Value("${cinelogia.service.url}") 
    private String cinelogiaServiceUrl; 

    @Value("${security.service.url}") 
    private String securityServiceUrl; 
    
    @Bean 
    public RestClient cinelogiaClient() 
    { 
        return RestClient.builder() 
        .baseUrl(cinelogiaServiceUrl)
        //Forzar UTF‑8 en todas las peticiones 
        .messageConverters(converters -> { 
            converters.add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8)); }) 
        .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8") 
        .defaultHeader("Accept-Charset", "UTF-8")
        .build(); 
    }

    @Bean 
    public PeliculaClient peliculaClient(@Qualifier("cinelogiaClient")RestClient cinelogiaClient) 
    { 
        RestClientAdapter adapter = RestClientAdapter.create(cinelogiaClient); 
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build(); 
        return factory.createClient(PeliculaClient.class); 
    }

    @Bean 
    public ActorClient actorClient(@Qualifier("cinelogiaClient")RestClient cinelogiaClient) 
    { 
        RestClientAdapter adapter = RestClientAdapter.create(cinelogiaClient); 
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build(); 
        return factory.createClient(ActorClient.class); 
    }


    @Bean 
    public RestClient securityClient() 
    { 
        return RestClient.builder() 
        .baseUrl(securityServiceUrl)
        .messageConverters(converters -> { 
            converters.add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8)); }) 
        .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8") 
        .defaultHeader("Accept-Charset", "UTF-8")
        .build(); 
    }


    @Bean 
    public RolClient rolClient(@Qualifier("securityClient") RestClient securityClient) 
    { 
        RestClientAdapter adapter = RestClientAdapter.create(securityClient); 
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build(); 
        return factory.createClient(RolClient.class); 
    }

    @Bean 
    public UsuarioClient usuarioClient(@Qualifier("securityClient") RestClient securityClient) 
    { 
        RestClientAdapter adapter = RestClientAdapter.create(securityClient); 
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build(); 
        return factory.createClient(UsuarioClient.class); 
    }

    @Bean 
    public ReviewClient reviewClient(@Qualifier("securityClient") RestClient securityClient) 
    {
        RestClientAdapter adapter = RestClientAdapter.create(securityClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(ReviewClient.class);
    }

    

}
