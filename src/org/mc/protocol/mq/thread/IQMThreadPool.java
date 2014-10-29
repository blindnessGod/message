/**
 * 
 */
package org.mc.protocol.mq.thread;

/**   
 *    
 * 项目名称：FileCheck   
 * 类名称：IMyThreadPool   
 * 类描述：   
 * 创建人：王开群
 * 创建时间：2014-3-5  
 * @version    
 *    
 */
public interface IQMThreadPool {
	public void interrupt(Thread thread);
	public void interruptAll();
}
