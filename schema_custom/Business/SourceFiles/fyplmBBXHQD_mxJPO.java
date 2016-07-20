import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.text.DecimalFormat;

import org.apache.log4j.Logger;

import matrix.db.AttributeType;
import matrix.db.Context;
import matrix.db.JPO;
import matrix.db.RelationshipType;
import matrix.util.MatrixException;
import matrix.util.StringList;
import matrix.db.MQLCommand;


import com.matrixone.apps.domain.DomainConstants;
import com.matrixone.apps.domain.DomainObject;
import com.matrixone.apps.domain.DomainRelationship;
import com.matrixone.apps.domain.util.ContextUtil;
import com.matrixone.apps.domain.util.FrameworkException;
import com.matrixone.apps.domain.util.FrameworkUtil;
import com.matrixone.apps.domain.util.MapList;
import com.matrixone.apps.domain.util.PropertyUtil;
import com.matrixone.apps.domain.util.XSSUtil;
import com.matrixone.apps.domain.util.i18nNow;
import com.matrixone.apps.engineering.Part;
import com.matrixone.apps.domain.util.MqlUtil;
import com.matrixone.apps.domain.util.PersonUtil;




public class fyplmBBXHQD_mxJPO extends DomainObject{
	
	private static final String STRING_FYPLMExpression = "fyplmExpression";
	
    private static Logger logger = Logger.getLogger(fyplmBBXHQD_mxJPO.class);
    
    public static final String ABEGIN = "attribute[";
    public static final String AEND="]";
    public static final String ATTRIBUTE_FYPLM_NET_USAGE = PropertyUtil.getSchemaProperty("attribute_FYPLMNetUsage");
    public static final String SELECT_ATTRIBUTE_FYPLM_NET_USAGE = new StringBuilder(ABEGIN).append(ATTRIBUTE_FYPLM_NET_USAGE).append(AEND).toString();

    public static final String ATTRIBUTE_FYPLM_GROSS_USAGE = PropertyUtil.getSchemaProperty("attribute_FYPLMGrossUsage");
    public static final String SELECT_ATTRIBUTE_FYPLM_GROSS_USAGE = new StringBuilder(ABEGIN).append(ATTRIBUTE_FYPLM_GROSS_USAGE).append(AEND).toString();
    
    public static final String ATTRIBUTE_FYPLM_CUSTOMER_PART_NUMBER = PropertyUtil.getSchemaProperty("attribute_FYPLMCustomerPartNumber");
    public static final String SELECT_ATTRIBUTE_FYPLM_CUSTOMER_PART_NUMBER = new StringBuilder(ABEGIN).append(ATTRIBUTE_FYPLM_CUSTOMER_PART_NUMBER).append(AEND).toString();

    public static final String ATTRIBUTE_FYPLM_PRODUCTION_TYPE = PropertyUtil.getSchemaProperty("attribute_FYPLMProductionType");
    public static final String SELECT_ATTRIBUTE_FYPLM_PRODUCTION_TYPE = new StringBuilder(ABEGIN).append(ATTRIBUTE_FYPLM_PRODUCTION_TYPE).append(AEND).toString();
    
    public static final String ATTRIBUTE_FYPLM_BBXHQD_PRODUCT_FEATURE = PropertyUtil.getSchemaProperty("attribute_FYPLMBBXHQDProductFeature");
    public static final String SELECT_ATTRIBUTE_FYPLM_BBXHQD_PRODUCT_FEATURE= new StringBuilder(ABEGIN).append(ATTRIBUTE_FYPLM_BBXHQD_PRODUCT_FEATURE).append(AEND).toString();
    
    public static final String ATTRIBUTE_FYPLM_FIXED_ASSETS_DEPRECIATION_CODE = PropertyUtil.getSchemaProperty("attribute_FYPLMFixedAssetsDepreciationCode");
    public static final String SELECT_ATTRIBUTE_FYPLM_FIXED_ASSETS_DEPRECIATION_CODE = new StringBuilder(ABEGIN).append(ATTRIBUTE_FYPLM_FIXED_ASSETS_DEPRECIATION_CODE).append(AEND).toString();
    
    public static final String ATTRIBUTE_FYPLM_MASTER_DEVICE = PropertyUtil.getSchemaProperty("attribute_FYPLMMasterDevice");
    public static final String SELECT_ATTRIBUTE_FYPLM_MASTER_DEVICE = new StringBuilder(ABEGIN).append(ATTRIBUTE_FYPLM_MASTER_DEVICE).append(AEND).toString();
        
    public static final String RELATIONSHIP_FYPLM_BBXHQD_TOPPART = PropertyUtil.getSchemaProperty("relationship_FYPLMBBXHQDTopPart");
    public static final String RELATIONSHIP_FYPLM_BBTOPPART_CLASSBMATERIALPART = PropertyUtil.getSchemaProperty("relationship_FYPLMBBTopPartClassBMaterialPart");
    public static final String RELATIONSHIP_FYPLM_BBXHQD_CLASSBMATERIALPART = PropertyUtil.getSchemaProperty("relationship_FYPLMBBXHQDClassBMaterialPart");
    public static final String RELATIONSHIP_FYPLM_BBXHQD_TECHNOLOGICAL_PROCESS = PropertyUtil.getSchemaProperty("relationship_FYPLMBBXHQDTechnologicalProcess");
    public static final String RELATIONSHIP_FYPLM_TECHNOLOGICAL_PROCESS_BB_STEP = PropertyUtil.getSchemaProperty("relationship_FYPLMTechnologicalProcessBBStep");
    public static final String RELATIONSHIP_FYPLM_LP_BBXHQD = PropertyUtil.getSchemaProperty("relationship_FYPLMLPBBXHQD");
    public static final String RELATIONSHIP_FYPLM_TECHNOLOGICAL_ROUTE_TOP_PART = PropertyUtil.getSchemaProperty("relationship_FYPLMTechnologicalRouteTopPart");
    public static final String RELATIONSHIP_FYPLM_BBXHQD_FITTING_ATTACHMENTS = PropertyUtil.getSchemaProperty("relationship_FYPLMBBXHQDFittingAttachments");
    public static final String RELATIONSHIP_FYPLM_ATTACHMENTS_FITTING_ATTACHMENT_PART = PropertyUtil.getSchemaProperty("relationship_FYPLMAttachmentsFittingAttachmentPart");
    public static final String RELATIONSHIP_FYPLM_ATTACHMENTS_FIT_TOP_PART = PropertyUtil.getSchemaProperty("relationship_FYPLMAttachmentsFitTopPart");
    
    public static final String TYPE_FYPLM_BBXHQD = PropertyUtil.getSchemaProperty("type_FYPLMBBXHQD");
    public static final String TYPE_FYPLM_BB_TOP_PART = PropertyUtil.getSchemaProperty("type_FYPLMBBTopPart");
    public static final String TYPE_FYPLM_BB_STEP = PropertyUtil.getSchemaProperty("type_FYPLMBBStep");
    public static final String TYPE_FYPLM_TECHNOLOGICAL_PROCESS = PropertyUtil.getSchemaProperty("type_FYPLMTechnologicalProcess");
    public static final String TYPE_FYPLM_BB_Temp_Part = PropertyUtil.getSchemaProperty("type_FYPLMBBTempPart");
    
    
    public StringList getFittingAttachmentsListName(Context context, String[] args) throws Exception {
        StringList str = new StringList();
        	HashMap programMap = (HashMap) JPO.unpackArgs(args);
        	MapList objList = (MapList)programMap.get("objectList");
        	for (Iterator iter = objList.iterator(); iter.hasNext();) {
                Map map = (Map) iter.next();
                String objectId = (String)map.get("id");
                DomainObject obj = DomainObject.newInstance(context,objectId);
                String strType = obj.getInfo(context, SELECT_TYPE);
                if(strType.equals(fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Fitting_Attachments)){
                    String sName = obj.getInfo(context, SELECT_NAME);
                    str.addElement(sName);
	            }else if(strType.equals(TYPE_FYPLM_BB_Temp_Part)){
	            	String tempPartName = obj.getAttributeValue(context, ATTRIBUTE_FYPLM_CUSTOMER_PART_NUMBER);
	            	str.addElement(tempPartName);
	            }else if(obj.isKindOf(context, fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Class_B_Attachment_Part)){
	            	String typeName = i18nNow.getTypeI18NString(strType, context.getSession().getLanguage());
	            	str.addElement(typeName);
	 	        } else {
	            	str.addElement(EMPTY_STRING);
	            }
            }
        return str;
    }
    
    public MapList getExpandFittingAttachmentsList(Context context, String[] args) throws Exception {
        MapList partList = new MapList();
            Map map = (Map) JPO.unpackArgs(args);
            String sObjectId = (String) map.get("objectId");
            DomainObject objFitAtt = DomainObject.newInstance(context, sObjectId);
            
            StringList slPart = new StringList();
            slPart.addElement(SELECT_ID);
            slPart.addElement(SELECT_TYPE);
            slPart.addElement(SELECT_NAME);
            
            StringList slObj = new StringList();
            slObj.addElement(SELECT_ID);
            slObj.addElement(SELECT_TYPE);
            slObj.addElement(SELECT_NAME);
            StringList slRel = new StringList(SELECT_RELATIONSHIP_ID);
            
            partList = objFitAtt.getRelatedObjects(context,
            		RELATIONSHIP_FYPLM_ATTACHMENTS_FITTING_ATTACHMENT_PART,
                    "*",
                    slPart,
                    slRel,
                    false,
                    true,
                    (short)1,
                    EMPTY_STRING,
                    EMPTY_STRING,
                    0);
            for (Iterator iter = partList.iterator(); iter.hasNext();) {
                Map mElement = (Map) iter.next();
                mElement.put("hasChildren", "false");
            }

            return partList;
    }
    
