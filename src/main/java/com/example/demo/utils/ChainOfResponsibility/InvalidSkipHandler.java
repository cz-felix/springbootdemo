package com.example.demo.utils.ChainOfResponsibility;

/**
 * 审批人无效跳过
 * Created by chenzhi on 2017/9/11.
 */
public class InvalidSkipHandler extends AuditHandler{

    @Override
    public void doHandler(HandlerContext context) {
        System.out.println("审批人无效逻辑");

        if(getNextHandler() != null){
            getNextHandler().doHandler(context);
        }
    }
}
