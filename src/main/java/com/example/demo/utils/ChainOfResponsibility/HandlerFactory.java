package com.example.demo.utils.ChainOfResponsibility;

/**
 *
 * Created by chenzhi on 2017/9/11.
 */
public class HandlerFactory {
    public static AuditHandler getAuditChain(){
        ImperativeHandler imperativeHandler = new ImperativeHandler();
        InvalidSkipHandler invalidSkipHandler = new InvalidSkipHandler();
        SameApproverHandler sameApproverHandler = new SameApproverHandler();
        imperativeHandler.setNextHandler(invalidSkipHandler);
        invalidSkipHandler.setNextHandler(sameApproverHandler);
        return imperativeHandler;
    }

}
