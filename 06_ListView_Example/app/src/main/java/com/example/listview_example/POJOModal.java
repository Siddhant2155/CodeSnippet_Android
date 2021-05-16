package com.example.listview_example;

import java.util.ArrayList;

public class POJOModal {
    private String name;
    private String course;

    public POJOModal(String name, String course) {
        this.name = name;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public static ArrayList<POJOModal> generate10Model() {
        ArrayList<POJOModal> list = new ArrayList<>();

        list.add(new POJOModal("John", "Java, Android, Swing, JWT"));
        list.add(new POJOModal("Amenda", "SQL, MongoDB, Postgres"));
        list.add(new POJOModal("Alex", "Python, Flask, Django, Tinkter"));
        list.add(new POJOModal("Rose", "JavaScript, HTML, CSS, React"));
        list.add(new POJOModal("Higenburg", "IOS, SwiftUI, Objective-C"));
        list.add(new POJOModal("Max", "Android, Flutter, Kotlin"));
        list.add(new POJOModal("Jane", "Testing, Penetration Testing"));
        list.add(new POJOModal("Bob", "Networking, Operating System, System Design"));
        list.add(new POJOModal("Kate", "JetPack Compose, Ionic, React Native"));
        list.add(new POJOModal("Junior", "React, Node JS, Express JS, Vew JS"));




        return list;
    }
}
