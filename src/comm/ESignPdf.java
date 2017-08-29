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
 * 电子盖章主类
 * @author maoxj
 *
 */

public class ESignPdf {
/**
 * 
 * @param oldfileurl 原pdf地址
 * @param newfileurl 电子盖章后生成地址
 * @throws Exception 
 */
	public void GetESignPdf(String oldfileurl,String newfileurl) throws Exception{
		 AccountService accountService = new AccountServiceImpl();
	        //appKey 第三方应用与天谷绑定的统一身份认证登陆信息
	        LoginResult loginResult = accountService.appLogin("3DF500D30000722B");//6117020700003148
	        //查看login返回数据
	        String accountId = loginResult.getAccountId();
	        String token=loginResult.getToken();
	       
	        System.out.println(accountId);
	        System.out.println(token);
	        //利用返回的accounId获取印章列表
	        SealListResult sealListResult=accountService.getSealList(accountId,null,null,0);
	        List<SealResult> sr1=new ArrayList<SealResult>();
	        sr1=sealListResult.getSeals();
	        //验证印章数据
	        System.out.print(sr1.get(0).getImgBase64()+"------");

	        Integer sealId=-1;
	        for (SealResult sr: sealListResult.getSeals()) {
	             System.err.println("印章ID:"+sr.getSealId());//打印所有的电子印章id
	            sealId=sr.getSealId();

	             }
	        //定义盖章信息
	        PosBean signPos = new PosBean();
	        signPos.setPosType("0");//0-坐标定位，1-关键字定位，默认0
	        signPos.setPosPage("1");//签署页码，若为多页签章，支持页码格式“1-3,5,8“，若为坐标定位时，不可空
	        signPos.setPosX(480);//签署位置X坐标，，若为关键字定位，相对于关键字的X坐标偏移量，默认0
	        signPos.setPosY(120);//签署位置Y坐标，，若为关键字定位，相对于关键字的Y坐标偏移量，默认0
	        signPos.setKey("签章处");//关键字，仅限关键字签章时有效，若为关键字定位时，不可空
	        
	        LocalFileService localFileService = new LocalFileServiceImpl();
	        //文件地址，应用方根据自己需求设定
//	        String srcPdfFile = "D:\\abc.pdf";
//	        String desPdfFile = "D:\\template_out.pdf";
	        //签章 1010633
	        SignPDFResult br = localFileService.localSignPDF(accountId,"",oldfileurl,
	        		newfileurl, sealId, 1, signPos);
	        Integer err=br.getErrCode();
	        if(err==0)
	            System.out.println("签署成功");
	        else
	        	throw new Exception(err+" "+br.getMsg());
//	            System.out.println(err);
//	            System.out.println(br.getMsg());
	}
	
}
