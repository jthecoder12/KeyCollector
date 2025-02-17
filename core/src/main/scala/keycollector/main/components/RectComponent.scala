package keycollector.main.components

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2

import java.awt.Dimension

// A component that creates a rect with position, size, and color
final class RectComponent(positionX: Vector2, sizeX: Dimension, color: Color) extends ShapeComponent {
    @Override
    override private[components] val position: Vector2 = positionX
    private[components] val size: Dimension = sizeX

    def getPosition: Vector2 = position
    
    @Override
    override def render(shapeRenderer: ShapeRenderer): Unit = {
        shapeRenderer.setColor(color)
        shapeRenderer.rect(position.x, position.y, size.width.toFloat, size.height.toFloat)
    }
}
