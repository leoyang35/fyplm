import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
    
    public static final String ATTRIBUTE_FYPLM_MASTER_DEVICE = PropertyUtil.getSchemaProperty("attribute_FYPLMMasterDevice");
    public static final String SELECT_ATTRIBUTE_FYPLM_MASTER_DEVICE = new StringBuilder(ABEGIN).append(ATTRIBUTE_FYPLM_MASTER_DEVICE).append(AEND).toString();
    
    public static final String ATTRIBUTE_FYPLM_FIXED_ASSETS_DEPRECIATION_CODE = PropertyUtil.getSchemaProperty("attribute_FYPLMFixedAssetsDepreciationCode");
    public static final String SELECT_ATTRIBUTE_FYPLM_FIXED_ASSETS_DEPRECIATION_CODE = new StringBuilder(ABEGIN).append(ATTRIBUTE_FYPLM_FIXED_ASSETS_DEPRECIATION_CODE).append(AEND).toString();
    
    public static final String RELATIONSHIP_FYPLM_BBXHQD_TOPPART = PropertyUtil.getSchemaProperty("relationship_FYPLMBBXHQDTopPart");
    public static final String RELATIONSHIP_FYPLM_BBTOPPART_CLASSBMATERIALPART = PropertyUtil.getSchemaProperty("relationship_FYPLMBBTopPartClassBMaterialPart");
    public static final String RELATIONSHIP_FYPLM_BBXHQD_CLASSBMATERIALPART = PropertyUtil.getSchemaProperty("relationship_FYPLMBBXHQDClassBMaterialPart");
    public static final String RELATIONSHIP_FYPLM_BBXHQD_TECHNOLOGICAL_PROCESS = PropertyUtil.getSchemaProperty("relationship_FYPLMBBXHQDTechnologicalProcess");
    public static final String RELATIONSHIP_FYPLM_TECHNOLOGICAL_PROCESS_BB_STEP = PropertyUtil.getSchemaProperty("relationship_FYPLMTechnologicalProcessBBStep");
    public static final String RELATIONSHIP_FYPLM_LP_BBXHQD = PropertyUtil.getSchemaProperty("relationship_FYPLMLPBBXHQD");
    
    public static final String TYPE_FYPLM_BBXHQD = PropertyUtil.getSchemaProperty("type_FYPLMBBXHQD");
    public static final String TYPE_FYPLM_BB_TOP_PART = PropertyUtil.getSchemaProperty("type_FYPLMBBTopPart");
    public static final String TYPE_FYPLM_BB_STEP = PropertyUtil.getSchemaProperty("type_FYPLMBBStep");
    public static final String TYPE_FYPLM_TECHNOLOGICAL_PROCESS = PropertyUtil.getSchemaProperty("type_FYPLMTechnologicalProcess");
  
    
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
         
         System.out.println("processId=================================="+processId);
         System.out.println("clxhqdId=================================="+BBId);
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
	        System.out.println("lpMap.size()=================================="+lpMap);
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
	    	  
            System.out.println(sProductionPlace);
	    }

        String sLanguage = context.getSession().getLanguage();

         AttributeType atrProductionType = new AttributeType(ATTRIBUTE_FYPLM_PRODUCTION_TYPE);
         atrProductionType.open(context);
         StringList strList = atrProductionType.getChoices(context);
         System.out.println("ATTRIBUTE_FYPLM_MASTER_DEVICE========"+ATTRIBUTE_FYPLM_PRODUCTION_TYPE);
         System.out.println("strList========"+strList);
         strList.sort();
         atrProductionType.close(context);
         for(int i=0; i<strList.size();i++){
             String key = (String)strList.get(i);
             
             System.out.println(key);
             
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
            //settingMap.put("Style Program", "cqacBOMProduct");)
            //settingMap.put("Style Function", "getdynamicbackground");)
            //settingMap.put("Column Style", "center-align");)
            //settingMap.put("Width", customWidth);
            //settingMap.put("Export", "true");)
            if(modeStr==null || modeStr.isEmpty()) {
                    settingMap.put("Editable", "true");
                    //settingMap.put("Edit Access Program", "cqacBOMProdu");
                    //settingMap.put("Edit Access Function", "editacess");
                    //settingMap.put("On Change Handler", "validatePELValue");
                    //settingMap.put("Range Function","getRangeValuesForDynamicColumn");
                    //settingMap.put("Range Program", "cqacBOMProduct");
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
            //settingMap2.put("Style Program", "cqacBOMProduct");)
            //settingMap2.put("Style Function", "getdynamicbackground");)
            //settingMap2.put("Column Style", "center-align");)
            //settingMap2.put("Width", customWidth);
            //settingMap2.put("Export", "true");)
            if(modeStr==null || modeStr.isEmpty()) {
                    settingMap2.put("Editable", "true");
                    //settingMap2.put("Edit Access Program", "cqacBOMProdu");
                    //settingMap2.put("Edit Access Function", "editacess");
                    //settingMap2.put("On Change Handler", "validatePELValue");
                    //settingMap2.put("Range Function","getRangeValuesForDynamicColumn");
                    //settingMap2.put("Range Program", "cqacBOMProduct");
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
	       logger.info("Entering getBMRName().");
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
	           logger.error("There is an Exception in getBMRName(): ", e);
	           throw e;
	       }
	       logger.info("Exiting getCreateBBXHQDFormValue().");
	       return sFieldValue;
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
	        logger.info("Entering createClxhqd()");
	        int i = 0;
	        String bbxhqdId = args[0];
	        boolean blHasPush = false;
	        try {
	            String sCurrentUser = context.getUser();
	            DomainObject objBBXHQD = DomainObject.newInstance(context);
	            objBBXHQD.setId(bbxhqdId);
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
	            strList.addElement(sCurrentUser);
	            System.out.println("firstChecker========="+sCurrentUser);
	            System.out.println("sCurrentUser========="+sCurrentUser);
	            strList.addElement(sCurrentUser);
	            MapList mlMembers = getMembers(context, objBBXHQD, strList);
	            System.out.println("mlMembers========="+mlMembers);
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
