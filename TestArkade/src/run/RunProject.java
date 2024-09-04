package run;

import controller.Control;
import model.Prealert;
import viewer.PrealertView;

public class RunProject {

	
	public static void main(String[] args) {
	    Prealert prealertModel = new Prealert();
	    PrealertView prealertView = new PrealertView(null);
		
       new Control(prealertView);
    }
}
