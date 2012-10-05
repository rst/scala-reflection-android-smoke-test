package rst.reflection_test

import _root_.android.app.Activity
import _root_.android.os.Bundle

import scala.reflect.runtime.{universe => ru}
import ru._

class MainActivity extends Activity with TypedActivity {
  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(R.layout.main)

    findView(TR.textview).setText("reflection not run yet")

    val TypeRef(optType, optClass, dummyArgs) = typeOf[Option[Any]]
    typeOf[Option[String]] match {
      case TypeRef(optType, optClass, List(arg)) => 
        findView(TR.textview).setText("got " + arg)
    }
  }
}
