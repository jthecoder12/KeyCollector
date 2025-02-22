package keycollector.main.levels

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Label
import keycollector.main.entities.Key

final class Level8 extends Level {
    @Override
    override def init(): Unit = {
        stage.addActor(new Label("Try looking in the top left corner (behind the keys collected text).", labelStyle))

        keys.add(new Key(new Vector2(10, 10)))
        keys.add(new Key(new Vector2(1270, 10)))
        keys.add(new Key(new Vector2(1270, 710)))
        keys.add(new Key(new Vector2(10, 710)))
    }
}
