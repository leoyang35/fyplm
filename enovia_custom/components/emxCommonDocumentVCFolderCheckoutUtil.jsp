<%--
  emxCommonDocumentVCFolderCheckoutUtil.jsp
  Copyright (c) 1992-2015 Dassault Systemes.
--%>

<%@ page import = "com.matrixone.apps.framework.ui.UINavigatorUtil" %>
<%@ page import = "com.matrixone.apps.domain.util.FrameworkProperties" %>
<%@ page import = "com.matrixone.apps.domain.util.EnoviaResourceBundle" %>
<%@ page import = "com.matrixone.servlet.Framework" %>
<%@include file = "../emxContentTypeInclude.inc"%>

<%
matrix.db.Context context = Framework.getFrameContext(session);

String language = request.getHeader("Accept-Language");
String ResFormFileId = "emxComponentsStringResource";
String sequenceNumAlert = EnoviaResourceBundle.getProperty(context, ResFormFileId,context.getLocale(), "emxComponents.Alert.WrongSequenceNumber");
String meetingDateAlert = EnoviaResourceBundle.getProperty(context, ResFormFileId,context.getLocale(), "emxComponents.Alert.MeetingDate");
String meetingTimeAlert = EnoviaResourceBundle.getProperty(context, ResFormFileId,context.getLocale(), "emxComponents.Alert.MeetingTime");
String sCanntedit = EnoviaResourceBundle.getProperty(context, ResFormFileId,context.getLocale(), "fyplm.CR.Canntedit");
String sNotPositiveInteger = EnoviaResourceBundle.getProperty(context, ResFormFileId,context.getLocale(), "fyplm.CR.NotPositiveInteger");
String sNotPositiveNumber = EnoviaResourceBundle.getProperty(context, ResFormFileId,context.getLocale(), "fyplm.CR.NotPositiveNumber");
String emxNameBadChars = EnoviaResourceBundle.getProperty(context,"emxFramework.Javascript.BadChars");
%>
var sequenceNumAlert = "<%=sequenceNumAlert%>";
var meetingDateAlert = "<%=meetingDateAlert%>";
var meetingTimeAlert = "<%=meetingTimeAlert%>";
 var sBadCharInName	= "<%=emxNameBadChars%>";
 var ARR_BAD_CHARS = "";
	
 if (sBadCharInName != "") 
 {    
  		ARR_BAD_CHARS = sBadCharInName.split(" ");
 }

function callCheckout(objectId, action, fileName, format, refresh, closeWindow, appName, appDir, partId, version, SB, customSortColumns, customSortDirections, uiType, table, getCheckoutMapFromSession, fromDataSessionKey, parentOID)
{
    var formName = "commonDocumentCheckout";
    var newForm = getTopWindow().document.getElementById(formName);

    if (newForm == null) {
        newForm = getTopWindow().opener.getTopWindow().document.getElementById(formName);
    }
    //IR-186383V6R2014x: step up once more to find the form
    if (newForm == null) {
        newForm = getTopWindow().opener.getTopWindow().opener.getTopWindow().document.getElementById(formName);
    }

    newForm.objectId.value = objectId;
    newForm.action.value = action;
    newForm.fileName.value = fileName;
    newForm.format.value = format;
    newForm.refresh.value = refresh;
    newForm.closeWindow.value = closeWindow;
    newForm.appDir.value = appDir;
    newForm.appName.value = appName;
    newForm.trackUsagePartId.value = partId;
    newForm.version.value = version;
    newForm.customSortColumns.value = customSortColumns;
    newForm.customSortDirections.value = customSortDirections;
    newForm.uiType.value = uiType;
    newForm.table.value = table;
    newForm.getCheckoutMapFromSession.value = getCheckoutMapFromSession;
    newForm.fromDataSessionKey.value = fromDataSessionKey;
    newForm.parentOID.value = parentOID;

    var intWidth = "730";
    var intHeight = "450";
    var strFeatures = "width=" + intWidth + ",height=" + intHeight;
    var intLeft = parseInt((screen.width - intWidth) / 2);
    var intTop = parseInt((screen.height - intHeight) / 2);

    if (isIE_M) {
        strFeatures += ",left=" + intLeft + ",top=" + intTop;
    } else {
        strFeatures += ",screenX=" + intLeft + ",screenY=" + intTop;
    }
    if (isNS4_M) {
        strFeatures += ",resizable=no";
    } else {
        strFeatures += ",resizable=yes";
    }

    // Code added for the Bug 295197 Dt 8/11/2005
    var today = new Date();
    var suffix = today.getTime();
    var strFeatures = "";
    var winName = "_self";
    var win = "";

    if (SB == null && "view" == action) {
        //modified for Bug no : 317605. Dt 03/04/2006. Added scrollbars=yes
        strFeatures = "width=730,height=450,dependent=yes,resizable=yes,toolbar=yes,scrollbars=yes";
        winName = "CheckoutWin"+suffix;
        // Till Here
        win = window.open('', winName, strFeatures);
    }

    if ("view" == action) {
        newForm.target = winName;
    } else {
        newForm.target ="hiddenFrame";
    }

    newForm.submit();
}


