package patterns.creational_patterns.factory_method


sealed class CheckMateIs : ChessPiece {
    abstract fun getSing(): PieceType

    data class PawnIs(
        override val file: Char,
        override val rank: Char
    ): CheckMateIs() {
        override fun getSing(): PieceType {
            return PieceType.PAWN
        }
    }

    data class QueenIs(
        override val file: Char,
        override val rank: Char
    ) : CheckMateIs() {
        override fun getSing(): PieceType {
            return PieceType.QUEEN
        }
    }

    data class KnightIs(
        override val file: Char,
        override val rank: Char
    ) : CheckMateIs() {
        override fun getSing(): PieceType {
            return PieceType.KNIGHT
        }
    }
}


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