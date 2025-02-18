package nicestring

fun String.isNice(): Boolean {
    val condition1 = setOf("bu","ba","be").none {this.contains(it)}
    val vowels = setOf('a','e','i','o','u')
    val condition2 =  this.count {it in vowels} >= 3
    val condition3 = this.windowed(2).any {it[0] == it[1]}

    val conditionsMet = listOf(condition1, condition2, condition3).count { it }

    // Check if at least 2 conditions are met
    return when {
        conditionsMet >= 2 -> true
        else -> false
    }

}