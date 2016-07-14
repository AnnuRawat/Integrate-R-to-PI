
library("RCurl")
library("rjson")
source('C:\\Program Files\\R\\R-3.3.1\\library\\Functions.R');
			
getPIPointWebId <- function(piDataArchive, piPointName) {	
    path = paste(c("\\\\", piDataArchive, "\\" , piPointName))
	compl_url= paste(c("/points?path=", path),collapse="");
	x = getHttpRequest(compl_url)
	x$WebId
}


getInterpolatedValues <- function(startTime, endTime, interval, webId1, webId2, webId3) {
	webIdsString= paste(c(webId1, webId2, webId3),collapse="&webId=");
	print(webIdsString)
	compl_url= paste(c("/streamsets/interpolated?starttime=",startTime,"&endtime=",endTime,"&interval=",interval,"&webId=", webIdsString),collapse="");
	compl_url
	x = getHttpRequest(compl_url)
}

getHttpRequest  <- function(compl_url) {
	base_url = 'https://marc-web-sql.marc.net/piwebapi'	
	url = paste(c(base_url, compl_url),collapse="");
	w=getURL(url,httpheader = c(Authorization = "Basic bsdgyYy5hZG06a2s="), ssl.verifypeer = FALSE);
	x=fromJSON(w);
}

tag1name = 'FAC.OAK.Weather-Inside_Humidity-Val.PV';
tag2name = 'FAC.OAK.Weather-Inside_Temperature-Val.PV';
tag3name = 'FAC.OAK.Weather-Outside_Temperature-Val.PV';
webId1 = getPIPointWebId('marc-pi2016',tag1name); 
webId2 = getPIPointWebId('marc-pi2016',tag2name); 
webId3 = getPIPointWebId('marc-pi2016',tag3name); 
values = getInterpolatedValues('1-Oct-2012','1-Nov-2012','1h', webId1, webId2, webId3);

i=1;
v1 = 1: length(values$Items[[1]]$Items)
v2 = 1: length(values$Items[[2]]$Items)
v3 = 1: length(values$Items[[3]]$Items)

for (value in  values$Items[[1]]$Items){
  v1[i] = value$Value
 i = i + 1 
}

i=1;
for (value in  values$Items[[2]]$Items){
  v2[i] = value$Value
 i = i + 1 
}

i=1;
for (value in  values$Items[[3]]$Items){
  v3[i] = value$Value
 i = i + 1 
}


impact3<-data.frame(v1,v2,v3);
tn3<-c(tag1name,tag2name,tag3name);
PI_Multi_Correlation3(impact3,tn3);