function callViewer(objectId, action, fileName, format, viewerURL, partId, version)
{
    var intWidth = "730";
    var intHeight = "450";
    var strFeatures = "width=" + intWidth + ",height=" + intHeight;
    var intLeft = parseInt((screen.width - intWidth) / 2);
    var intTop = parseInt((screen.height - intHeight) / 2);

    if (isIE_M) {
        strFeatures += ",left=" + intLeft + ",top=" + intTop;
    } else {
        strFeatures += ",screenX=" + intLeft + ",screenY=" + intTop;
    }
    if (isNS4_M) {
        strFeatures += ",resizable=no";
    } else {
        strFeatures += ",resizable=yes";
    }

  //modified for Bug no : 317605. Dt 03/04/2006. Added scrollbars=yes
    var strFeatures = "width=730,height=450,dependent=yes,resizable=yes,toolbar=no,scrollbars=yes";
    var win = window.open('', "CheckoutWin", strFeatures);
    win.document.commonDocumentViewer.target="CheckoutWin";

  win.document.write("<form name=\"commonDocumentViewer\" method=\"post\" action=\"../components/emxCommonDocumentPreCheckout.jsp\">");
    win.document.write("<table>");
    win.document.write("<input type=\"hidden\" name=\"objectId\" value=\"\" />");
    win.document.write("<input type=\"hidden\" name=\"id\" value=\"\" />");
    win.document.write("<input type=\"hidden\" name=\"fileAction\" value==\"view\" />");
    win.document.write("<input type=\"hidden\" name=\"format\" value=\"\" />");
    win.document.write("<input type=\"hidden\" name=\"fileName\" value=\"\" />");
    win.document.write("<input type=\"hidden\" name=\"file\" value=\"\" />");
    win.document.write("<input type=\"hidden\" name=\"trackUsagePartId\" value=\"\" />");
    win.document.write("<input type=\"hidden\" name=\"version\" value=\"\" />");
    win.document.write("</table>");
    win.document.write("</form>");
    win.document.commonDocumentViewer.objectId.value = objectId;
    win.document.commonDocumentViewer.id.value = objectId;
    win.document.commonDocumentViewer.fileName.value = fileName;
    win.document.commonDocumentViewer.file.value = fileName;
    win.document.commonDocumentViewer.format.value = format;
    win.document.commonDocumentViewer.fileAction.value = action;
    win.document.commonDocumentViewer.action = viewerURL;
    win.document.commonDocumentViewer.trackUsagePartId.value = partId;
    win.document.commonDocumentViewer.version.value = version;
    win.document.commonDocumentViewer.submit();

}

// Fix for bug 366418
function validateSequence() {
  var columnVals = getColumnDataAtLevel();
  var length = columnVals.length;
  var flag = false;
  columnVals.sort(function(a,b){return a - b});
  var temp1 = 1;

  for (var i = 0; i < columnVals.length; i++) {
      if (temp1 == columnVals[i]) {
          temp1++;
      } else {
          if (length-1 == i) {
              flag = true;
              break;
          }
      }
  }

  if (flag) {
      return sequenceNumAlert;
  }

  return "";
}


function trimWhitespace(strString)
{
    strString = strString.replace(/^[\s\u3000]*/g, "");
    return strString.replace(/[\s\u3000]+$/g, "");
}

