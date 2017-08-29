package test_webservice;


import com.timevale.esign.result.account.*;

import com.timevale.esign.result.file.SignPDFResult;

import com.timevale.esign.sdk.account.AccountService;
import com.timevale.esign.sdk.account.AccountServiceImpl;

import com.timevale.esign.sdk.file.LocalFileService;
import com.timevale.esign.sdk.file.LocalFileServiceImpl;

import esign.bean.PosBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/31.
 */

public class AppKey {

    public static void main(String[] args) {
        AccountService accountService = new AccountServiceImpl();
        //appKey ������Ӧ������Ȱ󶨵�ͳһ�����֤��½��Ϣ
        LoginResult loginResult = accountService.appLogin("123456789");
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
             System.err.println(sr.getSealId());
            sealId=sr.getSealId();

             }
        //���������Ϣ
        PosBean signPos = new PosBean();
        signPos.setPosType("0");
        signPos.setPosPage("1");
        signPos.setPosX(480);
        signPos.setPosY(120);
        signPos.setKey("ǩ�´�");
        signPos.setPosPage("1");
        LocalFileService localFileService = new LocalFileServiceImpl();
        //�ļ���ַ��Ӧ�÷������Լ������趨
        String srcPdfFile = "D:\\abc.pdf";
        String desPdfFile = "D:\\template_out.pdf";
        //ǩ�� 1010633
        SignPDFResult br = localFileService.localSignPDF(accountId,"",srcPdfFile,
                desPdfFile, 21207, 1, signPos);
        Integer err=br.getErrCode();
        if(err==0)
            System.out.println("ǩ��ɹ�");
        else
            System.out.println(err);
            System.out.println(br.getMsg());

    }
}