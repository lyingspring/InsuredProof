package comm;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author maoxj
 *
 */
public class InsuredProofBS {
	/**
	 * 
	 * @param aac001
	 * @return
	 * @throws Exception
	 */
	public Long CheckPeople(HashMap<String, String> hashmap) throws Exception {
		// TODO Auto-generated method stub
		oracle_jdbc jdbc = new oracle_jdbc();
		Long aac001 = null;
		String sql = "select * from ac01 where aac003='" + hashmap.get("aac003") + "' and aae135='"
				+ hashmap.get("aac002") + "'";
		List<HashMap<String, String>> listmap = new ArrayList<HashMap<String, String>>();
		listmap = jdbc.createSQLQuery(sql);
		if (listmap.isEmpty()) {
			throw new Exception("找不到该人员基本信息！");
		}
		listmap=null;
		sql = "select a.* from ac01 a,ac02 b,ac20 c where a.aac001=b.aac001 "
				+ "and b.aaz159=c.aaz159 "
				+ "and b.aae140='20' and  aac003='"
				+ hashmap.get("aac003") + "' and aae135='" + hashmap.get("aac002") + "'"
						+ " order by aac008,aac031";
		jdbc.getConnection();//连接再次开启
		listmap = jdbc.createSQLQuery(sql);
		if (listmap.isEmpty()) {
			throw new Exception("找不到该人员参保信息！");
		}
		aac001=Long.valueOf(listmap.get(0).get("AAC001"));
		return aac001;
	}
	
	
	
	   /** 
     * 查询并根据文件名删除所有不是今天生成的文件 
     * @return 
     */  
    public int DeleteFileDate(String savePlace){  
        int number=0;  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");  
        String date=sdf.format(new Date());  
        File file=new File(savePlace);  
        String[]  tempList  =  file.list();   
         File  temp  =  null;      
           for  (int  i  =  0;  i  <  tempList.length;  i++)  {      
              String path=savePlace+tempList[i];  
              temp  =  new  File(path);   
              if(temp.exists()&&temp.getName().endsWith(".pdf")&&!temp.getName().startsWith(date)){//如果存在这个文件  
                  System.out.println(temp.getName());  
                  temp.delete();  
                  number++;  
                    
              }  
                   
           }      
        return number;  
    }
    
    /**
     *  判断文件夹是否存在，不存在新建
     * @param file
     */
        public  void judeDirExists(String fileurl) {
        	File file = new File(fileurl);
         if (file.exists()) {
               if (file.isDirectory()) {
                     System.out.println("文件夹已存在，不新建");
                } else {
                     System.out.println("存在相同名称的文件");
               }
            } else {
               System.out.println("文件夹不存在，新建 ...");
               file.mkdir();
            }
    
        }
    
}
