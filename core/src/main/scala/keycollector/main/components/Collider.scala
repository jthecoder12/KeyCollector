package keycollector.main.components

import com.badlogic.ashley.core.Component

abstract class Collider extends Component {
    def touchingRect(boxCollider: BoxCollider): Boolean
    def touchingCircle(circleCollider: CircleCollider): Boolean
}
