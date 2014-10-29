/**
 * 
 */
package org.mc.protocol.biz.Interface;

/**   
 *    
 * 项目名称：MessageComponent   
 * 类名称：IBizReceiver   
 * 类描述：  业务的入口处，实现该接口获得消息 
 * 创建人：王开群
 * 创建时间：2014-3-6  
 * @version    
 *    
 */
public interface IBizReceiver {
	public void BizProc(byte[] ed) throws Exception;
}
