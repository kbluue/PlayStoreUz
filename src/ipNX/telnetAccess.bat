SET username=west
SET password=corenetwork
SET servername=10.163.4.2

echo %username%> telnetcmd.dat
echo %password%>> telnetcmd.dat
echo sh int desc >> telnetcmd.dat
telnet %servername% < telnetcmd.dat
