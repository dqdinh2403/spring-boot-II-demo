package com.example.springboot2.student;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	
	private StudentRepository studentRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents(){
		return this.studentRepository.findAll();
	}
	
	public void addNewStudent(Student student) {
		Optional<Student> studentOpt = this.studentRepository.findStudentByEmail(student.getEmail());
		
		if(studentOpt.isPresent()) {
			throw new IllegalStateException("Email exist");
		}
		
		this.studentRepository.save(student);
	}
	
	public void deleteStudent(Long id) {
		boolean exist = this.studentRepository.existsById(id);
		
		if(!exist) {
			throw new IllegalStateException("Student with id " + id + " does not exist");
		}
		
		this.studentRepository.deleteById(id);
	}
	
	@Transactional
	public void updateStudent(Long id, String name, String email) {
		this.studentRepository.findById(id)
			.map(student -> {
				student.setName(name);
				student.setEmail(email);
				return student;
			})
			.orElseThrow(() -> {
				throw new IllegalStateException("Student with id " + id + " does not exist");
			});
	}
	
}
