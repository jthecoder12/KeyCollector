package keycollector.main.levels

import com.badlogic.gdx.math.Vector2
import keycollector.main.entities.Key

final class Level6 extends Level {
    @Override
    override def init(): Unit = {
        for(j <- 1 to 10) for(i <- 1 to 25) keys.add(new Key(new Vector2(i.toFloat * 25, j.toFloat * 25)))

        keys.add(new Key(new Vector2(1000, 700)))
    }
}
