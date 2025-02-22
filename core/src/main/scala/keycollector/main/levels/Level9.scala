package keycollector.main.levels

import com.badlogic.gdx.math.Vector2
import keycollector.main.entities.Key

final class Level9 extends Level {
    @Override
    override def init(): Unit = {
        keys.add(new Key(new Vector2(10, 360)))
        keys.add(new Key(new Vector2(1270, 360)))
        keys.add(new Key(new Vector2(640, 10)))
        keys.add(new Key(new Vector2(640, 710)))

        keys.add(new Key(new Vector2(10, 10)))
        keys.add(new Key(new Vector2(1270, 10)))
        keys.add(new Key(new Vector2(1270, 710)))
        keys.add(new Key(new Vector2(10, 710)))
    }
}
