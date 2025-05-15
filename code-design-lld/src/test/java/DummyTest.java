import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class DummyTest {

  int a;
  String b;
  @Before
  public void initialize() {
    a = 10;
    b = null;
  }

  @Test
  public void basic() {
    assertEquals(a, 10);
    assertNull(b);
  }
}
