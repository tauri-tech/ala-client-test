namespace js alaccarService
namespace java com.alaccar.workorder.service.thrift


struct WorkOrderMsgModel {
		1:i64 serialVersionUID,
		2:i32 id,
		3:string gmtCreate,
		4:string gmtModified,
		5:i32 msgFrom,
		6:i32 msgTo,
		7:i32 sceneType,
		8:i32 sceneId,
		9:string content,
		10:i32 orderEntityId
}
struct WorkOrderModel {
		1:i64 serialVersionUID,
		2:i32 id,
		3:string gmtCreate,
		4:string gmtModified,
		5:i32 orderFrom,
		6:i32 orderTo,
		7:i32 sceneType,
		8:i32 sceneId,
		9:i32 orderStatus,
		10:list<WorkOrderMsgModel> msgList
}

service WorkOrderService {
	 	bool updateWorkOrderStatus(1:i32 arg0, 2:i32 arg1),		        	
	 	list<WorkOrderModel> getAllWorkOrderListBySenderId(1:i32 arg0),		        	
	 	WorkOrderModel getWorkOrderById(1:i32 arg0),		        	
	 	list<WorkOrderModel> getWorkOrderBySceneId(1:i32 arg0),		        	
	 	i32 addWorkOrder(1:WorkOrderModel arg0),		        	
	 	bool addWorkOrderMsg(1:WorkOrderMsgModel arg0)		        	
}
