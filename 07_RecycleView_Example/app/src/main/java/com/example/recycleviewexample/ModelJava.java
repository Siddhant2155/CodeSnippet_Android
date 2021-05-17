package com.example.recycleviewexample;

import java.util.ArrayList;
import java.util.Random;

public class ModelJava {
    private String name;
    private String course;
    private String completed;

    private static String NAMES[] = new String[]{
            "John",
            "Jane",
            "Bob",
            "Brian",
            "Max",
            "Mario",
            "Ethan",
            "Winter",
            "Mrinda",
            "Alex",
            "Alexa",
            "Droid"
    };

    private static String COURSES[] = new String[]{
            "Java",
            "Spring",
            "Android",
            "IOS",
            "NodeJS",
            "ReactJS",
            "Go lang",
            "Python",
            "SwiftUI",
            "ReactNative",
            "Strut",
            "Flutter",
            "Dart"
    };

    public ModelJava(String name, String course, String completed) {
        this.name = name;
        this.course = course;
        this.completed = completed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public static ArrayList<ModelJava> generateRandomElements(int n) {
        ArrayList<ModelJava> l = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            l.add(
                    new ModelJava(
                            ModelJava.NAMES[random.nextInt(ModelJava.NAMES.length)],
                            ModelJava.COURSES[random.nextInt(ModelJava.COURSES.length)],
                            Integer.toString(random.nextInt(100))
                    )
            );
        }
        return l;
    }
}
