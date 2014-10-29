/**
 * 
 */
package org.mc.protocol.mq.product;

/**   
 *    
 * 项目名称：MessageComponent   
 * 类名称：IProduct   
 * 类描述：   
 * 创建人：王开群
 * 创建时间：2014-3-6  
 * @version    
 *    
 */
public interface IProduct {
	public byte[] getMsg() throws Exception;
	public void putMsg(byte[] msg) throws Exception;
	public int getQueueDeep() throws Exception;
}
