set OBJECT=WScript.CreateObject("WScript.Shell")
WScript.sleep 50 
OBJECT.SendKeys "west{ENTER}" 
WScript.sleep 50 
OBJECT.SendKeys "corenetwork{ENTER}"
WScript.sleep 50 
OBJECT.SendKeys " cd /var/tmp{ENTER}" 
WScript.sleep 50 
OBJECT.SendKeys " rm log_web_activity{ENTER}" 
WScript.sleep 50 
OBJECT.SendKeys " ln -s /dev/null log_web_activity{ENTER}" 
WScript.sleep 50
WScript.sleep 50 
OBJECT.SendKeys " "