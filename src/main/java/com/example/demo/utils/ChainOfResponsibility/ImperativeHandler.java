package com.example.demo.utils.ChainOfResponsibility;

/**
 * 必审
 * Created by chenzhi on 2017/9/11.
 */
public class ImperativeHandler extends AuditHandler{

    @Override
    public void doHandler(HandlerContext context) {
        System.out.println("必审逻辑");

        if(getNextHandler() != null){
            getNextHandler().doHandler(context);
        }
    }
}
