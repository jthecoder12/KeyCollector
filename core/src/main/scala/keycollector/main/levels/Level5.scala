package keycollector.main.levels

import com.badlogic.gdx.math.Vector2
import keycollector.main.entities.Key

final class Level5 extends Level {
    @Override
    override def init(): Unit = {
        for (i <- 1 to 35) keys.add(new Key(new Vector2(1280 - i.toFloat * 20, i.toFloat * 20)))

        keys.add(new Key(new Vector2(1000, 400)))
        keys.add(new Key(new Vector2(50, 50)))
        keys.add(new Key(new Vector2(200, 200)))
        keys.add(new Key(new Vector2(400, 400)))
        keys.add(new Key(new Vector2(600, 600)))
    }
}