    public void createAttachmentFitPostProcess(Context context, String[] args) throws Exception {

  		ContextUtil.startTransaction(context, true);
  		logger.debug("Entering createAttachmentFitPostProcess");
  		HashMap programMap = (HashMap) JPO.unpackArgs(args);
  	    HashMap requestMap = (HashMap) programMap.get("requestMap");
  		HashMap paramMap = (HashMap) programMap.get("paramMap");

  		String sBBId = (String) paramMap.get("objectId");

  		String sAttachFitId= FrameworkUtil.autoName(context, "type_FYPLMFittingAttachments", "policy_FYPLMFittingAttachments");
  		DomainObject objAttachFit = DomainObject.newInstance(context, sAttachFitId);

  		DomainRelationship.connect(context, sBBId, RELATIONSHIP_FYPLM_BBXHQD_FITTING_ATTACHMENTS , sAttachFitId, true);
  		logger.debug("connecting " + RELATIONSHIP_FYPLM_BBXHQD_FITTING_ATTACHMENTS);

  		for(int i=1;i<=50;i++){
  			String relatedConfig = (String) requestMap.get("topPart"+i);
 			if (relatedConfig !=null && !relatedConfig.equals("")){
  				DomainRelationship.connect(context, sAttachFitId, RELATIONSHIP_FYPLM_ATTACHMENTS_FIT_TOP_PART, relatedConfig, true);
  			} 
  		}
  		logger.debug("connecting " + RELATIONSHIP_FYPLM_ATTACHMENTS_FIT_TOP_PART);
  		for(int i=1;i<=50;i++){
  			String sAttach = (String) requestMap.get("attach"+i);
  			if(sAttach!=null && !sAttach.equals("")){
  				DomainObject objProcess = DomainObject.newInstance(context,sAttach);
  				DomainRelationship rel = DomainRelationship.connect(context, objAttachFit, 
  						RELATIONSHIP_FYPLM_ATTACHMENTS_FITTING_ATTACHMENT_PART, objProcess);
  			} 
  		}
  		logger.debug("connecting " + RELATIONSHIP_FYPLM_ATTACHMENTS_FITTING_ATTACHMENT_PART);
  		ContextUtil.commitTransaction(context);
  	}
      
    public String getAttachmentCheckBox(Context context,String[] args) throws Exception{
  		String str = "";
  			HashMap programMap = (HashMap) JPO.unpackArgs(args);
  			HashMap paramMap = (HashMap) programMap.get("paramMap");
  			String objectId = (String) paramMap.get("objectId");

  			DomainObject objBB = DomainObject.newInstance(context, objectId);
  			StringList slObj = new StringList();
  		    slObj.addElement(SELECT_ID);
  		    slObj.addElement(SELECT_NAME);
  		    StringList slRel = new StringList(SELECT_RELATIONSHIP_ID);
  		    
  	        MapList mlAttachmentMaterial = objBB.getRelatedObjects(context,
  	        		RELATIONSHIP_FYPLM_BBXHQD_CLASSBMATERIALPART,
  	        		"FYPLM Class B Material Part",
  	                slObj,
  	                slRel,
  	                false,
  	                true,
  	                (short)1,
  	                EMPTY_STRING,
  	                EMPTY_STRING,
  	                0);
  			
  			mlAttachmentMaterial.sort("name", "ascending", "string");
  		    if (mlAttachmentMaterial.size() > 0) {
  		    	int i=1;
  		    	str += "<table border='0' cellspacing='0' cellpadding='0' ><tr>";
    				for (Iterator iter = mlAttachmentMaterial.iterator(); iter.hasNext();) {
        				Map process = (Map) iter.next();
        				String processId = (String)process.get(SELECT_ID);
        				
        			    String processName = (String) process.get(SELECT_NAME);
        			    processName = i18nNow.getRangeI18NString(TYPE_FYPLM_BB_STEP, processName, context.getSession().getLanguage());
        			    	str += "<td height='40px' style='padding-left:10px;padding-right:10px;'><input type='checkbox' name='attach"+i+"' value='"+processId+"'/>"+processName+"</td>";
            			    if(i%2==0){
            			    	str += "</tr><tr>";
            			    }
            			    i++;

          		}
    				str += "</tr></table>";
          	}
          return str;
      }
    
    public String getAttachFitTopPartValues(Context context,String[] args) throws Exception{
		String str = "";
		HashMap programMap = (HashMap) JPO.unpackArgs(args);
		HashMap paramMap = (HashMap) programMap.get("paramMap");
		String objectId = (String) paramMap.get("objectId");
		
		DomainObject objBB = DomainObject.newInstance(context, objectId);
		HashMap relTopPartMap = new HashMap();
		
        StringList slObj = new StringList();
        slObj.addElement(SELECT_ID);
        slObj.addElement(SELECT_TYPE);
        slObj.addElement(SELECT_NAME);

        StringList slRel = new StringList(SELECT_RELATIONSHIP_ID);
        MapList mapList = objBB.getRelatedObjects(context,
        		RELATIONSHIP_FYPLM_BBXHQD_FITTING_ATTACHMENTS,
                fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Fitting_Attachments,
                slObj,
                slRel,
                false,
                true,
                (short)1,
                EMPTY_STRING,
                EMPTY_STRING,
                0);
        for (Iterator iter = mapList.iterator(); iter.hasNext();) {
            Map mElement = (Map) iter.next();
            String sAttachFit = (String)mElement.get(SELECT_ID);
            DomainObject objAttachFit = DomainObject.newInstance(context, sAttachFit);
            MapList topPartList = objAttachFit.getRelatedObjects(context,
            		RELATIONSHIP_FYPLM_ATTACHMENTS_FIT_TOP_PART,
            		TYPE_FYPLM_BB_TOP_PART,
                    slObj,
                    slRel,
                    false,
                    true,
                    (short)1,
                    EMPTY_STRING,
                    EMPTY_STRING,
                    0);
            for (Iterator iter2 = topPartList.iterator(); iter2.hasNext();) {
                Map topPartMap = (Map) iter2.next();
                String topPartId = (String)topPartMap.get(SELECT_ID);
                relTopPartMap.put(topPartId, topPartId);
            }
        }
		StringList topPartSelect = new StringList();
        topPartSelect.addElement(SELECT_ID);
        topPartSelect.addElement(SELECT_NAME);
        topPartSelect.addElement(SELECT_REVISION);
        topPartSelect.addElement(SELECT_ATTRIBUTE_FYPLM_CUSTOMER_PART_NUMBER);
        
        StringList clxhqdTopPartRelList = new StringList();
    		clxhqdTopPartRelList.addElement(SELECT_RELATIONSHIP_ID);

	    MapList topPartList = objBB.getRelatedObjects(context,
	    				RELATIONSHIP_FYPLM_BBXHQD_TOPPART,
	    				TYPE_FYPLM_BB_TOP_PART,
	                    topPartSelect,
	                    clxhqdTopPartRelList,
	                    false,
	                    true,
	                    (short) 1,
	                    null,
	                    null,
	                    0);
	    topPartList.sort("name", "ascending", "string");
	    
	    if (topPartList.size() > 0) {
	    	int i=1;
	    	str += "<table border='0' ><tr>";
				for (Iterator iter = topPartList.iterator(); iter.hasNext();) {
  				Map topPart = (Map) iter.next();
  				String topPartId = (String)topPart.get(SELECT_ID);
  				if(relTopPartMap.get(topPartId) != null){
  					continue;
  				}
  			    String topPartName = (String) topPart.get(SELECT_ATTRIBUTE_FYPLM_CUSTOMER_PART_NUMBER);

  			    String topPartRev = (String) topPart.get(SELECT_REVISION);
  			    String topPartTitle = topPartName+" "+topPartRev;

      	        str += "<td height='25px;' style='padding-right:20px;'><input type='checkbox' name='topPart"+i+"' value='"+topPartId+"'/>"+topPartTitle+"</td>";
			    if(i%2==0){
			    	str += "</tr><tr>";
			    }
			    i++;
      	        
    		}
				str += "</tr></table>";
    	}
  return str;
}
    
    public void tecProcessAddStepPostProcess(Context context, String[] args) throws Exception {
			ContextUtil.startTransaction(context, true);
			HashMap programMap = (HashMap) JPO.unpackArgs(args);
		    HashMap requestMap = (HashMap) programMap.get("requestMap");
			HashMap paramMap = (HashMap) programMap.get("paramMap");
			String tecProcessId = (String) paramMap.get("tecProcessId");
			DomainObject objTecPro = DomainObject.newInstance(context,tecProcessId);

			for(int i=1;i<=50;i++){
				String processId = (String) requestMap.get("process"+i);
				if(processId!=null && !processId.equals("")){
					DomainObject objProcess = DomainObject.newInstance(context,processId);
					String processCode = objProcess.getInfo(context, SELECT_NAME);
					DomainRelationship rel = DomainRelationship.connect(context, objTecPro,
							RELATIONSHIP_FYPLM_TECHNOLOGICAL_PROCESS_BB_STEP , objProcess);
				}
			}
			ContextUtil.commitTransaction(context);
	}
    
    public String getOtherProcessRangeValues(Context context,String[] args) throws Exception{
  		String str = "";
  			logger.debug("Entering getOtherProcessRangeValues");
  			HashMap programMap = (HashMap) JPO.unpackArgs(args);
  			HashMap paramMap = (HashMap) programMap.get("paramMap");
  			String objectId = (String) paramMap.get("objectId");
  			
  			DomainObject objTecProcess = DomainObject.newInstance(context, objectId);
  			String strType = objTecProcess.getInfo(context, SELECT_TYPE);
  			if(strType.equals(TYPE_FYPLM_BB_STEP)){
  				return "";
  			}

  			StringList slObj = new StringList();
  		    slObj.addElement(SELECT_ID);
  		    slObj.addElement(SELECT_NAME);
  		    StringList slRel = new StringList();
              slRel.addElement(SELECT_RELATIONSHIP_ID);
  		    
  		    MapList oldProcessList = objTecProcess.getRelatedObjects(context,
  		    		RELATIONSHIP_FYPLM_TECHNOLOGICAL_PROCESS_BB_STEP,
  		    		TYPE_FYPLM_BB_STEP,
                      slObj,
                      slRel,
                      false,
                      true,
                      (short)1,
                      EMPTY_STRING,
                      EMPTY_STRING,
                      0);
  		    HashMap tempMap = new HashMap();
              for (Iterator iter = oldProcessList.iterator(); iter.hasNext();) {
                  Map process = (Map) iter.next();
                  String processId = (String)process.get(SELECT_ID);
                  tempMap.put(processId, processId);
              }
              String sWhere  = EMPTY_STRING;
  			MapList processList = findObjects(context,
  						TYPE_FYPLM_BB_STEP,
                      context.getVault().getName(),
                      sWhere,
                      slObj);
  			processList.sort("name", "ascending", "string");
  		    if (processList.size() > 0) {
  		    	int i=1;
  		    	str += "<table border='0' cellpadding='0'  cellspacing='0' ><tr>";
    				for (Iterator iter = processList.iterator(); iter.hasNext();) {
        				Map process = (Map) iter.next();
        				String processId = (String)process.get(SELECT_ID);
        			    if(i==1){
        			    	str += "<input type='hidden' name='tecProcessId' value='"+objectId+"' />";
        			    }
        			    if(tempMap.get(processId)==null ){
            			    String processName = (String)process.get(SELECT_NAME);
            			    processName = i18nNow.getRangeI18NString(TYPE_FYPLM_BB_STEP, processName, context.getSession().getLanguage());
        			    		str += "<td height='40px' style='padding-left:10px;padding-right:10px;'><input type='checkbox' name='process"+i+"' value='"+processId+"' />"+processName+"</td>";
            			    	if(i%2==0){
                			    	str += "</tr><tr>";
                			    }
                			    i++;
        			    }
          		}
    				str += "</tr></table>";
          	}
          return str;
      }
      
