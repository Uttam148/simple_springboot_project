package com.example.grades.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private Long id;
    private String name;
    private List<Subject> subjects = new ArrayList<>();

    public Student() {}

    public Student(Long id, String name, List<Subject> subjects) {
        this.id = id;
        this.name = name;
        this.subjects = subjects;
    }

    public double getAverage() {
        if (subjects == null || subjects.isEmpty()) return 0;
        return subjects.stream().mapToDouble(Subject::getMarks).average().orElse(0);
    }

    public String getGrade() {
        double avg = getAverage();
        if (avg >= 90) return "A+";
        if (avg >= 80) return "A";
        if (avg >= 70) return "B";
        if (avg >= 60) return "C";
        if (avg >= 50) return "D";
        return "F";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Subject> getSubjects() { return subjects; }
    public void setSubjects(List<Subject> subjects) { this.subjects = subjects; }
}
