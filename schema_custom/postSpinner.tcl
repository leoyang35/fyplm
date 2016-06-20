tcl;
eval {

    set strCommand [mql print channel 'FYPLMMyClxhqdViewChannel'  select command dump |]
    if { [string first "FYPLMMyBBCLXHQD" $strCommand] == -1 } {
        mql mod channel FYPLMMyClxhqdViewChannel place FYPLMMyBBCLXHQD after "FYPLMMyClxhqd"
    }
    
    #add Create BBXHQD command
    set strCommand [mql print menu 'FYPLMMyBMRAndLPListAllToolBar'  select command dump |]
    if { [string first "FYPLMCreateBBXHQDByBMRAndLPActionLink" $strCommand] == -1 } {
        mql mod menu FYPLMMyBMRAndLPListAllToolBar add command FYPLMCreateBBXHQDByBMRAndLPActionLink
    }
    
    #change Create CLXHQD command from image to button
    set strSetting [mql print command 'FYPLMCreateClxhqdByBMRAndLPCommand' select setting dump |]
    if { [string first "Image" $strSetting] != -1 } {
        mql mod command FYPLMCreateClxhqdByBMRAndLPCommand remove setting Image
    }
    
    #add Create BBXHQD command
    set strCommand [mql print menu 'FYPLMMyClxhqdListToolBar'  select command dump |]
    if { [string first "FYPLMCreateBBXHQDCommand" $strCommand] == -1 } {
        mql mod menu FYPLMMyClxhqdListToolBar add command FYPLMCreateBBXHQDCommand
    }
    
    #change Create CLXHQD command from image to button
    set strSetting [mql print command 'FYPLMCreateClxhqdCommand' select setting dump |]
    if { [string first "Image" $strSetting] != -1 } {
        mql mod command FYPLMCreateClxhqdCommand remove setting Image
    }
    
    #add BBXHQD to Route Releationship
    set strRelFrom [mql print relationship 'FYPLM Log Object Route' select fromtype dump |]
    if {[string first "FYPLM BBXHQD" $strRelFrom] == -1} {
    	mql mod relationship 'FYPLM Log Object Route' from add type 'FYPLM BBXHQD'
    }
    set strRelFrom [mql print relationship 'Object Route' select fromtype dump |]
    if {[string first "FYPLM BBXHQD" $strRelFrom] == -1} {
    	mql mod relationship 'Object Route' from add type 'FYPLM BBXHQD'
    }
    
    
}