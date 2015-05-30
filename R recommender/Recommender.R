rm(list=ls())

setwd("~/GitHub/OpenData_Project/R recommender")
dataset<-read.csv("dataset.txt", head=TRUE)
userInput<-read.csv("user.txt", head=TRUE)
like<-subset(dataset,( nchar( as.character( dataset$dislike[]) ))==0  )
like$dislike<-rep(1, nrow(like))
colnames(like)<-c("user","song", "value")

dislike<-subset(dataset,( nchar( as.character( dataset$dislike[]) ))>0  )
dislike$like<-dislike$dislike
dislike$dislike<-rep(-1, nrow(dislike))
colnames(dislike)<-c("user","song", "value")
dataset=rbind(like,dislike)

userlike<-userInput[nchar( as.character( userInput$dislike[]) )==0,]
userlike$dislike<-rep(1, nrow(userlike))
colnames(userlike)<-c("user","song", "value")

userdislike<-userInput[nchar( as.character( userInput$dislike[]) )>0,]
userdislike$like<-userdislike$dislike
userdislike$dislike<-rep(-1, nrow(userdislike))
colnames(userdislike)<-c("user","song", "value")
dataset=rbind(dataset,userlike,userdislike)
detach(dataset)
attach(dataset)
dataset<-dataset[order(user) ,]

#head(dataset)
library(Matrix)

i<-as.factor(dataset$user)
j<-as.factor(dataset$song)
levels(j)<-rep(1:nlevels(j))
levels(i)<-rep(1:nlevels(i))
Matrix <- Matrix(0, nrow = nlevels(as.factor(i)) ,
                     ncol = nlevels(as.factor(j)), sparse = TRUE);
for(row in 1:nrow(dataset)) {
  Matrix[as.numeric(i[row]) ,as.numeric(j[row]) ]<- dataset$value[row]
}
subset( Matrix[4,], Matrix[4,]!=0)
rownames(Matrix)<-levels(as.factor(dataset$user))
colnames(Matrix)<-levels(as.factor(dataset$song))
Matrix
dist<-dist(Matrix, method="binary", diag=FALSE, upper=FALSE)
matdist<-as.matrix(dist)

userCode<-as.character(userInput$user[1])

similCoef<- subset( matdist[userCode,] , matdist[userCode,]<0.98 ) 
#similCoef<-sort(similCoef)
usersSimilar<-names(similCoef)
usersSimilar<-subset(usersSimilar, usersSimilar!="6666")

songToReturn<-subset(like$song, (like$user[] %in% as.integer(usersSimilar))  )
songToReturn<-subset(songToReturn, !(songToReturn %in% userlike$song)  )
songToReturn<-subset(songToReturn, !(songToReturn %in% userdislike$song)  )
write(as.character(songToReturn), file="recommendedSongs.txt", sep=",")

