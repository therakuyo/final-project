package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import textbasedgame.finalproject.entities.ClassEntity;
import textbasedgame.finalproject.repositories.ClassRepository;


@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;


    public ClassEntity add(String name){

        ClassEntity classEntity = new ClassEntity();
        classEntity.setClassName(name);

        return this.classRepository.save(classEntity);

    }

    public void delete(int id){

        this.classRepository.deleteById(id);

    }

}
