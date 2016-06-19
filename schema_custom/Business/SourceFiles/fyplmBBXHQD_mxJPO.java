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

import com.dassault_systemes.i3dx.util.ContextUtil;
import com.matrixone.apps.domain.DomainConstants;
import com.matrixone.apps.domain.DomainObject;
import com.matrixone.apps.domain.DomainRelationship;
import com.matrixone.apps.domain.util.EnoviaResourceBundle;
import com.matrixone.apps.domain.util.FrameworkException;
import com.matrixone.apps.domain.util.FrameworkUtil;
import com.matrixone.apps.domain.util.MapList;
import com.matrixone.apps.domain.util.PropertyUtil;
import com.matrixone.apps.domain.util.XSSUtil;
import com.matrixone.apps.domain.util.i18nNow;
import com.matrixone.apps.engineering.Part;
import com.matrixone.apps.domain.util.MqlUtil;



public class fyplmBBXHQD_mxJPO extends DomainObject{
	
	private static final String STRING_FYPLMExpression = "fyplmExpression";
	
    private Context context;
    private static Logger logger = Logger.getLogger(fyplmBBXHQD_mxJPO.class);
    
    public static final String ABEGIN = "attribute[";
    public static final String AEND="]";
    public static final String ATTRIBUTE_FYPLM_NET_USAGE = PropertyUtil.getSchemaProperty("attribute_FYPLMNetUsage");
    public static final String SELECT_ATTRIBUTE_FYPLM_NET_USAGE = new StringBuilder(ABEGIN).append(ATTRIBUTE_FYPLM_NET_USAGE).append(AEND).toString();

    public static final String ATTRIBUTE_FYPLM_GROSS_USAGE = PropertyUtil.getSchemaProperty("attribute_FYPLMGrossUsage");
    public static final String SELECT_ATTRIBUTE_FYPLM_GROSS_USAGE = new StringBuilder(ABEGIN).append(ATTRIBUTE_FYPLM_GROSS_USAGE).append(AEND).toString();

    public static final String RELATIONSHIP_FYPLM_BBXHQD_TopPart = PropertyUtil.getSchemaProperty("relationship_FYPLMBBXHQDTopPart");
    public static final String RELATIONSHIP_FYPLM_BBTOPPART_CLASSBMATERIALPART = PropertyUtil.getSchemaProperty("relationship_FYPLMBBTopPartClassBMaterialPart");
    public static final String TYPE_FYPLM_BBXHQD = PropertyUtil.getSchemaProperty("type_FYPLMBBXHQD");
    public static final String TYPE_FYPLM_BB_TOP_PART = PropertyUtil.getSchemaProperty("type_FYPLMBBTopPart");
    
    
    /**
    *
    * @param context the eMatrix <code>Context</code> object
    * @param args holds no arguments
    * @throws Exception if the operation fails
    * @since AEF 10.0.SP4
    * @grade 0
    */
    
    public fyplmBBXHQD_mxJPO (Context context, String[] args)
        throws Exception
    {
      super();
      this.context = context;
      if ((args != null) && (args.length > 0))
      {
          setId(args[0]);
      }
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
        MapList topPartList = objBBXHQD.getRelatedObjects(context, RELATIONSHIP_FYPLM_BBXHQD_TopPart, 
        						TYPE_FYPLM_BB_TOP_PART, 
                                        objSelect, new StringList(SELECT_RELATIONSHIP_ID), 
                                                        false, true, (short)1, null, null, 0);
        MapList columntMap = (MapList) programMap.get("columnMap");
        for (int i=0 ; i< topPartList.size(); i++){
        	Map tmpMap = (Map)topPartList.get(i);
        	String sPartId = (String)tmpMap.get(SELECT_ID);
        	String sRelId = (String)tmpMap.get(SELECT_RELATIONSHIP_ID);
        	DomainObject objPart = DomainObject.newInstance(context,sPartId);
        	String sPartName = objPart.getInfo(context, SELECT_NAME);
        	
        	String sHeader = new StringBuilder("<input type=\"radio\" name=\"emxTableRowId\" value=\"")
        			.append(sRelId).append("|").append(sPartId).append("\" onclick=\"doRadioButtonClick(this);\">").append(sPartName).toString();
        	
        	Map colMap = new HashMap();
            Map settingMap = new HashMap();

            settingMap.put("Admin Type", "attribute_FYPLMNetUsage");
            settingMap.put("Column Type", "programHTMLOutput");
            // settingMap.put("Group Header", "emxProduct.Table.Selection");

            //settingMap.put("Registered Suite", "Configuration");)
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
            colMap.put("label", "净用量");
            columnMapList.add(colMap);

        	Map colMap2 = new HashMap();
            Map settingMap2 = new HashMap();

            settingMap2.put("Admin Type", "attribute_FYPLMGrossUsage");
            settingMap2.put("Column Type", "programHTMLOutput");
            // settingMap.put("Group Header", "emxProduct.Table.Selection");

            //settingMap.put("Registered Suite", "Configuration");)
            settingMap2.put("function", "getGrossUsageColumn");
            settingMap2.put("program", "fyplmBBXHQD");
            //settingMap.put("Style Program", "cqacBOMProduct");)
            //settingMap.put("Style Function", "getdynamicbackground");)
            //settingMap.put("Column Style", "center-align");)
            //settingMap.put("Width", customWidth);
            //settingMap.put("Export", "true");)
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
            colMap2.put("label", "毛用量");
            columnMapList.add(colMap2);
        }
        return columnMapList;
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
