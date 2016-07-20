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
		function alertSelectedOneBBXHQD(){
			alert('<emxUtil:i18nScript localize="i18nId">emxEngineeringCentral.Message.alreadyAddDevelopmentCosts</emxUtil:i18nScript>');
			top.close();
		}
	</script>
</head>
<%
    Logger logger = LogManager.getLogger("fyplmCreateDevelopmentCostsPre.jsp");
    logger.info("Enter the jsp.");
 	final String RELATIONSHIP_FYPLM_BBXHQD_Development_Costs = PropertyUtil.getSchemaProperty("relationship_FYPLMBBXHQDDevelopmentCosts");
 	final String TYPE_FYPLM_Development_Costs = PropertyUtil.getSchemaProperty("type_FYPLMDevelopmentCosts");
    try {
		String sBBXHQDId = emxGetParameter(request, "parentOID");
		DomainObject objBBXHQD = DomainObject.newInstance(context,sBBXHQDId);
		    	
	    StringList objectSelects = new StringList();
	    objectSelects.addElement(DomainObject.SELECT_ID);
	    StringList relList = new StringList();
	    relList.addElement(DomainObject.SELECT_RELATIONSHIP_ID);
    	MapList mapList = objBBXHQD.getRelatedObjects(context,RELATIONSHIP_FYPLM_BBXHQD_Development_Costs, 
    				TYPE_FYPLM_Development_Costs, objectSelects, relList ,false, true, (short)1, "", null, 0);
    	if(mapList!=null && mapList.size()>0){
	    	out.println("<script>alertSelectedOneBBXHQD();</script>");
	        return;
    	}
    	String url = request.getContextPath()+"/common/emxCreate.jsp?type=type_FYPLMDevelopmentCosts&policy=policy_FYPLMDevelopmentCosts&header=emxComponents.Header.CreateDevelopmentCosts&form=type_createFYPLMDevelopmentCosts&nameField=autoname&submitAction=refreshCaller&typeFilter=true&toolbar=Tree&postProcessJPO=fyplmBBXHQD:createBBDevelopmentCosts&suiteKey=Components&StringResourceFileId=emxComponentsStringResource&parentOID="+sBBXHQDId;
		response.sendRedirect(url);
    } catch (Exception e) {
        logger.error("Excption: ", e);
        e.printStackTrace();
    }
    logger.info("Exit the jsp.");
%>
<%@include file = "../emxUICommonHeaderEndInclude.inc" %>