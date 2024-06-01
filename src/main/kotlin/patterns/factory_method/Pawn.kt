package patterns.factory_method

sealed class CheckMate : ChessPiece {
    data class Pawn(
        override val file: Char,
        override val rank: Char
    ) : CheckMate()


    data class Queen(
        override val file: Char,
        override val rank: Char
    ) : CheckMate()

    data class Knight(
        override val file: Char,
        override val rank: Char
    ) : CheckMate()

    data class King(
        override val file: Char,
        override val rank: Char
    ) : CheckMate()

    data class Bishop(
        override val file: Char,
        override val rank: Char
    ) : CheckMate()

    data class Rook(
        override val file: Char,
        override val rank: Char
    ) : CheckMate()
}

enum class PieceType(val notation: Char) {
    QUEEN('q'),
    PAWN('p'),
    KNIGHT('h'),
    KING('k'),
    BISHOP('b'),
    ROOK('r');

}