package com.viictrp.api.finance.server.api.converter.carteira;

import com.viictrp.api.finance.server.api.converter.Converter;
import com.viictrp.api.finance.server.api.domain.Carteira;
import com.viictrp.api.finance.server.api.dto.CarteiraDTO;
import org.springframework.stereotype.Component;

@Component
public class CarteiraConverter implements Converter<Carteira, CarteiraDTO> {

    @Override
    public Carteira toEntity(CarteiraDTO carteiraDTO) {
        Carteira carteira = null;
        if (carteiraDTO != null) {
            carteira = new Carteira();
            carteira.setId(carteiraDTO.getId());
            carteira.setMes(carteiraDTO.getMes());
        }
        return carteira;
    }

    @Override
    public CarteiraDTO toDto(Carteira carteira) {
        CarteiraDTO carteiraDTO = null;
        if (carteira != null) {
            carteiraDTO = new CarteiraDTO();
            carteiraDTO.setId(carteira.getId());
            carteiraDTO.setMes(carteiraDTO.getMes());
        }
        return carteiraDTO;
    }
}
