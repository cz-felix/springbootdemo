package com.example.demo.utils.ChainOfResponsibility;

/**
 * Created by chenzhi on 2017/9/11.
 */
public abstract class AuditHandler {

    private AuditHandler nextHandler;

    public AuditHandler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(AuditHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void doHandler(HandlerContext context);
}
