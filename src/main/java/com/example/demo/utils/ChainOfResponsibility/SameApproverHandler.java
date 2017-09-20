package com.example.demo.utils.ChainOfResponsibility;

/**
 * 相同审批人跳过
 * Created by chenzhi on 2017/9/11.
 */
public class SameApproverHandler extends AuditHandler{
    @Override
    public void doHandler(HandlerContext context) {
        System.out.println("相同审批人逻辑");

        if(getNextHandler() != null){
            getNextHandler().doHandler(context);
        }
    }
}