<%
String nonNumericValueAlert = EnoviaResourceBundle.getProperty(context, ResFormFileId,context.getLocale(), "emxComponents.Alert.NonNumericValue");
%>

var nonNumericValueAlert = "<%=nonNumericValueAlert%>";

function validateMeetingDuration(value)
{
    var strMeetingDuration = value;
    var returnFlag = true;

    if (trimWhitespace(strMeetingDuration) == '')
        returnFlag = true; // AEF will take care of mandatory field validation

    if (isNaN(strMeetingDuration)) {
        alert(nonNumericValueAlert +" <%=EnoviaResourceBundle.getProperty(context, ResFormFileId,context.getLocale(), "emxComponents.Common.Duration")%>" );
        returnFlag = false;
    } else if (strMeetingDuration > 0 && strMeetingDuration <= 500) {
        returnFlag = true;
    } else {
        alert("<%=EnoviaResourceBundle.getProperty(context, ResFormFileId,context.getLocale(), "emxComponents.Alert.MeetingDuration")%>");
        returnFlag = false;
    }

    return returnFlag;
}

function validateAgendaItemDuration(value)
{
    var strDuration = value;
    var returnFlag = true;

    if (trimWhitespace(strDuration) == '')
        returnFlag = true; // AEF will take care of mandatory field validation

    if (isNaN(strDuration)) {
        alert(nonNumericValueAlert +" <%=EnoviaResourceBundle.getProperty(context, ResFormFileId,context.getLocale(), "emxComponents.Common.Duration")%>" );
        returnFlag = false;
    } else if(strDuration > 0 && strDuration <= 500) {
        returnFlag = true;
    } else {
        alert("<%=EnoviaResourceBundle.getProperty(context, ResFormFileId,context.getLocale(), "emxComponents.Alert.Duration")%>");
        returnFlag = false;
    }

    return returnFlag;
}
//Cloud Begin

function reloadProductionTypeRange(newValue, current_Id) {
    emxEditableTable.reloadCell("FYPLM Production Type");
    return true;
}

function reloadMasterDeviceRange(newValue, current_Id){
	var sProdType = emxEditableTable.getCellValueByRowId(newValue, "FYPLM Production Type").value.current.actual;
	var sMasterDevice = this;
	alert(sMasterDevice)
}
//Cloud End
function validateMeetingDate(editedDate, rowId)
{
    var startTimeColIndex = colMap.getColumnByName('StartTime').index;
    var startDateColIndex = colMap.getColumnByName('Startdate').index;
    var startTimeColObj = emxUICore.selectSingleNode(oXML,"/mxRoot/rows//r[@id='"+rowId+"']/c[" +  startTimeColIndex + "]");
    var startDateColObj = emxUICore.selectSingleNode(oXML,"/mxRoot/rows//r[@id='"+rowId+"']/c[" +  startDateColIndex + "]");
    var meetingDate = startDateColObj.getAttribute("date"); //New meeting date time
    meetingDate = meetingDate.substring(0, meetingDate.indexOf(" "));
    var meetingTime = startTimeColObj.getAttribute("newA");
    if( meetingTime == " " || meetingTime == null)
    {
        meetingTime = startTimeColObj.getAttribute("a"); // if time is not edited take the existing time value
    }
    var tt = emxUICore.getText(startTimeColObj);
    var isEdited = startTimeColObj.getAttribute("edited") == "true"?true:false;
    var dd = editedDate;

    var meetingNameIndex = colMap.getColumnByName('Name').index;
    var meetingNameObj = emxUICore.selectSingleNode(oXML,"/mxRoot/rows//r[@id='"+rowId+"']/c[" +  meetingNameIndex + "]");
    var meetingName = emxUICore.getText(meetingNameObj);
//Added:30-Apr-2010:di1:R210:PRG:Meeting Usability
    var arr=tt.split(':');
    if(-1==arr[1].indexOf(' '))
    {
        var exp=arr[1].substring(0,2);
        var testExp=arr[1].substring(2,3);
        var exp1=arr[1].substring(3,5);
        tt = arr[0]+":"+exp+" "+exp1;
    }
//Addition End:30-Apr-2010:di1:R210:PRG:Meeting Usability
    var meetingDate = new Date(meetingDate+" "+meetingTime);
    var meetingDay = meetingDate.getDate();
    var meetingMonth = meetingDate.getMonth();
    var meetingYear = meetingDate.getFullYear();

    var currentDate = new Date();
    var currentDay = currentDate.getDate();
    var currentMonth = currentDate.getMonth();
    var currentYear = currentDate.getFullYear();

    var meetingDateMod = Date.parse(meetingDate);
    var currentDateMod = Date.parse(currentDate);

    if (meetingDateMod < currentDateMod) {
        if (meetingDay == currentDay && meetingMonth == currentMonth && meetingYear == currentYear) {
            if(!isEdited) {
                return meetingName + " : " + meetingTimeAlert + "\n";
            }
        } else {
            return meetingName + " : " + meetingDateAlert + "\n";
        }
    }

    return "";
}

