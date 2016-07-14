#Find the PublicKeyToken of PI AF SDK using PowerShell
#([system.reflection.assembly]::loadFile("C:\Program Files (x86)\PIPC\AF\PublicAssemblies\4.0\OSIsoft.AFSDK.dll")).FullName



source('C:\\Program Files\\R\\R-3.3.1\\library\\Functions.R');
library('rClr')
clrLoadAssembly("OSIsoft.AFSDK, Version=4.0.0.0, Culture=neutral, PublicKeyToken=6238be57836698e6")
clrLoadAssembly('c:/1/rClrDotNetLibrary.dll')

w <- clrNew("rClrDotNetLibrary.PISystemWrapper")
clrCall(w, "GetPIData","marc-pi2016", "FAC.OAK.Weather-Inside_Humidity-Val.PV", "FAC.OAK.Weather-Inside_Temperature-Val.PV", "FAC.OAK.Weather-Outside_Temperature-Val.PV", "1-Oct-2012", "1-Nov-2012", "1h");
v1=clrCall(w, "GetValues",as.integer(1))
v2=clrCall(w, "GetValues",as.integer(2))
v3=clrCall(w, "GetValues",as.integer(3))


impact3<-data.frame(v1,v2,v3);
tn3<-c("FAC.OAK.Weather-Inside_Humidity-Val.PV", "FAC.OAK.Weather-Inside_Temperature-Val.PV", "FAC.OAK.Weather-Outside_Temperature-Val.PV")
PI_Multi_Correlation3(impact3,tn3);