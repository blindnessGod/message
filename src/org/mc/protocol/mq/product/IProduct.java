/**
 * 
 */
package org.mc.protocol.mq.product;

/**   
 *    
 * ��Ŀ���ƣ�MessageComponent   
 * �����ƣ�IProduct   
 * ��������   
 * �����ˣ�����Ⱥ
 * ����ʱ�䣺2014-3-6  
 * @version    
 *    
 */
public interface IProduct {
	public byte[] getMsg() throws Exception;
	public void putMsg(byte[] msg) throws Exception;
	public int getQueueDeep() throws Exception;
}
