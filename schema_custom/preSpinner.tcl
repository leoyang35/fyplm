tcl;
eval {
	
	#Add BBXHQD column in My Loading Position page
	set strField [mql print table 'FYPLMMyBMRAndLPTable' system select column dump |]
    if { [string first "FYPLM BBXHQD Name" $strField] == -1 } {
		mql mod table FYPLMMyBMRAndLPTable system column name "FYPLM BBXHQD Name"
		set num [mql print table  FYPLMMyBMRAndLPTable system  select column\[FYPLM CLXHQD Name\].number dump |]
		incr num
		mql mod table FYPLMMyBMRAndLPTable system column mod name 'FYPLM BBXHQD Name' order $num
	}
    
}