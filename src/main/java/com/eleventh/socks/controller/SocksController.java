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
    public ResponseEntity<?> doIncome(@RequestBody SocksDto socks) {
        socksService.doIncome(socks);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/outcome")
    public ResponseEntity<?> doOutcome(@RequestBody SocksDto socks) {
        socksService.doOutcome(socks);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<SocksDto>> findWithParams(@PathParam("color") String color,
                                                               @PathParam("operation") String operation,
                                                               @PathParam("cottonPart") Integer cottonPart) {
        return ResponseEntity.ok(socksService.findWithParams(color, operation, cottonPart));
    }
}
