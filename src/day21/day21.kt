package day21

class Fighter(var hitPoints: Int,
              var damage: Int,
              var armor: Int = 0,
              var hasArmor: Boolean = false,
              var hasRings: Int = 0 )

class Accessory(val cost: Int,
                val damage: Int,
                val armor: Int)

private val weapons = listOf(
        Accessory(8, 4, 0),
        Accessory(10, 5, 0),
        Accessory(25, 6, 0),
        Accessory(40, 7, 0),
        Accessory(74, 8, 0)
)
private val armors = listOf(
        Accessory(13, 0, 1),
        Accessory(31, 0, 2),
        Accessory(53, 0, 3),
        Accessory(75, 0, 4),
        Accessory(102, 0, 5)
)
private val rings = listOf(
        Accessory(25, 1, 0),
        Accessory(50, 2, 0),
        Accessory(100, 3, 0),
        Accessory(20, 0, 1),
        Accessory(40, 0, 2),
        Accessory(80, 0, 3)
)

private const val bossMaxHp = 100
private const val bossDamage = 8
private const val bossArmor = 2

private const val playerHp = 100

fun main(args: Array<String>) {
//    File("/Users/david.valic/IdeaProjects/AdventCalendar/src/day21/day21src.txt").forEachLine { line ->
//
//    }

    var minCost = Int.MAX_VALUE
    for (weapon in weapons) {
        val newCost = buildPlayer1(Fighter(playerHp, weapon.damage), weapon.cost)
        if (newCost < minCost) {
            minCost = newCost
        }
    }
    var maxCost = Int.MIN_VALUE
    for (weapon in weapons) {
        val newCost = buildPlayer2(Fighter(playerHp, weapon.damage), weapon.cost)
        if (newCost > maxCost) {
            maxCost = newCost
        }
    }
    println(simulateGame(Fighter(100, 9, 0))) // max cost = 158 with ring dmg +3 (100), ring dmg +2 (50), dagger (8)
    println(minCost)
    println(maxCost)
}

fun simulateGame(player: Fighter): Boolean {
    var bossHp = bossMaxHp
    var playersTurn = true
    var playerDamage = player.damage - bossArmor
    if (playerDamage <= 0 ) playerDamage = 1
    var bossDamage = bossDamage - player.armor
    if (bossDamage <= 0) bossDamage = 1
    while(player.hitPoints > 0 && bossHp > 0) {
        if (playersTurn) {
            bossHp -= playerDamage
            playersTurn = false
        } else {
            player.hitPoints -= bossDamage
            playersTurn = true
        }
    }
    return player.hitPoints > 0
}

fun buildPlayer1(player: Fighter, currentCost: Int): Int {
    var totalCost = currentCost
    player.hitPoints = playerHp
    if (simulateGame(player)) {
        return totalCost
    } else {
        var cost = Int.MAX_VALUE
        if (player.hasArmor.not()){
            for (armor in armors) {
                player.armor += armor.armor
                player.hasArmor = true
                totalCost += armor.cost
                val newCost = buildPlayer1(player, totalCost)
                if (newCost < cost) {
                    cost = newCost
                }
                totalCost -= armor.cost
                player.hasArmor = false
                player.armor -= armor.armor
            }
        }
        if (player.hasRings < 3) {
            for (ring in rings) {
                player.armor += ring.armor
                player.damage += ring.damage
                player.hasRings ++
                totalCost += ring.cost
                val newCost = buildPlayer1(player, totalCost)
                if (newCost < cost) {
                    cost = newCost
                }
                totalCost -= ring.cost
                player.hasRings --
                player.armor -= ring.armor
                player.damage -= ring.damage
            }
        }
        return cost
    }
}

fun buildPlayer2(player: Fighter, currentCost: Int): Int {
    var totalCost = currentCost
    player.hitPoints = playerHp
    if (!simulateGame(player)) {
        return totalCost
    } else {
        var cost = Int.MIN_VALUE
        if (player.hasArmor.not()){
            for (armor in armors) {
                player.armor += armor.armor
                player.hasArmor = true
                totalCost += armor.cost
                val newCost = buildPlayer2(player, totalCost)
                if (newCost > cost) {
                    cost = newCost
                }
                totalCost -= armor.cost
                player.hasArmor = false
                player.armor -= armor.armor
            }
        }
        if (player.hasRings < 3) {
            for (ring in rings) {
                player.armor += ring.armor
                player.damage += ring.damage
                player.hasRings ++
                totalCost += ring.cost
                val newCost = buildPlayer2(player, totalCost)
                if (newCost > cost) {
                    cost = newCost
                }
                totalCost -= ring.cost
                player.hasRings --
                player.armor -= ring.armor
                player.damage -= ring.damage
            }
        }
        return cost
    }
}