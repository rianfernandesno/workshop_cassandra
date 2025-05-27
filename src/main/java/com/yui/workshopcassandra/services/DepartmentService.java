package com.yui.workshopcassandra.services;

import com.yui.workshopcassandra.model.dto.DepartmentDTO;
import com.yui.workshopcassandra.model.entities.Department;
import com.yui.workshopcassandra.repositories.DepartmentRepository;
import com.yui.workshopcassandra.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    public List<DepartmentDTO> findAll(){
        List<Department> list = repository.findAll();
        return list.stream().map(x -> new DepartmentDTO(x)).toList();
    }

    public DepartmentDTO findById(UUID id){
        Optional<Department> dep = repository.findById(id);
        Department result = dep.orElseThrow(()-> new ResourceNotFoundException("Objeto não encontrado"));

        return new DepartmentDTO(result);
    }
}
