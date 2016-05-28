:A
@echo off
mql -b MATRIX-R-local -c "set context user creator;exec prog emxSpinnerAgent.tcl;run postSpinner.tcl;"
pause
goto A
