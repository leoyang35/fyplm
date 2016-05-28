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
   
   
   
   
}
