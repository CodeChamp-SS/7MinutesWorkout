package com.saarthak.a7minuteworkout.model

import java.io.Serializable

class Exercises (var id : Int, var name : String, var img : Int,
                 var isSelected : Boolean, var isCompleted : Boolean) : Serializable