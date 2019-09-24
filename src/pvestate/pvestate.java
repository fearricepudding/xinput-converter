package pvestate;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;




public class pvestate {

    static String newLine = System.getProperty("line.separator");
    
    private static Text output;
    private static Button button;
    private static Shell shell;
    private static Text screen_width; 
    private static Text screen_height;
    private static Text click_3_x;
    private static Text click_3_y;
    private static Text click_0_x;
    private static Text click_0_y;
    private static Label screen_width_label;
    private static Label screen_height_label;
    private static Label click_3_x_label;
    private static Label click_3_y_label;
    private static Label click_0_y_label;
    private static Label click_0_x_label;
    private static Text screen_name;
    
    public static void main(String[] args) {
    	
	    int x= 60;
        int y=20;
        int width =400;
        int height=460;
	
 
        Display display = new Display();
        shell = new Shell(display);
        
        
        // === Number inputs ===
        screen_width_label = new Label(shell, SWT.NONE);
        screen_width_label.setText("Screen Width");
        screen_width = new Text(shell, SWT.BORDER);
        
        screen_height_label = new Label(shell, SWT.NONE);
        screen_height_label.setText("Screen Height");
        screen_height = new Text(shell, SWT.BORDER);
        
        click_3_x_label = new Label(shell, SWT.NONE);
        click_3_x_label.setText("Click 3 X");
        click_3_x = new Text(shell, SWT.BORDER);
        
        click_3_y_label = new Label(shell, SWT.NONE);
        click_3_y_label.setText("Click 3 Y");
        click_3_y = new Text(shell, SWT.BORDER);
        
        click_0_x_label = new Label(shell, SWT.NONE);
        click_0_x_label.setText("Click 0 X");
        click_0_x = new Text(shell, SWT.BORDER);
        
        click_0_y_label = new Label(shell, SWT.NONE);
        click_0_y_label.setText("Click 0 Y");
        click_0_y = new Text(shell, SWT.BORDER);
        
        screen_name = new Text(shell, SWT.BORDER);
        screen_name.setText("Screen name here");
        
        
        // === Output text-box ===
        output = new Text(shell, SWT.READ_ONLY | SWT.BORDER);
        output.setText("Ready...");
       
        
        // === Calculate button ===
        button = new Button(shell, SWT.PUSH);
        button.setText("Convert");
        
	    //register listener for the selection event
	    button.addSelectionListener(new SelectionAdapter() {
	        @Override
	        public void widgetSelected(SelectionEvent e) {
	            calculate();
	        }
	    });
	    
	    
	    // Positioning and bounds
	    output.setBounds(x, height-80, width, 50);
	    button.setBounds(x, height-20, width, 50);
	    
	    int colLeft = x+50;
	    int colRight = (x+width/2)+50;
	    
	    screen_width_label.setBounds(colLeft, 30, 100, 20);
	    screen_width.setBounds(colLeft, 50, 100, 50);
	    screen_height_label.setBounds(colRight, 30, 100, 20);
	    screen_height.setBounds(colRight, 50, 100, 50);
	    click_3_x_label.setBounds(colLeft, 130, 100, 20);
	    click_3_x.setBounds(colLeft, 150, 100, 50);
	    click_3_y_label.setBounds(colRight, 130, 100, 20);
	    click_3_y.setBounds(colRight, 150, 100, 50);
	    click_0_x_label.setBounds(colLeft, 230, 100, 20);
	    click_0_x.setBounds(colLeft, 250, 100, 50);
	    click_0_y_label.setBounds(colRight, 230, 100, 20);
	    click_0_y.setBounds(colRight, 250, 100, 50);
	    
	    
	    screen_name.setBounds(x, height-140, width, 50);
	   
	    
	    
	    // Shell stuff
        int toolbarSize = 30;
        shell.setBounds(200, 400, width+2*x , height + 2*y +toolbarSize);
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }
    
    

    public static boolean calculate() {
    	System.out.println("Calculating...");
    	
    	double sw, sh, c3x, c3y, c0x, c0y, a, c, e, f;
    	
    	try {
	    	
    		// We have offsets for borders or issues with screen size alignment
    		int widthOffset = 155;
    		int heightOffset = 0;
    		
	    	sw = Double.parseDouble(screen_width.getText()) - widthOffset;
	    	sh = Double.parseDouble(screen_height.getText()) - heightOffset;
	    	
	    	c3x = Double.parseDouble(click_3_x.getText());
	    	c3y	= Double.parseDouble(click_3_y.getText());
	    	c0x	= Double.parseDouble(click_0_x.getText());
	    	c0y	= Double.parseDouble(click_0_y.getText());
	    	
	    	String sn = screen_name.getText();
	    	
	    	a = (sw * 7 / 8) / (c3x - c0x);
			c = ((sw / 8) - (a * c0x)) / sw;
			e = (sh * 6 / 8) / (c3y - c0y);
			f = ((sh / 8) - (e * c0y)) / sh;	
	
			System.out.println(a);
			System.out.println(c);
			System.out.println(e);
			System.out.println(f);
	
	    	output.setText(String.format("xinput set-prop \"%s\" \"libinput Calibration Matrix\" %.15f, 0.0, %.15f, 0.0, %.15f, %.15f, 0.0, 0.0, 1.0",sn, a, c, e, f));
	    	
	    	return true;
	    	
    	}catch(NumberFormatException error) {
    		System.out.println(error);
    		output.setText(error.toString());
    		return false;
    	}
    	
    }
}