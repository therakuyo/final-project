package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import textbasedgame.finalproject.dtos.ClassDTO;
import textbasedgame.finalproject.entities.ClassEntity;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.repositories.ClassRepository;


@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;


    public ClassEntity add(ClassDTO classDTO) {

        ClassEntity classEntity = new ClassEntity();
        classEntity.setClassName(classDTO.getName());

        return this.classRepository.save(classEntity);

    }

    @Transactional
    public void delete(int id) throws NonexistentResourceException {

        ClassEntity classEntity = this.classRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Class doesn't exist", id));

        this.classRepository.delete(classEntity);

    }

    @Transactional
    public ClassEntity update(int id, ClassDTO classDTO) throws NonexistentResourceException {

        ClassEntity classEntity = this.classRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Class doesn't exist", id));

        classEntity.setClassName(classDTO.getName());

        return this.classRepository.save(classEntity);

    }

    public Iterable<ClassEntity> getAll(){

        return this.classRepository.findAll();

    }

}
