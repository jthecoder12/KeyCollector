package keycollector.main.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2

import java.awt.Dimension

// A component that creates a rect with position, size, and color
class RectComponent(positionX: Vector2, size: Dimension, color: Color) extends Component {
    private val position: Vector2 = positionX

    def move(moveBy: Vector2): Unit = position.add(moveBy)

    def render(shapeRenderer: ShapeRenderer): Unit = {
        shapeRenderer.setColor(color)
        shapeRenderer.rect(position.x, position.y, size.width.toFloat, size.height.toFloat)
    }
}
