package keycollector.main.levels

import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import keycollector.main.components.{CircleCollider, CircleComponent}
import keycollector.main.entities.{Key, Player}

import java.util

abstract class Level {
    protected val keys: util.ArrayList[Key] = new util.ArrayList[Key]()
    private var score: Int = 0

    def addKeys(engine: Engine): Unit = keys.forEach(key => engine.addEntity(key))
    def render(shapeRenderer: ShapeRenderer, player: Player): Unit = {
        keys.forEach(key => {
            key.render(shapeRenderer)

            key.getComponent(classOf[CircleCollider]).update(key.getComponent(classOf[CircleComponent]))

        })

        for(i <- 0 until keys.size()) {
            score += 1
            if(keys.get(i).isColliding(player)) keys.remove(keys.get(i))
        }
    }

    def init(): Unit
}
