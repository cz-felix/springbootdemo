package com.example.demo.utils.ChainOfResponsibility;

/**
 * Created by chenzhi on 2017/9/11.
 */
public class Test {
    public static void main(String[] args) {
        HandlerContext context = new HandlerContext();
        context.setOperateType("1111");
        AuditHandler s = HandlerFactory.getAuditChain();
        s.doHandler(context);
    }
}
