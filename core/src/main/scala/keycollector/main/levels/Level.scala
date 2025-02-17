package keycollector.main.levels

import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.ScreenViewport
import keycollector.main.components.{CircleCollider, CircleComponent}
import keycollector.main.entities.{Key, Player}

import java.util

abstract class Level extends Disposable {
    protected val keys: util.ArrayList[Key] = new util.ArrayList[Key]
    private var score: Int = 0

    val stage: Stage = new Stage(new ScreenViewport)
    protected val font: BitmapFont = new BitmapFont(Gdx.files.internal("OpenSans.fnt"))
    protected val labelStyle: LabelStyle = new LabelStyle(font, Color.WHITE)

    val scoreLabel: Label = new Label("Keys Collected: 0", labelStyle)
    scoreLabel.setY(Gdx.graphics.getHeight.toFloat - 35)

    stage.addActor(scoreLabel)

    private val coinSound: Sound = Gdx.audio.newSound(Gdx.files.internal("coin.ogg"))

    def addKeys(engine: Engine): Unit = keys.forEach(key => engine.addEntity(key))
    def render(shapeRenderer: ShapeRenderer, player: Player): Unit = {
        keys.forEach(key => {
            key.render(shapeRenderer)

            key.getComponent(classOf[CircleCollider]).update(key.getComponent(classOf[CircleComponent]))
        })

        for(i <- 0 until keys.size())
            if(keys.get(i).isColliding(player)) {
                coinSound.play(3)
                score += 1
                scoreLabel.setText(String.format("Keys Collected: %d", score))
                keys.remove(keys.get(i))
            }

        if(keys.isEmpty && getClass.getSimpleName != "TitleScreen") println("You win")
    }

    def init(): Unit

    @Override
    override def dispose(): Unit = {
        coinSound.dispose()
        font.dispose()
        stage.dispose()
    }
}
