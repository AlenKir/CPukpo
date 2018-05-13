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

public class NotYetReady {

private FrameFixture window;
    
    @Before
  public void setUp() {
    MyFrame frame = GuiActionRunner.execute(() -> new MyFrame());
    window = new FrameFixture(frame);
    window.show();
  }
    @Test
    public void somePatterns() {
      window.textBox("EnterString").enterText("abc");
      window.textBox("EnterPattern").enterText("a, b");
      window.button("Execute").click();
      window.textBox("Result").requireText("[0, 1]");
    }
    
    @Test
    public void somePatternsOneOfThemDoesntExist() {
      window.textBox("EnterString").enterText("abc");
      window.textBox("EnterPattern").enterText("a, g");
      window.button("Execute").click();
      window.textBox("Result").requireText("[0]");
    }
    
    @Test
    public void shouldFindPatternTheFirstWhenClickExecute() {
      window.textBox("EnterString").enterText("a");
      window.textBox("EnterPattern").enterText("a");
      window.button("Execute").click();
      try {
  		Thread.sleep(100);
  	} catch (InterruptedException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  	}
      window.textBox("Result").requireText("[0]");
    }
    
    @After
    public void tearDown() {
      window.cleanUp();
    }

}
