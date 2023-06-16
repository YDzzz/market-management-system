package Mode;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class MyFrame extends JFrame{
    static LinkedList<MyFrame> C = null;
    static {C = new LinkedList<MyFrame>();}

    public MyFrame() throws HeadlessException {
        C.offer(this);
    }
    public MyFrame(GraphicsConfiguration gc) {
        super(gc);
        C.offer(this);
    }

    public MyFrame(String title, GraphicsConfiguration gc) {
        super(title, gc);
        C.offer(this);
    }

    public MyFrame(String title) throws HeadlessException {
        super(title);
        C.offer(this);
    }

    public void closeFrame(){
        MyFrame frame = C.poll();
        frame.dispose();
    }

}
