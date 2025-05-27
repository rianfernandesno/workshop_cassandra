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
        Department result = getRefById(id);
        return new DepartmentDTO(result);
    }

    public DepartmentDTO insert(DepartmentDTO dto){
        Department dep = new Department();
        dep.setId(UUID.randomUUID());
        copy(dto, dep);
        dep = repository.insert(dep);
        return  new DepartmentDTO(dep);
    }

    public DepartmentDTO update(UUID id, DepartmentDTO dto){
        Department result = getRefById(id);
        copy(dto, result);
        result = repository.save(result);
        return new DepartmentDTO(result);
    }

    public void delete(UUID id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Entidade não existe");
        }

        repository.deleteById(id);
    }

    public Department getRefById(UUID id){
        Optional<Department> dep = repository.findById(id);
        return dep.orElseThrow(()-> new ResourceNotFoundException("Objeto não encontrado"));

    }

    private void copy(DepartmentDTO dto, Department dep){
        dep.setName(dto.getName());
    }
}
