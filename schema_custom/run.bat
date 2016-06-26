:A
@echo off
mql -b MATRIX-R-local -c "set context user creator;run preSpinner.tcl;exec prog emxSpinnerAgent.tcl;run postSpinner.tcl;"
xcopy "..\enovia_custom\WEB-INF\classes\*.properties" "C:\apache-tomee-plus-1.6.0\webapps\enovia\WEB-INF\classes" /y /q
xcopy "..\enovia_custom\engineeringcentral\*.jsp" "C:\apache-tomee-plus-1.6.0\webapps\enovia\engineeringcentral" /y /q
xcopy "..\enovia_custom\components\*" "C:\apache-tomee-plus-1.6.0\webapps\enovia\components" /y /q
pause
goto A
