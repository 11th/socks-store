package com.eleventh.socks.service;

import com.eleventh.socks.dto.SocksDto;
import com.eleventh.socks.exception.SocksDataException;
import com.eleventh.socks.mapper.SocksMapper;
import com.eleventh.socks.model.Operation;
import com.eleventh.socks.model.Socks;
import com.eleventh.socks.repository.SocksRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public void doIncome(SocksDto incomeSocks) {
        log.info("Income socks {}", incomeSocks);
        validate(incomeSocks);
        Socks socks = findSocks(incomeSocks.getColor(), incomeSocks.getCottonPart());
        if (socks == null) {
            socks = socksMapper.toSocks(incomeSocks);
        } else {
            socks.increaseQuantity(incomeSocks.getQuantity());
        }
        socksRepository.save(socks);
    }

    @Transactional
    public void doOutcome(SocksDto outcomeSocks) {
        log.info("Outcome outcomeSocks {}", outcomeSocks);
        validate(outcomeSocks);
        Socks socks = findSocks(outcomeSocks.getColor(), outcomeSocks.getCottonPart());
        if (socks == null) {
            throw new SocksDataException("Socks not found");
        } else if (socks.getQuantity() < outcomeSocks.getQuantity()) {
            throw new SocksDataException("Invalid socks quantity");
        } else {
            socks.decreaseQuantity(outcomeSocks.getQuantity());
        }
        socksRepository.save(socks);
    }

    public Collection<SocksDto> findWithParams(String color, String operation, int cottonPart) {
        log.info("Find socks with color {} and cottonPart {} {}", color, operation, cottonPart);
        Operation op = Operation.fromValue(operation)
                .orElseThrow(() -> new IllegalArgumentException("Invalid operation"));
        Collection<Socks> socks = new ArrayList<>();
        switch (op) {
            case MORE_THAN -> {
                socks = socksRepository.findByColorAndCottonPartGreaterThan(color, cottonPart);
            }
            case LESS_THAN -> {
                socks = socksRepository.findByColorAndCottonPartLessThan(color, cottonPart);
            }
            case EQUAL -> {
                socks = socksRepository.findByColorAndCottonPart(color, cottonPart);
            }
        }
        return socksMapper.toCollectionSocksDto(socks);
    }

    private Socks findSocks(String color, int cottonPart) {
        return socksRepository.findFirstByColorAndCottonPart(color, cottonPart);
    }

    private void validate(SocksDto socksDto) {
        StringBuilder stringBuilder = new StringBuilder();
        if (socksDto == null) {
            stringBuilder.append("Socks is empty; ");
        } else {
            if (socksDto.getColor() == null) {
                stringBuilder.append("Color is empty; ");
            }
            if (socksDto.getCottonPart() <= 0) {
                stringBuilder.append("Cotton part should be greater then 0; ");
            }
            if (socksDto.getQuantity() <= 0) {
                stringBuilder.append("Quantity should be greater then 0; ");
            }
        }
        if (!stringBuilder.isEmpty()) {
            throw new SocksDataException(stringBuilder.toString());
        }
    }
}
