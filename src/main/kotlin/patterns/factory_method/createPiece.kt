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

fun createPieceEnumIs(directoryType: CheckMateIs) {
    val objectType = when (directoryType) {
        is CheckMateIs.QueenIs -> "${directoryType.file}${directoryType.rank}"
        is CheckMateIs.PawnIs -> "${directoryType.file}${directoryType.rank}"
        is CheckMateIs.KnightIs -> "${directoryType.file}${directoryType.rank}"
    }
    println(objectType)
}

fun main(){
    println(createPiece("q12"))
/*    println(createPiece("z1222"))*/
    println(createPieceEnum("k34")).toString()
    val queen = CheckMateIs.QueenIs('1', 'q')
    val pawn = CheckMateIs.PawnIs('2', 'p')
    val knight = CheckMateIs.KnightIs('2', 'p')
    createPieceEnumIs(queen).toString()
    createPieceEnumIs(pawn).toString()
    createPieceEnumIs(knight).toString()
}
