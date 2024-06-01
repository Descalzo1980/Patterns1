package patterns.factory_method

fun createPiece(notation: String): ChessPiece {
    val (type, file, rank) = notation.toCharArray()
    return when (type) {
        'q' -> CheckMate.Queen(file, rank)
        'p' -> CheckMate.Pawn(file, rank)
        'h' -> CheckMate.Knight(file, rank)
        'k' -> CheckMate.King(file, rank)
        'b' -> CheckMate.Bishop(file, rank)
        'r' -> CheckMate.Rook(file, rank)
        else -> throw IllegalArgumentException("Unknown piece: $type")
    }
}

fun createPieceEnum(notation: String): ChessPiece {
    val (type, file, rank) = notation.toCharArray()

    return when(type) {
        PieceType.QUEEN.notation -> CheckMate.Queen(file, rank)
        PieceType.PAWN.notation -> CheckMate.Pawn(file, rank)
        PieceType.KNIGHT.notation -> CheckMate.Knight(file, rank)
        PieceType.KING.notation -> CheckMate.King(file, rank)
        PieceType.BISHOP.notation -> CheckMate.Bishop(file, rank)
        PieceType.ROOK.notation -> CheckMate.Rook(file, rank)
        else -> throw IllegalArgumentException("Unknown piece: $type")
    }
}

fun main(){
    println(createPiece("q12"))
/*    println(createPiece("z1222"))*/
    println(createPieceEnum("k34")).toString()
}
