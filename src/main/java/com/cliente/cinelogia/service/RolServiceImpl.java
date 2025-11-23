package com.cliente.cinelogia.service;
import org.springframework.stereotype.Service;
import com.cliente.cinelogia.client.RolClient;
import java.util.List;
import com.cliente.cinelogia.model.Rol;

@Service
public class RolServiceImpl implements IRolService {

    private final RolClient rolClient; 
    
    //@Autowired
    public RolServiceImpl(RolClient rolClient) {
        this.rolClient = rolClient;
    }

    @Override 
    public List<Rol> listarRoles(){
        List<Rol> lista = rolClient.listarRoles(); 
        return lista;
    }

    @Override 
    public Rol buscarPorId(Integer id){
        return rolClient.buscarPorId(id);
    }

    @Override
    public Rol crearRol(Rol rol) {
        return rolClient.creaRol(rol);
    }

    @Override
    public Rol eliminarRol(Integer id) {
        return rolClient.eliminaRol(id);
    }           
    
}
