package keycollector.main.entities

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

abstract class Renderable extends Entity {
    def render(shapeRenderer: ShapeRenderer): Unit
}
