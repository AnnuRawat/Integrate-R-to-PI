import org.rosuda.JRI.RBool;
import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;

public class ImportingObjs {
	public static void Start(Rengine re)
	{
		System.out.print("ImportingObjs");
		
	
		String RCodeString ="";
        //R string vector
        RCodeString = "mycharvector<-c(\"hello\",\"bye\",\"OSIsoft\")";
        System.out.println("\nR Code: " + RCodeString);
        REXP Rstringvector = re.eval(RCodeString);
        String[] myStringArray = Rstringvector.asStringArray();
        System.out.println("\nmyStringArray: ");
        int i = 1;
        for(String myString : myStringArray)
        {
        	System.out.println(i + " value: " + myString);
            i++;
        }
        

        //R int vector
        RCodeString = "myintvector<-1:10";
        System.out.println("\nR Code: " + RCodeString);
        REXP Rintvector = re.eval(RCodeString);
        int[] myIntegerArray = Rintvector.asIntArray();
        System.out.println("\nInteger Vector: ");
        i = 1;
        for(int myInteger : myIntegerArray)
        {
            System.out.println(i + " value=" + myInteger);
            i++;
        }


        //R real vector
        RCodeString = "myrealvector<-rnorm(5, 0, 1)";
        System.out.println("\nR Code: " + RCodeString);
        REXP Rrealvector= re.eval(RCodeString);
        double[] myDoubleArray = Rrealvector.asDoubleArray();
        System.out.println("\nNumeric Vector: ");
        i = 1;
        for(double myNumeric : myDoubleArray)
        {
            System.out.println(i + " value=" + myNumeric);
            i++;
        }


        //R Boolean
        RCodeString = "mybool<-c(FALSE)";
        System.out.println("\nR Code: " + RCodeString);
        RBool myBool = re.eval(RCodeString ).asBool();
        System.out.println(i + " value=" + myBool.toString());

	}
}
