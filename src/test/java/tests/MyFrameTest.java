package tests;

import java.io.*;
import code.MyFrame;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class MyFrameTest {
    
 private FrameFixture window;
    
    @Before
  public void setUp() {
    MyFrame frame = GuiActionRunner.execute(() -> new MyFrame());
    window = new FrameFixture(frame);
    window.show();
  }

  @Test
  public void AllFieldsInPlaces()
  {
      window.textBox("EnterString").requireVisible();
      window.textBox("EnterPattern").requireVisible();
      window.textBox("Result").requireVisible();
  }
  
  @Test
  public void shouldFindPatternTheFirstWhenClickExecute() {
    window.textBox("EnterString").enterText("a");
    window.textBox("EnterPattern").enterText("a");
    window.button("Execute").click();
    window.textBox("Result").requireText("[0]");
  }
  
  @Test
  public void shouldFindPatternTheLastWhenClickExecute() {
    window.textBox("EnterString").enterText("abc");
    window.textBox("EnterPattern").enterText("c");
    window.button("Execute").click();
    window.textBox("Result").requireText("[2]");
  }
  
  @Test
  public void shouldFindPatternInTheMiddleWhenClickExecute() {
    window.textBox("EnterString").enterText("abc");
    window.textBox("EnterPattern").enterText("b");
    window.button("Execute").click();
    window.textBox("Result").requireText("[1]");
  }
  
  @Test
  public void shouldEmptyAllFieldWhenTheClickExecuteAndCancel_AllWereNotEmpty()
  {
    window.textBox("EnterString").enterText("a");
    window.textBox("EnterPattern").enterText("a");
    window.button("Execute").click();
    window.button("Cancel").click();
    window.textBox("Result").requireText("");
    window.textBox("EnterString").requireText("");
    window.textBox("EnterPattern").requireText("");
  }
  
  @Test
  public void shouldEmptyAllFieldWhenTheClickExecuteAndCancel_AllWereEmpty()
  {
    window.button("Cancel").click();
    window.textBox("Result").requireText("");
    window.textBox("EnterString").requireText("");
    window.textBox("EnterPattern").requireText("");
  }
  
  @Test
  public void ExecuteShouldBeInvisibleIfFieldsAreEmpty()
  {
      boolean found = true;
      try{
      window.button("Execute").requireNotVisible();
      }
      catch(Exception e)
      {
          found = false;
      }
        assertEquals(found, false);
  }
  
  @Test
  public void ExecuteShouldBeVisibleIfFieldsAreFilled()
  {
      window.textBox("EnterString").enterText("a");
      window.textBox("EnterPattern").enterText("a");
      window.button("Execute").requireVisible();
  }
  
  @Test
  public void ExecuteShouldBeInvisibleIfOneFieldIsntEmpty()
  {
      window.textBox("EnterString").enterText("a");
      boolean found = true;
      try{
      window.button("Execute").requireNotVisible();
      }
      catch(Exception e)
      {
          found = false;
      }
        assertEquals(found, false);
  }
  
  @Test
  public void ExecuteShouldBeInvisibleIfAnotherFieldIsntEmpty()
  {
      window.textBox("EnterPattern").enterText("a");
      boolean found = true;
      try{
      window.button("Execute").requireNotVisible();
      }
      catch(Exception e)
      {
          found = false;
      }
        assertEquals(found, false);
  }

  @After
  public void tearDown() {
    window.cleanUp();
  }
    
}
