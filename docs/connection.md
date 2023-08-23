#
#### Connection string:
```
EBSDEMO = (DESCRIPTION= (ADDRESS=(PROTOCOL=tcp)(HOST=144.24.221.114)(PORT=1521)) (CONNECT_DATA= (SERVICE_NAME=ebs_ebsdb) (INSTANCE_NAME=ebscdb)))

Schema username: xxmob

Schema Pass: xxmob 

```


#### HRSS-4 – Authenticating the users & On-Boarding to Home Page
```
https://it-ranks.atlassian.net/browse/HRSS-4?atlOrigin=eyJpIjoiYmJkN2MwZTgxOTBlNGMwOGIyMWY0NGY2MWEzNzY2ZTkiLCJwIjoiaiJ9 )  
```
# First 
=====================================================================
```
(P_LANGUAGE) 

------ 

The parameter should be fed by one of the following values as per the oracle available languages  

------------ 

 SQL: 

SELECT nls_language value ,initcap(nls_language) id FROM apps.fnd_languages WHERE installed_flag IN ('B', 'I'); 

-------------- 

'AMERICAN' 

'ARABIC' 



```
#End
===========================================================================================
### Object  
``` 
XXMOB.XXX_MOBILE_LOGIN.alter_session 
```
### Description  
``` 
to be executed just before logging, to initiate the session with the (APPS schema, session language and current sys date)  
```
### Parameter  
``` 
(P_LANGUAGE) 

------ 

The parameter should be fed by one of the following values as per the oracle available languages  

------------ 

 SQL: 

SELECT nls_language value ,initcap(nls_language) id 
FROM apps.fnd_languages 
WHERE installed_flag IN ('B', 'I'); 

-------------- 

'AMERICAN' 

'ARABIC' 

```



