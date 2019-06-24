package lk.codelabs.springboot.samples.propertyorderexample.service;

import lk.codelabs.springboot.samples.propertyorderexample.modal.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {


    @Override
    public Student fetch()  {
        Student student= new Student();
        student.setId(10);
        student.setName("Krish");
        student.setCode("S001");
        return student;
    }
}
