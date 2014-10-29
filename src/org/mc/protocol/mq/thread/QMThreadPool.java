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
* ��Ŀ���ƣ�FileCheck   
* �����ƣ�MyThreadPoolTest   
* ��������   
* �����ˣ�����Ⱥ
* ����ʱ�䣺2014-3-6  
* @version    
*    
*/
public class QMThreadPool implements IQMThreadPool {
    /**
     * Ĭ�ϳ����߳���
     */
    public static int worker_num = 1;
    /**
     * ���������߳�
     */
    public QMThreadWorkTask[] workers;
    
    private static class QMThreadPoolHolder{
    	private static QMThreadPool instance = new QMThreadPool();
    }
    
    public static QMThreadPool getInstance(){
    	return QMThreadPoolHolder.instance;
    }
    
    //����Ĭ���߳������߳���
	public QMThreadPool(){
		System.out.println("[QMThreadPool] ConstantValue.configName = "+ConstantValue.configName);
        
        worker_num = new Integer(LoadMsgConfig.getInstance(ConstantValue.configName).getMessageComponent().getThreadPoolCapacity()).intValue();
        
        workers = new QMThreadWorkTask[worker_num];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new QMThreadWorkTask(i);
        }
    }
	//���ݳ�ʼ���������������߳���
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
	
	
	//����Ĵ���Ϊ���ǿ���ʵ�ֶ��߳������Ŀ��ƣ��Դﵽ���ŵ���Դʹ����
//	/**
//	*    
//	* ��Ŀ���ƣ�FileCheck   
//	* �����ƣ�QueueDeepStrategy   
//	* ��������   ɨ�������ȣ�����������Զ������߳�����
//	* �����ˣ�����Ⱥ
//	* ����ʱ�䣺2014-3-6  
//	* @version    
//	*
//	 */
//	private class QueueDeepStrategy implements Runnable{
//
//		@Override
//		public void run() {
//			// TODO Auto-generated method stub
//			IProduct product = (IProduct)ConstantValue.context.getBean("mqIBMReceive");
//			//Ĭ��Ȩֵ
//			int weights = 3;
//			//��ǰ�߳���
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
//	 * ����ΪҪ���µ��߳���
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
//	 * ����ΪҪ���ӵ����߳���
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
