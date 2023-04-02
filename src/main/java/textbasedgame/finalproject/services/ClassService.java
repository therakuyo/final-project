package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import textbasedgame.finalproject.entities.ClassEntity;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.repositories.ClassRepository;

import java.util.Optional;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;


    public ClassEntity add(String name){

        ClassEntity classEntity = new ClassEntity();
        classEntity.setClassName(name);

        return this.classRepository.save(classEntity);

    }


    public void delete(int id) throws NonexistentResourceException {

        Optional<ClassEntity> foundClass = this.classRepository.findById(id);

        if (!foundClass.isPresent()){
            throw new NonexistentResourceException("This class doesn't exist", id);
        } else {
            this.classRepository.delete(foundClass.get());
        }

    }

}
