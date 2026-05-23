package com.example.grades.controller;

import com.example.grades.model.Student;
import com.example.grades.model.Subject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final List<Student> students = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public StudentController() {
        students.add(new Student(counter.incrementAndGet(), "Aarav Sharma", List.of(
            new Subject("Maths", 92), new Subject("Science", 88),
            new Subject("English", 76), new Subject("History", 84)
        )));
        students.add(new Student(counter.incrementAndGet(), "Priya Patel", List.of(
            new Subject("Maths", 78), new Subject("Science", 95),
            new Subject("English", 89), new Subject("History", 72)
        )));
        students.add(new Student(counter.incrementAndGet(), "Rohan Verma", List.of(
            new Subject("Maths", 55), new Subject("Science", 61),
            new Subject("English", 70), new Subject("History", 58)
        )));
        students.add(new Student(counter.incrementAndGet(), "Sneha Gupta", List.of(
            new Subject("Maths", 98), new Subject("Science", 94),
            new Subject("English", 91), new Subject("History", 96)
        )));
    }

    @GetMapping
    public List<Student> getAll() { return students; }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return students.stream().filter(s -> s.getId().equals(id))
                .findFirst().orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        student.setId(counter.incrementAndGet());
        students.add(student);
        return student;
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updated) {
        Student s = getById(id);
        s.setName(updated.getName());
        s.setSubjects(updated.getSubjects());
        return s;
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        students.removeIf(s -> s.getId().equals(id));
        return "Deleted student " + id;
    }
}
