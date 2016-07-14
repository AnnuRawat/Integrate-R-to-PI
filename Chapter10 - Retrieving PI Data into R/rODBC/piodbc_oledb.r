source('C:\\Program Files\\R\\R-3.3.1\\library\\Functions.R');

# Load RODBC package
library(RODBC)


dbhandle <- odbcDriverConnect('Driver={PI ODBC Driver};Server=MARC-WEB-SQL;Trusted Connection=Yes;Provider Type=PIOLEDBENT;Initial Catalog=NuGreen; Provider String={Data Source=MARC-PI2016; Integrated Security=SSPI};')
values <- sqlQuery(dbhandle, 'SELECT ea.Name As Name, Format(i.Time, \'MMM-dd-yyyy HH:mm:ss\'), i.Value
FROM [IntegratingPISystemAndR].[Asset].[ElementHierarchy] eh
INNER JOIN [IntegratingPISystemAndR].[Asset].[ElementAttribute] ea
ON ea.ElementID = eh.ElementID,
[IntegratingPISystemAndR].[Data].[ft_InterpolateRange] i
WHERE i.ElementAttributeID = ea.ID 
AND i.StartTime = \'1-Oct-2012\' 
AND i.EndTime = \'1-Nov-2012\' 
AND i.TimeStep = \'1h\' 
OPTION (FORCE ORDER, EMBED ERRORS)')

tag1name = 'FAC.OAK.Weather-Inside_Humidity-Val.PV';
tag2name = 'FAC.OAK.Weather-Inside_Temperature-Val.PV';
tag3name = 'FAC.OAK.Weather-Outside_Temperature-Val.PV';

n = (dim(values)[1])/3;
v1 = 1: n;
v2 = 1: n;
v3 = 1: n;

i=1;
j=1;
k=1;
m = 1;


while(m<3*n) {
	
	if (values[[1]][[m]]=="OutsideTemperature")
	{
		v3[i] = values[[3]][[m]]
		i=i+1
	}
	if (values[[1]][[m]]=="Inside Temperature")
	{
		v2[j] = values[[3]][[m]]
		j=j+1
	}
	if (values[[1]][[m]]=="Inside Humidity")
	{
		v1[k] = values[[3]][[m]]
		k=k+1
	}
	m <- m+1; 
}


impact3<-data.frame(v1,v2,v3);
tn3<-c(tag1name,tag2name,tag3name);
PI_Multi_Correlation3(impact3,tn3);




