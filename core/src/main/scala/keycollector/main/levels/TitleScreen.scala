package keycollector.main.levels

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.{InputEvent, InputListener}
import com.badlogic.gdx.scenes.scene2d.ui.{Label, TextButton}
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle
import keycollector.main.StaticManager

final class TitleScreen extends Level {
    @Override
    override def init(): Unit = {
        scoreLabel.remove()

        val titleLabel: Label = new Label("Key Collector", labelStyle)
        titleLabel.setPosition(Gdx.graphics.getWidth.toFloat / 2f - 80, Gdx.graphics.getHeight.toFloat - 35)
        stage.addActor(titleLabel)

        val buttonStyle: TextButtonStyle = new TextButtonStyle()
        buttonStyle.font = font
        buttonStyle.fontColor = Color.WHITE

        val playButton: TextButton = new TextButton("Play", buttonStyle)
        playButton.setPosition(Gdx.graphics.getWidth.toFloat / 2f - 15, Gdx.graphics.getHeight.toFloat - 150)
        playButton.addListener(new InputListener {
            @Override
            override def touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean = {
                if(button == 0) {
                    val clickSound: Sound = Gdx.audio.newSound(Gdx.files.internal("click3.wav"))
                    clickSound.play(3)
                    new Thread(() => {
                        Thread.sleep(100)
                        clickSound.dispose()
                    }).start()

                    StaticManager.main.setLevel(StaticManager.main.getLevels.get(1), StaticManager.main.getEngine)
                }

                true
            }
        })

        stage.addActor(playButton)
    }
}
