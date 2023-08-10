package com.eleventh.socks.repository;

import com.eleventh.socks.model.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Integer> {
    Socks findFirstByColorAndCottonPart(String color, int cottonPart);

    Collection<Socks> findByColorAndCottonPart(String color, int cottonPart);

    Collection<Socks> findByColorAndCottonPartGreaterThan(String color, int cottonPart);

    Collection<Socks> findByColorAndCottonPartLessThan(String color, int cottonPart);
}
