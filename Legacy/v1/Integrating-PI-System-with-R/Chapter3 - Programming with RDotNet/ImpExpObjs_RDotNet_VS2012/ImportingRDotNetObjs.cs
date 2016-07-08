using RDotNet;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Numerics;
using System.Text;
using System.Threading.Tasks;

namespace ImpExpObjs_RDotNet_VS2012
{
    public class ImportingRDotNetObjs
    {
        public static void Start(REngine engine)
        {
            Console.WriteLine("\n\nImporting Objects\n\n");

            string RCodeString = string.Empty;


            //R character vector -- R.NET RDotNet.CharacterVector
            RCodeString = "mycharvector<-c(\"hello\",\"bye\",\"OSIsoft\")";
            Console.WriteLine("\nR Code: " + RCodeString);
            engine.Evaluate(RCodeString);
            CharacterVector myCharacterVector = engine.GetSymbol("mycharvector").AsCharacter();
            string[] myStringArray = myCharacterVector.ToArray();
            Console.WriteLine("\nmyCharacterVector: ");
            int i = 1;
            foreach (string myCharacter in myCharacterVector)
            {
                Console.WriteLine(i + " value: " + myCharacter);
                i++;
            }


            //R int vector -- R.NET RDotNet.IntegerVector
            RCodeString = "myintvector<-1:10";
            Console.WriteLine("\nR Code: " + RCodeString);
            IntegerVector myIntegerVector = engine.Evaluate(RCodeString).AsInteger();
            int[] myIntegerArray = myIntegerVector.ToArray();
            Console.WriteLine("\nInteger Vector: ");
            i = 1;
            foreach (int myInteger in myIntegerVector)
            {
                Console.WriteLine(i + " value=" + myInteger);
                i++;
            }


            //R real vector -- R.NET RDotNet.NumericVector
            RCodeString = "myrealvector<-rnorm(5, 0, 1)";
            Console.WriteLine("\nR Code: " + RCodeString);
            NumericVector myNumericVector = engine.Evaluate(RCodeString).AsNumeric();
            double[] myDoubleArray = myNumericVector.ToArray();
            Console.WriteLine("\nNumeric Vector: ");
            i = 1;
            foreach (double myNumeric in myNumericVector)
            {
                Console.WriteLine(i + " value=" + myNumeric);
                i++;
            }

            //R complex vector -- R.NET RDotNet.ComplexVector
            RCodeString = "mycomplexvector<- 1:2 + 1i*(8:9)";
            Console.WriteLine("\nR Code: " + RCodeString);
            ComplexVector myComplexVector = engine.Evaluate(RCodeString).AsComplex();
            Complex[] myComplexArray = myComplexVector.ToArray();
            Console.WriteLine("\nComplex Vector: ");
            i = 1;
            foreach (Complex myComplex in myComplexVector)
            {
                Console.WriteLine(i + " value=" + myComplex.Imaginary + "i + " + myComplex.Real);
                i++;
            }

            //R raw vector -- R.NET RDotNet.RawVector
            RCodeString = "myrawvector<-charToRaw(\"u03a0\")";
            Console.WriteLine("\nR Code: " + RCodeString);
            RawVector myRawVector = engine.Evaluate(RCodeString).AsRaw();
            Console.WriteLine("\nRaw Vector: ");
            i = 1;
            foreach (Byte myByte in myRawVector)
            {
                Console.WriteLine(i + " value=" + myByte);
                i++;
            }

            //R logical vector -- R.NET RDotNet.LogicalVector
            RCodeString = "mylogicalvector<-c(FALSE, TRUE, FALSE, TRUE, FALSE)";
            Console.WriteLine("\nR Code: " + RCodeString);
            LogicalVector myLogicalVector = engine.Evaluate("c(FALSE, TRUE, FALSE, TRUE, FALSE)").AsLogical();
            Boolean[] myBooleanArray = myLogicalVector.ToArray();
            Console.WriteLine("\nLogical Vector: \n");
            i = 1;
            foreach (Boolean myBoolean in myLogicalVector)
            {
                Console.WriteLine(i + " value=" + myBoolean.ToString());
                i++;
            }



            //Close R.NET connection
            engine.Close();
        }
    }
}
