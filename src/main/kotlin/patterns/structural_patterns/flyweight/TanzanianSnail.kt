package patterns.structural_patterns.flyweight

import java.awt.Color
import java.awt.Graphics
import java.io.File
import javax.swing.JFrame

enum class Direction {
    LEFT,
    RIGHT
}

class TansanianSnail() {
    val directionFacing = Direction.LEFT
    val sprites = SnailSprites.sprites

    fun getCurrentSprite(): File {
        return when (directionFacing) {
            Direction.LEFT -> sprites[0]
            Direction.RIGHT -> sprites[1]
        }
    }
}

object SnailSprites {
    val sprites = List(8) { i ->
        File(
            when (i) {
                0 -> "snail-left.jpg"
                1 -> "snail-right.jpg"
                in 2..4 -> "snail-move-left-${i - 1}.jpg"
                else -> "snail-move-right${(4 - i)}.jpg"
            }
        )
    }
}


fun main() {
    val snail = TansanianSnail()
    println(snail.getCurrentSprite())
    println(snail.directionFacing)
    println(snail.sprites)


    val canvasSize = 1000
    val numTreesToDraw = 1000
    val numTreeTypes = 4

    val forest = Forest();
    for (i in 0..numTreesToDraw/numTreeTypes) {
        forest.plantTree(random(0, canvasSize), random(0, canvasSize), "Summer Oak", Color.GREEN)
        forest.plantTree(random(0, canvasSize), random(0, canvasSize), "Autumn Oak", Color.ORANGE)
        forest.plantTree(random(0, canvasSize), random(0, canvasSize), "Spring Oak", Color.YELLOW)
        forest.plantTree(random(0, canvasSize), random(0, canvasSize), "Winter Oak", Color.BLUE)
    }
    forest.setSize(canvasSize, canvasSize);
    forest.isVisible = true
}

/***********************************************************/


/*class Tree(private val x: Int,
           private val y: Int,
           private val name: String,
           private val color: Color) {
}*/


class Tree(private val x: Int,
           private val y: Int,
           private val type: TreeType) {

    fun draw(g: Graphics) {
        type.draw(g, x, y)
    }
}


class TreeType(private val name: String,
               private val color: Color) {

    fun draw(g: Graphics, x: Int, y: Int) {
        g.color = Color.BLACK;
        g.fillRect(x-1, y, 3, 5)
        g.color = color
        g.fillOval(x-5, y-10, 10, 10)
    }
}

class TreeTypeFactory {
    companion object {
        private val treeTypes: HashMap<String, TreeType> = hashMapOf()

        fun getTreeType(name: String, color: Color): TreeType {
            return treeTypes.getOrPut(name) { TreeType(name, color) }
        }
    }
}

class Forest(): JFrame() {

    private val trees: MutableList<Tree> = mutableListOf()

    fun plantTree(x: Int, y: Int, name: String, color: Color) {
        val treeType = TreeTypeFactory.getTreeType(name, color)
        trees.add(Tree(x, y, treeType))
    }

    override fun paint(g: Graphics) {
        for (tree in trees) {
            tree.draw(g);
        }
    }
}

fun random(min: Int, max: Int): Int {
    return (min + Math.random()*(max-min)+1).toInt()
}
