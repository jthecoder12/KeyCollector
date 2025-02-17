package keycollector.main.levels

import com.badlogic.gdx.math.Vector2
import keycollector.main.entities.{Key, Player}

final class Level1 extends Level {
    @Override
    override def init(): Unit = keys.add(new Key(new Vector2(10, 10)))
}
