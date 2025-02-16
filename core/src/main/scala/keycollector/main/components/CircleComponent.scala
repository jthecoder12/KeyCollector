package keycollector.main.components

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2

final class CircleComponent(positionX: Vector2, radiusX: Float, color: Color) extends ShapeComponent {
    @Override
    override private[components] val position: Vector2 = positionX
    private[components] val radius: Float = radiusX

    @Override
    override def render(shapeRenderer: ShapeRenderer): Unit = {
        shapeRenderer.setColor(color)
        shapeRenderer.circle(position.x, position.y, radius)
    }
}
