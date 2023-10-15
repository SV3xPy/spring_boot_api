package com.example.crud_shopall.services;


import com.example.crud_shopall.model.Tipo_pago;
        import com.example.crud_shopall.model.Privilegio;
        import com.example.crud_shopall.repositories.Tipo_pagoRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;

@Service
public class Tipo_pagoService {
    private final Tipo_pagoRepository tpRepository;

    @Autowired
    public Tipo_pagoService(Tipo_pagoRepository tpRepository) {
        this.tpRepository = tpRepository;
    }

    public List<Tipo_pago> getTipo_pago(){
        return this.tpRepository.findAll();
    }

    public Tipo_pago setTipo_pago(Tipo_pago tipo_pago){
        return this.tpRepository.save(tipo_pago);
    }

    public void deleteTipo_pago(Long id_tipo_dato) { this.tpRepository.deleteById(id_tipo_dato); }
    
}