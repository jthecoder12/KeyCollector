package keycollector.main.levels

import com.badlogic.gdx.math.Vector2
import keycollector.main.entities.Key

final class Level2 extends Level {
    override def init(): Unit =
        for(i <- 1 to 35)
            keys.add(new Key(new Vector2(i.toFloat * 20, i.toFloat * 20)))
            keys.add(new Key(new Vector2(1280 - i.toFloat * 20, i.toFloat * 20)))
}
