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
        
        if (objectId.indexOf("|") != -1) {
        	strList = FrameworkUtil.split(objectId, "|");
        	objectId = (String) strList.get(0);
        }

        String url = request.getContextPath()+"/common/emxCreate.jsp?objectId="+objectId+"&type=type_FYPLMBBXHQD&policy=policy_FYPLMBBXHQD&form=type_createBBXHQDByBMRAndLPForm&mode=edit&HelpMarker=emxhelpdocumenteditdetails&submitAction=refreshCaller&formHeader=emxComponents.Header.FYPLMCreateBBXHQDByBMRAndLPForm&Export=False&findMxLink=false&suiteKey=Components&StringResourceFileId=emxComponentsStringResource&nameField=autoname&showPolicy=false&relationship=relationship_FYPLMLPBBXHQD";
		response.sendRedirect(url);
        
    } catch (Exception e) {
        logger.error("Excption: ", e);
        e.printStackTrace();
    }
    logger.info("Exit the jsp.");
%>
<%@include file = "../emxUICommonHeaderEndInclude.inc" %>