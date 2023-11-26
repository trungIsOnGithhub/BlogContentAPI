package com.api.forum.service.implementation;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.forum.entity.Role;
import com.api.forum.payload.RoleDTO;
import com.api.forum.repository.RoleRepository;
import com.api.forum.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository repository;
    @Autowired
    ModelMapper mapper;

    public RoleDTO createRole(RoleDTO dto) {
        Role entity = mapper.map(dto, Role.class);

        Role newAdded = this.repository.save(entity);

        if (Objects.isNull(newAdded))
            return null;        

        return dto;
    }
}
