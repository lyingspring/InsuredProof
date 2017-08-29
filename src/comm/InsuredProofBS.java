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
			throw new Exception("�Ҳ�������Ա������Ϣ��");
		}
		listmap=null;
		sql = "select a.* from ac01 a,ac02 b,ac20 c where a.aac001=b.aac001 "
				+ "and b.aaz159=c.aaz159 "
				+ "and b.aae140='20' and  aac003='"
				+ hashmap.get("aac003") + "' and aae135='" + hashmap.get("aac002") + "'"
						+ " order by aac008,aac031";
		jdbc.getConnection();//�����ٴο���
		listmap = jdbc.createSQLQuery(sql);
		if (listmap.isEmpty()) {
			throw new Exception("�Ҳ�������Ա�α���Ϣ��");
		}
		aac001=Long.valueOf(listmap.get(0).get("AAC001"));
		return aac001;
	}
	
	
	
	   /** 
     * ��ѯ�������ļ���ɾ�����в��ǽ������ɵ��ļ� 
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
              if(temp.exists()&&temp.getName().endsWith(".pdf")&&!temp.getName().startsWith(date)){//�����������ļ�  
                  System.out.println(temp.getName());  
                  temp.delete();  
                  number++;  
                    
              }  
                   
           }      
        return number;  
    }
    
    /**
     *  �ж��ļ����Ƿ���ڣ��������½�
     * @param file
     */
        public  void judeDirExists(String fileurl) {
        	File file = new File(fileurl);
         if (file.exists()) {
               if (file.isDirectory()) {
                     System.out.println("�ļ����Ѵ��ڣ����½�");
                } else {
                     System.out.println("������ͬ���Ƶ��ļ�");
               }
            } else {
               System.out.println("�ļ��в����ڣ��½� ...");
               file.mkdir();
            }
    
        }
    
}
