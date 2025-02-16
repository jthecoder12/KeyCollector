package keycollector.main.entities

import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import keycollector.main.components.{BoxCollider, CircleCollider, CircleComponent}

final class Key(position: Vector2) extends Renderable {
    // Adds a circle component
    add(new CircleComponent(position, 10, Color.YELLOW))

    // Adds a circle collider
    add(new CircleCollider(getComponent(classOf[CircleComponent])))

    @Override
    override def render(shapeRenderer: ShapeRenderer): Unit = getComponent(classOf[CircleComponent]).render(shapeRenderer)

    def isColliding(player: Player): Boolean = getComponent(classOf[CircleCollider]).touchingRect(player.getComponent(classOf[BoxCollider]))
}
