/**
 * 
 */
package org.mc.protocol.mq.SandR;

/**   
 *    
 * 项目名称：MessageComponent   
 * 类名称：IConnectionSend   
 * 类描述：   接收消息接口
 * 创建人：王开群
 * 创建时间：2014-3-6  
 * @version    
 *    
 */
public interface IConnectionSend {
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
	 * 发送消息
	 * 
	 * @return 返回字节数组
	 * @throws Exception
	 */
	public void putMsg( byte[] edi) throws Exception;
}
