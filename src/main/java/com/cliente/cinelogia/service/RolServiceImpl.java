package com.cliente.cinelogia.service;
import org.springframework.stereotype.Service;
import com.cliente.cinelogia.client.RolClient;
import java.util.List;
import com.cliente.cinelogia.model.Authority;

@Service
public class RolServiceImpl implements IRolService {

    private final RolClient rolClient; 
    
    //@Autowired
    public RolServiceImpl(RolClient rolClient) {
        this.rolClient = rolClient;
    }

    @Override 
    public List<Authority> listarRoles(){
        List<Authority> lista = rolClient.listarRoles(); 
        return lista;
    }
    
    @Override 
    public Authority buscarPorId(Integer id){
        return rolClient.buscarPorId(id);
    }

    @Override
    public Authority crearRol(Authority rol) {
        return rolClient.guardarRol(rol);
    }

    @Override
    public Authority eliminarRol(Integer id) {
        return rolClient.eliminarPorId(id);
    }           
    
}
