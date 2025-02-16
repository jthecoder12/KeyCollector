package keycollector.main

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.ScreenUtils
import keycollector.main.components.{BoxCollider, RectComponent}
import keycollector.main.entities.Player
import keycollector.main.levels.{Level, Level1}

final class Main extends ApplicationAdapter {
    private var player: Player = _
    private var shapeRenderer: ShapeRenderer = _
    private var currentLevel: Level = _

    @Override
    override def create(): Unit = {
        // Entity engine
        val engine: PooledEngine = new PooledEngine

        // More instances
        shapeRenderer = new ShapeRenderer()
        player = new Player(engine)

        setLevel(new Level1, engine)
    }

    @Override
    override def render(): Unit = {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1)

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)

        // Draw player
        player.render(shapeRenderer)
        //Update player collider
        player.getComponent(classOf[BoxCollider]).update(player.getComponent(classOf[RectComponent]))

        // Draw keys from the current level
        currentLevel.render(shapeRenderer, player)

        shapeRenderer.end()
    }

    private def setLevel(level: Level, engine: PooledEngine): Unit = {
        currentLevel = level
        currentLevel.init()
        currentLevel.addKeys(engine)
    }
}
