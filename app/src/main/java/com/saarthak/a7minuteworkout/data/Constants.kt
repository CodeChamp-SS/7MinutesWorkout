package com.saarthak.a7minuteworkout.data

import com.saarthak.a7minuteworkout.R
import com.saarthak.a7minuteworkout.model.Exercises

class Constants {

    companion object{

        fun exerciseList() : ArrayList<Exercises>{

            val allExercises = ArrayList<Exercises>()

            val e13 = Exercises(1, "Jumping Jacks", R.drawable.e13, false, false)
            allExercises.add(e13)

            val e17 = Exercises(2, "Burpees", R.drawable.e17, false, false)
            allExercises.add(e17)

            val e11 = Exercises(3, "Pushups", R.drawable.e11, false, false)
            allExercises.add(e11)

            val e9 = Exercises(4, "Squats", R.drawable.e9, false, false)
            allExercises.add(e9)

            val e1 = Exercises(5, "Mountain Climbers", R.drawable.e1, false, false)
            allExercises.add(e1)

            val e7 = Exercises(6, "Planks", R.drawable.e7, false, false)
            allExercises.add(e7)

            val e4 = Exercises(7, "Leg Raises", R.drawable.e4, false, false)
            allExercises.add(e4)

            val e23 = Exercises(8, "Lunges", R.drawable.e23, false, false)
            allExercises.add(e23)

            val e2 = Exercises(9, "Hip Raises", R.drawable.e2, false, false)
            allExercises.add(e2)

            val e3 = Exercises(10, "Alternate Heel Touches", R.drawable.e3, false, false)
            allExercises.add(e3)

            val e15 = Exercises(11, "High Knees", R.drawable.e15, false, false)
            allExercises.add(e15)

            val e21 = Exercises(12, "High to Low Planks", R.drawable.e21, false, false)
            allExercises.add(e21)

            val e6 = Exercises(13, "Bicycle Crunches", R.drawable.e6, false, false)
            allExercises.add(e6)


            val e24 = Exercises(14, "Advanced Crunches", R.drawable.e24, false, false)
            allExercises.add(e24)

            val e8 = Exercises(15, "Abdominal Crunches", R.drawable.e8, false, false)
            allExercises.add(e8)

            val e18 = Exercises(16, "V Sit Crunches", R.drawable.e18, false, false)
            allExercises.add(e18)

            val e5 = Exercises(17, "Windshield Wipers", R.drawable.e5, false, false)
            allExercises.add(e5)

            val e10 = Exercises(18, "Pushup with Arm Raises", R.drawable.e10, false, false)
            allExercises.add(e10)

            val e12 = Exercises(19, "Alternate Leg and Arm Raises", R.drawable.e12, false, false)
            allExercises.add(e12)

            val e14 = Exercises(20, "Step Ups", R.drawable.e14, false, false)
            allExercises.add(e14)

            val e16 = Exercises(21, "Jumping Squats", R.drawable.e16, false, false)
            allExercises.add(e16)

            val e19 = Exercises(22, "V Crunches", R.drawable.e19, false, false)
            allExercises.add(e19)

            val e20 = Exercises(23, "Diamond Pushups", R.drawable.e20, false, false)
            allExercises.add(e20)

            val e22 = Exercises(24, "Reverse Lung to Squats", R.drawable.e22, false, false)
            allExercises.add(e22)

            val e25 = Exercises(25, "Plank Jacks", R.drawable.e25, false, false)
            allExercises.add(e25)


            return allExercises
        }

    }

}