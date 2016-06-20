<%@ page import="org.apache.log4j.*" %>
<%@ page import="com.matrixone.apps.framework.ui.UINavigatorUtil"%>
<%@ page import="com.matrixone.apps.domain.DomainObject"%>
<%@ page import="com.matrixone.apps.domain.DomainConstants"%>
<%@ page import="matrix.util.StringList"%>
<%@ page import="com.matrixone.apps.domain.util.MapList"%>
<%@ page import="com.matrixone.apps.domain.util.i18nNow"%>
<%@ page import="com.matrixone.apps.common.Company"%>
<%@ page import="com.matrixone.apps.domain.util.ContextUtil"%>
<%@ page import="com.matrixone.apps.domain.util.FrameworkUtil"%>
<%@ page import="com.matrixone.apps.domain.DomainRelationship"%>
<%@ page import="matrix.db.JPO"%>

<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.matrixone.apps.domain.util.eMatrixDateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Locale"%>

<%@include file="../common/emxNavigatorInclude.inc"%>
<%@include file="../common/emxNavigatorTopErrorInclude.inc"%>
<emxUtil:localize id="i18nId" bundle="emxEngineeringCentralStringResource" locale='<%= request.getHeader("Accept-Language") %>' />
	<script type="text/javascript">
		function alertSuccessMsg(){
			alert('<emxUtil:i18nScript localize="i18nId">emxEngineeringCentral.Message.RiseClxhqdSuccess</emxUtil:i18nScript>');
			top.close();
		}
		function alertFailMsg(){
			alert('<emxUtil:i18nScript localize="i18nId">emxEngineeringCentral.Message.RiseClxhqdFail</emxUtil:i18nScript>');
			top.close();
		}
		function alterSelectOneBBXHQD(){
			alert('<emxUtil:i18nScript localize="i18nId">emxEngineeringCentral.Message.SelectOneBBXHQD</emxUtil:i18nScript>');
			top.close();
		}
		function alertSelectedReleaseClxhqd(){
			alert('<emxUtil:i18nScript localize="i18nId">emxEngineeringCentral.Message.selectedReleaseClxhqd</emxUtil:i18nScript>');
			top.close();
		}
	</script>
<head>
</head>
<%
	String path = request.getContextPath();
    Logger logger = LogManager.getLogger("fyplmBBXHQDWin.jsp");
    logger.info("Enter the jsp.");
    try {
    	String sSelectIds[] = emxGetParameterValues(request, "emxTableRowId");
    	StringList strList = new StringList();
    	
    	if (sSelectIds == null || sSelectIds.length > 1) {
    		out.println("<script>alterSelectOneBBXHQD()</script>");
        	return;
    	}

            String rowId = (String) sSelectIds[0];
	        String bbxhqdId = "";
	        if (rowId.indexOf("|") != -1) {
	        	strList = FrameworkUtil.split(rowId, "|");
	        	bbxhqdId = (String) strList.get(0);
	        }
	        DomainObject objBBXHQD = DomainObject.newInstance(context,bbxhqdId);
	        String strCurrent = objBBXHQD.getInfo(context, DomainObject.SELECT_CURRENT);
	        if(!strCurrent.equals("Release")){
            	out.println("<script>alertSelectedReleaseClxhqd()</script>");
            	return;
            }
	        String[] paramArr = {bbxhqdId};
	        
	        JPO.invoke(context, "fyplmBBXHQD", new String[0], "setBBXHQDWin", paramArr, String.class);
	       
    	
    } catch (Exception e) {
        logger.error("fyplmBBXHQDWin.jsp---Excption: ", e);
        e.printStackTrace();
    }
    logger.info("Exit the jsp.");
%>
<body>
	<img src="<%=path %>/common/images/iconSmallLoading.gif"/>
	
</body>
<script>
//alert(parent.location.href);
//parent.location.href = parent.location.href + '&DefaultCategory=FYPLMBOMAndProcessCommand';
	top.parent.opener.location.href = top.parent.opener.location.href;
	top.close();
</script>