    public void createTecProcessPostProcess(Context context, String[] args) throws Exception {

		ContextUtil.startTransaction(context, true);
		logger.debug("Entering createTecProcessPostProcess");
		HashMap programMap = (HashMap) JPO.unpackArgs(args);
	    HashMap requestMap = (HashMap) programMap.get("requestMap");
		HashMap paramMap = (HashMap) programMap.get("paramMap");

		String sBBId = (String) paramMap.get("objectId");

		String tecProId= FrameworkUtil.autoName(context, "type_FYPLMTechnologicalProcess", "policy_FYPLMTechnologicalProcess");
		DomainObject objTecPro = DomainObject.newInstance(context, tecProId);
		DomainRelationship.connect(context, sBBId, RELATIONSHIP_FYPLM_BBXHQD_TECHNOLOGICAL_PROCESS , tecProId, true);

		for(int i=1;i<=100;i++){
			String relatedConfig = (String) requestMap.get("topPart"+i);
			if (relatedConfig !=null && !relatedConfig.equals("")){
				DomainRelationship.connect(context, tecProId, RELATIONSHIP_FYPLM_TECHNOLOGICAL_ROUTE_TOP_PART, relatedConfig, true);
			} else {
				break;
			}
		}

		for(int i=1;i<=20;i++){
			String process = (String) requestMap.get("process"+i);
			if(process!=null && !process.equals("")){
				DomainObject objProcess = DomainObject.newInstance(context,process);
				DomainRelationship rel = DomainRelationship.connect(context, objTecPro, 
						RELATIONSHIP_FYPLM_TECHNOLOGICAL_PROCESS_BB_STEP, objProcess);
			} 
		}
		
		ContextUtil.commitTransaction(context);
	}
    
    public String getProductFeature(Context context, String[] args) throws Exception{
    	Map programMap = (Map) JPO.unpackArgs(args);
        HashMap requestMap = (HashMap) programMap.get("requestMap");
        
        String sId = (String) requestMap.get("objectId");
        DomainObject objBB = DomainObject.newInstance(context,sId);
        String sFeature = objBB.getAttributeValue(context, ATTRIBUTE_FYPLM_BBXHQD_PRODUCT_FEATURE);
        sFeature = i18nNow.getRangeI18NString(ATTRIBUTE_FYPLM_BBXHQD_PRODUCT_FEATURE, sFeature, context.getSession().getLanguage());
        
        return sFeature;
    }
    public String getProcessRangeValues(Context context,String[] args) throws Exception{
  		String str = "";
  			HashMap programMap = (HashMap) JPO.unpackArgs(args);
  			HashMap paramMap = (HashMap) programMap.get("paramMap");
  			String objectId = (String) paramMap.get("objectId");

  			DomainObject objBB = DomainObject.newInstance(context, objectId);
  			StringList slObj = new StringList();
  		    slObj.addElement(SELECT_ID);
  		    slObj.addElement(SELECT_NAME);
  		    
  			MapList processList = findObjects(context,
  					TYPE_FYPLM_BB_STEP,
  					context.getVault().getName(),
  					EMPTY_STRING,
                      slObj);
  			processList.sort("name", "ascending", "string");
  		    if (processList.size() > 0) {
  		    	int i=1;
  		    	str += "<table border='0' cellspacing='0' cellpadding='0' ><tr>";
    				for (Iterator iter = processList.iterator(); iter.hasNext();) {
        				Map process = (Map) iter.next();
        				String processId = (String)process.get(SELECT_ID);
        				
        			    String processName = (String) process.get(SELECT_NAME);
        			    processName = i18nNow.getRangeI18NString(TYPE_FYPLM_BB_STEP, processName, context.getSession().getLanguage());
        			    	str += "<td height='40px' style='padding-left:10px;padding-right:10px;'><input type='checkbox' name='process"+i+"' value='"+processId+"' checked/>"+processName+"</td>";
            			    if(i%2==0){
            			    	str += "</tr><tr>";
            			    }
            			    i++;

          		}
    				str += "</tr></table>";
          	}
          return str;
      }
    public String getTopPartRangeValues(Context context,String[] args) throws Exception{
    		String str = "";
			HashMap programMap = (HashMap) JPO.unpackArgs(args);
			HashMap paramMap = (HashMap) programMap.get("paramMap");
			String objectId = (String) paramMap.get("objectId");
			
			DomainObject objBB = DomainObject.newInstance(context, objectId);
			HashMap relTopPartMap = new HashMap();
			
            StringList slObj = new StringList();
            slObj.addElement(SELECT_ID);
            slObj.addElement(SELECT_TYPE);
            slObj.addElement(SELECT_NAME);
 
            StringList slRel = new StringList(SELECT_RELATIONSHIP_ID);
            MapList mapList = objBB.getRelatedObjects(context,
            		RELATIONSHIP_FYPLM_BBXHQD_TECHNOLOGICAL_PROCESS,
                    fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Technological_Process,
                    slObj,
                    slRel,
                    false,
                    true,
                    (short)1,
                    EMPTY_STRING,
                    EMPTY_STRING,
                    0);
            for (Iterator iter = mapList.iterator(); iter.hasNext();) {
                Map mElement = (Map) iter.next();
                String tecProcessId = (String)mElement.get(SELECT_ID);
                DomainObject objTecPro = DomainObject.newInstance(context, tecProcessId);
                MapList topPartList = objTecPro.getRelatedObjects(context,
                		RELATIONSHIP_FYPLM_TECHNOLOGICAL_ROUTE_TOP_PART,
                		TYPE_FYPLM_BB_TOP_PART,
                        slObj,
                        slRel,
                        false,
                        true,
                        (short)1,
                        EMPTY_STRING,
                        EMPTY_STRING,
                        0);
                for (Iterator iter2 = topPartList.iterator(); iter2.hasNext();) {
                    Map topPartMap = (Map) iter2.next();
                    String topPartId = (String)topPartMap.get(SELECT_ID);
                    relTopPartMap.put(topPartId, topPartId);
                }
            }

			StringList topPartSelect = new StringList();
            topPartSelect.addElement(SELECT_ID);
            topPartSelect.addElement(SELECT_NAME);
            topPartSelect.addElement(SELECT_REVISION);
            topPartSelect.addElement(SELECT_ATTRIBUTE_FYPLM_CUSTOMER_PART_NUMBER);
            
            StringList clxhqdTopPartRelList = new StringList();
	    		clxhqdTopPartRelList.addElement(SELECT_RELATIONSHIP_ID);
    
		    MapList topPartList = objBB.getRelatedObjects(context,
		    				RELATIONSHIP_FYPLM_BBXHQD_TOPPART,
		    				TYPE_FYPLM_BB_TOP_PART,
		                    topPartSelect,
		                    clxhqdTopPartRelList,
		                    false,
		                    true,
		                    (short) 1,
		                    null,
		                    null,
		                    0);
		    topPartList.sort("name", "ascending", "string");
		    if (topPartList.size() > 0) {
		    	int i=1;
		    	str += "<table border='0' ><tr>";
  				for (Iterator iter = topPartList.iterator(); iter.hasNext();) {
      				Map topPart = (Map) iter.next();
      				String topPartId = (String)topPart.get(SELECT_ID);
      				if(relTopPartMap.get(topPartId) != null){
      					continue;
      				}
      			    String topPartName = (String) topPart.get(SELECT_ATTRIBUTE_FYPLM_CUSTOMER_PART_NUMBER);

      			    String topPartRev = (String) topPart.get(SELECT_REVISION);
      			    String topPartTitle = topPartName+" "+topPartRev;

	      	        str += "<td height='25px;' style='padding-right:20px;'><input type='checkbox' name='topPart"+i+"' value='"+topPartId+"'/>"+topPartTitle+"</td>";
    			    if(i%2==0){
    			    	str += "</tr><tr>";
    			    }
    			    i++;
	      	        
        		}
  				str += "</tr></table>";
        	}
      return str;
    }
    public Map getProductionType(Context context, String[] args) throws Exception {
        logger.info("Entering getProductionType().");
        //String str = "";
        Map rangeMap = new HashMap();
        StringList slValue = new StringList();
        StringList slDisplay = new StringList();
         Map programMap = (Map) JPO.unpackArgs(args);
         HashMap requestMap = (HashMap) programMap.get("requestMap");
         HashMap rowValuesMap = (HashMap) programMap.get("rowValues");
         String processId = (String) rowValuesMap.get("objectId");
         String BBId = (String) requestMap.get("parentOID");
         
         DomainObject objProcess = DomainObject.newInstance(context, processId);
         String processName = objProcess.getInfo(context, SELECT_NAME);
         
         DomainObject objBB = DomainObject.newInstance(context, BBId);
      
	    String sProductionPlace = "CHN";
	    StringList slObj = new StringList();
         slObj.addElement(SELECT_ID);
         slObj.addElement(SELECT_TYPE);
         slObj.addElement(SELECT_NAME);
         //logger.info("o is slObj ------------------" + slObj);
         StringList slRel = new StringList();
         slRel.addElement(SELECT_RELATIONSHIP_ID);
         MapList mapList = objBB.getRelatedObjects(context,
        		 RELATIONSHIP_FYPLM_LP_BBXHQD,
                 fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Loading_Position,
                 slObj,
                 slRel,
                 true,
                 false,
                 (short)1,
                 EMPTY_STRING,
                 EMPTY_STRING,
                 0);
	    if(mapList!=null && mapList.size()>0){
	    	Map lpMap = (Map)mapList.get(0);
	    	String lpId = (String)lpMap.get(DomainObject.SELECT_ID); 	
	    	DomainObject objLp = DomainObject.newInstance(context, lpId);
	    	String sSelect = new StringBuilder(ABEGIN)
	    			.append(fyplmClxhqdConstants_mxJPO.ATTR_FYPLM_CLXHQD_Yieldly)
	    			.append(AEND).toString();
	    	slObj.addElement(sSelect);
	    	MapList mlBMs = objLp.getRelatedObjects(context, 
	    			"FYPLM LP BM", 
	    			fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Bid_Management_Report, 
	    			slObj, 
	    			slRel, 
	    			true, 
	    			false, 
	    			(short) 1, 
	    			EMPTY_STRING, 
	    			EMPTY_STRING,
	    			0);
	    	if (mlBMs.size() > 0) {
	    		
		    	sProductionPlace = (String)((Map)mlBMs.get(0)).get(sSelect);
	    	}
	    	  
	    }

        String sLanguage = context.getSession().getLanguage();

         AttributeType atrProductionType = new AttributeType(ATTRIBUTE_FYPLM_PRODUCTION_TYPE);
         atrProductionType.open(context);
         StringList strList = atrProductionType.getChoices(context);
         strList.sort();
         atrProductionType.close(context);
         for(int i=0; i<strList.size();i++){
             String key = (String)strList.get(i);
             String value = i18nNow.getRangeI18NString(ATTRIBUTE_FYPLM_PRODUCTION_TYPE, key, sLanguage);
             String startKey = "";
             
 		  	if (key.startsWith("USA")) {
 		  		if (sProductionPlace.equals("DT")) {
 		  		slValue.add(key);
 		  		slDisplay.add(value);
 		  		}
 		  	} else if (key.startsWith("RUS")){
		  		if (sProductionPlace.equals("RU")) {
	 		  		slValue.add(key);
	 		  		slDisplay.add(value);
		  		}
 		  	} else {
 		  		if (!sProductionPlace.equals("DT") && !sProductionPlace.equals("RU")) {
	 		  		slValue.add(key);
	 		  		slDisplay.add(value);
 		  		}
 		  	}
          }
         rangeMap.put("RangeValues", slValue);
         rangeMap.put("RangeDisplayValue", slDisplay);
        return rangeMap;
    }
    
