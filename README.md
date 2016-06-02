# fyplm
FYPLM


Notice note when merge to FY SVN
1. attribute FYPLM Vendor Grade will conflict.



#############Parameter Change################
change following git path in run.bat to deploy all properties after running spinner:
xcopy "C:\git\fyplm\enovia_custom\WEB-INF\classes\*.properties" "C:\apache-tomee-plus-1.6.0\webapps\enovia\WEB-INF\classes" /y /q
