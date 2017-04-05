keytool -importcert -alias woot.com -keystore /path/to/jdk/jre/lib/security/cacerts -storepass changeit -file ldap.auth.curtin.edu.au.cer

keytool -importcert -alias ldap.auth.curtin.edu.au -keystore cacerts -storepass changeit -file ldap.auth.curtin.edu.au.cer