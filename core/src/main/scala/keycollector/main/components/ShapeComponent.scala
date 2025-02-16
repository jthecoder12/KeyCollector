package keycollector.main.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2

abstract class ShapeComponent extends Component {
    private[components] val position: Vector2
    
    def move(moveBy: Vector2): Unit = position.add(moveBy)
    def render(shapeRenderer: ShapeRenderer): Unit
}
