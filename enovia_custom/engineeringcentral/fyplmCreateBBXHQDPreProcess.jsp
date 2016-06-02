<%@ page import="org.apache.log4j.*" %>
<%@ page import="com.matrixone.apps.framework.ui.UINavigatorUtil"%>
<%@ page import="com.matrixone.apps.domain.DomainObject"%>
<%@ page import="com.matrixone.apps.domain.DomainConstants"%>
<%@ page import="matrix.util.StringList"%>
<%@ page import="com.matrixone.apps.domain.util.MapList"%>
<%@ page import="com.matrixone.apps.domain.util.i18nNow"%>
<%@ page import="com.matrixone.apps.common.Company"%>

<%@ page import="matrix.db.JPO"%>

<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.matrixone.apps.domain.util.eMatrixDateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Locale"%>


<%@include file="../common/emxNavigatorInclude.inc"%>
<%@include file="../common/emxNavigatorTopErrorInclude.inc"%>
<%@include file="../emxUICommonHeaderBeginInclude.inc"%>

<emxUtil:localize id="i18nId" bundle="emxEngineeringCentralStringResource" locale='<%= request.getHeader("Accept-Language") %>' />

<head>
<script language="JavaScript" src="scripts/emxUIConstants.js"></script>
<script language="javascript" src="scripts/emxUIModal.js"></script>
<script language="javascript" src="scripts/emxUIFormUtil.js"></script>
	<script language="javascript" type="text/javascript" src="../common/scripts/emxUICalendar.js"></script>
	<script language="javascript" type="text/javascript" src="../common/scripts/emxUIFormUtil.js"></script>
	<script language="javascript" type="text/javascript" src="../common/scripts/emxUIModal.js"></script>
	<script language="javascript" type="text/javascript" src="../common/scripts/emxUICore.js"></script>
	<script language="javascript" type="text/javascript" src="../common/scripts/emxUICreate.js"></script>
	<script language="javascript" type="text/javascript" src="../common/scripts/emxTypeAhead.js"></script>
	<script language="javascript" type="text/javascript" src="../common/scripts/emxUIFormHandler.js"></script>
	<script language="javascript" type="text/javascript" src="../common/scripts/emxQuery.js"></script>
	<script language="javascript" type="text/javascript" src="../components/emxComponentsJSFunctions.js"></script>
	
	<script type="text/javascript">
		function alertExistingClxhqd(){
			alert('<emxUtil:i18nScript localize="i18nId">emxEngineeringCentral.Message.ExistingClxhqd</emxUtil:i18nScript>');
			top.close();
		}
		function alertSelectedOneLoadingPosition(){
			alert('<emxUtil:i18nScript localize="i18nId">emxEngineeringCentral.Message.SelectedOneLoadingPosition</emxUtil:i18nScript>');
			top.close();
		}
	</script>
</head>
<%
    Logger logger = LogManager.getLogger("fyplmCreateBBXHQDPreProcess.jsp");
    logger.info("Enter the fyplmCreateBBXHQDPreProcess.jsp.");
    String objectId = "";
    StringList strList = new StringList();
    try {
        String sSelectIds[] = emxGetParameterValues(request, "emxTableRowId");
        if(sSelectIds.length != 1){
        	out.println("<script>alertSelectedOneLoadingPosition();</script>");
            return;
        }
        objectId = (String) sSelectIds[0];
        
        System.out.println("sObjectId111========"+objectId);
        if (objectId.indexOf("|") != -1) {
        	strList = FrameworkUtil.split(objectId, "|");
        	//System.out.println("strList======="+strList);
        	objectId = (String) strList.get(0);
        }
        DomainObject objLp = DomainObject.newInstance(context, objectId);
        StringList slObj = new StringList();
            slObj.addElement(DomainObject.SELECT_ID);
            slObj.addElement(DomainObject.SELECT_TYPE);
            slObj.addElement(DomainObject.SELECT_NAME);
 
            //logger.info("o is slObj ------------------" + slObj);
            StringList slRel = new StringList();
            slRel.addElement(DomainObject.SELECT_RELATIONSHIP_ID);
            /**
            MapList mapList = objLp.getRelatedObjects(context,
            		"FYPLM LP Clxhqd",
                    "FYPLM Clxhqd",
                    slObj,
                    slRel,
                    false,
                    true,
                    (short)1,
                    DomainObject.EMPTY_STRING,
                    DomainObject.EMPTY_STRING,
                    0);
           //System.out.println("mapList======="+mapList.size());         
           if(mapList!=null && mapList.size()>0){
           	   out.println("<script>alertExistingClxhqd();</script>");
               return;
           }**/
        
        String url = request.getContextPath()+"/common/emxForm.jsp?objectId="+objectId+"&form=type_createBBXHQDByBMRAndLPForm&mode=edit&HelpMarker=emxhelpdocumenteditdetails&formHeader=emxComponents.Header.FYPLMCreateClxhqdByBMRAndLPForm&Export=False&findMxLink=false&submitAction=refreshCaller&suiteKey=Components&StringResourceFileId=emxComponentsStringResource&postProcessJPO=fyplmClxhqd:createClxhqdByBMRAndLPPostProcess";
		response.sendRedirect(url);
        //System.out.println("objectId======="+objectId);
        
    } catch (Exception e) {
        logger.error("Excption: ", e);
        e.printStackTrace();
    }
    logger.info("Exit the jsp.");
%>
<%@include file = "../emxUICommonHeaderEndInclude.inc" %>