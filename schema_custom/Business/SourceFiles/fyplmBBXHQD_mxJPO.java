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


/**
 * The <code>cplmAsset</code> class contains code for the "cplm Asset" business
 * type.
 * 
 * @version
 */
public class fyplmBBXHQD_mxJPO extends Part{
    private Context context;
    private static Logger logger = Logger.getLogger(fyplmBBXHQD_mxJPO.class);
    
    public static final String ABEGIN = "attribute[";
    public static final String AEND="]";
    public static final String ATTRIBUTE_FYPLM_NET_USAGE = PropertyUtil.getSchemaProperty("attribute_FYPLMNetUasge");
    public static final String SELECT_ATTRIBUTE_FYPLM_NET_USAGE = new StringBuilder(ABEGIN).append(ATTRIBUTE_FYPLM_NET_USAGE).append(AEND).toString();
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
   
   
    public MapList getDynamicPartsColumn(Context context, String[] args) throws Exception {
    	logger.debug("entering JPO getDynamicPartsColumn.");
        HashMap programMap = (HashMap) JPO.unpackArgs(args);
        MapList objectList = (MapList) programMap.get("objectList");

        HashMap paramList = (HashMap) programMap.get("requestMap");
        String modeStr = (String)paramList.get("mode");
        String objectId = (String) paramList.get("objectId");
        MapList columnMapList = new MapList();
        
        DomainObject objBBXHQD = DomainObject.newInstance(context, objectId);
        StringList objSelect = new StringList();
        objSelect.addElement(SELECT_ID);
        StringList relSelectList = new StringList();
        relSelectList.addElement(SELECT_ATTRIBUTE_FYPLM_NET_USAGE);
        MapList topPartList = objBBXHQD.getRelatedObjects(context, RELATIONSHIP_FYPLM_BBXHQD_TopPart, 
        						TYPE_FYPLM_BB_TOP_PART, 
                                        objSelect, relSelectList, 
                                                        false, true, (short)1, null, null, 0);
        MapList columntMap = (MapList) programMap.get("columnMap");
        for (int i=0 ; i< topPartList.size(); i++){
        	Map tmpMap = (Map)topPartList.get(i);
        	String sPartId = (String)tmpMap.get(SELECT_ID);
        	DomainObject objPart = DomainObject.newInstance(context,sPartId);
        	String sPartName = objPart.getInfo(context, SELECT_NAME);

        	Map colMap = new HashMap();
            Map settingMap = new HashMap();

            settingMap.put("Admin Type", "attribute_FYPLMNetUasge");
            settingMap.put("Column Type", "programHTMLOutput");// modifyed by)
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
                    //settingMap.put("Update Function", "updateDynamicColumn");
                    //settingMap.put("Update Program", "cqacBOMProduct");
                    settingMap.put("Input Type", "textbox");
                    settingMap.put("Field Type", "attribute");
            }
            colMap.put("topPartId", sPartId);
            colMap.put("settings", settingMap);
            colMap.put("name", sPartName);
            colMap.put("label", "label");
            columnMapList.add(colMap);
        }
        return columnMapList;
    }
    
      public StringList getNetUsageColumn(Context context, String[] args) throws FrameworkException{
    	  StringList lsNetUsage = null;
    	   HashMap programMap = (HashMap) JPO.unpackArgs(args);
           Map colMap = (HashMap) programMap.get("columnMap");
           String sPartId = (String) colMap.get("topPartId");
           MapList lsMaterials = (MapList) programMap.get("objectList");
           String[] arrays = new String[lsMaterials.size()];
           for (int i = 0; i < lsMaterials.size(); i++) {
        	   Map mapMaterial = (Map) lsMaterials.get(i);
        	   String sMatId = (String) mapMaterial.get(SELECT_ID);
        	   arrays[i] = sMatId;
           }
           StringList select = new StringList("to["+RELATIONSHIP_FYPLM_BBTOPPART_CLASSBMATERIALPART+"]." + SELECT_ATTRIBUTE_FYPLM_NET_USAGE);
           MapList mlNetUsages = DomainObject.getInfo(context, arrays, select);
      }
   
    public static void main(){
    	logger.debug(SELECT_ATTRIBUTE_FYPLM_NET_USAGE);
    }
}
