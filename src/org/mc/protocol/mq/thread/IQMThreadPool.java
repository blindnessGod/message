/**
 * 
 */
package org.mc.protocol.mq.thread;

/**   
 *    
 * ��Ŀ���ƣ�FileCheck   
 * �����ƣ�IMyThreadPool   
 * ��������   
 * �����ˣ�����Ⱥ
 * ����ʱ�䣺2014-3-5  
 * @version    
 *    
 */
public interface IQMThreadPool {
	public void interrupt(Thread thread);
	public void interruptAll();
}