    public StringList getTecProcessListName(Context context, String[] args) throws Exception {
        StringList str = new StringList();
    	HashMap programMap = (HashMap) JPO.unpackArgs(args);
    	MapList objList = (MapList)programMap.get("objectList");
    	for (Iterator iter = objList.iterator(); iter.hasNext();) {
            Map map = (Map) iter.next();
            String objectId = (String)map.get("id");
            DomainObject obj = DomainObject.newInstance(context,objectId);
            String strType = obj.getInfo(context, SELECT_TYPE);
            if(strType.equals(TYPE_FYPLM_TECHNOLOGICAL_PROCESS)){
                String sName = obj.getInfo(context, SELECT_NAME);
                str.addElement(sName);
            }else if(strType.equals(TYPE_FYPLM_BB_STEP)){
             	String processName = obj.getInfo(context, SELECT_NAME);
            	processName = i18nNow.getRangeI18NString("FYPLM BB Step", processName, context.getSession().getLanguage());
            	str.addElement(processName);
            } else {
            	str.addElement(EMPTY_STRING);
            }
    	}
        return str;
    }
    
    public MapList getExpandTecProcessList(Context context, String[] args) throws Exception {
        logger.info("Entering getTecProcessList().");
        MapList mlElements = new MapList();
        Map map = (Map) JPO.unpackArgs(args);
        String sObjectId = (String) map.get("objectId");
        DomainObject objElement = DomainObject.newInstance(context, sObjectId);
        StringList slObj = new StringList();
        slObj.addElement(SELECT_ID);
        slObj.addElement(SELECT_TYPE);
        slObj.addElement(SELECT_NAME);

        StringList slRel = new StringList();
        slRel.addElement(SELECT_RELATIONSHIP_ID);
        slRel.addElement(ATTRIBUTE_FYPLM_PRODUCTION_TYPE);
        slRel.addElement(ATTRIBUTE_FYPLM_MASTER_DEVICE);
        slRel.addElement(ATTRIBUTE_FYPLM_FIXED_ASSETS_DEPRECIATION_CODE);
        mlElements = objElement.getRelatedObjects(context,
        		RELATIONSHIP_FYPLM_TECHNOLOGICAL_PROCESS_BB_STEP,
                TYPE_FYPLM_BB_STEP,
                slObj,
                slRel,
                true,
                true,
                (short)0,
                EMPTY_STRING,
                EMPTY_STRING,
                0);
        for (Iterator iter = mlElements.iterator(); iter.hasNext();) {
            Map mElement = (Map) iter.next();
            mElement.put("hasChildren", "false");
        }
        mlElements.sort("name", "ascending", "string");
        return mlElements;
    }
    
    @com.matrixone.apps.framework.ui.ExcludeOIDProgramCallable
    public StringList excludeConnected(Context context, String[] args) throws Exception {
    	StringList excludeList= new StringList();
        Map programMap = (Map) JPO.unpackArgs(args);
        String strOId = (String)programMap.get("objectId");
        String strRel=(String)programMap.get("relName");
        DomainObject obj = DomainObject.newInstance(context,strOId);
        String sRelName = PropertyUtil.getSchemaProperty(context,strRel);
        System.out.println(strOId);
        System.out.println(sRelName);
        MapList childObjects=obj.getRelatedObjects(context,
        		sRelName,
                "*",
                new StringList(DomainConstants.SELECT_ID),
                null,
                true,
                true,
               (short) 1,
                DomainConstants.EMPTY_STRING,
                DomainConstants.EMPTY_STRING,
                0);
        System.out.println(childObjects.size());
        for(int i=0;i<childObjects.size();i++){
            Map tempMap=(Map)childObjects.get(i);
            excludeList.add(tempMap.get(DomainConstants.SELECT_ID));
        }
        System.out.println(" excludeList " + excludeList);
        return excludeList;
    }
    
   public MapList getDynamicPartsColumn(Context context, String[] args) throws Exception {
    	logger.debug("entering JPO getDynamicPartsColumn for generate dynamic BOM table column");
        HashMap programMap = (HashMap) JPO.unpackArgs(args);
        MapList objectList = (MapList) programMap.get("objectList");

        HashMap paramList = (HashMap) programMap.get("requestMap");
        String modeStr = (String)paramList.get("mode");
        String objectId = (String) paramList.get("objectId");
        MapList columnMapList = new MapList();
        
        DomainObject objBBXHQD = DomainObject.newInstance(context, objectId);
        StringList objSelect = new StringList();
        objSelect.addElement(SELECT_ID);
        MapList topPartList = objBBXHQD.getRelatedObjects(context, RELATIONSHIP_FYPLM_BBXHQD_TOPPART, 
        						TYPE_FYPLM_BB_TOP_PART, 
                                        objSelect, new StringList(SELECT_RELATIONSHIP_ID), 
                                                        false, true, (short)1, null, null, 0);
        MapList columntMap = (MapList) programMap.get("columnMap");
        for (int i=0 ; i< topPartList.size(); i++){
        	Map tmpMap = (Map)topPartList.get(i);
        	String sPartId = (String)tmpMap.get(SELECT_ID);
        	String sRelId = (String)tmpMap.get(SELECT_RELATIONSHIP_ID);
        	DomainObject objPart = DomainObject.newInstance(context,sPartId);
        	String sPartName = objPart.getInfo(context, SELECT_ATTRIBUTE_FYPLM_CUSTOMER_PART_NUMBER);
        	
        	String sHeader = new StringBuilder("<input type=\"radio\" name=\"emxTableRowId\" value=\"")
        			.append(sRelId).append("|").append(sPartId).append("\" onclick=\"doRadioButtonClick(this);\">").append(sPartName).toString();
        	
        	Map colMap = new HashMap();
            Map settingMap = new HashMap();

            settingMap.put("Admin Type", "attribute_FYPLMNetUsage");
            settingMap.put("Column Type", "programHTMLOutput");
            // settingMap.put("Group Header", "emxProduct.Table.Selection");

            settingMap.put("Registered Suite", "Framework");
            settingMap.put("function", "getNetUsageColumn");
            settingMap.put("program", "fyplmBBXHQD");
            if(modeStr==null || modeStr.isEmpty()) {
                    settingMap.put("Editable", "true");
                    settingMap.put("Update Function", "updateNetUsage");
                    settingMap.put("Update Program", "fyplmBBXHQD");
                    settingMap.put("Input Type", "textbox");
                    settingMap.put("Field Type", "attribute");
                    settingMap.put("Group Header", sHeader);
                    
            }
            colMap.put("topPartId", sPartId);
            colMap.put("settings", settingMap);
            colMap.put("name", sPartName+"_Net");
            colMap.put("label", "emxFramework.Attribute.FYPLM_Net_Usage");
            columnMapList.add(colMap);

        	Map colMap2 = new HashMap();
            Map settingMap2 = new HashMap();

            settingMap2.put("Admin Type", "attribute_FYPLMGrossUsage");
            settingMap2.put("Column Type", "programHTMLOutput");
            // settingMap2.put("Group Header", "emxProduct.Table.Selection");

            settingMap2.put("Registered Suite", "Framework");
            settingMap2.put("function", "getGrossUsageColumn");
            settingMap2.put("program", "fyplmBBXHQD");
            if(modeStr==null || modeStr.isEmpty()) {
                    settingMap2.put("Editable", "true");
                    settingMap2.put("Update Function", "updateGrossUsage");
                    settingMap2.put("Update Program", "fyplmBBXHQD");
                    settingMap2.put("Input Type", "textbox");
                    settingMap2.put("Field Type", "attribute");
                    settingMap2.put("Group Header", sHeader);
            }
            colMap2.put("topPartId", sPartId);
            colMap2.put("settings", settingMap2);
            colMap2.put("name", sPartName+"_Gross");
            colMap2.put("label", "emxFramework.Attribute.FYPLM_Gross_Usage");
            columnMapList.add(colMap2);
        }
        return columnMapList;
    }
    
    public void createTopPartPostProcess(Context context, String[] args) throws Exception{

		HashMap programMap = (HashMap) JPO.unpackArgs(args);
		HashMap requestMap = (HashMap) programMap.get("requestMap");
		HashMap paramMap = (HashMap) programMap.get("paramMap");
		String parentId = (String) requestMap.get("parentOID");
		String sObjectId = (String) paramMap.get("objectId");

		DomainObject objTempPart = DomainObject.newInstance(context,sObjectId);
		objTempPart.addFromObject(context, 
				new RelationshipType(RELATIONSHIP_FYPLM_BBXHQD_TOPPART), parentId);
    }
    
