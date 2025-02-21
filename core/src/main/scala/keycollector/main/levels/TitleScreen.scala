package keycollector.main.levels

import com.badlogic.gdx.{Gdx, utils}
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.{InputEvent, InputListener}
import com.badlogic.gdx.scenes.scene2d.ui.{Label, TextButton}
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle
import keycollector.main.StaticManager

final class TitleScreen extends Level {
    private var instance: TitleScreen = _

    @Override
    override def init(): Unit = {
        instance = this

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

                    val realLevels: com.badlogic.gdx.utils.Array[Level] = new utils.Array[Level](StaticManager.main.getLevels)
                    realLevels.removeIndex(0)

                    realLevels.forEach(level => level.dispose)
                    realLevels.clear()
                    StaticManager.main.setLevels(new utils.Array[Level])
                    StaticManager.main.getLevels.add(instance)
                    StaticManager.main.getLevels.add(new Level1)
                    StaticManager.main.getLevels.add(new Level2)
                    StaticManager.main.getLevels.add(new Credits)

                    StaticManager.score = 0
                    StaticManager.main.getLevels.forEach(level => level.resetScore())
                    StaticManager.main.setLevel(StaticManager.main.getLevels.get(1), StaticManager.main.getEngine)
                }

                true
            }
        })

        val creditsButton: TextButton = new TextButton("Credits", buttonStyle)
        creditsButton.setPosition(Gdx.graphics.getWidth.toFloat / 2f - 35, Gdx.graphics.getHeight.toFloat - 225)
        creditsButton.addListener(new InputListener() {
            @Override
            override def touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean = {
                if(button == 0) {
                    val clickSound: Sound = Gdx.audio.newSound(Gdx.files.internal("click3.wav"))
                    clickSound.play(3)
                    new Thread(() => {
                        Thread.sleep(100)
                        clickSound.dispose()
                    }).start()

                    StaticManager.main.setLevel(StaticManager.main.getLevels.get(StaticManager.main.getLevels.size - 1), StaticManager.main.getEngine)
                }

                true
            }
        })

        stage.addActor(playButton)
        stage.addActor(creditsButton)
    }
}
