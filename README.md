# k8s-pod-cleaner
This Application can clean pod with specified name and days.

Please set the value of parameters in application.properties.

cloud.master.url  #The url and port of K8s server.
E.g. https://xxx.xxx.com:1234

cloud.token  #The OauthToken of K8s server.

cloud.namespace.name #The name of target namespace.

cloud.pod.name  #This application only clean pod which name contains the value of "cloud.pod.name".

cloud.clear.days  #If the value of this parameter is 2, this application only clean pod which was created 2 days ago.