    public void createTempPartPostProcess(Context context, String[] args) throws Exception{

		HashMap programMap = (HashMap) JPO.unpackArgs(args);
		HashMap requestMap = (HashMap) programMap.get("requestMap");
		HashMap paramMap = (HashMap) programMap.get("paramMap");
		String parentId = (String) requestMap.get("parentOID");
		String sObjectId = (String) paramMap.get("objectId");

		DomainObject objTempPart = DomainObject.newInstance(context,sObjectId);
		objTempPart.addFromObject(context, 
				new RelationshipType(RELATIONSHIP_FYPLM_BBXHQD_CLASSBMATERIALPART), parentId);
    }
    
    
    public Boolean updateGrossUsage(Context context, String[] args)
            throws Exception {

    	   HashMap programMap = (HashMap) JPO.unpackArgs(args);
    	   HashMap paramMap = (HashMap) programMap.get("paramMap");
           HashMap request = (HashMap) programMap.get("requestMap");

            Map colMap = (HashMap) programMap.get("columnMap");
            Map settings = (HashMap) colMap.get("settings");
            
            String strRelId = (String) paramMap.get("relId");
            String topPartId = (String) colMap.get("topPartId");
            String materialID = (String) paramMap.get("objectId");
            
            DomainObject objMat = DomainObject.newInstance(context,materialID);
            
            MapList mlNetUsages =
     			   objMat.getRelatedObjects (context, 
     			   RELATIONSHIP_FYPLM_BBTOPPART_CLASSBMATERIALPART,
     			   TYPE_FYPLM_BB_TOP_PART,null, 
     			   new StringList(SELECT_RELATIONSHIP_ID),
     			   true, false, (short)1,  
     			   new StringBuilder("id==").append(topPartId).toString(), 
     			   null, 0);
           DomainRelationship rel = new DomainRelationship();
           
     	   if (mlNetUsages ==null || mlNetUsages.size()== 0) {
     		  rel = objMat.addFromObject(context, 
        			new RelationshipType(RELATIONSHIP_FYPLM_BBTOPPART_CLASSBMATERIALPART), topPartId);
     	   } else {
     		   rel = new DomainRelationship((String)((Map)mlNetUsages.get(0)).get(SELECT_RELATIONSHIP_ID));
     	   }
     	   

            String strUsageInput = (String) paramMap.get("New Value");
            System.out.println("gross usage set to new value: " + strUsageInput);
            rel.setAttributeValue(context, ATTRIBUTE_FYPLM_GROSS_USAGE, strUsageInput); 
            return new Boolean(true);
    }
    
    public Boolean updateNetUsage(Context context, String[] args)
            throws Exception {

    	   HashMap programMap = (HashMap) JPO.unpackArgs(args);
    	   HashMap paramMap = (HashMap) programMap.get("paramMap");
           HashMap request = (HashMap) programMap.get("requestMap");

            Map colMap = (HashMap) programMap.get("columnMap");
            Map settings = (HashMap) colMap.get("settings");
            
            String strRelId = (String) paramMap.get("relId");
            String topPartId = (String) colMap.get("topPartId");
            String materialID = (String) paramMap.get("objectId");
            
            DomainObject objMat = DomainObject.newInstance(context,materialID);
            
            MapList mlNetUsages =
     			   objMat.getRelatedObjects (context, 
     			   RELATIONSHIP_FYPLM_BBTOPPART_CLASSBMATERIALPART,
     			   TYPE_FYPLM_BB_TOP_PART,null, 
     			   new StringList(SELECT_RELATIONSHIP_ID),
     			   true, false, (short)1,  
     			   new StringBuilder("id==").append(topPartId).toString(), 
     			   null, 0);
           DomainRelationship rel = new DomainRelationship();
           
     	   if (mlNetUsages ==null || mlNetUsages.size()== 0) {
     		  rel = objMat.addFromObject(context, 
        			new RelationshipType(RELATIONSHIP_FYPLM_BBTOPPART_CLASSBMATERIALPART), topPartId);
     	   } else {
     		   rel = new DomainRelationship((String)((Map)mlNetUsages.get(0)).get(SELECT_RELATIONSHIP_ID));
     	   }
     	   

            String strUsageInput = (String) paramMap.get("New Value");
            System.out.println("net usage set to new value: " + strUsageInput);
            rel.setAttributeValue(context, ATTRIBUTE_FYPLM_NET_USAGE, strUsageInput); 
            return new Boolean(true);
    }
    
      public StringList getNetUsageColumn(Context context, String[] args) throws Exception {
    	  StringList lsNetUsage = new StringList();
    	   HashMap programMap = (HashMap) JPO.unpackArgs(args);
           Map colMap = (HashMap) programMap.get("columnMap");
           String sPartId = (String) colMap.get("topPartId");
           MapList lsMaterials = (MapList) programMap.get("objectList");
           for (int i = 0; i < lsMaterials.size(); i++) {
        	   Map mapMaterial = (Map) lsMaterials.get(i);
        	   String sMatId = (String) mapMaterial.get(SELECT_ID);
        	   DomainObject objMat = DomainObject.newInstance(context,sMatId);
        	   String sNetUsage ="";
        	   MapList mlNetUsages =
        			   objMat.getRelatedObjects (context, 
        			   RELATIONSHIP_FYPLM_BBTOPPART_CLASSBMATERIALPART,
        			   TYPE_FYPLM_BB_TOP_PART,null, 
        			   new StringList(SELECT_ATTRIBUTE_FYPLM_NET_USAGE),
        			   true, false, (short)1,  
        			   new StringBuilder("id==").append(sPartId).toString(), 
        			   null, 0);
        	   if (mlNetUsages !=null && mlNetUsages.size()> 0) {
        		   sNetUsage = (String)((Map)mlNetUsages.get(0)).get(SELECT_ATTRIBUTE_FYPLM_NET_USAGE);
        	   }
        	   lsNetUsage.add(sNetUsage);
           }
           return lsNetUsage;
      }
   
      public StringList getGrossUsageColumn(Context context, String[] args) throws Exception {
    	  StringList lsNetUsage = new StringList();
    	   HashMap programMap = (HashMap) JPO.unpackArgs(args);
           Map colMap = (HashMap) programMap.get("columnMap");
           String sPartId = (String) colMap.get("topPartId");
           MapList lsMaterials = (MapList) programMap.get("objectList");
           for (int i = 0; i < lsMaterials.size(); i++) {
        	   Map mapMaterial = (Map) lsMaterials.get(i);
        	   String sMatId = (String) mapMaterial.get(SELECT_ID);
        	   DomainObject objMat = DomainObject.newInstance(context,sMatId);
        	   String sGrossUsage ="";
        	   MapList mlNetUsages =
        			   objMat.getRelatedObjects (context, 
        			   RELATIONSHIP_FYPLM_BBTOPPART_CLASSBMATERIALPART,
        			   TYPE_FYPLM_BB_TOP_PART,null, 
        			   new StringList(SELECT_ATTRIBUTE_FYPLM_GROSS_USAGE),
        			   true, false, (short)1,  
        			   new StringBuilder("id==").append(sPartId).toString(), 
        			   null, 0);
        	   if (mlNetUsages !=null && mlNetUsages.size()> 0) {
        		   sGrossUsage = (String)((Map)mlNetUsages.get(0)).get(SELECT_ATTRIBUTE_FYPLM_GROSS_USAGE);
        	   }
        	   lsNetUsage.add(sGrossUsage);
           }
           return lsNetUsage;
      }
      
    public static void main(){
    	logger.debug(SELECT_ATTRIBUTE_FYPLM_NET_USAGE);
    }
   
   public String getCreateBBXHQDFormValue(Context context, String[] args) throws Exception {
	       logger.info("Entering getCreateBBXHQDFormValue().");
	       String sFieldValue = DomainConstants.EMPTY_STRING;
	       try {
	           Map map = (Map) JPO.unpackArgs(args);
	           Map mRequestMap = (Map) map.get(fyplmConstants_mxJPO.STRING_REQUESTMAP);
	           Map mFieldMap = (Map) map.get(fyplmConstants_mxJPO.STRING_FIELDMAP);
	           String sLPId = (String) mRequestMap.get(fyplmConstants_mxJPO.STRING_OBJECTID);
	           Map mSettings = (Map) mFieldMap.get(fyplmConstants_mxJPO.STRING_SETTINGS);
	           String sExpression = (String) mSettings.get(STRING_FYPLMExpression);
	//           logger.info("sLPId: " + sLPId);
	//           logger.info("sExpression: " + sExpression);
	           if (sLPId != null && !DomainConstants.EMPTY_STRING.equals(sLPId)) {
	               DomainObject objLP = DomainObject.newInstance(context, sLPId);
	               sFieldValue = objLP.getInfo(context, sExpression);
	//               sFieldValue = MqlUtil.mqlCommand(context,
	//                       "print bus " + sLPId
	//                               + " select " + sExpression + " dump");
	               StringBuffer appender = new StringBuffer("");
	               //remove wrong char
	               if (sFieldValue == null) {
	            	   return DomainConstants.EMPTY_STRING;
	               }
	               
	               appender = new StringBuffer(sFieldValue.length());  
	               for (int i = 0; i < sFieldValue.length(); i++) {  
	                   char ch = sFieldValue.charAt(i);  
	                   if ((ch == 0x9) || (ch == 0xA) || (ch == 0xD)  
	                           || ((ch >= 0x20) && (ch <= 0xD7FF))  
	                           || ((ch >= 0xE000) && (ch <= 0xFFFD))  
	                           || ((ch >= 0x10000) && (ch <= 0x10FFFF)))  
	                       appender.append(ch);  
	               } 
	               sFieldValue = appender.toString();
	               //logger.info("sFieldValue: " + sFieldValue);
	           }
	           
	       } catch (Exception e) {
	           logger.error("There is an Exception in getCreateBBXHQDFormValue(): ", e);
	           throw e;
	       }
	       logger.info("Exiting getCreateBBXHQDFormValue().");
	       return sFieldValue;
	   }
   
