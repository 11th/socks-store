package com.eleventh.socks.service;

import com.eleventh.socks.dto.SocksDto;
import com.eleventh.socks.exception.SocksDataException;
import com.eleventh.socks.mapper.SocksMapper;
import com.eleventh.socks.model.Socks;
import com.eleventh.socks.repository.SocksRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Slf4j
public class SocksService {
    private final SocksRepository socksRepository;
    private final SocksMapper socksMapper;

    public SocksService(SocksRepository socksRepository,
                        SocksMapper socksMapper) {
        this.socksRepository = socksRepository;
        this.socksMapper = socksMapper;
    }

    @Transactional
    public void income(SocksDto incomeSocks) {
        log.info("Income socks {}", incomeSocks);
        socksMapper.validateSocksDto(incomeSocks);
        Socks socks = findSocks(incomeSocks.getColor(), incomeSocks.getCottonPart());
        if (socks == null) {
            socks = socksMapper.toSocks(incomeSocks);
        } else {
            socks.increaseQuantity(incomeSocks.getQuantity());
        }
        socksRepository.save(socks);
    }

    @Transactional
    public void outcome(SocksDto outcomeSocks) {
        log.info("Outcome outcomeSocks {}", outcomeSocks);
        socksMapper.validateSocksDto(outcomeSocks);
        Socks socks = findSocks(outcomeSocks.getColor(), outcomeSocks.getCottonPart());
        if (socks == null) {
            throw new SocksDataException("socks not found");
        } else if (socks.getQuantity() < outcomeSocks.getQuantity()) {
            throw new SocksDataException("invalid socks quantity");
        } else {
            socks.decreaseQuantity(outcomeSocks.getQuantity());
        }
        socksRepository.save(socks);
    }

    public Collection<SocksDto> findWithParams(String color, int cottonPart) {
        log.info("Find socks by color {} and cottonPart {}", color, cottonPart);
        return socksMapper.toCollectionSocksDto(socksRepository.findByColorAndCottonPart(color, cottonPart));
    }

    private Socks findSocks(String color, int cottonPart) {
        return socksRepository.findFirstByColorAndCottonPart(color, cottonPart);
    }
}
