using RDotNet;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Numerics;
using System.Text;
using System.Threading.Tasks;

namespace ImpExpObjs_RDotNet_VS2012
{
    public class ExportingRDotNetObjs
    {
        public static void Start(REngine engine)
        {           
            
            Console.WriteLine("\n\nExporting Objects\n\n");
            string RCodeString = string.Empty;

            //R character vector -- R.NET RDotNet.RDotNet.CharacterVector
            Console.WriteLine("\nR character vector\n");
            string[] myStringArray = new string[] { "PIDataLink","PIProcessBook","PIWebParts" };
            CharacterVector myCharacterVector = engine.CreateCharacterVector(myStringArray.AsEnumerable());
            engine.SetSymbol("mycharvector", myCharacterVector);
            engine.Evaluate("print(mycharvector)");

                        
            //R integer vector -- R.NET RDotNet.NumericVector
            Console.WriteLine("\nR int vector\n");
            int[] myIntegerArray = new int[] { 4, 6, 10, 140, 54, 25 };           
            IntegerVector myIntegerVector = engine.CreateIntegerVector(myIntegerArray);
            engine.SetSymbol("myintvector", myIntegerVector);
            engine.Evaluate("print(myintvector)");

            //R real vector -- R.NET RDotNet.NumericVector
            Console.WriteLine("\nR real vector\n");
            NumericVector myNumericVector = engine.CreateNumericVector(new double[] { 30.02, 29.99, 30.11, 29.97, 30.01, 29.99 });
            engine.SetSymbol("mynumvector", myNumericVector);
            engine.Evaluate("print(mynumvector)");

            //R complex vector -- R.NET RDotNet.ComplexVector
            Console.WriteLine("\nR complex vector\n");
            Complex[] myComplexArray = new Complex[] { Complex.FromPolarCoordinates(10, 0.524), new Complex(12, 6), new Complex(14, 3), (Complex)12.3m, Complex.One + Complex.One };
            ComplexVector myComplexVector = engine.CreateComplexVector(myComplexArray);
            engine.SetSymbol("mycomplexvector", myComplexVector);
            engine.Evaluate("print(mycomplexvector)");


            //R raw vector -- R.NET RDotNet.RawVector
            Console.WriteLine("\nR raw vector\n");
            byte[] myByteArray = System.Text.Encoding.ASCII.GetBytes("u03a0");
            RawVector myRawVector = engine.CreateRawVector(myByteArray);
            engine.SetSymbol("myrawvector", myRawVector);
            engine.Evaluate("print(myrawvector)");

            //R logical vector -- R.NET RDotNet.LogicalVector
            Console.WriteLine("\nR logical vector\n");
            LogicalVector myLogicalVector = engine.CreateLogicalVector(new Boolean[] { true, false, false, false, true });
            engine.SetSymbol("mylogicalvector", myLogicalVector);
            engine.Evaluate("print(mylogicalvector)");
        }
    }
}