	public MapList getBBAllPrototypeEquipment(Context context, String[] args) throws Exception {
	    logger.info("Entering getAllBBPrototypeEquipment().");
	    MapList resList = new MapList();
	    StringList objectSelects = new StringList();
	    objectSelects.addElement(SELECT_ID);
	    StringList relList = new StringList();
	    relList.addElement(SELECT_RELATIONSHIP_ID);
//	    objectSelects.addElement(SELECT_NAME);
	    try {
	    	HashMap programMap = (HashMap) JPO.unpackArgs(args);
	    	String sBBXHQDId = (String)programMap.get("parentOID");

	    	DomainObject objBBXHQD = DomainObject.newInstance(context,sBBXHQDId);
	    	//System.out.println("clxhqdOID:::::::::"+clxhqdOID);
	    	String objectWhere = "";
	    	MapList pdeList = objBBXHQD.getRelatedObjects(context, fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Prototype_Dedicated_Equipment, fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Prototype_Dedicated_Equipment, objectSelects, relList ,false, true, (short)1, objectWhere, null, 0);
	    	resList.addAll(pdeList);
	    	MapList pdgList = objBBXHQD.getRelatedObjects(context, fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Prototype_Dedicated_Gauge, fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Prototype_Dedicated_Gauge, objectSelects, relList ,false, true, (short)1, objectWhere, null, 0);
	    	resList.addAll(pdgList);
	    	MapList pdmList = objBBXHQD.getRelatedObjects(context, fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Prototype_Dedicated_Mould, fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Prototype_Dedicated_Mould, objectSelects, relList ,false, true, (short)1, objectWhere, null, 0);
	    	resList.addAll(pdmList);
	    	MapList pceList = objBBXHQD.getRelatedObjects(context, fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Prototype_Common_Equipment, fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Prototype_Common_Equipment, objectSelects, relList ,false, true, (short)1, objectWhere, null, 0);
	    	resList.addAll(pceList);
	    	MapList pcgList = objBBXHQD.getRelatedObjects(context, fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Prototype_Common_Gauge, fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Prototype_Common_Gauge, objectSelects, relList ,false, true, (short)1, objectWhere, null, 0);
	    	resList.addAll(pcgList);
	    	MapList pcmList = objBBXHQD.getRelatedObjects(context, fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Prototype_Common_Mould, fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Prototype_Common_Mould, objectSelects, relList ,false, true, (short)1, objectWhere, null, 0);
	    	resList.addAll(pcmList);
	    	//System.out.println("pceList::::::::::::"+pceList);
	    } catch (FrameworkException e) {
	        logger.error("There is an Exception in getAllBBPrototypeEquipment(): ", e);
	        throw e;
	    }
	    logger.info("Exiting getAllBBPrototypeEquipment().");
	    return resList;
	}
	
    public void createBBAllEquipment(Context context, String[] args)
            throws Exception {
        try {
            logger.debug("Entering createBBAllEquipment");
            ContextUtil.startTransaction(context, true);
            HashMap programMap = (HashMap) JPO.unpackArgs(args);
            HashMap requestMap = (HashMap) programMap.get("requestMap");
            HashMap paramMap = (HashMap) programMap.get("paramMap");
            String parentId = (String) requestMap.get("parentOID");
            String objId = (String) paramMap.get("objectId");
            DomainObject objEquipment = DomainObject.newInstance(context,objId);
            String en = (String) requestMap.get("FYPLM Equipment Name");
            objEquipment.setAttributeValue(context, "FYPLM Equipment Name", en);
            String strType = objEquipment.getInfo(context, SELECT_TYPE);
            String relBBXHQDEquipment = "";
            String relEquipmentTopPart = "";
            if(strType.equals(fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Prototype_Common_Mould)){
            	relBBXHQDEquipment = fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Prototype_Common_Mould;
            	relEquipmentTopPart = fyplmClxhqdConstants_mxJPO.RELATIONSHIP_FYPLM_Prototype_Common_Mould_Top_Part;
            }else if(strType.equals(fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Prototype_Common_Gauge)){
            	relBBXHQDEquipment = fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Prototype_Common_Gauge;
            	relEquipmentTopPart = fyplmClxhqdConstants_mxJPO.RELATIONSHIP_FYPLM_Prototype_Common_Gauge_Top_Part;
            }else if(strType.equals(fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Prototype_Common_Equipment)){
            	relBBXHQDEquipment = fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Prototype_Common_Equipment;
            	relEquipmentTopPart = fyplmClxhqdConstants_mxJPO.RELATIONSHIP_FYPLM_Prototype_Common_Equipment_Top_Part;
            }else if(strType.equals(fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Prototype_Dedicated_Mould)){
            	relBBXHQDEquipment = fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Prototype_Dedicated_Mould;
            	relEquipmentTopPart = fyplmClxhqdConstants_mxJPO.RELATIONSHIP_FYPLM_Prototype_Dedicated_Mould_Top_Part;
            }else if(strType.equals(fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Prototype_Dedicated_Gauge)){
            	relBBXHQDEquipment = fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Prototype_Dedicated_Gauge;
            	relEquipmentTopPart = fyplmClxhqdConstants_mxJPO.RELATIONSHIP_FYPLM_Prototype_Dedicated_Gauge_Top_Part;
            }else if(strType.equals(fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Prototype_Dedicated_Equipment)){
            	relBBXHQDEquipment = fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Prototype_Dedicated_Equipment;
            	relEquipmentTopPart = fyplmClxhqdConstants_mxJPO.RELATIONSHIP_FYPLM_Prototype_Dedicated_Equipment_Top_Part;
            }else if(strType.equals(fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Official_Common_Mould)){
            	relBBXHQDEquipment = fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Official_Common_Mould;
            	relEquipmentTopPart = fyplmClxhqdConstants_mxJPO.RELATIONSHIP_FYPLM_Official_Common_Mould_Top_Part;
            }else if(strType.equals(fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Official_Common_Gauge)){
            	relBBXHQDEquipment = fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Official_Common_Gauge;
            	relEquipmentTopPart = fyplmClxhqdConstants_mxJPO.RELATIONSHIP_FYPLM_Official_Common_Gauge_Top_Part;
            }else if(strType.equals(fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Official_Common_Equipment)){
            	relBBXHQDEquipment = fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Official_Common_Equipment;
            	relEquipmentTopPart = fyplmClxhqdConstants_mxJPO.RELATIONSHIP_FYPLM_Official_Common_Equipment_Top_Part;
            }else if(strType.equals(fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Official_Dedicated_Mould)){
            	relBBXHQDEquipment = fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Official_Dedicated_Mould;
            	relEquipmentTopPart = fyplmClxhqdConstants_mxJPO.RELATIONSHIP_FYPLM_Official_Dedicated_Mould_Top_Part;
            }else if(strType.equals(fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Official_Dedicated_Gauge)){
            	relBBXHQDEquipment = fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Official_Dedicated_Gauge;
            	relEquipmentTopPart = fyplmClxhqdConstants_mxJPO.RELATIONSHIP_FYPLM_Official_Dedicated_Gauge_Top_Part;
            }else if(strType.equals(fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Official_Dedicated_Equipment)){
            	relBBXHQDEquipment = fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Official_Dedicated_Equipment;
            	relEquipmentTopPart = fyplmClxhqdConstants_mxJPO.RELATIONSHIP_FYPLM_Official_Dedicated_Equipment_Top_Part;
            }
            if(strType.indexOf("Common")>=0){
            	DomainObject objBBXHQD = DomainObject.newInstance(context,parentId);
            	StringList objectSelects = new StringList();
        	    objectSelects.addElement(SELECT_ID);
        	    StringList relList = new StringList();
        	    relList.addElement(SELECT_RELATIONSHIP_ID);
        	    String objectWhere = "";
            	MapList mapList = objBBXHQD.getRelatedObjects(context,fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Top_Part, fyplmBBXHQDConstants_mxJPO.TYPE_FYPLM_BB_Top_Part, objectSelects, relList ,false, true, (short)1, objectWhere, null, 0);
  				for (Iterator iter = mapList.iterator(); iter.hasNext();) {
      				Map topPart = (Map) iter.next();
      				String topPartId = (String)topPart.get(SELECT_ID);
      				DomainRelationship.connect(context, objId, relEquipmentTopPart, topPartId, true);
  				}
            }else{
            	for(int i=0;i<=100;i++){
    				String relatedConfig = (String) requestMap.get("relatedConfig"+i);
    				if(relatedConfig!=null && !relatedConfig.equals("")){
    					//System.out.println("process---------========="+process);
    					DomainRelationship.connect(context, objId, relEquipmentTopPart, relatedConfig, true);
    				}
    			}
            }
            //System.out.println("pceId========="+pceId);
            DomainRelationship.connect(context, parentId,relBBXHQDEquipment, objId, true);
            ContextUtil.commitTransaction(context);
        } catch (Exception e) {
        	ContextUtil.abortTransaction(context);
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            logger.debug("Exiting createBBAllEquipment");
        }
    }
    
    public void createBBCertificate(Context context, String[] args)
            throws Exception {
        try {
            logger.debug("Entering createBBCertificate");
            ContextUtil.startTransaction(context, true);
            HashMap programMap = (HashMap) JPO.unpackArgs(args);
            HashMap requestMap = (HashMap) programMap.get("requestMap");
            HashMap paramMap = (HashMap) programMap.get("paramMap");
            String parentId = (String) requestMap.get("parentOID");
            String objId = (String) paramMap.get("objectId");
            DomainObject objEquipment = DomainObject.newInstance(context,objId);
            String cp = (String) requestMap.get("FYPLM Certificate Program");
            objEquipment.setAttributeValue(context, "FYPLM Certificate Program",cp);
            String fe = (String) requestMap.get("FYPLM First Expand");
            objEquipment.setAttributeValue(context, "FYPLM First Expand",fe);
            //System.out.println("odeId========="+odeId);
            DomainRelationship.connect(context, parentId, fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Certificate, objId, true);
            ContextUtil.commitTransaction(context);
        } catch (Exception e) {
        	ContextUtil.abortTransaction(context);
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            logger.debug("Exiting createBBCertificate");
        }
    }
    
