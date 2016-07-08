
import javax.swing.JFrame;
import org.rosuda.javaGD.GDCanvas;
import org.rosuda.javaGD.GDInterface;


public class MyJavaGD1 extends GDInterface {
	
public JFrame f;

public void gdOpen(double w, double h) {
f = new JFrame("JavaGD");
c = new GDCanvas(w, h);
f.add((GDCanvas) c);
f.pack();
f.setVisible(true);
System.out.println("Hello");
f.setTitle("Naked R plot");
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}
