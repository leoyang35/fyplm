import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import matrix.db.AttributeType;
import matrix.db.Context;
import matrix.db.JPO;
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



public class fyplmBBXHQD_mxJPO extends Part{
	
	private static final String STRING_FYPLMExpression = "fyplmExpression";

	
    private Context context;
    private static Logger logger = Logger.getLogger(fyplmBBXHQD_mxJPO.class);
    
    
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
