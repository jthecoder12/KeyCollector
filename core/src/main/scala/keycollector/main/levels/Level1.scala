package keycollector.main.levels

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Label
import keycollector.main.entities.Key

final class Level1 extends Level {
    @Override
    override def init(): Unit = {
        stage.addActor(new Label("Use WASD to move around. Collect the keys (the yellow circles).", labelStyle))

        keys.add(new Key(new Vector2(1000, 400)))
        keys.add(new Key(new Vector2(50, 50)))
        keys.add(new Key(new Vector2(200, 200)))
        keys.add(new Key(new Vector2(400, 400)))
        keys.add(new Key(new Vector2(600, 600)))
    }
}
