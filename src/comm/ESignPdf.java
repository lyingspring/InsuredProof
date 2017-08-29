package comm;

import java.util.ArrayList;
import java.util.List;

import com.timevale.esign.result.account.LoginResult;
import com.timevale.esign.result.account.SealListResult;
import com.timevale.esign.result.account.SealResult;
import com.timevale.esign.result.file.SignPDFResult;
import com.timevale.esign.sdk.account.AccountService;
import com.timevale.esign.sdk.account.AccountServiceImpl;
import com.timevale.esign.sdk.file.LocalFileService;
import com.timevale.esign.sdk.file.LocalFileServiceImpl;

import esign.bean.PosBean;

/**
 * ���Ӹ�������
 * @author maoxj
 *
 */

public class ESignPdf {
/**
 * 
 * @param oldfileurl ԭpdf��ַ
 * @param newfileurl ���Ӹ��º����ɵ�ַ
 * @throws Exception 
 */
	public void GetESignPdf(String oldfileurl,String newfileurl) throws Exception{
		 AccountService accountService = new AccountServiceImpl();
	        //appKey ������Ӧ������Ȱ󶨵�ͳһ�����֤��½��Ϣ
	        LoginResult loginResult = accountService.appLogin("3DF500D30000722B");//6117020700003148
	        //�鿴login��������
	        String accountId = loginResult.getAccountId();
	        String token=loginResult.getToken();
	       
	        System.out.println(accountId);
	        System.out.println(token);
	        //���÷��ص�accounId��ȡӡ���б�
	        SealListResult sealListResult=accountService.getSealList(accountId,null,null,0);
	        List<SealResult> sr1=new ArrayList<SealResult>();
	        sr1=sealListResult.getSeals();
	        //��֤ӡ������
	        System.out.print(sr1.get(0).getImgBase64()+"------");

	        Integer sealId=-1;
	        for (SealResult sr: sealListResult.getSeals()) {
	             System.err.println("ӡ��ID:"+sr.getSealId());//��ӡ���еĵ���ӡ��id
	            sealId=sr.getSealId();

	             }
	        //���������Ϣ
	        PosBean signPos = new PosBean();
	        signPos.setPosType("0");//0-���궨λ��1-�ؼ��ֶ�λ��Ĭ��0
	        signPos.setPosPage("1");//ǩ��ҳ�룬��Ϊ��ҳǩ�£�֧��ҳ���ʽ��1-3,5,8������Ϊ���궨λʱ�����ɿ�
	        signPos.setPosX(480);//ǩ��λ��X���꣬����Ϊ�ؼ��ֶ�λ������ڹؼ��ֵ�X����ƫ������Ĭ��0
	        signPos.setPosY(120);//ǩ��λ��Y���꣬����Ϊ�ؼ��ֶ�λ������ڹؼ��ֵ�Y����ƫ������Ĭ��0
	        signPos.setKey("ǩ�´�");//�ؼ��֣����޹ؼ���ǩ��ʱ��Ч����Ϊ�ؼ��ֶ�λʱ�����ɿ�
	        
	        LocalFileService localFileService = new LocalFileServiceImpl();
	        //�ļ���ַ��Ӧ�÷������Լ������趨
//	        String srcPdfFile = "D:\\abc.pdf";
//	        String desPdfFile = "D:\\template_out.pdf";
	        //ǩ�� 1010633
	        SignPDFResult br = localFileService.localSignPDF(accountId,"",oldfileurl,
	        		newfileurl, sealId, 1, signPos);
	        Integer err=br.getErrCode();
	        if(err==0)
	            System.out.println("ǩ��ɹ�");
	        else
	        	throw new Exception(err+" "+br.getMsg());
//	            System.out.println(err);
//	            System.out.println(br.getMsg());
	}
	
}
