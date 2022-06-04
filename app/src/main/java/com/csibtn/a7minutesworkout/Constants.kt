package com.csibtn.a7minutesworkout

object Constants {


    fun defaultExerciseList() :  MutableList<ExerciseModel>{
        val exerciseList = mutableListOf<ExerciseModel>()

        val neckRotations = ExerciseModel(1,"Neck Rotations",R.drawable.neck_rotation,false,false)
        val armStretching = ExerciseModel(2,"Arm Stretching",R.drawable.arm_stretching,false,false)
        val backArmStretching = ExerciseModel(3,"Back and Arm Stretching",R.drawable.back_and_arm_stretching,false,false)
        val jumpingJacks = ExerciseModel(4,"Jumping Jacks",R.drawable.jumping_jacks,false,false)
        val abs = ExerciseModel(5,"ABS",R.drawable.abs,false,false)
        val squats = ExerciseModel(6,"Squats",R.drawable.squats,false,false)
        val plank = ExerciseModel(7,"Plank",R.drawable.plank,false,false)
        val star = ExerciseModel(8,"Star",R.drawable.star,false,false)
        val lunges = ExerciseModel(9,"Lunges",R.drawable.lunges,false,false)
        val pushUps = ExerciseModel(10,"Push-ups",R.drawable.push_ups,false,false)
        exerciseList.add(neckRotations)
        exerciseList.add(armStretching)
        exerciseList.add(backArmStretching)
        exerciseList.add(jumpingJacks)
        exerciseList.add(abs)
        exerciseList.add(squats)
        exerciseList.add(plank)
        exerciseList.add(star)
        exerciseList.add(lunges)
        exerciseList.add(pushUps)
        return exerciseList
    }
}