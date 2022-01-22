package com.practice2.SpringPractice2;

import com.practice2.SpringPractice2.student.StudentModel;
import com.practice2.SpringPractice2.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class SpringPractice2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringPractice2Application.class, args);
	}


}
@RestController
class Welcome{

	private final StudentService studentService;

	@Autowired
	public Welcome(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/")
	public String welcome(){
		return studentService.getAllStudents().toString();
	}
}
