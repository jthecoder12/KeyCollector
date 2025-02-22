package keycollector.main.levels

import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.StretchViewport
import imgui.ImGui
import imgui.`type`.ImBoolean
import keycollector.main.{ImGuiUI, StaticManager}
import keycollector.main.components.{CircleCollider, CircleComponent}
import keycollector.main.entities.{Key, Player}

abstract class Level extends Disposable {
    protected val keys: Array[Key] = new Array[Key]
    var alreadyInit: Boolean = false
    var stage: Stage = _

    private var generator: FreeTypeFontGenerator = _
    private var parameter: FreeTypeFontParameter = _
    protected var font: BitmapFont = _

    protected var labelStyle: LabelStyle = _

    var scoreLabel: Label = _

    private var coinSound: Sound = _

    val paused: ImBoolean = new ImBoolean

    if(!alreadyInit) {
        stage = new Stage(new StretchViewport(1280, 720))

        generator = new FreeTypeFontGenerator(Gdx.files.internal("OpenSans.ttf"))
        parameter = new FreeTypeFontParameter
        parameter.size = 32
        font = generator.generateFont(parameter)
        generator.dispose()

        labelStyle = new LabelStyle(font, Color.WHITE)
        scoreLabel = new Label(String.format("Keys Collected: %d", StaticManager.score), labelStyle)
        scoreLabel.setY(Gdx.graphics.getHeight.toFloat - 35)
        stage.addActor(scoreLabel)

        coinSound = Gdx.audio.newSound(Gdx.files.internal("coin.ogg"))
    }

    def addKeys(engine: Engine): Unit = if(!alreadyInit) keys.forEach(key => engine.addEntity(key))
    def render(shapeRenderer: ShapeRenderer, player: Player): Unit = {
        if(!alreadyInit) alreadyInit = true

        keys.forEach(key => {
            key.render(shapeRenderer)

            key.getComponent(classOf[CircleCollider]).update(key.getComponent(classOf[CircleComponent]))
        })

        val iterator: Array.ArrayIterator[Key] = keys.iterator()
        while(iterator.hasNext)
            if (iterator.next.isColliding(player)) {
                coinSound.play(3)
                StaticManager.score += 1
                scoreLabel.setText(String.format("Keys Collected: %d", StaticManager.score))
                // We use iterator.remove() to remove
                iterator.remove()
            }

        if(keys.isEmpty && getClass.getSimpleName != "TitleScreen" && getClass.getSimpleName != "Credits" && getClass.getSimpleName != "Level10")
            StaticManager.score = 0
            StaticManager.main.setLevel(StaticManager.main.getLevels.get(getClass.getSimpleName.takeRight(1).toInt + 1), StaticManager.main.getEngine)

        if(keys.isEmpty && getClass.getSimpleName == "Level10") StaticManager.main.setLevel(StaticManager.main.getLevels.get(StaticManager.main.getLevels.size - 1), StaticManager.main.getEngine)

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) && getClass.getSimpleName != "TitleScreen" && getClass.getSimpleName != "Credits") paused.set(!paused.get)
    }

    def renderImGui(): Unit = {
        if (paused.get()) {
            ImGuiUI.beginLoop()

            ImGui.begin("Paused", paused, 38)
            // Center the window
            ImGui.setWindowPos(Gdx.graphics.getWidth.toFloat / 2f - ImGui.getWindowSizeX / 2f, Gdx.graphics.getHeight.toFloat / 2f - ImGui.getWindowSizeY / 2f)
            ImGui.setWindowSize(750, 500)

            if(ImGui.button("Main Menu")) {
                val clickSound: Sound = Gdx.audio.newSound(Gdx.files.internal("click3.wav"))
                clickSound.play(3)
                new Thread(() => {
                    Thread.sleep(100)
                    clickSound.dispose()
                }).start()

                StaticManager.main.setLevel(StaticManager.main.getLevels.get(0), StaticManager.main.getEngine)
            }

            ImGui.end()

            ImGuiUI.endLoop()
        }
    }

    def resetScore(): Unit = scoreLabel.setText(String.format("Keys Collected: %d", StaticManager.score))

    def getKeys: Array[Key] = keys

    def init(): Unit

    @Override
    override def dispose(): Unit = {
        coinSound.dispose()
        font.dispose()
        stage.dispose()
    }
}
