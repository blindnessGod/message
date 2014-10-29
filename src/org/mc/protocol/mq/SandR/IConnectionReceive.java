/**
 * 
 */
package org.mc.protocol.mq.SandR;

import com.ibm.mq.MQQueueManager;

/**   
 *    
 * 项目名称：MessageComponent   
 * 类名称：IConnectionReceive   
 * 类描述：   发送消息接口
 * 创建人：王开群
 * 创建时间：2014-3-6  
 * @version    
 *    
 */
public interface IConnectionReceive {
	/**
	 * 重置
	 * 
	 * @throws Exception
	 */
	public void release() throws Exception;

	/**
	 * 断开连接
	 * 
	 * @throws Exception
	 */
	public void disconnect() throws Exception;

	/**
	 * 接收连接
	 * 
	 * @throws Exception
	 */
	public Object Connect() throws Exception;
	/**
	 * 得到消息
	 * 
	 * @return 返回字节数组
	 * @throws Exception
	 */
	public byte[] getMsg() throws Exception;
	/**
	 * 获得队列深度
	 * @return 返回深度值
	 * @throws Exception
	 */
	public int getDeep() throws Exception;
}
