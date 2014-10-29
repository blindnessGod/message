/**
 * 
 */
package org.mc.protocol.mq.thread;

import java.io.IOException;
import java.nio.CharBuffer;

import org.mc.protocol.config.ConstantValue;
import org.mc.protocol.mq.product.IProduct;

import com.framework.component.config.LoadMsgConfig;
import com.framework.component.config.ConfigObj.Parm;

/**   
*    
* 项目名称：FileCheck   
* 类名称：MyThreadPoolTest   
* 类描述：   
* 创建人：王开群
* 创建时间：2014-3-6  
* @version    
*    
*/
public class QMThreadPool implements IQMThreadPool {
    /**
     * 默认池中线程数
     */
    public static int worker_num = 1;
    /**
     * 池中所有线程
     */
    public QMThreadWorkTask[] workers;
    
    private static class QMThreadPoolHolder{
    	private static QMThreadPool instance = new QMThreadPool();
    }
    
    public static QMThreadPool getInstance(){
    	return QMThreadPoolHolder.instance;
    }
    
    //启动默认线程数的线程量
	public QMThreadPool(){
		System.out.println("[QMThreadPool] ConstantValue.configName = "+ConstantValue.configName);
        
        worker_num = new Integer(LoadMsgConfig.getInstance(ConstantValue.configName).getMessageComponent().getThreadPoolCapacity()).intValue();
        
        workers = new QMThreadWorkTask[worker_num];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new QMThreadWorkTask(i);
        }
    }
	//根据初始化的数量，定义线程数
	public QMThreadPool(int pool_worker_num){
        worker_num = pool_worker_num;
        workers = new QMThreadWorkTask[worker_num];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new QMThreadWorkTask(i);
        }
	}
	/* (non-Javadoc)
	 * @see person.obj.thread.IMyThreadPool#interrupt(java.lang.Thread)
	 */
	@Override
	public void interrupt(Thread thread) {
		// TODO Auto-generated method stub
		thread.interrupt();
	}

	@Override
	public void interruptAll() {
		// TODO Auto-generated method stub
		for(Thread thread : workers){
			thread.currentThread().isInterrupted();
		}
	}
	
	
	//下面的处理为的是可以实现对线程数量的控制，以达到最优的资源使用率
//	/**
//	*    
//	* 项目名称：FileCheck   
//	* 类名称：QueueDeepStrategy   
//	* 类描述：   扫面队列深度，并根据深度自动调整线程数量
//	* 创建人：王开群
//	* 创建时间：2014-3-6  
//	* @version    
//	*
//	 */
//	private class QueueDeepStrategy implements Runnable{
//
//		@Override
//		public void run() {
//			// TODO Auto-generated method stub
//			IProduct product = (IProduct)ConstantValue.context.getBean("mqIBMReceive");
//			//默认权值
//			int weights = 3;
//			//当前线程数
//			int icurrent = 0;
//			try{
//				while(true){
//					if (weights >= worker_num) break;
//					
//					int iDeep = product.getQueueDeep();
//					
//					if(iDeep <= 200){
//						if(icurrent > Math.round(weights))
//							cutThread(weights);
//						else
//							addThread(weights);
//					}else if(iDeep >200 && iDeep <=2000){
//						if(icurrent < Math.round(a))
//						Math.round(4);
//					}else if(iDeep >2000 && iDeep <= 50000){
//						
//					}else if(iDeep > 50000 && iDeep <= 10000){
//						
//					}else if(iDeep > 10000){
//						
//					}
//					
//				}
//			}catch(Exception ex){
//				ex.printStackTrace();
//			}
//		}
//	}
//	/**
//	 * 参数为要留下的线程数
//	 */
//	@Override
//	public void cutThread(int i)throws InterruptedException {
//		int iTmp = workers.length;
//		for(Thread thread : workers){
//			if(iTmp == i) break;
//			if(thread.currentThread().isInterrupted()){
//				continue;
//			}else{
//				thread.interrupt();
//				iTmp--;
//				continue;
//			}
//		}
//	}
//	/**
//	 * 参数为要增加到的线程数
//	 */
//	@Override
//	public void addThread(int i)throws InterruptedException {
//		// TODO Auto-generated method stub
//		int iTmp = 0;
//		for(Thread thread : workers){
//			if(iTmp == i) break;
//			if(thread.currentThread().isInterrupted()){
//				thread.currentThread().interrupted();
//				iTmp++;
//				continue;
//			}
//		}
//	}
	
}
