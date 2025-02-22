package keycollector.main.levels

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import imgui.ImGui
import keycollector.main.{ImGuiUI, StaticManager}

final class Credits extends Level {
    private var credits: String = _

    @Override
    override def init(): Unit = {
        scoreLabel.remove()

        credits = Gdx.files.internal("credits.txt").readString
    }

    def update(): Unit = {
        ImGuiUI.beginLoop()

        ImGui.begin("Credits", 38)
        ImGui.setWindowPos(0, 0)
        ImGui.setWindowSize(Gdx.graphics.getWidth.toFloat, Gdx.graphics.getHeight.toFloat)

        ImGui.textUnformatted(credits)
        if(ImGui.button("Back")) {
            val clickSound: Sound = Gdx.audio.newSound(Gdx.files.internal("click3.wav"))
            clickSound.play(3)
            new Thread(() => {
                Thread.sleep(100)
                clickSound.dispose()
            }).start()

            StaticManager.reset()
            StaticManager.main.setLevel(StaticManager.main.getLevels.get(0), StaticManager.main.getEngine)
        }

        ImGui.end()

        ImGuiUI.endLoop()
    }
}
