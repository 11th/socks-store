package com.eleventh.socks.mapper;

import com.eleventh.socks.dto.SocksDto;
import com.eleventh.socks.exception.SocksDataException;
import com.eleventh.socks.model.Socks;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SocksMapper {
    public Socks toSocks(SocksDto socksDto) {
        Socks socks = new Socks();
        socks.setColor(socksDto.getColor());
        socks.setCottonPart(socksDto.getCottonPart());
        socks.setQuantity(socksDto.getQuantity());
        return socks;
    }

    public SocksDto toSocksDto(Socks socks) {
        SocksDto socksDto = new SocksDto();
        socksDto.setColor(socks.getColor());
        socksDto.setCottonPart(socks.getCottonPart());
        socksDto.setQuantity(socks.getQuantity());
        return socksDto;
    }

    public Collection<SocksDto> toCollectionSocksDto(Collection<Socks> socks){
        return socks.stream()
                .map(this::toSocksDto)
                .toList();
    }

    public void validateSocksDto(SocksDto socksDto) {
        if (socksDto == null) {
            throw new SocksDataException("Socks is empty");
        }
        if (socksDto.getColor() == null) {
            throw new SocksDataException("Color is empty");
        }
        if (socksDto.getCottonPart() <= 0) {
            throw new SocksDataException("Cotton part should be greater then 0");
        }
        if (socksDto.getQuantity() <= 0) {
            throw new SocksDataException("Quantity should be greater then 0");
        }
    }
}