function validateMeetingTime(editedTime, rowId)
{
    var startDateColIndex = colMap.getColumnByName('Startdate').index;
    var startDateColObj = emxUICore.selectSingleNode(oXML,"/mxRoot/rows//r[@id='"+rowId+"']/c[" +  startDateColIndex + "]");
    var isEdited = startDateColObj.getAttribute("edited") == "true"?true:false;
    var meetingDate = startDateColObj.getAttribute("date");
    if(isEdited) {   // If Date is edited, extract only date field
        meetingDate = meetingDate.substring(0, meetingDate.indexOf(" "));
    }
    var dd = emxUICore.getText(startDateColObj);
    var tt = editedTime;

    var meetingNameIndex = colMap.getColumnByName('Name').index;
    var meetingNameObj = emxUICore.selectSingleNode(oXML,"/mxRoot/rows//r[@id='"+rowId+"']/c[" +  meetingNameIndex + "]");
    var meetingName = emxUICore.getText(meetingNameObj);

    var arr=tt.split(':');
    if(-1==arr[1].indexOf(' '))
    {
        var exp=arr[1].substring(0,2);
        var testExp=arr[1].substring(2,3);
        var exp1=arr[1].substring(3,5);
        tt = arr[0]+":"+exp+" "+exp1;
    }

    var meetingDate = new Date(meetingDate+" "+editedTime);
    var meetingDay = meetingDate.getDate();
    var meetingMonth = meetingDate.getMonth();
    var meetingYear = meetingDate.getFullYear();

    var currentDate = new Date();
    var currentDay = currentDate.getDate();
    var currentMonth = currentDate.getMonth();
    var currentYear = currentDate.getFullYear();

    var meetingDateMod = Date.parse(meetingDate);
    var currentDateMod = Date.parse(currentDate);

    if (meetingDateMod < currentDateMod) {
        if (meetingDay == currentDay && meetingMonth == currentMonth && meetingYear == currentYear) {
            return meetingName + " : " + meetingTimeAlert + "\n";
        }
        else if(!isEdited) {
            return meetingName + " : " + meetingDateAlert + "\n";
        }
    }
    return "";
}

 //Validate Currency rate
function validateCurrencyValue(value)
{
    if (value == "" || isNaN(value) || ((value)<=0) ) {
        alert("<%=EnoviaResourceBundle.getProperty(context, ResFormFileId,context.getLocale(), "emxComponents.Common.AlertNumber")%>");
        return false;
    } else if (value.length>15 ) {
        alert("<%=EnoviaResourceBundle.getProperty(context, ResFormFileId,context.getLocale(), "emxComponents.Common.AlertCurrencyRateLengthExceeded")%>");
        return false;
    }
    return true;
}

function validatePersonSkillSummaryExperience(value)
{
    var returnFlag = true;
    if (trimWhitespace(value) == '')
        returnFlag = true; // AEF will take care of mandatory field validation

    for (var i = 0; i < value.length; i++) {
        var ch = value.charAt(i)
        if (ch < "0" || ch > "9") {
            alert("<%=EnoviaResourceBundle.getProperty(context, ResFormFileId,context.getLocale(), "emxComponents.Alert.ExperienceEnterPositiveInteger")%>");
            returnFlag = false;
            break;
        }
    }

    return returnFlag;
}

