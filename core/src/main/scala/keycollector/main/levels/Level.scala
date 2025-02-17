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
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.StretchViewport
import keycollector.main.components.{CircleCollider, CircleComponent}
import keycollector.main.entities.{Key, Player}

abstract class Level extends Disposable {
    protected val keys: Array[Key] = new Array[Key]
    private var score: Int = 0

    val stage: Stage = new Stage(new StretchViewport(1280, 720))
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

        val iterator: Array.ArrayIterator[Key] = keys.iterator()
        while(iterator.hasNext)
            if (iterator.next.isColliding(player)) {
                coinSound.play(3)
                score += 1
                scoreLabel.setText(String.format("Keys Collected: %d", score))
                // We use iterator.remove() to remove
                iterator.remove()
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
