
#This is the collection of single variable statistics function


#Simple histogram - v is a vector of numercial values - breaks is the number of breaks in the histogram
PI_Histogram <- function(tag1,n_breaks=5,xname="tag1",n_density=0,n_col=1){
hist(tag1, breaks=n_breaks,main = "Histogram of Outside Temperature",density=n_density,col=n_col,xlab="Values")


}



#Plotting density fundtion - It calculates and plots the density of numerical vector v
PI_Density_Plot <- function(v,s_col="red",s_border="blue",s_type="l",n_asp=NULL,tagname="tag"){
d <- density(v[!is.na(v)])
plot(d, main="Distribution of Outside Temperature",type=s_type,asp=n_asp)
polygon(d, col=s_col, border=s_border)
}






#Comparing density functions of two vectors ; can be extended to more than two vectors - You need to install package sm
PI_Density_Compare_TwoTags <- function(v,w,tag1name,tag2name){
require(sm)
sm.density.compare(c(v,w), c(rep(1, length(v)),rep(2, length(w))),xlab = paste("Values"))
title(main="Comparison of Distributions")
legend("topright", c(1, 2), fill=2+0:1, legend=c("Outside Temperature", "Inside Temperature"))

} 


#Comparing density functions of three vectors ; can be extended to more than two vectors - You need to install package sm
PI_Density_Compare_ThreeTags <- function(v,w,z,tag1name,tag2name,tag3name){
require(sm)
sm.density.compare(c(v,w,z), c(rep(1, length(v)),rep(2, length(w)),rep(3, length(z))),xlab = "Values")
legend("topright", c(1,2,3), fill=2+0:2,legend=c("Outside Temperature", "Inside Temperature","Inside Humidity"))
title(main="Comparison of Distributions")
} 




#Box plot of two tags compared - v and w are two numerical vectors
PI_Box_Plot <- function(v,w,tag1name,tag2name,d_range=1.5,d_boxwex=0.8,d_staplewex=0.5){
boxplot(v,w,names=c("Outside Temperature","Inside Temperature"),range=d_range,pars=list(boxwex=d_boxwex,staplewex=d_staplewex),main = "Boxplot of Temperatures")
}


#Regular Correlation between tags - v and w are two numerical vectors
PI_Regular_Correlation<- function(v,w,tag1name="tag1",tag2name="tag2"){
plot(v, w, xlab="Output Temparature", ylab="Total Power Demand", main="Scatter Plot")
}


#Smooth Scatter between tags  - v and w are two numerical vectors
PI_Smooth_Scatter<- function(v,w,tag1name="tag1",tag2name="tag2"){
smoothScatter(v, w, xlab="Output Temparature", ylab="Total Power Demand",main="Smooth Scatter Plot")
}





#Multi-Correlation between 3 tags
PI_Multi_Correlation3<- function(impact,tagnames){

tag<-tagnames

require(gclus)
library(gclus)
impact.r <- abs(cor(impact))
impact.col <- dmat.color(impact.r)
impact.o <- order.single(impact.r)

i=1
while (i<=length(impact.o))
{
tag[i]=tagnames[impact.o[i]]
i=i+1
}


cpairs(impact, impact.o, panel.colors=impact.col, gap=0.5, main="Color-coded Correlation Matrix",labels=c(tag[1],tag[2],tag[3]))
}



#Multi-Correlation between 4 tags
PI_Multi_Correlation4<- function(impact,tagnames){
tag<-tagnames
require(gclus)
library(gclus)
impact.r <- abs(cor(impact))
impact.col <- dmat.color(impact.r)
impact.o <- order.single(impact.r)
i=1
while (i<=length(impact.o))
{
tag[i]=tagnames[impact.o[i]]
i=i+1
}
cpairs(impact, impact.o, panel.colors=impact.col, gap=0.5, main="Color-coded Correlation Matrix",labels=c(tag[1],tag[2],tag[3],tag[4]))
}


#Multi-Correlation between 5 tags
PI_Multi_Correlation5<- function(impact,tagnames){

tag<-tagnames
require(gclus)
library(gclus)
impact.r <- abs(cor(impact))
impact.col <- dmat.color(impact.r)
impact.o <- order.single(impact.r)
i=1
while (i<=length(impact.o))
{
tag[i]=tagnames[impact.o[i]]
i=i+1
}
cpairs(impact, impact.o, panel.colors=impact.col, gap=0.5, main="Color-coded Correlation Matrix",labels=c(tag[1],tag[2],tag[3],tag[4],tag[5]))
}




