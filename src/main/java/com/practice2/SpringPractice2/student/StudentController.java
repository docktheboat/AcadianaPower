package com.practice2.SpringPractice2.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//API layer
@RestController
@RequestMapping(path = "/Student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public void addStudent(@RequestBody StudentModel student){ studentService.addStudent(student); }

    @GetMapping
    public List<StudentModel> getAllStudents(){ return studentService.getAllStudents(); }

    @DeleteMapping(path = "{idToDelete}")
    public void deleteStudent(@PathVariable("idToDelete") Long idToDelete){ studentService.deleteStudent(idToDelete); }

   @PutMapping(path = "{idToUpdate}")
    public void updateStudent(@PathVariable("idToUpdate") Long idToUpdate,
                                 @RequestParam(required = false) String name){
        studentService.updateStudent(idToUpdate, name);
    }

}
