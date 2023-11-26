package com.api.forum.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.forum.payload.RoleDTO;
import com.api.forum.service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/trial/roles")
public class RoleController {
    @Autowired
    RoleService service;
    
    @PostMapping(value = "/",
                 produces = {"application/json"})
    public ResponseEntity<RoleDTO> createRole(@Valid @RequestBody RoleDTO dto)
        throws URISyntaxException {
        RoleDTO savedRoleDTO = this.service.createRole(dto);

        if (Objects.isNull(savedRoleDTO)) {
            return ResponseEntity.badRequest().body(savedRoleDTO);
        }

        return ResponseEntity.created(new URI("")).body(savedRoleDTO);
    }
}
