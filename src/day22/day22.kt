package day22

class Fighter(var hitPoints: Int,
              var skills: MutableList<Skill>,
              var mana: Int = 500)

class Skill(val name: String,
            val manaCost: Int = 0,
            val damage: Int = 0,
            val heal: Int = 0,
            val armor: Int = 0,
            val newMana: Int = 0,
            val duration: Int = 0,
            val type: Int = 0) {
    var timer = 0
}

private val skills = listOf(
        Skill("Magic missile",53, 4),
        Skill("drain",73, 2, 2),
        Skill("shield",113, 0, 0, 7, 0, 6),
        Skill("poison",173, 3, 0, 0, 0, 6),
        Skill("recharge",229, 0, 0, 0, 101, 5)
)

private const val bossMaxHp = 45
private const val bossDamage = 9

private const val playerHp = 50

fun main(args: Array<String>) {

    var minManaUsed = Int.MAX_VALUE
    for (skill in skills) {
//    var skill = skills[0]
        val newSkill = Skill(skill.name, skill.manaCost, skill.damage, skill.heal, skill.armor, skill.newMana, skill.duration, skill.type)
        newSkill.timer = newSkill.duration
        var manaUsed: Int? = null
        if (newSkill.timer == 0) {
            manaUsed = simulateGame(playerHp+skill.heal, mutableListOf(), 300-skill.manaCost, bossMaxHp-skill.damage, skill.manaCost,  mutableListOf(skill), 1)
        } else {
            manaUsed = simulateGame(playerHp, mutableListOf(newSkill), 300-skill.manaCost, bossMaxHp, skill.manaCost,  mutableListOf(skill), 1)
        }
        println(manaUsed)
        if (manaUsed != null && manaUsed < minManaUsed) {
            minManaUsed = manaUsed
        }
    }
    println(minManaUsed)
}

fun simulateGame(pHp: Int, pSkillsa: MutableList<Skill>, pMana: Int, bossHp: Int, manaSpent: Int, skillsUsed: MutableList<Skill>, round: Int): Int? {
    var bossCurrentHp = bossHp
    var hp = pHp
    var mana = pMana
    var armor = 0
    val pSkills = pSkillsa.toMutableList()
    var minManaSpent = Int.MAX_VALUE
    if (round%2 == 0) {


        if (hp <= 0) {
            return null
        }
        val skillsToRemove = mutableListOf<Skill>()
        pSkills.forEachIndexed { index, it ->
            bossCurrentHp -= it.damage
            hp += it.heal
            mana += it.newMana
            if (it.timer > 0) {
                it.timer--
                if (it.timer == 0) {
                    skillsToRemove.add(it)
                }
            } else {
                skillsToRemove.add(it)
            }
        }
        skillsToRemove.forEach { pSkills.remove(it) }

        skills.forEach { skill ->
            if (mana >= skill.manaCost && pSkills.map { it.name }.contains(skill.name).not()) {
                val newSkill = Skill(skill.name, skill.manaCost, skill.damage, skill.heal, skill.armor, skill.newMana, skill.duration, skill.type)
                newSkill.timer = newSkill.duration

                if (newSkill.timer == 0) {
                    bossCurrentHp -= skill.damage
                    hp += skill.heal
                } else {
                    pSkills.add(newSkill)
                }
                skillsUsed.add(newSkill)


                val manaLeft = simulateGame(hp, pSkills, mana - skill.manaCost, bossCurrentHp, manaSpent + skill.manaCost, skillsUsed, round +1)
                if (manaLeft != null && manaLeft < minManaSpent) {
                    minManaSpent = manaLeft
                }

                if (skill.timer == 0) {
                    bossCurrentHp += skill.damage
                    hp -= skill.heal
                } else {
                    pSkills.remove(newSkill)
                }
                skillsUsed.remove(newSkill)
            }
        }
//        return minManaSpent
    }

    else if (round%2 == 1) {
        val skillsToRemove = mutableListOf<Skill>()
        pSkills.forEach {
            bossCurrentHp -= it.damage
            armor += it.armor
            mana += it.newMana
            it.timer --
            if (it.timer == 0) {
                skillsToRemove.add(it)
            }
        }
        skillsToRemove.forEach { pSkills.remove(it) }


        if (bossCurrentHp <= 0 && hp > 0) {
            if (manaSpent == 833) {
                println("${skillsUsed.map { it.manaCost }}  $manaSpent $hp")
                sim(round, skillsUsed)
            }

            return manaSpent
        }

        hp -= (bossDamage - armor).coerceAtLeast(1)



        return simulateGame(hp, pSkills, mana, bossCurrentHp, manaSpent,  skillsUsed, round + 1)
    }
    return minManaSpent
}






fun sim(rounds: Int, skills: MutableList<Skill>) {
    val activeSkills = mutableListOf<Skill>()
    var hp = playerHp
    var bossHp = bossMaxHp
    var playerArmor = 0
    var playerMana = 300
    (0..rounds).forEach { r ->

        if (r%2 == 0) {
            println("Player turn:")
            println("--Player has $hp hp, $playerArmor armor, $playerMana mana")
            println("--Boss has $bossHp hp")
            println("Player casts ${skills[r/2].name}")

            val skillsToRemove = mutableListOf<Skill>()
            activeSkills.forEach {
                playerArmor = it.armor
                bossHp -= it.damage
                playerMana += it.newMana
                it.timer --
                println("${it.name} does ${it.damage} damage and ${it.newMana} mana and adds ${it.armor} armor, its timer is ${it.timer}")
                if (it.timer == 0) {
                    skillsToRemove.add(it)
                    println("${it.name} wears off")
                }
            }
            skillsToRemove.forEach { activeSkills.remove(it) }

            if (skills[r/2].duration == 0) {
                bossHp -= skills[r/2].damage
                println(", does ${skills[r/2].damage} dmg and ${skills[r/2].heal} heal")
            } else {
                val newSkill = skills[r/2]
                newSkill.timer = newSkill.duration
                activeSkills.add(newSkill)
            }
            playerMana -= skills[r/2].manaCost
            println()
        } else {
            println("Boss turn:")
            println("--Player has $hp hp, $playerArmor armor, $playerMana mana")
            println("--Boss has $bossHp hp")

            val skillsToRemove = mutableListOf<Skill>()
            activeSkills.forEach {
                playerArmor = it.armor
                bossHp -= it.damage
                playerMana += it.newMana
                it.timer --
                println("${it.name} does ${it.damage} damage and ${it.newMana} mana and adds ${it.armor} armor, its timer is ${it.timer}")
                if (it.timer == 0) {
                    skillsToRemove.add(it)
                    println("${it.name} wears off")
                }
            }
            skillsToRemove.forEach { activeSkills.remove(it) }

            hp -= (bossDamage - playerArmor).coerceAtLeast(1)
            println("Boss deals $bossDamage - $playerArmor = ${bossDamage - playerArmor} damage")
            println()
        }
    }
}


//        var skill = skills[0]
//        if (round == 2) skill = skills[4]
//        if (round == 4) skill = skills[0]
//        if (round == 6) skill = skills[2]
//        if (round == 8) skill = skills[0]
//        if (round == 10) skill = skills[3]
//        if (round == 12) skill = skills[0]
//        if (round == 14) skill = skills[0]
//        if (round == 16) skill = skills[0]