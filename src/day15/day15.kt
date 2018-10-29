package day15

import java.io.File

data class Ingredient(val name: String,
                      val capacity: Int,
                      val durability: Int,
                      val flavor: Int,
                      val texture: Int,
                      val calories: Int)


fun main(args: Array<String>) {
    val ingredients = mutableListOf<Ingredient>()
    File("/Users/david.valic/IdeaProjects/AdventCalendar/src/day15/day15src.txt").forEachLine { line ->
        val l = line.replace(":", "").replace(",", "").split(" ")
        ingredients.add(Ingredient(l[0], l[2].toInt(), l[4].toInt(), l[6].toInt(), l[8].toInt(), l[10].toInt()))
    }
    var bestRecipe = Int.MIN_VALUE
    (0..100).forEach{ first ->
        (0..100-first).forEach {second ->
            (0..100-first-second).forEach { third ->
                val forth = 100 - first - second - third
                val capacity = (ingredients[0].capacity * first + ingredients[1].capacity * second + ingredients[2].capacity * third + ingredients[3].capacity * forth).coerceAtLeast(0)
                val durability = (ingredients[0].durability * first + ingredients[1].durability * second + ingredients[2].durability * third + ingredients[3].durability * forth).coerceAtLeast(0)
                val flavor = (ingredients[0].flavor * first + ingredients[1].flavor * second + ingredients[2].flavor * third + ingredients[3].flavor * forth).coerceAtLeast(0)
                val texture = (ingredients[0].texture * first + ingredients[1].texture * second + ingredients[2].texture * third + ingredients[3].texture * forth).coerceAtLeast(0)
                val mixture = capacity * durability * flavor * texture

                //part2
                val calories = (ingredients[0].calories * first + ingredients[1].calories * second + ingredients[2].calories * third + ingredients[3].calories * forth)
                if (mixture > bestRecipe && calories == 500) {
                    bestRecipe = mixture
                }
            }
        }
    }
    println(bestRecipe)
}