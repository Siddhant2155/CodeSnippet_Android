package com.example.recycleviewexample

import kotlin.random.Random

data class Model(
    val name: String,
    val course: String,
    val completed: Int
) {

    companion object {
        @JvmField
        val NAMES = arrayOf(
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
        )

        @JvmField
        val COURSES = arrayOf(
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
        )

        @JvmStatic
        fun generateRandomCourses(n: Int): ArrayList<Model> {
            val array = ArrayList<Model>()

            for (i in 0..n) {
                array.add(
                    Model(
                        Model.NAMES[Random.nextInt(Model.NAMES.size)],
                        Model.COURSES[Random.nextInt(Model.COURSES.size)],
                        Random.nextInt(100)
                    )
                )
            }
            return array
        }
    }
}