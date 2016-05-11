import org.rosuda.JRI.Rengine;
import org.rosuda.JRI.REXP;


public class ExportingObjs {
	public static void Start(Rengine re)
	{

        System.out.println("\n\nExporting Objects\n\n");
        REXP x = null;
        x=new REXP();
        System.out.println(x.toString());
        //R character vector -- R.NET RDotNet.RDotNet.CharacterVector
        System.out.println("\nR character vector\n");
        String[] myStringArray = new String[] { "PIDataLink","PIProcessBook","PIWebParts" };
        re.assign("mystringarray", myStringArray);
        System.out.println(x=re.eval("mystringarray"));
                    
        //R integer vector 
        System.out.println("\nR int vector\n");
        int[] myIntegerArray = new int[] { 4, 6, 10, 140, 54, 25 };  
        re.assign("myintarray", myIntegerArray);
        System.out.println(x=re.eval("myintarray"));
        
        //R real vector
        System.out.println("\nR real vector\n");
        double[] myDoubleArray = new double[] { 30.02, 29.99, 30.11, 29.97, 30.01, 29.99 };
        re.assign("mydoublearray", myDoubleArray);
        re.eval("print(mydoublearray)");
        System.out.println(x=re.eval("mydoublearray"));
   
        
        //R logical vector 
        System.out.println("\nR boolean vector\n");
        boolean[] myBoolArray = new boolean[]{ true, false, false };
        re.assign("myboolvector", myBoolArray);  
        System.out.println(x=re.eval("myboolvector"));
        
	}
}
