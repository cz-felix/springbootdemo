package com.example.demo.Utils.ChainOfResponsibility;

/**
 * Created by Administrator on 2017/8/31.
 */
abstract class ConsumeHandler {
    private ConsumeHandler nextHandler;

    public ConsumeHandler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(ConsumeHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /** free报销费用 */
    public abstract void doHandler(double free);

    /**
     * 项目经理审核
     */
    static class ProjectHandler extends ConsumeHandler{
        @Override
        public void doHandler(double free) {
            if(free < 500){
                System.out.println("项目经理通过报销");
            }else{
                if (getNextHandler() != null) {
                    System.out.println("项目经理通过报销");
                    getNextHandler().doHandler(free);
                }
            }
        }
    }

    /**
     * 部门经理审核
     */
    static class DeptHandler extends ConsumeHandler{
        @Override
        public void doHandler(double free) {
            if(free < 1000){
                System.out.println("部门经理审核通过！");
            }else{
                if(getNextHandler() != null){
                    System.out.println("部门经理审核通过！");
                    getNextHandler().doHandler(free);
                }
            }
        }
    }

    /**
     * 总经理审核
     */
    static class GeneralHandler extends ConsumeHandler{

        @Override
        public void doHandler(double free) {
            if(free >= 1000){
                System.out.println("总经理审核通过！");
            }else{
                if(getNextHandler() != null){
                    System.out.println("总经理审核通过！");
                    getNextHandler().doHandler(free);
                }
            }
        }
    }

    public static void main(String[] args) {
        ConsumeHandler.ProjectHandler projectHandler =new ProjectHandler();
        ConsumeHandler.DeptHandler deptHandler =new DeptHandler();
        ConsumeHandler.GeneralHandler generalHandler =new GeneralHandler();
        projectHandler.setNextHandler(deptHandler);
        deptHandler.setNextHandler(generalHandler);
        projectHandler.doHandler(450);
        System.out.println("450审核完毕===========");
        projectHandler.doHandler(600);
        System.out.println("600审核完毕===========");
        projectHandler.doHandler(1500);
        System.out.println("1500审核完毕===========");
    }
}
