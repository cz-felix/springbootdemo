package com.demo.utils.shiro;

import org.apache.shiro.subject.Subject;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import static org.apache.shiro.SecurityUtils.getSubject;

/**
 * @author chenzhi
 * @date 2017/12/12 0012
 * @description
 */
public class HasAnyPermissionTag extends BodyTagSupport {

    private static final long serialVersionUID = 1L;

    private String permissions;

    @Override
    public int doStartTag() throws JspException { // 在标签开始处出发该方法

        boolean hasAnyPermissions = false;

        Subject subject = getSubject();

        if (subject != null) {
            for (String role : this.permissions.split(",")) {

                if (subject.isPermitted(role.trim())) {
                    hasAnyPermissions = true;
                    break;
                }
            }
        }
        return hasAnyPermissions ? EVAL_BODY_INCLUDE : SKIP_BODY;

    }

    @Override
    public int doEndTag() throws JspException {
        return BodyTagSupport.EVAL_BODY_INCLUDE;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
