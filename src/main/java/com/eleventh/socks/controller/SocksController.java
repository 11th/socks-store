package com.eleventh.socks.controller;

import com.eleventh.socks.dto.SocksDto;
import com.eleventh.socks.service.SocksService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/socks")
public class SocksController {
    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping("/income")
    public ResponseEntity<?> income(@RequestBody SocksDto socks) {
        socksService.income(socks);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/outcome")
    public ResponseEntity<?> outcome(@RequestBody SocksDto socks) {
        socksService.outcome(socks);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<SocksDto>> findWithParams(@PathParam("color") String color,
                                                               @PathParam("cottonPart") Integer cottonPart) {
        return ResponseEntity.ok(socksService.findWithParams(color, cottonPart));
    }
}
