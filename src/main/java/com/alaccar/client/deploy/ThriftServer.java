package com.alaccar.client.deploy;

import org.apache.thrift.TException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alaccar.workorder.shared.model.WorkOrderModel;
import com.alaccar.workorder.shared.model.WorkOrderMsgModel;
import com.alaccar.workorder.shared.service.WorkOrderService;

import java.util.List;

/**
 * Created by shang on 16/5/16.
 */


public class ThriftServer {
    public static void main( String[] args ) {
        try{   
            ApplicationContext context = new ClassPathXmlApplicationContext( "classpath:applicationContext.xml" );

            WorkOrderService.Iface service = (WorkOrderService.Iface) context.getBean("workOrderService");
            System.out.println("ThriftClient  Start...");
            getWorkOrderModel(service);
        }catch(Exception e){
            throw new RuntimeException("thrift Error"+"\n"+e.getMessage());
        }
    }
    public static void changeWorkOrderStatus(WorkOrderService.Iface service){
        try {
            service.updateWorkOrderStatus(5, 200);
        } catch (TException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void getWorkOrderModel(WorkOrderService.Iface service){
        try {
            int sceneId = 100;
            int sceneType = 0;
            int senderId = 2;
            List<WorkOrderModel> modelList = service.getWorkOrderListBySenderInfo(senderId, sceneId, sceneType);
            for(WorkOrderModel model :modelList){
                System.out.println("workmodel id is "+model.getId());
                System.out.println("workmodel status is "+model.getOrderStatus());
                System.out.println("content is ");
                for(WorkOrderMsgModel msg :model.getMsgList()){
                    System.out.println(msg.getContent());
                }
            }
        } catch (TException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static WorkOrderModel constructWorkOrderModel(){
        WorkOrderModel model = new WorkOrderModel();
        model.setOrderFrom(2);
        model.setOrderTo(4);
        model.setSceneId(100);
        return model;
    }
    public static  void  addSingleWorkOrder(WorkOrderService.Iface service){
        WorkOrderModel model = constructWorkOrderModel();
        try {
            int id = service.addWorkOrder(model);
            System.out.println(id);
        } catch (TException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void addMsg2WorkOrder(WorkOrderService.Iface service){
        WorkOrderMsgModel msgModel = constructMsgModel();
        try {
            boolean pass = service.addWorkOrderMsg(msgModel);
            System.out.println("add msg ===>"+pass);
        } catch (TException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private static WorkOrderMsgModel constructMsgModel() {
        WorkOrderMsgModel msgModel =new WorkOrderMsgModel();
        msgModel.setContent("第二个测试中文");
        msgModel.setOrderEntityId(5);
        msgModel.setMsgFrom(2);
        msgModel.setMsgTo(4);
        return msgModel;
    }
}
