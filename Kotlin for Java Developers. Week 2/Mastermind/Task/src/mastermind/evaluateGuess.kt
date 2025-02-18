package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    var rightPosition = 0
    var wrongPosition = 0
    val used = BooleanArray(secret.length)

    // First pass - correct positions
    for (i in secret.indices) {
        if (secret[i] == guess[i]) {
            rightPosition++
            used[i] = true
        }
    }

    // Second pass - wrong positions
    for (i in secret.indices) {
        if (secret[i] != guess[i]) {
            val ch = secret[i]
            var wrongCount = 0

            // Count how many times we can mark this char as wrong
            val remainingInStr2 = guess.filterIndexed { index, c -> !used[index] && c == ch }.count()
            val remainingInStr1 = secret.filterIndexed { index, c -> guess[index] != c && c == ch }.count()
            val possibleWrongs = minOf(remainingInStr1, remainingInStr2)

            // Mark positions as wrong
            for (j in guess.indices) {
                if (!used[j] && secret[i] == guess[j] && wrongCount < possibleWrongs) {
                    wrongPosition++
                    used[j] = true
                    wrongCount++
                }
            }
        }
    }

    return Evaluation(rightPosition, wrongPosition)
}
