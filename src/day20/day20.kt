package day20
fun main(args: Array<String>) {
    val presents = 34000000
    var presentsToDeliver = presents
    var wantedHouse: Int? = null

    //part 1
//    var house = 1
//    while (wantedHouse == null){
//        for (elf in 1..house) {
//            if (house % elf == 0) {
//                presentsToDeliver -= elf * 10
//            }
//        }
//        if (presentsToDeliver <= 0) {
//            wantedHouse = house
//        } else {
//            presentsToDeliver = presents
//            house ++
//        }
//        println(house)
//    }
    //part2
    var house = 786240
    var firstHouse = house/50
    while (wantedHouse == null){
        for (elf in firstHouse..house) {
            if (house % elf == 0 && house / elf <= 50 ) {
                presentsToDeliver -= elf * 11
            }
        }
        if (presentsToDeliver <= 0) {
            wantedHouse = house
        } else {
            presentsToDeliver = presents
            house ++
        }
        println(house)
    }
    println(wantedHouse)
}