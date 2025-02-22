package keycollector.main.levels

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Label
import keycollector.main.entities.Key

final class Level10 extends Level {
    @Override
    override def init(): Unit = {
        for (i <- 1 to 35) keys.add(new Key(new Vector2(1280 - i.toFloat * 20, i.toFloat * 20)))

        keys.add(new Key(new Vector2(10, 360)))
        keys.add(new Key(new Vector2(1270, 360)))
        keys.add(new Key(new Vector2(640, 10)))
        keys.add(new Key(new Vector2(640, 710)))

        keys.add(new Key(new Vector2(10, 10)))
        keys.add(new Key(new Vector2(1270, 710)))
        keys.add(new Key(new Vector2(10, 710)))

        keys.add(new Key(new Vector2(640, 360)))

        stage.addActor(new Label("Thanks for playing", labelStyle))
    }
}