    /**
     * create postProcessJPO.
     */
    public void createBBInvestment(Context context, String[] args)
            throws Exception {
        try {
            logger.debug("Entering createBBInvestment");
            HashMap programMap = (HashMap) JPO.unpackArgs(args);
            HashMap requestMap = (HashMap) programMap.get("requestMap");
            HashMap paramMap = (HashMap) programMap.get("paramMap");
            String parentId = (String) requestMap.get("parentOID");
            String objId = (String) paramMap.get("objectId");
            //System.out.println("odeId========="+odeId);
            DomainRelationship.connect(context, parentId, fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Investment, objId, true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            logger.debug("Exiting createBBInvestment");
        }
    }
    
    /**
     * create postProcessJPO.
     */
    public void createBBDevelopmentCosts(Context context, String[] args)
            throws Exception {
        try {
        	ContextUtil.startTransaction(context, true);
            logger.debug("Entering createBBDevelopmentCosts");
            HashMap programMap = (HashMap) JPO.unpackArgs(args);
            HashMap requestMap = (HashMap) programMap.get("requestMap");
            HashMap paramMap = (HashMap) programMap.get("paramMap");
            String parentId = (String) requestMap.get("parentOID");
            String objId = (String) paramMap.get("objectId");
            //System.out.println("odeId========="+odeId);
            DomainRelationship.connect(context, parentId, fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Development_Costs, objId, true);
            
            ContextUtil.commitTransaction(context);
        } catch (Exception e) {
        	ContextUtil.abortTransaction(context);
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            logger.debug("Exiting createBBDevelopmentCosts");
        }
    }

    
    
    
	public MapList getBBAllOfficialEquipment(Context context, String[] args) throws Exception {
	    logger.info("Entering getBBAllOfficialEquipment().");
	    MapList resList = new MapList();
	    StringList objectSelects = new StringList();
	    objectSelects.addElement(SELECT_ID);
	    StringList relList = new StringList();
	    relList.addElement(SELECT_RELATIONSHIP_ID);
//	    objectSelects.addElement(SELECT_NAME);
	    try {
	    	HashMap programMap = (HashMap) JPO.unpackArgs(args);
	    	String sBBXHQDId = (String)programMap.get("parentOID");

	    	DomainObject objBBXHQD = DomainObject.newInstance(context,sBBXHQDId);
	    	//System.out.println("clxhqdOID:::::::::"+clxhqdOID);
	    	String objectWhere = "";
	    	MapList odeList = objBBXHQD.getRelatedObjects(context, fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Official_Dedicated_Equipment, fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Official_Dedicated_Equipment, objectSelects, relList ,false, true, (short)1, objectWhere, null, 0);
	    	resList.addAll(odeList);
	    	MapList odgList = objBBXHQD.getRelatedObjects(context, fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Official_Dedicated_Gauge, fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Official_Dedicated_Gauge, objectSelects, relList ,false, true, (short)1, objectWhere, null, 0);
	    	resList.addAll(odgList);
	    	MapList odmList = objBBXHQD.getRelatedObjects(context, fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Official_Dedicated_Mould, fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Official_Dedicated_Mould, objectSelects, relList ,false, true, (short)1, objectWhere, null, 0);
	    	resList.addAll(odmList);
	    	MapList oceList = objBBXHQD.getRelatedObjects(context,fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Official_Common_Equipment, fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Official_Common_Equipment, objectSelects, relList ,false, true, (short)1, objectWhere, null, 0);
	    	resList.addAll(oceList);
	    	MapList ocgList = objBBXHQD.getRelatedObjects(context,fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Official_Common_Gauge, fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Official_Common_Gauge, objectSelects, relList ,false, true, (short)1, objectWhere, null, 0);
	    	resList.addAll(ocgList);
	    	MapList ocmList = objBBXHQD.getRelatedObjects(context,fyplmBBXHQDConstants_mxJPO.RELATIONSHIP_FYPLM_BBXHQD_Official_Common_Mould, fyplmClxhqdConstants_mxJPO.TYPE_FYPLM_Official_Common_Mould, objectSelects, relList ,false, true, (short)1, objectWhere, null, 0);
	    	resList.addAll(ocmList);
	    	
	    	//System.out.println("pceList::::::::::::"+pceList);
	    } catch (FrameworkException e) {
	        logger.error("There is an Exception in getBBAllOfficialEquipment(): ", e);
	        throw e;
	    }
	    logger.info("Exiting getBBAllOfficialEquipment().");
	    return resList;
	}





	public MapList getBBXHQDRevisionList(Context context, String[] args) throws Exception {
	    logger.info("Entering getBBXHQDRevisionList().");
	    MapList mapList = new MapList();
	    StringList slObj = new StringList();
	    slObj.addElement(SELECT_ID);
	    slObj.addElement(SELECT_NAME);
	    try {
	    	HashMap programMap = (HashMap) JPO.unpackArgs(args);
	    	String bbxhqdId = (String)programMap.get("parentOID");
	    	DomainObject objBBXHQD = DomainObject.newInstance(context, bbxhqdId);
	    	String sName = objBBXHQD.getInfo(context, SELECT_NAME);
	    	String sWhere = "name=='" + sName + "'";
	    	
	       mapList = findObjects(context,
	               fyplmBBXHQDConstants_mxJPO.TYPE_FYPLM_BBXHQD,
	               context.getVault().getName(),
	               sWhere,
	               slObj);
	       
	       mapList.sort("name", "descending", "String");
	    } catch (FrameworkException e) {
	        logger.error("There is an Exception in getBBXHQDRevisionList(): ", e);
	        throw e;
	    }
	    logger.info("Exiting getBBXHQDRevisionList().");
	    return mapList;
	}


	public void setBBXHQDWin(Context context, String[] args) throws Exception {
	   StringList slObj = new StringList();
	   slObj.addElement(SELECT_ID);
	   slObj.addElement(SELECT_TYPE);
	   slObj.addElement(SELECT_NAME);
	
	   StringList slRel = new StringList();
	   slRel.addElement(SELECT_RELATIONSHIP_ID);
	   String bbxhqdId = args[0];
		try {
			logger.debug("Entering setBBXHQDWin");
			
			DomainObject objBBXHQD = DomainObject.newInstance(context, bbxhqdId);
	    	String bbxqdName = objBBXHQD.getInfo(context, DomainObject.SELECT_NAME);
	    	String sSelectedWin = objBBXHQD.getInfo(context, fyplmBBXHQDConstants_mxJPO.ATTRIBUTE_FYPLM_BBXHQD_WIN);
	       String sWhere = "name=='" + bbxqdName + "'";
	       
	       MapList mapList = findObjects(context,
	               fyplmBBXHQDConstants_mxJPO.TYPE_FYPLM_BBXHQD,
	               context.getVault().getName(),
	               sWhere,
	               slObj);
	       for (Iterator iter = mapList.iterator(); iter.hasNext();) {
	           Map map = (Map) iter.next();
	           String objCurrentId = (String)map.get(SELECT_ID);
	           DomainObject objCurrentBBXHQD = DomainObject.newInstance(context, objCurrentId);
	           String sCurrentWin = objCurrentBBXHQD.getInfo(context, fyplmBBXHQDConstants_mxJPO.ATTRIBUTE_FYPLM_BBXHQD_WIN);
	           
	           if ( objCurrentId.equals(bbxhqdId) && !"Yes".equals(sCurrentWin)) {
	        	   objCurrentBBXHQD.setAttributeValue(context, fyplmBBXHQDConstants_mxJPO.ATTRIBUTE_FYPLM_BBXHQD_WIN, "Yes");
	           } else {
	        	   objCurrentBBXHQD.setAttributeValue(context, fyplmBBXHQDConstants_mxJPO.ATTRIBUTE_FYPLM_BBXHQD_WIN, "-");
	           }
	       }
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			logger.debug("Exiting setBBXHQDWin");
		}
	}


	public int triggerBBXHQDCreateAction(Context context, String[] args) throws Exception {
	        logger.info("Entering triggerBBXHQDCreateAction()");
	        int i = 0;
	        String bbxhqdId = args[0];
	        boolean blHasPush = false;
	        try {
	            String sCurrentUser = context.getUser();
	            DomainObject objBBXHQD = DomainObject.newInstance(context);
	            objBBXHQD.setId(bbxhqdId);
	            String sOwner = objBBXHQD.getInfo(context,SELECT_OWNER);
	            StringList slObj = new StringList();
	            slObj.addElement(SELECT_ID);
	            slObj.addElement(SELECT_TYPE);
	            slObj.addElement(SELECT_NAME);
	 
	            //logger.info("o is slObj ------------------" + slObj);
	
		    	
	            ContextUtil.pushContext(context);
	            blHasPush = true;
	            fyplmRoute_mxJPO.setRouteLog(context, bbxhqdId, "state_Review");
	            
	            StringList strList = new StringList();
	//            String firstChecker = objClxhqd.getAttributeValue(context, "FYPLM Clxhqd First Checker");
	//            if(firstChecker==null || firstChecker.equals("")){
	//            	String message = i18nNow.getI18nString(
	//            			"emxEngineeringCentral.Message.NoClxhqdFirstChecker",
	//	                    "emxEngineeringCentralStringResource",
	//	                    context.getSession().getLanguage());
	//				throw new FrameworkException(message);
	//            }
	            strList.addElement(sOwner);
	            strList.addElement(sCurrentUser);
	            MapList mlMembers = getMembers(context, objBBXHQD, strList);
	            fyplmRoute_mxJPO.createRoute(context,
	                    mlMembers,
	                    bbxhqdId,
	                    "state_Review",
	                    "policy_FYPLMBBXHQD",
	                    "Review BBXHQD",
	                    sCurrentUser,
	                    "Promote Connected Object",
	                    "All");
	            fyplmRoute_mxJPO.startRoute(context, bbxhqdId, "state_Review");
	        } catch (Exception e) {
	            logger.error("There is an exception in triggerBBXHQDCreateAction(): ", e);
	            throw e;
	        } finally {
	            if (blHasPush) {
	                try {
	                    ContextUtil.popContext(context);
	                } catch (Exception e2) {
	                    logger.error("There is an exception in triggerBBXHQDCreateAction() pop: ", e2);
	                    throw e2;
	                }
	                
	            }
	        }
	        logger.info("Exiting triggerBBXHQDCreateAction()");
	        return i;
	    }

	public void createBBProductPackaging(Context context, String[] args) throws Exception {
		try {
			ContextUtil.startTransaction(context, true);
		    logger.debug("Entering createBBProductPackaging");
		    HashMap programMap = (HashMap) JPO.unpackArgs(args);
		    HashMap requestMap = (HashMap) programMap.get("requestMap");
		    HashMap paramMap = (HashMap) programMap.get("paramMap");
		    String parentId = (String) requestMap.get("parentOID");
		    String objId = (String) paramMap.get("objectId");

		    String sLayerNo = (String)requestMap.get("FYPLM Layer No");
		    String sNumPerLayer = (String) requestMap.get("FYPLM Packing Number Per Layer");
		    String sSingleTotoalWeight = (String) requestMap.get("FYPLM Single Total Weight");
		    
		    double dLayerNo = sLayerNo!=null&&!"".equals(sLayerNo)?Double.parseDouble(sLayerNo):0.00;
		    double dNumPerLayer = sNumPerLayer!=null&&!"".equals(sNumPerLayer)?Double.parseDouble(sNumPerLayer):0.00;
		    double dSingleTotoalWeight = sSingleTotoalWeight!=null&&!"".equals(sSingleTotoalWeight)?Double.parseDouble(sSingleTotoalWeight):0.00;
		    
		    double dNumPerBox = dLayerNo*dNumPerLayer;
		    double dPackNetWeight = dNumPerBox*dSingleTotoalWeight;
		    
		    DecimalFormat df = new DecimalFormat("######0.00");
		    DomainObject objPack = DomainObject.newInstance(context, objId);

		    objPack.setAttributeValue(context, "FYPLM Packing Number Per Box", df.format(dNumPerBox));
		    objPack.setAttributeValue(context, "FYPLM Packing Net Weight", df.format(dPackNetWeight));
		    
		    ContextUtil.commitTransaction(context);
		} catch (Exception e) {
			ContextUtil.abortTransaction(context);
		    logger.error(e.getMessage(), e);
		    throw e;
		} finally {
		    logger.debug("Exiting createProductPackaging");
		}
	}
	
	

	public String getQuotationBasedRangeValues(Context context,String[] args) throws Exception{
			String str = "";
			try {
				 logger.debug("Entering getQuotationBasedRangeValues");
				 Map map = (Map) JPO.unpackArgs(args);
				 Map mRequestMap = (Map) map.get(fyplmConstants_mxJPO.STRING_REQUESTMAP);
				 
				 String sPage = (String) mRequestMap.get("page");
				 String[] qbv = null;
				 
				 if (sPage != null && "editdetails".equals(sPage)) {
	            	 String sBBXHQDId = (String) mRequestMap.get("parentOID");
	            	 if (sBBXHQDId != null && !"".equals(sBBXHQDId)) {
	            		 DomainObject objBBXHQD = DomainObject.newInstance(context, sBBXHQDId);
	            		 String sQuotBasedValue = objBBXHQD.getAttributeValue(context, "FYPLM Quotation Based Value");
	            		 if (sQuotBasedValue!=null && !"".equals(sQuotBasedValue)) {
	            			 qbv = sQuotBasedValue.split(",");
	            		 }
	            	 }
				 }
				 
				 
				 String sLanguage = context.getSession().getLanguage();
		         AttributeType atrTaskConstraint = new AttributeType("FYPLM Quotation Based");
		         atrTaskConstraint.open(context);
		         StringList strList = atrTaskConstraint.getChoices(context);
		         strList.sort();
		         atrTaskConstraint.close(context);
	
		         str += "<table border=\"0\"><tbody>";
		         for(int i=0; i<strList.size();i++){
		             String key = (String)strList.get(i);
		             String value = i18nNow.getRangeI18NString("FYPLM Quotation Based", key, sLanguage);
		             if (qbv != null && Arrays.asList(qbv).contains(key)) {
		            	 str += "<tr><td><input type=\"checkbox\" checked=\"checked\" name=\"quotationBased";
		             } else {
		            	 str += "<tr><td><input type=\"checkbox\" name=\"quotationBased";
		             }
		             str += (i+1);
		             str += "\" value=\"";
		             str += key;
		             str += "\"/></td><td>";
		             str += value;
		             str += "</td></tr>";
		         } 			 
		         str += "</tbody></table>";
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw e;
			} finally {
				logger.debug("Exiting getQuotationBasedRangeValues");
			}
	       return str;
	   }


	public void copyCreateBBXHQDPostProcess(Context context, String[] args) throws Exception {
		//ContextUtil.startTransaction(context, true);
		logger.debug("Entering copyCreateBBXHQDPostProcess");
		HashMap programMap = (HashMap) JPO.unpackArgs(args);
		//System.out.println("programMap========="+programMap);
	    HashMap requestMap = (HashMap) programMap.get("requestMap");
	   System.out.println("requestMap========="+requestMap);
		HashMap paramMap = (HashMap) programMap.get("paramMap");
		System.out.println("paramMap========="+paramMap);
		String clxhqdOldId = (String) paramMap.get("objectId");
		System.out.println("objectId========="+clxhqdOldId);
		
		
	}

	public String getBBPackingType(Context context,String[] args) throws Exception{
    	//getChoices context.getat
    	String packingType = "";
		try {
			 logger.debug("Entering getBBPackingType");
	         Map map = (Map) JPO.unpackArgs(args);
	         Map mRequestMap = (Map) map.get(fyplmConstants_mxJPO.STRING_REQUESTMAP);
	         Map mFieldMap = (Map) map.get(fyplmConstants_mxJPO.STRING_FIELDMAP);
	         String sBBXHQDId = (String) mRequestMap.get(fyplmConstants_mxJPO.STRING_OBJECTID);
	         
	         if (sBBXHQDId != null && !"".equals(sBBXHQDId)) {
				 DomainObject objBBXHQD = DomainObject.newInstance(context, sBBXHQDId);
				 String val = objBBXHQD.getInfo(context, "to[FYPLM LP BBXHQD].from.to[FYPLM LP BM].from.attribute[FYPLM Packing Type].value");
				 String sLanguage = context.getSession().getLanguage();
	             packingType = i18nNow.getRangeI18NString("FYPLM Packing Type", val, sLanguage);
	         }

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			logger.debug("Exiting getBBPackingType");
		}
        return packingType;
    }
	
	public String getBBPackingTypeModel(Context context,String[] args) throws Exception{
    	//getChoices context.getat
    	String str = "";
		try {
			 logger.debug("Entering getBBPackingTypeModel");
			 HashMap programMap = (HashMap) JPO.unpackArgs(args);
			 //System.out.println("programMap=="+programMap);
			 HashMap fieldMap = (HashMap) programMap.get("fieldMap");
			 HashMap requestMap = (HashMap) programMap.get("requestMap");
			 String attrName = (String) fieldMap.get("name");
			 String sBBXHQDId = (String) requestMap.get("objectId");
			 DomainObject objBBXHQD = DomainObject.newInstance(context, sBBXHQDId);
			 String val = objBBXHQD.getInfo(context, "to[FYPLM LP BBXHQD].from.to[FYPLM LP BM].from.attribute[FYPLM Packing Type].value");
			 if(val.equals(fyplmClxhqdConstants_mxJPO.Tx_Code)){
				 str = "--";
			 }else{
				 String sLanguage = context.getSession().getLanguage();
		         AttributeType atrTaskConstraint = new AttributeType(attrName);
		         atrTaskConstraint.open(context);
		         StringList strList = atrTaskConstraint.getChoices(context);
		         strList.sort();
		         atrTaskConstraint.close(context);
		         //System.out.println("strList========"+strList);

	        	 str += "<select name='"+attrName+"' id='"+attrName+"' >";
	        	 //str += "<option value=''></option>";
		         for(int i=0; i<strList.size();i++){
		             String key = (String)strList.get(i);
		             String value = i18nNow.getRangeI18NString(attrName, key, sLanguage);
		             //System.out.println("val========"+val.equals(fyplmClxhqdConstants_mxJPO.Zx_Code));
		             //System.out.println("key========"+key.indexOf("TWZX"));
		             if(val.equals(fyplmClxhqdConstants_mxJPO.Zx_Code) && key.indexOf("TWZX")>=0){
		            	 str += "<option value='"+key+"'>"+value+"</option>";
					 }else if(val.equals(fyplmClxhqdConstants_mxJPO.Mx_Code) && key.indexOf("MX")>=0){
						 str += "<option value='"+key+"'>"+value+"</option>";
					 }
		         }
	  			 str += "</select>";
			 }
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			logger.debug("Exiting getBBPackingTypeModel");
		}
        return str;
    }



public MapList getMembers(Context context, DomainObject obj, StringList slUserName) throws FrameworkException {
	    logger.info("Entering getMembers()");
	    MapList mlMembers = new MapList();
	    try {
	    	String sPersonId1 = PersonUtil.getPersonObjectID(context, (String)slUserName.get(0));
	    	Map mPM1 = new HashMap();
	    	mPM1.put(DomainConstants.SELECT_ID, sPersonId1);
	    	mPM1.put("Route Instructions", "Review BBXHQD");
	    	mPM1.put("Route Action", "Approve");
	    	mPM1.put("Title", "Review BBXHQD");
	    	mPM1.put("Allow Delegation", "True");
	    	mPM1.put("Route Sequence", "1");
	    	mPM1.put("Parallel Node Procession Rule", "All");
	    	mlMembers.add(mPM1);
	        MapList mlApproveLines = fyplmApprovalLine_mxJPO.getDirectManager(context, (String)slUserName.get(1), 1);
	        int iSize = mlApproveLines.size();
	        for (int i = 0; i < iSize; i++) {
	            Map mApproveLine = (Map) mlApproveLines.get(i);
	            String sPersonId = (String) mApproveLine.get(DomainConstants.SELECT_ID);
	            //String sLevel = (String) mApproveLine.get(fyplmConstants_mxJPO.STRING_LEVEL);
	            Map mPM = new HashMap();
	            mPM.put(DomainConstants.SELECT_ID, sPersonId);
	            mPM.put("Route Instructions", "Review BBXHQD");
	            mPM.put("Route Action", "Approve");
	            mPM.put("Title", "Review BBXHQD");
	            mPM.put("Allow Delegation", "True");
	            mPM.put("Route Sequence", String.valueOf(i+2));
	            mPM.put("Parallel Node Procession Rule", "All");
	            mlMembers.add(mPM);
	        }
	    } catch (FrameworkException e) {
	        logger.error("There is an exception in getMembers(): ", e);
	        throw e;
	    }
	    logger.info("Exiting getMembers()");
	    return mlMembers;
	}


public void updateQuotationBasedField(Context context, String[] args) throws Exception {
		try {
			logger.debug("Entering updateQuotationBasedValue");
			HashMap programMap = (HashMap) JPO.unpackArgs(args);
            HashMap requestMap = (HashMap) programMap.get("requestMap");
            HashMap paramMap = (HashMap) programMap.get("paramMap");
            HashMap fieldMap = (HashMap) programMap.get("fieldMap");
            HashMap settings = (HashMap) fieldMap.get("settings");

            String attrQuotBasedValue = "";
			for(int i=1;i<10;i++){
				String[] qbTemp = (String[]) requestMap.get("quotationBased"+i);
				if(qbTemp!=null && qbTemp.length>0){
					if(attrQuotBasedValue.equals("")){
						attrQuotBasedValue += qbTemp[0];
					}else{
						attrQuotBasedValue += "," + qbTemp[0];
					}
				}
			}
			
			if ("".equals(attrQuotBasedValue)) {
            	String message = i18nNow.getI18nString("emxFramework.Message.InputQuotationBased","emxFrameworkStringResource",context.getSession().getLanguage());
				throw new MatrixException(message);
			}
			
			String objectId = (String) paramMap.get(fyplmConstants_mxJPO.STRING_OBJECTID);
			DomainObject objBBXHQD = DomainObject.newInstance(context, objectId);
			objBBXHQD.setAttributeValue(context, "FYPLM Quotation Based Value", attrQuotBasedValue);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			logger.debug("Exiting updateQuotationBasedValue");
		}
   }
   
}