//Checking for Positive Integer value of the field
function checkPositiveInteger(value)
{

    if( isNaN(value) || value < 0 || parseInt(value) != value )
    {
    	//XSSOK
        msg = "<%=EnoviaResourceBundle.getProperty(context, ResFormFileId,context.getLocale(),"emxComponents.Alert.IntegerPositive")%>";
        alert(msg);
        return false;
    }
    return true;
}

//Checking for Positive Real value of the field
function checkPositiveReal(value)
{
    if( isNaN(value) || value < 0 )
    {
    	//XSSOK
        msg = "<%=EnoviaResourceBundle.getProperty(context, ResFormFileId,context.getLocale(),"emxComponents.Alert.checkPositiveNumeric")%>";
        alert(msg);
        return false;
    }
    return true;
}

//Checking for Bad characters in the 'Reason For Change' Text Area field
function checkBadCharsForEditAll(value)
{
    var badChars = "";
    badChars=checkForBadChars(value);
    if ((badChars).length != 0)
    {    
    	//XSSOK
        msg = "<%=EnoviaResourceBundle.getProperty(context, ResFormFileId,context.getLocale(),"emxComponents.Alert.InvalidChars")%>";
        msg += badChars;
        msg += "<%=EnoviaResourceBundle.getProperty(context, ResFormFileId,context.getLocale(),"emxComponents.Alert.RemoveInvalidChars")%>";
        msg += "<%=EnoviaResourceBundle.getProperty(context, ResFormFileId,context.getLocale(),"emxComponents.EngineeringChange.ReasonforChange")%>";
        //fieldName.focus();
        alert(msg);
        return false;
        
        
    }
    return true;
}


function checkStringForChars(strText, arrBadChars, blnReturnAll){
        var strBadChars = "";

        for (var i=0; i < arrBadChars.length; i++) {
                if (strText.indexOf(arrBadChars[i]) > -1) {
                        strBadChars += arrBadChars[i] + " ";
                }
        }

        if (strBadChars.length > 0) {
                if (blnReturnAll) {
                        return arrBadChars.join(" ");
                } else {
                        return strBadChars;
                }
        } else {
                return "";
        }
}

// this function checks a text field for bad characters
// Parameters:
//      objField (HTMLInputElement) - the text field to check.
//      arrBadChars (Array) - array of bad characters to check for.
//      blnReturnAll (boolean) - determines if the function should
//              return all bad characters or just the ones that were
//              found. True for all, false for found.
function checkFieldForChars(value, arrBadChars, blnReturnAll){
        var strResult = checkStringForChars(value, arrBadChars, blnReturnAll);
        return strResult;
}

//check for the characters specified above by property file
function checkForBadChars(value){
        return checkFieldForChars(value, ARR_BAD_CHARS, false);
}

//add by xianggang
function validateCRItemQuantity(a) {
    var vTest = isPositiveInteger(a);
    if (vTest) {
        return checkFYPLMIfUpdate();
    } else {
        alert('<%=sNotPositiveInteger %>');
        return false;
    }
}

function validateCRItemMoney(a) {
    var vTest = isPositiveNumber(a);
    if (vTest) {
        return checkFYPLMIfUpdate();
    } else {
        alert('<%=sNotPositiveNumber %>');
        return false;
    }
}

function validateCRItemCycle(a) {
    var vTest = isPositiveNumber(a);
    if (vTest) {
        return checkFYPLMIfUpdate();
    } else {
        alert('<%=sNotPositiveNumber %>');
        return false;
    }
}

function checkFYPLMIfUpdate() {
    var vRowId = currentRow.getAttribute("id");
    var vIfUpdate2actual = emxEditableTable.getCellValueByRowId(vRowId, "IfUpdate").value.current.actual;
    var vStateActual = emxEditableTable.getCellValueByRowId(vRowId, "State").value.current.actual;
    if (vIfUpdate2actual != 'Y' && vStateActual != 'Completion') {
        alert('<%=sCanntedit %>');
        return false;
    } else {
        return true;
    }
}

function isPositiveInteger(str) {
    var g = /^\d+$/;
    return g.test(str);
}

function isPositiveNumber(num) {
    var reg = /^\d+(\.\d+)?$/;
    return reg.test(num);
}
