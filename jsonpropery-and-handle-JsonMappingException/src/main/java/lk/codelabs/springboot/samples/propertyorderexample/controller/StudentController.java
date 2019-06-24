package lk.codelabs.springboot.samples.propertyorderexample.controller;

import lk.codelabs.springboot.samples.propertyorderexample.modal.Student;
import lk.codelabs.springboot.samples.propertyorderexample.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/services")
@RestController
public class StudentController {

    @Autowired
    StudentService studentService;



    @RequestMapping(value = "/student")
    public Student fetchStudent() {

        return studentService.fetch();

    }
    @RequestMapping(value = "/student",method = RequestMethod.POST)
    public String create(@RequestBody Student student)  {


            System.out.println(student.getName() + "received");
            return "OK";


    }
}
