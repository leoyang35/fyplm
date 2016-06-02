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
    
    
}