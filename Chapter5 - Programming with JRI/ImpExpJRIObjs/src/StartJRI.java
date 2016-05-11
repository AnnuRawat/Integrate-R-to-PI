import org.rosuda.JRI.Rengine;


public class StartJRI {

	public static void main(String[] args) {
		
		System.out.println("Creating Rengine (with arguments)");
		// If not started with --vanilla, funny things may happen in this R
		// shell.
		String[] Rargs = { "--vanilla" };
		Rengine re = new Rengine(Rargs, false, null);
		System.out.println("Rengine created, waiting for R");
		// the engine creates R is a new thread, so we should wait until it's
		// ready
		if (!re.waitForR()) {
			System.out.println("Cannot load R");
			return;
		}		
		
		ImportingObjs.Start(re);
		ExportingObjs.Start(re);
		
		re.end();
	}

}
