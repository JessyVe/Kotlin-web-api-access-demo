package models

data class Cat(val name : String,
               val color : String,
               val age : Int,
               val state : String){

    override fun toString(): String{
        return "Cat $name (Color: $color, Age: $age, State: $state)"
    }
}