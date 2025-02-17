package keycollector.main

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.{ApplicationAdapter, Gdx}
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.Array
import keycollector.main.components.{BoxCollider, RectComponent}
import keycollector.main.entities.Player
import keycollector.main.levels.{Level, TitleScreen}

final class Main extends ApplicationAdapter {
    private var player: Player = _
    private var shapeRenderer: ShapeRenderer = _
    private var currentLevel: Level = _
    private var currentStage: Stage = _
    private var engine: PooledEngine = _
    private val levels: Array[Level] = new Array[Level]

    @Override
    override def create(): Unit = {
        // Set instance in instance manager
        InstanceManager.main = this

        // Entity engine
        engine = new PooledEngine

        // More instances
        shapeRenderer = new ShapeRenderer
        player = new Player(engine)

        levels.add(new TitleScreen)
        
        setLevel(levels.get(0), engine)
    }

    @Override
    override def render(): Unit = {
        Gdx.gl.glClearColor(23/255f, 24/255f, 26/255f, 1)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        currentStage.act()
        currentStage.draw()

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)

        if(currentLevel.getClass.getSimpleName != "TitleScreen") {
            // Draw player
            player.render(shapeRenderer)
            //Update player collider
            player.getComponent(classOf[BoxCollider]).update(player.getComponent(classOf[RectComponent]))
        }

        // Draw keys from the current level
        currentLevel.render(shapeRenderer, player)

        shapeRenderer.end()
    }

    @Override
    override def resize(width: Int, height: Int): Unit = {
        currentStage.getViewport.update(width, height, true)
        currentLevel.scoreLabel.setY(height.toFloat - 35)
    }

    @Override
    override def dispose(): Unit = levels.forEach(level => level.dispose)

    def setLevel(level: Level, engine: PooledEngine): Unit = {
        // This line does not work with TeaVM
        currentLevel = level
        currentLevel.init()
        currentStage = currentLevel.stage
        Gdx.input.setInputProcessor(currentStage)
        currentLevel.addKeys(engine)
    }

    def getEngine: PooledEngine = engine
}

object InstanceManager {
    var main: Main = _
}
