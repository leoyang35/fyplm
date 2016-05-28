tcl;
eval {

    set strCommand [mql print channel 'FYPLMMyClxhqdViewChannel'  select command dump |]
    if { [string first "FYPLMMyBBCLXHQD" $strCommand] == -1 } {
        mql mod channel FYPLMMyClxhqdViewChannel place FYPLMMyBBCLXHQD after "FYPLMMyClxhqd"
    }

}