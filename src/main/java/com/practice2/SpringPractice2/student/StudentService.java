package com.practice2.SpringPractice2.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

//Service layer/Business Logic
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) { this.studentRepository = studentRepository; }

    public void addStudent(StudentModel student){ studentRepository.save(student); }

    public List<StudentModel> getAllStudents(){ return studentRepository.findAll(); }

    public void deleteStudent(Long idToDelete){
        if(studentRepository.existsById(idToDelete)) studentRepository.deleteById(idToDelete); }

    @Transactional
    public void updateStudent(Long idToUpdate, String name) {

        if(studentRepository.existsById(idToUpdate)){
           StudentModel student = studentRepository.getById(idToUpdate);
            Optional<String> updatedName = Optional.ofNullable(name);
            if (updatedName.isPresent() && updatedName.get().length() > 0 &&
                    !updatedName.get().equals(student.getName())){
                    student.setName(name);
            }
        }
    }
}
