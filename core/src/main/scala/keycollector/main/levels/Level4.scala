package keycollector.main.levels

import com.badlogic.gdx.math.Vector2
import keycollector.main.entities.Key

final class Level4 extends Level {
    @Override
    override def init(): Unit = {
        for (i <- 1 to 35) {
            keys.add(new Key(new Vector2(640, i.toFloat * 20)))
            keys.add(new Key(new Vector2(i.toFloat * 20, i.toFloat * 20)))
            keys.add(new Key(new Vector2(1280 - i.toFloat * 20, i.toFloat * 20)))
        }
        for (i <- 1 to 62) keys.add(new Key(new Vector2(i.toFloat * 20, 360)))
    }
}
