package keycollector.main

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.{ApplicationAdapter, Gdx}
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.Array
import keycollector.main.components.{BoxCollider, RectComponent}
import keycollector.main.entities.Player
import keycollector.main.levels.{Credits, Level, Level1, Level2, Level3, Level4, TitleScreen}

final class Main extends ApplicationAdapter {
    private var player: Player = _
    private var shapeRenderer: ShapeRenderer = _
    private var currentLevel: Level = _
    private var currentStage: Stage = _
    private var engine: PooledEngine = _
    private var music: Music = _
    private val levels: Array[Level] = new Array[Level]

    @Override
    override def create(): Unit = {
        // Set instance in instance manager
        StaticManager.main = this

        // Entity engine
        engine = new PooledEngine

        // More instances
        shapeRenderer = new ShapeRenderer
        player = new Player(engine)

        levels.add(new TitleScreen)

        setLevel(levels.get(0), engine)

        ImGuiUI.init()

        music = Gdx.audio.newMusic(Gdx.files.internal("ChillLofiR.mp3"))
        music.setLooping(true)
        music.play()
    }

    @Override
    override def render(): Unit = {
        Gdx.gl.glClearColor(23/255f, 24/255f, 26/255f, 1)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        currentStage.act()
        currentStage.draw()

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)

        if(currentLevel.getClass.getSimpleName != "TitleScreen" && currentLevel.getClass.getSimpleName != "Credits") {
            // Draw player
            player.render(shapeRenderer)
            //Update player collider
            player.getComponent(classOf[BoxCollider]).update(player.getComponent(classOf[RectComponent]))
            //Update player moving status
            player.moving = !currentLevel.paused.get
        }

        // Draw keys from the current level
        currentLevel.render(shapeRenderer, player)

        shapeRenderer.end()

        if(currentLevel.getClass.getSimpleName == "Credits") currentLevel.asInstanceOf[Credits].update()
        currentLevel.renderImGui()
    }

    @Override
    override def resize(width: Int, height: Int): Unit = currentStage.getViewport.update(width, height, true)

    @Override
    override def dispose(): Unit = {
        levels.forEach(level => level.dispose)
        music.dispose()
        ImGuiUI.dispose
    }

    def setLevel(level: Level, engine: PooledEngine): Unit = {
        player.getComponent(classOf[RectComponent]).getPosition.set(Gdx.graphics.getWidth.toFloat / 2f, Gdx.graphics.getHeight.toFloat / 2f)
        currentLevel = level
        if(!currentLevel.alreadyInit) currentLevel.init()
        currentStage = currentLevel.stage
        Gdx.input.setInputProcessor(currentStage)
        currentLevel.addKeys(engine)
    }

    def getEngine: PooledEngine = engine

    def getLevels: Array[Level] = levels
}

// Simulates Java's static variables using a Scala singleton
object StaticManager {
    var main: Main = _
    var score: Int = 0
    
    def reset(): Unit = {
        // Reset everything
        score = 0
        main.getLevels.forEach(level => level.dispose)

        main.getLevels.clear()
        main.getLevels.add(new TitleScreen)
        main.getLevels.add(new Level1)
        main.getLevels.add(new Level2)
        main.getLevels.add(new Level3)
        main.getLevels.add(new Level4)
        main.getLevels.add(new Credits)
    }
}
