package keycollector.main

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.ScreenUtils
import keycollector.main.entities.Player

final class Main extends ApplicationAdapter {
    private var player: Player = _
    private var shapeRenderer: ShapeRenderer = _

    @Override
    override def create(): Unit = {
        // Entity engine
        val engine: PooledEngine = new PooledEngine

        // More instances
        shapeRenderer = new ShapeRenderer()
        player = new Player(engine)
    }

    @Override
    override def render(): Unit = {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1)
        
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        // Draw player
        player.render(shapeRenderer)
        shapeRenderer.end()
    }
}
