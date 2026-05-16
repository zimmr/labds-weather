javac -cp "lib\gson-2.14.0.jar;lib\mysql-connector-j-9.7.0.jar;lib\jbcrypt-0.4.jar" -d out (Get-ChildItem -Recurse -Filter "*.java" src\ | % { $_.FullName })
